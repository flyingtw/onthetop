package com.onthetop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.onthetop.domain.Board;

@Repository
public interface BoardDao {

	public List<Board> getBoardList() throws Exception;

	public void insertBoard(Board board) throws Exception;

	public void updateReadCount(int num) throws Exception;

	public Board getBoardDetail(int num) throws Exception;

	public void updateBoard(Board board) throws Exception;

	public void deleteBoard(int num) throws Exception;

}
