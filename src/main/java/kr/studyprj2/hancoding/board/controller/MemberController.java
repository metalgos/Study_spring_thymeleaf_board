package kr.studyprj2.hancoding.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController {
    //회원가입 페이지
    @GetMapping("/save")
    public String getMemberSave(){

        return "savemember";
    }


    @PostMapping("/save")
    public String postMemberSave(@RequestParam("memberEmail") String memberEmail){

        System.out.println("memberEmail = " + memberEmail);
        return "savemember";
    }
}
