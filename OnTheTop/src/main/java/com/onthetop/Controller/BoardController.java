package com.onthetop.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onthetop.domain.Board;
import com.onthetop.domain.Reply;
import com.onthetop.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {	//게시판 Controller

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "list")
	public String boardList(Model model) throws Exception {
		model.addAttribute("list", boardService.getBoardList());
		return "board/list";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add() {
		return "board/fwriteForm";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile,
			@ModelAttribute Board board) throws Exception {

		String filename = null;
		if (!multipartFile.isEmpty()) { // 업로드한 파일이 있을 때
			ServletContext application = request.getServletContext();
			String realPath = application.getRealPath("/upload");

			filename = multipartFile.getOriginalFilename();

			int index = filename.lastIndexOf("\\");
			filename = filename.substring(index + 1);

			File file = new File(realPath, filename);
			if (file.exists()) {
				filename = System.currentTimeMillis() + "_" + filename;
				file = new File(realPath, filename);
			}

			logger.debug("업로드 경로: " + realPath);
			logger.debug("업로드 파일명: " + filename);

			IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
		} else {
			logger.debug("파일이  존재하지 않거나 파일크기가 0 입니다.");
		}

		board.setFilename(filename);

		/* board.setReg_date(new Timestamp(System.currentTimeMillis())); */
		board.setIp(request.getRemoteAddr());
		board.setReadcount(0);

		boardService.insertBoard(board);

		return "redirect:/board/list";
	}

	@RequestMapping("detail")
	public String detail(@RequestParam int num, Model model) throws Exception {

		boardService.updateReadCount(num);
		Board board = boardService.getBoardDetail(num);
		List<Reply> reply = boardService.getReply(num);

		model.addAttribute("board", board);
		model.addAttribute("reply", reply);

		return "board/Content";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(@RequestParam int num, Model model) throws Exception {
		Board board = boardService.getBoardDetail(num); // 상세보기

		model.addAttribute("board", board);

		return "board/updateForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute Board board, @RequestParam String pageNum, HttpServletResponse response)
			throws Exception {
		int check = boardService.updateBoard(board);

		if (check == 0) { // 패스워듭 불일치로 수정 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('패스워드틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

		ModelAndView mav = new ModelAndView("redirect:/board/list");
		mav.addObject("pageNum", pageNum);
		return mav;

	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete() {
		return "board/deleteForm";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam int num, @RequestParam String passwd, @RequestParam String pageNum,
			HttpServletResponse response) throws Exception {
		int check = boardService.deleteBoard(num, passwd);

		if (check == 0) { // 패스워듭 불일치로 수정 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('패스워드틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/list");
		mav.addObject("pageNum", pageNum);
		return mav;
	}

	@RequestMapping(value = "reAdd", method = RequestMethod.POST)
	public String add(HttpServletRequest request, @ModelAttribute Reply reply) throws Exception {

		reply.setIp(request.getRemoteAddr());

		boardService.insertReply(reply);

		return "redirect:/board/list";
	}

	@RequestMapping(value = "reDelete", method = RequestMethod.GET)
	public String reDelete() {
		return "board/replyDeleteForm";
	}

	@RequestMapping(value = "reDelete", method = RequestMethod.POST)
	public ModelAndView reDelete(@RequestParam int reNum, @RequestParam String passwd, @RequestParam String pageNum,
			HttpServletResponse response) throws Exception {
		int check = boardService.deleteReply(reNum, passwd);

		if (check == 0) { // 패스워듭 불일치로 수정 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('패스워드틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/list");
		mav.addObject("pageNum", pageNum);
		return mav;
	}

	/**
	 * @RequestMapping public String list(@RequestParam(value = "pageNum", required
	 *                 = false, defaultValue = "1") int pageNum, Model model) {
	 * 
	 *                 int count = boardService.getBoardCount();
	 * 
	 *                 int pageSize = 15; int startRow = (pageNum - 1) * pageSize +
	 *                 1; int endRow = pageNum * pageSize;
	 * 
	 *                 List<Board> list = boardService.getBoardList(startRow,
	 *                 pageSize);
	 * 
	 *                 int pageCount = count / pageSize + (count % pageSize == 0 ? 0
	 *                 : 1); int pageBlock = 10; int startPage = ((pageNum /
	 *                 pageBlock) - (pageNum % pageBlock == 0 ? 1 : 0)) * pageBlock
	 *                 + 1; int endPage = startPage + pageBlock - 1; if (endPage >
	 *                 pageCount) endPage = pageCount;
	 * 
	 *                 PageInfo pageInfo = new PageInfo(); pageInfo.setCount(count);
	 *                 pageInfo.setPageNum(pageNum);
	 *                 pageInfo.setPageCount(pageCount);
	 *                 pageInfo.setPageBlock(pageBlock);
	 *                 pageInfo.setStartPage(startPage);
	 *                 pageInfo.setEndPage(endPage);
	 * 
	 *                 model.addAttribute("list", list);
	 *                 model.addAttribute("pageInfo", pageInfo);
	 * 
	 *                 return "board/list"; }
	 **/

}
