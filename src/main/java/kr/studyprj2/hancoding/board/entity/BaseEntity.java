package kr.studyprj2.hancoding.board.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 속성받아서 사용하기위해 부모에 선언해주는 어노테이션
@EntityListeners(AuditingEntityListener.class) //트리거처럼 사용할 클래스를 포함. AuditingEntityListener 클래스 사용
@Getter
public class BaseEntity {
    @CreationTimestamp //생성됬을떄 시간 생성
    @Column(updatable = false) //업데이트 불가
    private LocalDateTime boardCreatedTime;

    @UpdateTimestamp
    @Column(insertable = false) //인서트 불가
    private LocalDateTime boardUpdatedTime;
}
