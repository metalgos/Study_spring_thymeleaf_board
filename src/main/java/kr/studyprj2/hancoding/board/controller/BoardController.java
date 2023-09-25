package kr.studyprj2.hancoding.board.controller;

import kr.studyprj2.hancoding.board.dto.BoardDTO;
import kr.studyprj2.hancoding.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    ) throws IOException {

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
    public String findById(@PathVariable Long id, Model model,@PageableDefault(page = 1)Pageable pageable) {
        //게시글 조회수를 하나 올리고 게시글 가져와서 detail.html에 출력

        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board",boardDTO);
        model.addAttribute("page",pageable.getPageNumber());
        return "boarddetail";


    }

    @GetMapping("/update/{id}")
    public String updateFrom(@PathVariable Long id,Model model){

        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "boardupdate";

    }

    @PostMapping("/update")
    public String update(BoardDTO boardDTO,Model model){
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board",board);
        return "boarddetail";
       // return "redirect:/board"+boardDTO.getId(); 수정하면 조회수가 올라갈수가 있음


    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);

        return "redirect:/board/";
    }

    // /board/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page =1)Pageable pageable, Model model) {
        //pagabledefault = 기본 으로 보여줄 페이지 지정
        // pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();
        //페이지 갯수가 20개
        //현재 사용자가 3페이지
        //12345
        //하단에 보여지는 페이지 개수 3개 -> 1 2 3
        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "boardpaging";
    }

}