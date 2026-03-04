package com.busanit501.springboot0226.service;

import com.busanit501.springboot0226.dto.BoardDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
}
