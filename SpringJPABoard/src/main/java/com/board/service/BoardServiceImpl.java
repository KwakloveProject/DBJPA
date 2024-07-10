package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.Board;
import com.board.repository.BoardRepository;
@Service 
public class BoardServiceImpl implements BoardService {

	@Autowired 
	private BoardRepository repository;
	
	@Override
	@Transactional 
	public void register(Board board) throws Exception {
		 repository.save(board);	
	}
	//내가 선택한것만 보여줌
	@Override
	@Transactional(readOnly = true) 
	public Board read(Integer boardNo) throws Exception {
		 return repository.findById(boardNo)
                 .orElseThrow(() -> new Exception("게시글을 찾을 수 없습니다. 게시글 번호: " + boardNo));
	 }

	@Override
	@Transactional 
	public void modify(Board board) throws Exception {
		//read한 상태에서 정보를 수정하는거니깐
		 Board boardEntity = repository.findById(board.getBoardNo())
                 .orElseThrow(() -> new Exception("게시글을 찾을 수 없습니다."));
		 //수정부분이니깐 쓴거임
		boardEntity.setTitle(board.getTitle()); 
		boardEntity.setContent(board.getContent());
	}
	
	@Override
	@Transactional 
	//그냥 삭제만 하면 되서 read안함
	public void remove(Integer boardNo) throws Exception {
		 repository.deleteById(boardNo);
	}
	//전체 리스트보여줌
	@Override
	@Transactional(readOnly = true) 
	public List<Board> list() throws Exception {
		return  repository.findAll(Sort.by(Direction.DESC, "boardNo"));
	}

}
