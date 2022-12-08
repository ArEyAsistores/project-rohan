package com.yonduunversity.rohan.services;

import java.util.List;

import com.yonduunversity.rohan.models.ClassBatchId;
import com.yonduunversity.rohan.models.Grade;

public interface GradeService {
    List<Grade> retrieveStudenGrades(String email);

    List<Grade> retrieveClassGrades(ClassBatchId id);

    Grade giveGrade(Grade grade, int score);
}
