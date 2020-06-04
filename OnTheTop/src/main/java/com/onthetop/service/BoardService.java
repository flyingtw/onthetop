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

	public void updateReadCount(int num) throws Exception {
		boardDao.updateReadCount(num);
	}

	public Board getBoardDetail(int num) throws Exception {
		return boardDao.getBoardDetail(num);
	}

	public int updateBoard(Board board) throws Exception {
		Board boardDB = boardDao.getBoardDetail(board.getNum());

		int check = 0;
		if (board.getPasswd().equals(boardDB.getPasswd())) {
			boardDao.updateBoard(board);
			check = 1;
		} else {
			check = 0;
		}
		return check;
	}

}
