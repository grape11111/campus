package com.gdut.imis.campus.dataobject;

import com.gdut.imis.campus.model.Student;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Student user;
    private List<String> tags;
}
