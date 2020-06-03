package com.onthetop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.onthetop.domain.Board;

@Repository
public interface BoardDao {

	public List<Board> getBoardList() throws Exception;

	public void insertBoard(Board board) throws Exception;

}
