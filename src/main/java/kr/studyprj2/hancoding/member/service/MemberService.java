package kr.studyprj2.hancoding.member.service;

import kr.studyprj2.hancoding.member.dto.MemberDTO;
import kr.studyprj2.hancoding.member.entity.MemberEntity;
import kr.studyprj2.hancoding.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {

        //1. dto - entity 변환

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        //2. save메소드 호출
        memberRepository.save(memberEntity);

        //repository 의 세이브 메소드 호출, (조건 : entity객채를 넘겨줘야함)

    }
}
