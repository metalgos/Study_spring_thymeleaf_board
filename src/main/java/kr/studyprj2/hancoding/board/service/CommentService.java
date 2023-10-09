package kr.studyprj2.hancoding.board.service;

import kr.studyprj2.hancoding.board.dto.CommentDTO;
import kr.studyprj2.hancoding.board.entity.BoardEntity;
import kr.studyprj2.hancoding.board.entity.CommentEntity;
import kr.studyprj2.hancoding.board.repository.BoardRepository;
import kr.studyprj2.hancoding.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final BoardRepository boardRepository;
    public Long save(CommentDTO commentDTO) {
        /*부모 엔티티 보드 엔티티 조회*/
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
        if(optionalBoardEntity.isPresent()) {
            //부모 엔티티 조회가 되면 저장처리
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getId();

        }else{
            return null;
        }

    }

    public List<CommentDTO> findAll(Long boardId) {
        //select * from comment_table shere board_id =? order by id desc;
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        /*엔티티 리스트를 dto리스트로 변경*/
        List<CommentDTO>commentDTOList = new ArrayList<>();
        for(CommentEntity commentEntity : commentEntityList){
            CommentDTO commentDTO =  CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;


    }
}
