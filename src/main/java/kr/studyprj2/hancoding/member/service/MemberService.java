package kr.studyprj2.hancoding.member.service;

import kr.studyprj2.hancoding.member.dto.MemberDTO;
import kr.studyprj2.hancoding.member.entity.MemberEntity;
import kr.studyprj2.hancoding.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


        if (byMemberEmail.isPresent()) {
            //if문으로 메일아이디가 디비에서 검색될경우

            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                //비빌번호 일치 겁색
                // entity - > dto로 변환후 리턴
                return MemberDTO.toMemberDTO(memberEntity);
            } else {

                //비번불일치
                return null;
            }

        } else {
            //메일아이디 없음

            return null;
        }

    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();

        for (MemberEntity memberEntity : memberEntityList) {
            //foreach문법으로 일일히 하나씩 변환
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
            // entity 객채를dto로 변환하여 리스트에 추가
            /*
            //두줄로 쓰던 한줄로 쓰던 같음
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            memberDTOList.add(memberDTO);
            */
        }

        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);

        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }else {
            return null;
        }

    }
}
