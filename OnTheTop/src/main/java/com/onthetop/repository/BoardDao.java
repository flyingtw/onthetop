package com.onthetop.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.onthetop.domain.Board;

@Repository
public class BoardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert simpleJdbcInsert;

	public Integer getMaxNum() {
		try {
			Integer maxNum = jdbcTemplate.queryForObject("select max(num) from board", Integer.class);
			return maxNum;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void add(Board board) {
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(board));
	}

	public List<Board> getBoardList(int startRow, int pageSize) {
		String sql = "select * from board order by re_ref desc, re_seq asc offset ? limit ?";

		List<Board> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Board>(Board.class), startRow - 1,
				pageSize);
		return list;
	}

	public int count() {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM board", Integer.class);
		return count;
	}

}
