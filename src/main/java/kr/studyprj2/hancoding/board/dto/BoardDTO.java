package kr.studyprj2.hancoding.board.dto;

import kr.studyprj2.hancoding.board.entity.BoardEntity;
import kr.studyprj2.hancoding.board.entity.BoardFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<MultipartFile> boardFile; //스프링에서 지원하는 파일을 담는 변수
    private List<String> originalFileName; //원본파일이름
    private List<String> storedFileName;  //서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부여부(첨부1, 미첨부0), int 대신 bulean형은 나중에 dto에서 처리가 어려움.

    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        if(boardEntity.getFileAttached()==0){
            boardDTO.setFileAttached(boardEntity.getFileAttached());
        }else{
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();


            boardDTO.setFileAttached(boardEntity.getFileAttached());
            //파일 이름을 가져가야함
            //originalFileName, storedFilename : board_file_table(BaoardFileEntity)
            //db를 연관관계를 맺어놨기 떄문에 jpa에서 조인해옴

            for(BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) { //파일 여러개 목록 리스트에 담기

                originalFileNameList.add(boardFileEntity.getOriginalFileName());
                storedFileNameList.add(boardFileEntity.getStoredFileName());

            }
            boardDTO.setOriginalFileName(originalFileNameList); //리스트 담기
            boardDTO.setStoredFileName(storedFileNameList);

        }

        return boardDTO;
    }

}
