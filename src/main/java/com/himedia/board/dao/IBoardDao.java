package com.himedia.board.dao;

import com.himedia.board.dto.BoardDto;
import com.himedia.board.dto.Paging;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface IBoardDao {
    int getAllCount();
    ArrayList<BoardDto> selectBoard(Paging paging);
    BoardDto getBoard(int num);
    void AddReadcount(int num);
    void insert(BoardDto bdto);
    void update(BoardDto bdto);
    void delete(int num);
    void updatePwd(int num, String newPwd);
    void deleteBoardAll(String userid);
}
