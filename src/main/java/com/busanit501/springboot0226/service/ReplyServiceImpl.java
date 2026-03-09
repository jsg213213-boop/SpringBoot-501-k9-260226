package com.busanit501.springboot0226.service;

import com.busanit501.springboot0226.domain.Board;
import com.busanit501.springboot0226.domain.Reply;
import com.busanit501.springboot0226.dto.BoardDTO;
import com.busanit501.springboot0226.dto.PageRequestDTO;
import com.busanit501.springboot0226.dto.PageResponseDTO;
import com.busanit501.springboot0226.dto.ReplyDTO;
import com.busanit501.springboot0226.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }

    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> replyOptional = replyRepository.findById(rno);
        Reply reply = replyOptional.orElseThrow();
        ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
        return replyDTO;
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        // 1)디비에서 데이터를 가져오고, 가져온 내용을 화면에서, 2)변경할 내용으로 교체후, 3) 저장(수정)
        Optional<Reply> result = replyRepository.findById(replyDTO.getRno());
        Reply reply = result.orElseThrow();
        //2)
        reply.changeText(replyDTO.getReplyText());
        //3)
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {
        return null;
    }
}
