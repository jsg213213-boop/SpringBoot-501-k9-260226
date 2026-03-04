package com.busanit501.springboot0226.service;

import com.busanit501.springboot0226.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("오늘 점심 뭐 먹죠?")
                .content("오늘 점심 뭐 먹죠?")
                .writer("이상용")
                .build();

        Long bno = boardService.register(boardDTO);
       log.info("등록된 bno 확인 : " + bno);
    }

}
