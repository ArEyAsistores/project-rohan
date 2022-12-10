package com.yonduunversity.rohan.services.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.cache.spi.entry.StructuredMapCacheEntry;
import org.springframework.stereotype.Service;

import com.yonduunversity.rohan.exception.GradeOutOfBoundException;
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

    public Grade giveQuizScore(int quiz_id, String email, int score) {
        Quiz quiz = QuizServiceImpl.unwrapQuiz(quizRepo.findById(quiz_id), quiz_id);
        checkScore(score, quiz.getMinScore(), quiz.getMaxScore());
        Student student = studentRepo.findByEmail(email);
        ClassBatch classBatch = quiz.getClassBatch();

        Grade grade = new Grade();
        grade.setQuiz(quiz);
        grade.setStudent(student);
        grade.setScore(score);
        grade.setClassBatch(classBatch);

        return gradeRepo.save(grade);
    }

    public Grade giveExerciseScore(int exercise_id, String email, int score) {
        Exercise exercise = ExerciseServiceImpl.unwrapExercise(exerciseRepo.findById(exercise_id), exercise_id);
        checkScore(score, exercise.getMinScore(), exercise.getMaxScore());
        Student student = studentRepo.findByEmail(email);
        ClassBatch classBatch = exercise.getClassBatch();

        Grade grade = new Grade();
        grade.setExercise(exercise);
        grade.setStudent(student);
        grade.setScore(score);
        grade.setClassBatch(classBatch);

        return gradeRepo.save(grade);
    }

    public Grade giveProjectScore(long project_id, String email, int score) {
        Project project = ProjectServiceImpl.unwrapProject(projectRepo.findById(project_id), project_id);
        checkScore(score, 0, 100);
        Student student = studentRepo.findByEmail(email);
        ClassBatch classBatch = project.getClassBatch();
        Grade grade = new Grade();
        grade.setProject(project);
        grade.setStudent(student);
        grade.setScore(score);
        grade.setClassBatch(classBatch);
        return gradeRepo.save(grade);
    }

    public List<Grade> retrieveStudentGrades(String email) {
        return (List<Grade>) gradeRepo.findByStudentEmail(email);
    }

    public List<Grade> retrieveClassGrades(long id) {
        return (List<Grade>) gradeRepo.findByClassBatchId(id);
    }

    static void checkScore(int score, int min, int max) {
        if (score < min && score > max) {
            throw new GradeOutOfBoundException(score, min, max);
        }
    }

}
