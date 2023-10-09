package kr.studyprj2.hancoding.board.controller;

import kr.studyprj2.hancoding.board.dto.CommentDTO;
import kr.studyprj2.hancoding.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")

    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO){

        System.out.println("commentDTO :"+commentDTO);
        Long saveResult = commentService.save(commentDTO);
        if(saveResult !=null){
            //댓글 작성 성공
            //댓글 작성 성공후 댓글을 다시 가져와서 리턴함
            //댓글목록 : 해당 댓글 게시글의 댓글 전체
            List<CommentDTO> commentDTOList=  commentService.findAll(commentDTO.getBoardId());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        }else {

            return new ResponseEntity<>("해당 게시글이 존제 하지 않습니다", HttpStatus.NOT_FOUND);
        }
    }
}
