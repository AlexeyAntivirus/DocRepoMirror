package com.revex.docrepo.database.mappers;

import com.revex.docrepo.database.entities.QualificationWork;
import com.revex.docrepo.database.utils.QualificationWorkType;
import com.revex.docrepo.database.utils.SemesterType;
import com.revex.docrepo.database.views.DisciplineView;
import com.revex.docrepo.database.views.GroupView;
import com.revex.docrepo.database.views.StudentView;
import com.revex.docrepo.database.views.TeacherView;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class QualificationWorkMapper implements ResultSetExtractor<QualificationWork> {
    @Override
    public QualificationWork extractData(ResultSet rs) throws SQLException, DataAccessException {
        QualificationWork.QualificationWorkBuilder builder = QualificationWork.builder();

        builder.id(rs.getLong("id"))
                .beginYear(rs.getInt("rik1"))
                .endYear(rs.getInt("rik2"))
                .workType(QualificationWorkType.getByNumber(rs.getInt("kd")))
                .semesterNumber(rs.getInt("sem"))
                .courseNumber(rs.getInt("kurs"))
                .faculty(rs.getString("fakult"))
                .specialty(rs.getString("spec"))
                .branch(rs.getString("galuz"))
                .educationLevel(rs.getString("okr"))
                .educationProgram(rs.getString("op"))
                .discipline(DisciplineView.builder()
                        .id(rs.getLong("disciplineId"))
                        .shortName(rs.getString("disciplineShortName"))
                        .build())
                .group(GroupView.builder()
                        .id(rs.getLong("groupId"))
                        .groupName(rs.getString("groupName"))
                        .build())
                .student(StudentView.builder()
                        .id(rs.getLong("studentId"))
                        .fullName(rs.getString("studentFullName"))
                        .build())
                .title(rs.getString("tema"));

        List<TeacherView> teachers = new ArrayList<>();

        while (rs.next()) {
            teachers.add(TeacherView.builder()
                    .id(rs.getLong("teacherId"))
                    .fullName(rs.getString("teacherFullName"))
                    .build());
        }

        builder.teachers(teachers);

        return builder.build();
    }
}
