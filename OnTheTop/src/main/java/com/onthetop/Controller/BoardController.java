package com.onthetop.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onthetop.domain.Board;
import com.onthetop.domain.PageInfo;
import com.onthetop.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping
	public String list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			Model model) {

		int count = boardService.getBoardCount();

		int pageSize = 15;
		int startRow = (pageNum - 1) * pageSize + 1;
		int endRow = pageNum * pageSize;

		List<Board> list = boardService.getBoardList(startRow, pageSize);

		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((pageNum / pageBlock) - (pageNum % pageBlock == 0 ? 1 : 0)) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		PageInfo pageInfo = new PageInfo();
		pageInfo.setCount(count);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageCount(pageCount);
		pageInfo.setPageBlock(pageBlock);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);

		model.addAttribute("list", list);
		model.addAttribute("pageInfo", pageInfo);

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

			System.out.println("업로드 경로: " + realPath);
			System.out.println("업로드 파일명: " + filename);

			IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
		} else {
			System.out.println("파일이 존재하지 않거나 파일크기가 0 입니다.");
		}

		board.setFilename(filename);

		board.setReg_date(new Timestamp(System.currentTimeMillis()));
		board.setIp(request.getRemoteAddr());

		boardService.add(board);

		return "redirect:/board/list";
	}

	@RequestMapping("detail")
	public String detail(@RequestParam int num, Model model) {

		boardService.updateReadCount(num);
		Board board = boardService.getBoard(num);

		model.addAttribute("board", board);

		return "board/content";
	}

}
