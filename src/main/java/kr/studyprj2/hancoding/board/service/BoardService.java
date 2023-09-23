package kr.studyprj2.hancoding.board.service;

import kr.studyprj2.hancoding.board.dto.BoardDTO;
import kr.studyprj2.hancoding.board.entity.BoardEntity;
import kr.studyprj2.hancoding.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {

        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO); // static 메소드라서 가능
        boardRepository.save(boardEntity); //레포지토리는 서비스 객채만 이용 가능 dto를 엔티티로 변환
        System.out.println(boardEntity.toString());
    }
}
