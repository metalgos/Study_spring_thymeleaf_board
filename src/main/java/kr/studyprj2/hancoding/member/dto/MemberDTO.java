package kr.studyprj2.hancoding.member.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
}
