package kr.studyprj2.hancoding.member.controller;

import kr.studyprj2.hancoding.member.dto.MemberDTO;
import kr.studyprj2.hancoding.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor //final 혹은 @NotNull이 붙은 필드를 @Aurowired 생성자 주입 어노테이션 없이 사용 가능하게 하는 롬복 어노테이션
public class MemberController {

    //생성자 주입, 롬복 어노테이션 이용해 주입
    private final MemberService memberService;

    //회원가입 페이지
    @GetMapping("/save")
    public String getMemberSave() {

        return "savemember";
    }


    @PostMapping("/save")
    //@ModelAttribute 생략가능
    public String postMemberSave(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("in postMemberSave");
        System.out.println("memberDTO = " + memberDTO);

        //컨트롤러에서 서비스 호출하며 dto객체 넘김, 서비스에서는 레포지토리 호출하면서 dto를 entity로 변환후 entity 넘김, 레포지토리는 jpa 에서 상속받은 sava 메소드 호출
        memberService.save(memberDTO);

        return "login";
    }
}
