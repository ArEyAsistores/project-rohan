package com.yonduunversity.rohan.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GradeSheet {
    String email;
    String code;
    long batch;
    ArrayList<QuizGrade> quizGrades = new ArrayList<QuizGrade>();
    ArrayList<ExerciseGrade> exerciseGrades = new ArrayList<ExerciseGrade>();
    ArrayList<ProjectGrade> projectGrades = new ArrayList<ProjectGrade>();

    public GradeSheet(List<Grade> grades, String email, String code, long batch) {
        this.email = email;
        this.code = code;
        this.batch = batch;

        for (int i = 0; i < grades.size(); i++) {
            Grade grade = grades.get(i);
            if (grade.getQuiz() != null) {
                quizGrades.add(new QuizGrade(grade.getQuiz().getId(), grade.getScore(), grade.getQuiz().getMinScore(),
                        grade.getQuiz().getMaxScore()));
            }
            if (grade.getExercise() != null) {
                exerciseGrades.add(new ExerciseGrade(grade.getExercise().getId(), grade.getScore(),
                        grade.getExercise().getMinScore(), grade.getExercise().getMaxScore()));
            }
            if (grade.getProject() != null) {
                projectGrades.add(new ProjectGrade(grade.getProject().getId(), grade.getScore(), 0, 100));
            }
        }
    }
}
