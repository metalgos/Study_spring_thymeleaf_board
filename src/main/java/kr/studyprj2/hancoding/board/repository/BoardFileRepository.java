package kr.studyprj2.hancoding.board.repository;

import kr.studyprj2.hancoding.board.entity.BoardEntity;
import kr.studyprj2.hancoding.board.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
}
