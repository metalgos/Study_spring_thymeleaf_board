package kr.studyprj2.hancoding.board.dto;

import kr.studyprj2.hancoding.board.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private  String commentWriter;
    private String  commentContents;
    private Long boardId;
    private LocalDateTime  commentCreatedTime;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long boardId) {
        CommentDTO commentDTO = new CommentDTO()    ;
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        //commentDTO.setBoardId(commentEntity.getBoardEntity().getId()); //이 방식은 서비스 메서드에 @Transactional 붙여야함
        commentDTO.setBoardId(boardId);

        return  commentDTO;

    }
}
