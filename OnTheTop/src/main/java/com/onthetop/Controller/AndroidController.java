package com.onthetop.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onthetop.domain.Board;
import com.onthetop.domain.RequestResult;
import com.onthetop.service.BoardService;

@Controller
@RequestMapping("/android/") // 안드로이드 통신 전용 Controller, return 값은 객체로
public class AndroidController {

	private static final Logger logger = LoggerFactory.getLogger(AndroidController.class);

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "list")
	public @ResponseBody List<Board> getBoardList() throws Exception {
		logger.info("====getBoardList====");

		List<Board> board = null;
		board = boardService.getBoardList();
		
		logger.info("====getBoardList Success!!=====");

		return board;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody RequestResult add(@RequestBody Board board) throws Exception {
		RequestResult requestResult = new RequestResult();

		logger.info("====insertBoard====");

		boardService.insertBoard(board);
		logger.info("====insertBoard Success!!====");

		requestResult.setResult(true);
		return requestResult;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody RequestResult update(@RequestBody Board board) throws Exception {
		RequestResult requestResult = new RequestResult();

		logger.info("====updateBoard====");

		boardService.updateBoard(board);
		logger.info("====updateBoard Success!====");

		requestResult.setResult(true);
		return requestResult;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody RequestResult delete(@RequestBody int num, @RequestBody String passwd) throws Exception {
		RequestResult requestResult = new RequestResult();

		logger.info("====deleteBoard====");

		boardService.deleteBoard(num, passwd);
		logger.info("====deleteBoard Success!====");

		requestResult.setResult(true);
		return requestResult;
	}
}
