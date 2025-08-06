package com.himedia.board.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReplyDto {
    private int replynum;
    private int boardnum;
    private String userid;
    private Timestamp writedate;
    private String content;
}
