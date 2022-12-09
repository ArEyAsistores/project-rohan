package com.yonduunversity.rohan.services.impl;

import java.util.List;
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
        ClassBatch classBatch = quiz.getClassBatch();
        Grade grade = new Grade();
        grade.setQuiz(quiz);
        grade.setStudent(student);
        grade.setScore(score);
        grade.setClassBatch(classBatch);

        return gradeRepo.save(grade);
    }

    public Grade giveExerciseScore(int exercise_id, String email, int score) {
        Optional<Exercise> optionalExercise = exerciseRepo.findById(exercise_id);
        Exercise exercise = optionalExercise.get();
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
        Optional<Project> optionalProject = projectRepo.findById(project_id);
        Project project = optionalProject.get();
        Student student = studentRepo.findByEmail(email);
        ClassBatch classBatch = project.getClassBatch();
        Grade grade = new Grade();
        grade.setProject(project);
        grade.setStudent(student);
        grade.setScore(score);
        grade.setClassBatch(classBatch);
        return gradeRepo.save(grade);
    }

    // public List<Grade> retrieveStudentGrades(String email) {
    // return (List<Grade>) gradeRepo.findByStudentId(email);
    // }

}
