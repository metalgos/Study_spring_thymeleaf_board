package kr.studyprj2.hancoding.member.service;

import kr.studyprj2.hancoding.member.dto.MemberDTO;
import kr.studyprj2.hancoding.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {

    }
}
