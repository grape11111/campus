package com.gdut.imis.campus.mapper;


import com.gdut.imis.campus.model.Question;

public interface QuestionExtMapper {
    int incView(Question record);
    int incComment(Question record);
}