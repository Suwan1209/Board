package com.himedia.board.dao;

import com.himedia.board.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMemberDao {
    MemberDto getMember(String userid);
    void insert(MemberDto mdto);
    void update(MemberDto mdto);
    void deleteMember(String userid);
}
