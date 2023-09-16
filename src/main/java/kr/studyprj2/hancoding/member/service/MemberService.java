package kr.studyprj2.hancoding.member.service;

import kr.studyprj2.hancoding.member.dto.MemberDTO;
import kr.studyprj2.hancoding.member.entity.MemberEntity;
import kr.studyprj2.hancoding.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String save(MemberDTO memberDTO) {

        //1. dto - entity 변환

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        //2. save메소드 호출
        memberRepository.save(memberEntity);
        //repository 의 세이브 메소드 호출, (조건 : entity객채를 넘겨줘야함)

        return "login";

    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
        1. 회원이 입력한 이메일로 디비 조회
        2. 디비에서 조회한 비밀번호와 사용자가 입력한 비번이 일치하는지 확인
        * */

        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());


        if(byMemberEmail.isPresent()){
            //if문으로 메일아이디가 디비에서 검색될경우

            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                //비빌번호 일치 겁색
                // entity - > dto로 변환후 리턴
                return MemberDTO.toMemberDTO(memberEntity);
            }else{

                //비번불일치
                return null;
            }

        }else{
            //메일아이디 없음

            return null;
        }

    }
}
