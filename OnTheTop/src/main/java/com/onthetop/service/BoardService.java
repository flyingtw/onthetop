package com.onthetop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onthetop.repository.BoardDao;

@Service
@Transactional
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;

}
