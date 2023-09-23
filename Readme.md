# 개발환경
1. IDE: IntelliJ IDEA Community
2. Spring Boot 2.6.13 
3. JDK 11
4. mysql
5. Spring Data JPA
6. Thymeleaf

# 회원가입 주요기능 (구현완료)
회원가입
로그인
회원목록
회원조회
회원정보 수정
회원삭제
로그아웃

# 게시판 주요기능 (구현중)
1. 글쓰기(/board/save)
2. 글목록(/board/)
3. 글조회(/board/{id})
4. 글수정(/board/update/{id})
    - 상세화면에서 수정 버튼 클릭 
    - 서버에서 해당 게시글의 정보를 가지고 수정 화면 출력 
    - 제목, 내용 수정 입력 받아서 서버로 요청 
    - 수정 처리 
5. 글삭제(/board/delete/{id})
6. 페이징처리(/board/paging)
    - /board/paging?page=2
    - /board/paging/2
    - 게시글 14
      - 한페이지에 5개씩 => 3개
      - 한페이지에 3개씩 => 5개
7. 파일(이미지)첨부하기 
   - 단일 파일 첨부
   - 다중 파일 첨부
   - 파일 첨부와 관련하여 추가될 부분들  
     - save.html  
     - BoardDTO  
     - BoardService.save()  
     - BoardEntity
     - BoardFileEntity, BoardFileRepository 추가
     - detail.html

    - board_table(부모) - board_file_table(자식)
```


한코딩

접속주소 : http://localhost:8092/
