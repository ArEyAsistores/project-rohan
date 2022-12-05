package com.yonduuniversity.rohan.services;

import java.time.LocalDate;
import java.util.List;
import com.yonduuniversity.rohan.models.Class;

public interface ClassService {

    Class createClass(int courseCode, int attendancePercentage, int quizPercentage, int exercisePercentage,
            int projectPercentage, LocalDate starDate, LocalDate endDate);

    List<Class> retrieveClasses(String email, int pageSize, int pageNumber);

    Class retrieveClass(int courseCode, int batch);

    Class searchClass(String info);

    void deactivateClass(int courseCode, int batch);

}
