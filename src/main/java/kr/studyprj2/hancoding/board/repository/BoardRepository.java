package kr.studyprj2.hancoding.board.repository;

import kr.studyprj2.hancoding.board.dto.BoardDTO;
import kr.studyprj2.hancoding.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    //update board_table set board_hits=boardhits+1 where id =?
    @Modifying //delete 같은 쿼리를 사용할떄는 필수로 붙이는 어노테이션
    @Query(value = "update BoardEntity b set b.boardHits=boardHits+1 where b.id =:id")
    //쿼리 옵션으로 네이티브 설정해서 네이티브 쿼리 설정 가능
    //쿼리 뒤의 b.id =:id 는 @param의 id와 일치시키면 매칭됨
    void updateHits(@Param("id") Long id);

}
