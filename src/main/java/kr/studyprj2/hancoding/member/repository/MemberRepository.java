package kr.studyprj2.hancoding.member.repository;

import kr.studyprj2.hancoding.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
