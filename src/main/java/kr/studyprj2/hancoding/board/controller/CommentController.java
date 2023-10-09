package kr.studyprj2.hancoding.board.controller;

import kr.studyprj2.hancoding.board.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {


    @PostMapping("/save")
    @ResponseBody
    public String save(@ModelAttribute CommentDTO commentDTO){

        System.out.println("commentDTO :"+commentDTO);

        return "요청 성공";

    }
}
