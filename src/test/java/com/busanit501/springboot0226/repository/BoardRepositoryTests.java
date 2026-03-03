package com.busanit501.springboot0226.repository;

import com.busanit501.springboot0226.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        // 더미 데이터 100개를 임의로 작성, 하드코딩. , 반목문을 이용해서, 추가 할 예정.
        IntStream.rangeClosed(1,100).forEach(i -> {
            // 더미 엔티티 객체 생성, 빌드 패턴이용.
            Board board = Board.builder()
                    .title("더미 제목...title" + i)
                    .content("더미 내용...content" + i)
                    .writer("더미 사용자...user" + (i % 10))
                    .build();
            // boardRepository 의 기능들 중에서, save 라는 메서드를 이용해서, 실제 디비에 저장.
            Board result = boardRepository.save(board);
            log.info("결과 확인: " + result.getBno());
        });
    }

    @Test
    public void testSelect() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info("board 확인 : " +board );
    }

    @Test
    public void testUpdate() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        // 수정 메서드를 호출해서, 변경 후, 저장 및 수정.
        board.change("수정 테스트 제목", "수정 테스트 내용");
        boardRepository.save(board);
    }
}
