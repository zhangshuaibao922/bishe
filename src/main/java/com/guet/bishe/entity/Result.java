package com.guet.bishe.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Result {
    private String code;
    private String Time;
    private String message;
    private String name;
    public Result(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.Time= String.valueOf(LocalDateTime.now().format(formatter));
    }
}
