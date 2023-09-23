package kr.studyprj2.hancoding.board.controller;

import kr.studyprj2.hancoding.board.dto.BoardDTO;
import kr.studyprj2.hancoding.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList =  boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        return "boardlist";
    }

    @GetMapping("{id}")
    public String findAll(@PathVariable Long id, Model model) {
        //게시글 조회수를 하나 올리고 게시글 가져와서 detail.html에 출력

        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board",boardDTO);
        return "boarddetail";


    }



}