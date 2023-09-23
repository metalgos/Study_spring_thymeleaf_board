package kr.studyprj2.hancoding.board.controller;

import kr.studyprj2.hancoding.board.dto.BoardDTO;
import kr.studyprj2.hancoding.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // final 로 불러오는 것들 autowired
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/save")
    public String saveForm() {

        return "saveboard";
    }


    @PostMapping("/save")
    public String save(
            //@RequestParam("boardWriter") String boadWriter 하나씩 받을떄는 리퀘스트파람, 여러개는 dto로 받는게 편함
            @ModelAttribute  BoardDTO boardDTO
    ) {

        boardService.save(boardDTO);
        return "index";
    }
}