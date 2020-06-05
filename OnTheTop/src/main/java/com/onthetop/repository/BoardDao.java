package com.onthetop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.onthetop.domain.Board;
import com.onthetop.domain.Reply;

@Repository
public interface BoardDao {

	public List<Board> getBoardList() throws Exception;

	public void insertBoard(Board board) throws Exception;

	public void updateReadCount(int num) throws Exception;

	public Board getBoardDetail(int num) throws Exception;

	public void updateBoard(Board board) throws Exception;

	public void deleteBoard(int num) throws Exception;

	public List<Reply> getReply(int num) throws Exception;

	public void insertReply(Reply reply) throws Exception;

	public void deleteReply(int reNum) throws Exception;

	public Reply getReplyOne(int reNum) throws Exception;

}
