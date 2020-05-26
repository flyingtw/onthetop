package com.onthetop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onthetop.domain.Board;
import com.onthetop.repository.BoardDao;

@Service
@Transactional
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public void add(Board board) {
		Integer maxNum = boardDao.getMaxNum();
		if (maxNum == null) {
			board.setNum(1);
		} else {
			board.setNum(maxNum + 1);
		}

		board.setRe_ref(board.getNum());
		board.setRe_lev(0);
		board.setRe_seq(0);
		board.setReadcount(0);

		boardDao.add(board);
	}

	public List<Board> getBoardList(int startRow, int pageSize) {
		return boardDao.getBoardList(startRow, pageSize);
	}

	public int getBoardCount() {
		return boardDao.count();
	}

	public void updateReadCount(int num) {
		boardDao.updateReadCount(num);
	}

	public Board getBoard(int num) {
		return boardDao.getBoard(num);
	}

}
