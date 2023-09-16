package kr.studyprj2.hancoding.board.controller;

import kr.studyprj2.hancoding.board.dto.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/save")
    public String saveForm(){

        return "save";
    }


    @PostMapping("/save")
    public String save(
            //@RequestParam("boardWriter") String boadWriter 하나씩 받을떄는 리퀘스트파람, 여러개는 dto로 받는게 편함
BoardDTO boardDTO
    ){

        return "";
    }
}