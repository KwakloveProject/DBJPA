package com.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.board.domain.Board;
// DAO 생략 해버린거임 (ㅈ사기임)
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
