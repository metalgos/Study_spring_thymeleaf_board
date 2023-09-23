package kr.studyprj2.hancoding.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 받는 생성자
@ToString
public class BoardDTO {

    private Long id;
    private String boardWriter,boardPass,boardTitle,boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime,boardUpdatedTime;

}
