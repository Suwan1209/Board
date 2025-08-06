package com.himedia.board.service;

import com.himedia.board.dao.IBoardDao;
import com.himedia.board.dao.IMemberDao;
import com.himedia.board.dao.IReplyDao;
import com.himedia.board.dto.MemberDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    IMemberDao mdao;
    @Autowired
    IBoardDao bdao;
    @Autowired
    IReplyDao rdao;

    public MemberDto getMember(String userid) {
        MemberDto mdto = mdao.getMember(userid);
        return mdto;
    }

    public void insert(MemberDto mdto) {
        mdao.insert(mdto);
    }

    public void update(MemberDto memberdto) {
        mdao.update(memberdto);
    }
    public void deleteMember(String userid) {
        rdao.deleteReplyAll(userid);
        bdao.deleteBoardAll(userid);
        mdao.deleteMember(userid);
    }
}
