package kr.studyprj2.hancoding.board.service;

import kr.studyprj2.hancoding.board.dto.BoardDTO;
import kr.studyprj2.hancoding.board.entity.BaseEntity;
import kr.studyprj2.hancoding.board.entity.BoardEntity;
import kr.studyprj2.hancoding.board.entity.BoardFileEntity;
import kr.studyprj2.hancoding.board.repository.BoardFileRepository;
import kr.studyprj2.hancoding.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        //파일 첨부 여부에 따라 로직 분리
        if(boardDTO.getBoardFile().isEmpty()){
            //첨부파일이 없을경우
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO); // static 메소드라서 가능
            boardRepository.save(boardEntity); //레포지토리는 서비스 객채만 이용 가능 dto를 엔티티로 변환
        }else{
            //첨부 파일이 있을경우
            /*
            1.DTO에 담긴 파일을 꺼냄
            2. 파일의 이름 가져옴
            3. 서버 저장용 이름을 만듦
               내사진.jpg -> 23414134324_내사진.jpg
            4. 저장 경로 설정
            5. 해당 경로에 파일 저장
            6. board_table에 해당 데이터 save 처리
            7. board_file_table에 해당 데이터 save 처리
            */
            /* 파일 여러개를 저장할경우 부모파일이 먼저 들어가야함. */
            BoardEntity boardEntity= BoardEntity.toSaveFileEntity(boardDTO); //id값이 현재 존재하지 않음
            Long savedId = boardRepository.save(boardEntity).getId(); //부모 게시글의 pk id값을 알아야함
            BoardEntity board = boardRepository.findById(savedId).get();//부모엔티티를 db로 가져오기, id값을 가져오기위해 사용
            /*                                              */

            for(MultipartFile boardFile : boardDTO.getBoardFile()) {

               // MultipartFile boardFile = boardDTO.getBoardFile(); //1  여러개 가져올떄는 이문장 없어도 됨
                String originalFilename = boardFile.getOriginalFilename();//2
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename; //3 날짜시간을 붙여서 파일이름 생성
                String savePath = "C:/temp/" + storedFileName; //4.c드라이브에 저장할 폴더 와 파일이름 설정
                boardFile.transferTo(new File(savePath)); // 5.파일을 자바io파일로 넘김'

                BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);//엔티티 변환
                boardFileRepository.save(boardFileEntity);
            }
        }
    }

    @Transactional
    public List<BoardDTO> findAll() {
        System.out.println("in findall");
        List<BoardEntity> boardEntityList = boardRepository.findAll(); //db에서 넘어온 객채를 변경해야함
        System.out.println("in findall2");
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

        return boardDTOList;

    }

    @Transactional //jpa가 지원하지 않는 직접만든 메소드는 이 어노테이션을 붙여야함
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity); //레포지토리 save는 인서트 업데이트 둘다가능. 전달받은 변수에 id값이 없으면 인서트 있으면 업데이트됨
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작
        int pageLimit = 3; // 한페이지에 보여줄 글 개수        
        // 한페이지당 3개씩 글로 보여주고 정렬 기준은 id기준으로 내림차순 정렬

        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        //보드 엔티티를 dto로 변환해야하는데 기존의 리스트 객채 변환 메소드는 페이지의 객채에서 사용하는 메소드는 사용불가
        //페이지 객체에서 지원해주는 map - > : 맵의 객채 엔티티 board를 하나씩 옮겨서 BoardDTO로 담아준다. foreach처럼.
        //목록에서 보여주려는 데이터 : id, writer,title,hjits,createTime
        // 목록: id, writer, title, hits, createdTime
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDTOS;

    }
}
