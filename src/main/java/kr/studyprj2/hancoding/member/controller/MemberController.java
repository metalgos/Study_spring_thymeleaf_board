package kr.studyprj2.hancoding.member.controller;

import kr.studyprj2.hancoding.member.dto.MemberDTO;
import kr.studyprj2.hancoding.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor //final 혹은 @NotNull이 붙은 필드를 @Aurowired 생성자 주입 어노테이션 없이 사용 가능하게 하는 롬복 어노테이션
public class MemberController {

    //생성자 주입, 롬복 어노테이션 이용해 주입
    private final MemberService memberService;

    //회원가입 페이지
    @GetMapping("/member/save")
    public String getMemberSave() {

        return "savemember";
    }


    @PostMapping("/member/save")
    //@ModelAttribute 생략가능
    public String postMemberSave(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("in postMemberSave");
        System.out.println("memberDTO = " + memberDTO);

        //컨트롤러에서 서비스 호출하며 dto객체 넘김, 서비스에서는 레포지토리 호출하면서 dto를 entity로 변환후 entity 넘김, 레포지토리는 jpa 에서 상속받은 sava 메소드 호출
        memberService.save(memberDTO);

        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }


    @PostMapping("/member/login")
    //@ModelAttribute 생략가능
    public String postMemberLogin(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        System.out.println("in postMemberLogin");
        System.out.println("memberDTO = " + memberDTO);


        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null){
            //login success
            session.setAttribute("loginEmail",loginResult.getMemberEmail());
            return "main";
        }else {
            return "login";
        }

    }

    @GetMapping("/member")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();

        //레포지토리에서 검색결과 모델에 담아서 반환하기
        model.addAttribute("memberList",memberDTOList);

        return "list";
    }
    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);

        //레포지토리에서 검색결과 모델에 담아서 반환하기
        model.addAttribute("member",memberDTO);

        return "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        System.out.println("in memberupdate");
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO= memberService.updateForm(myEmail);
        model.addAttribute("updateMember",memberDTO);

        return "update";
    }

    @PostMapping("/member/update")
    public String memberupdate(MemberDTO memberDTO, Model model){

        memberService.memberUpdate(memberDTO);

        return "redirect:/member/"+ memberDTO.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id){

        memberService.deleteById(id);

        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate(); //세션값 모두 삭제

        return "index";
    }

    @PostMapping("/member/email-check")
    @ResponseBody
    //매개변수 값에 @requestParam("memberEmail") 으로 명시적으로 설정 안해줘도 변수명 같게 하면 됨
    public String emailCheck(String memberEmail){
        System.out.println("memberEmail : "+memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }


}
