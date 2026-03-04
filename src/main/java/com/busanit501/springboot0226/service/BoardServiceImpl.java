package com.busanit501.springboot0226.service;

import com.busanit501.springboot0226.domain.Board;
import com.busanit501.springboot0226.dto.BoardDTO;
import com.busanit501.springboot0226.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional // 작업 후 저장 또는 되돌리기 하기위해서, 필요함.
public class BoardServiceImpl implements BoardService{
// 화면으로부터 , 글작성 재료를 DTO 에 받아서, 엔티티 객체 타입으로 변환해서, 전달하는 용도.

    private final ModelMapper modelMapper; // DTO -> Entity 객체 변환
    private final BoardRepository boardRepository; // 실제 DB에 일을 시키는 기능.

    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    @Override
    public BoardDTO readOne(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        // 1)디비에서 데이터를 가져오고, 가져온 내용을 화면에서, 2)변경할 내용으로 교체후, 3) 저장(수정)
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        Board board = result.orElseThrow();
        //2)
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        //3)
        boardRepository.save(board);
    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }
}
