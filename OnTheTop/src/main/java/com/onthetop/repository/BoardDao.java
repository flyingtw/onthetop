package com.onthetop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.onthetop.domain.Board;

@Repository
public interface BoardDao {

	List<Board> getBoardList();

	Board insertBoard();
	
}
