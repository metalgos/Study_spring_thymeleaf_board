package kr.studyprj2.hancoding.board.repository;

import kr.studyprj2.hancoding.board.dto.BoardDTO;
import kr.studyprj2.hancoding.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
}
