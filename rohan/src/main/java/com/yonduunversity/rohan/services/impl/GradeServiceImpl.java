package com.yonduunversity.rohan.services.impl;

import java.util.Optional;

import org.hibernate.cache.spi.entry.StructuredMapCacheEntry;
import org.springframework.stereotype.Service;

import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.models.student.Student;
import com.yonduunversity.rohan.repository.ClassBatchRepo;
import com.yonduunversity.rohan.repository.ExerciseRepo;
import com.yonduunversity.rohan.repository.GradeRepo;
import com.yonduunversity.rohan.repository.ProjectRepo;
import com.yonduunversity.rohan.repository.QuizRepo;
import com.yonduunversity.rohan.repository.StudentRepo;
import com.yonduunversity.rohan.services.GradeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    QuizRepo quizRepo;
    ExerciseRepo exerciseRepo;
    ProjectRepo projectRepo;
    StudentRepo studentRepo;
    ClassBatchRepo classBatchRepo;
    GradeRepo gradeRepo;
    // List<Grade> retrieveStudentGrades(String email) {

    // }

    // List<Grade> retrieveClassGrades(ClassBatchId id) {

    // }

    public Grade giveQuizScore(int quiz_id, String email, int score) {
        // searchstudent;
        Optional<Quiz> optionalQuiz = quizRepo.findById(quiz_id);
        Quiz quiz = optionalQuiz.get();
        Student student = studentRepo.findByEmail(email);
        Grade grade = new Grade();
        grade.setQuiz(quiz);
        grade.setStudent(student);
        grade.setScore(score);
        return gradeRepo.save(grade);
    }

    public Grade giveExerciseScore(int exercise_id, String email, int score) {
        return new Grade();
    }

    public Grade giveProjectScore(int project_id, String email, int score) {
        return new Grade();
    }

}
