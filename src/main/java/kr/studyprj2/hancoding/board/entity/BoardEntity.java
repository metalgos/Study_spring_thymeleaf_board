package kr.studyprj2.hancoding.board.entity;

import kr.studyprj2.hancoding.board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_table")
@ToString
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto인크리먼트
    private Long id;

    @Column(length = 20, nullable = false)//크기 20, not null
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;
    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        //스프르링 버전 3.0 부터는 BeanUtils.copyProperties(boardDTO, boardEntity); 로 간단하게 객채 복사 가능
        return boardEntity;
    }


}
