package kr.studyprj2.hancoding.member.repository;

import kr.studyprj2.hancoding.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    //옵셔널 : 널방지를 위한 변수순언

    Optional<MemberEntity> findByMemberEmail(String memberEmail); //이메일로 디비 셀렉트. save와 다르게 셀렉트는 메서드명으로 직접 생성
}
