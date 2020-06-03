package com.onthetop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onthetop.domain.Board;
import com.onthetop.repository.BoardDao;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<Board> getBoardList() throws Exception {
		return boardDao.getBoardList();
	}

	public void insertBoard(Board board) throws Exception {
		boardDao.insertBoard(board);
	}

}
