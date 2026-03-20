package com.busanit501.springboot0226.service;

import com.busanit501.springboot0226.domain.Board;
import com.busanit501.springboot0226.dto.*;

public interface BoardService {

    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    // 전체 목록 +  댓글 갯수 포함
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

    // 전체 목록 +  댓글 갯수 포함 + 첨부이미지도 포함
    PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

    // dto -> entity 로 자주 변경하니, 기본 메스드로 추가.
    // 화면 (DTO)-> 디비(엔티티),
    // 기능: 게시글 작성,
    default Board dtoToEntity(BoardDTO dto) {
        // 박스에서 꺼내서, 디비 타입(Entity) 변경.
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        // 첨부 이미지들이 존재한다면, 꺼내서 담기.
        if(dto.getFileNames() != null) {
            dto.getFileNames().forEach(fileName -> {
                // 파일이름 형식 = {UUID}_{파일명}
                String[] arr = fileName.split("_");
                board.addImage(arr[0], arr[1]);
            });

        }
        return board;
    }
}
