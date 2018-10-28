package com.revex.docrepo.services;

import com.revex.docrepo.database.entities.Student;
import com.revex.docrepo.database.mappers.StudentMapper;
import com.revex.docrepo.database.mappers.StudentViewMapper;
import com.revex.docrepo.database.views.StudentView;
import com.revex.docrepo.exchange.student.FindStudentViewsByParamRequestParameterRequestPayload;
import com.revex.docrepo.exchange.student.FindStudentViewsByParamRequestParameterResponsePayload;
import com.revex.docrepo.exchange.student.GetAllStudentsResponsePayload;
import com.revex.docrepo.exchange.student.InsertNewStudentRequestPayload;
import com.revex.docrepo.exchange.student.InsertNewStudentResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
	private final NamedParameterJdbcTemplate template;
	private final StudentMapper mapper;
	private final StudentViewMapper viewMapper;

	@Autowired
	public StudentService(NamedParameterJdbcTemplate template,
	                      StudentMapper mapper,
	                      StudentViewMapper viewMapper) {
		this.template = template;
		this.mapper = mapper;
		this.viewMapper = viewMapper;
	}

	public GetAllStudentsResponsePayload getAllStudents() {
		List<Student> query = template.query("SELECT" +
				" stud.id," +
				" stud.pib," +
				" sg.rik1," +
				" sg.rik2," +
				" sg.sem," +
				" groups.id as groupId," +
				" groups.nazva as groupName" +
				" FROM sg" +
				" JOIN groups ON groups.id = sg.idgroup" +
				" JOIN stud on sg.idstud = stud.id", mapper);

		return GetAllStudentsResponsePayload.builder()
				.students(query)
				.build();
	}

	public FindStudentViewsByParamRequestParameterResponsePayload findStudentViewsByParameter(
			FindStudentViewsByParamRequestParameterRequestPayload payload) {
		List<StudentView> parameterValue = template.query(
				"SELECT id, pib FROM stud " +
						"WHERE cast(" + payload.getParameterKey() + " as varchar(512)) ~ cast(:parameterValue as varchar(512))",
				new MapSqlParameterSource("parameterValue", payload.getParameterValue()),
				viewMapper);
		return FindStudentViewsByParamRequestParameterResponsePayload.builder()
				.students(parameterValue)
				.build();
	}

	public InsertNewStudentResponsePayload insertNewStudent(InsertNewStudentRequestPayload payload) {
		Long studentId = template.queryForObject(
				"INSERT INTO stud (pib)\n" +
						"VALUES (:fullName)\n" +
						"RETURNING stud.id;\n",
				new MapSqlParameterSource("fullName", payload.getFullName()),
				Long.class);

		int update = template.update("INSERT INTO sg (idstud, idgroup, rik1, rik2, sem)\n" +
						"VALUES (:studentId, :groupId, :beginYear, :endYear, :semester);",
				new MapSqlParameterSource()
						.addValue("studentId", studentId)
						.addValue("groupId", payload.getGroupId())
						.addValue("beginYear", payload.getBeginYear())
						.addValue("endYear", payload.getEndYear())
						.addValue("semester", payload.getSemesterType().getNumber())
		);

		return InsertNewStudentResponsePayload.builder()
				.isSuccessful(update == 1)
				.build();
	}
}
