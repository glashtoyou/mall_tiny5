package com.example.webtest.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryVo {

    private String uname;
    private String gname;
    private String uemail;
    private String uphone;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",fallbackPatterns ={"yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd'T'HH:mm" })
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Timestamp htime;
}
