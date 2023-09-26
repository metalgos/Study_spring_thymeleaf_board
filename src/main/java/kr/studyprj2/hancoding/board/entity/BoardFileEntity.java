package kr.studyprj2.hancoding.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFileEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    //게시글 하나에 파일 여러개 올수 있음 파일과 게시글은 n : 1 의 관계
    @ManyToOne(fetch = FetchType.LAZY) //부모 엔티티 객채를 조회할떄 자식을 가져오는 방법, lazy는 필요할때만. eager는 항상가져옴. eager는 잘 안씀
    @JoinColumn(name = "board_id")//db에 만들어진 컬럼이름
    private BoardEntity boardEntity; //부모 엔티티 값으로 받아야함.  Long id가 아님

    public static BoardFileEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName, String storedFileName){
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(boardEntity); // pk값이 아니라 부모 엔티티 객채를 넘겨줘야함
        return boardFileEntity;

    }

}
