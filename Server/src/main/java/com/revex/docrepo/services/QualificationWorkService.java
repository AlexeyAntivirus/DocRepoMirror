package com.revex.docrepo.services;

import com.revex.docrepo.database.entities.QualificationWork;
import com.revex.docrepo.database.mappers.QualificationWorksResultSetExtractor;
import com.revex.docrepo.database.views.TeacherView;
import com.revex.docrepo.exchange.works.DeleteQualificationWorkByIdRequestPayload;
import com.revex.docrepo.exchange.works.DeleteQualificationWorkByIdResponsePayload;
import com.revex.docrepo.exchange.works.GetAllCourseWorksResponsePayload;
import com.revex.docrepo.exchange.works.GetAllDiplomaWorksResponsePayload;
import com.revex.docrepo.exchange.works.InsertNewQualificationWorkRequestPayload;
import com.revex.docrepo.exchange.works.InsertNewQualificationWorkResponsePayload;
import com.revex.docrepo.utils.DocumentType;
import com.revex.docrepo.utils.UploadFileOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class QualificationWorkService {
	private final NamedParameterJdbcTemplate parameterJdbcTemplate;
	private final QualificationWorksResultSetExtractor mapper;
	private final DocumentUploadingService documentUploadingService;

	@Autowired
	public QualificationWorkService(NamedParameterJdbcTemplate parameterJdbcTemplate,
	                                QualificationWorksResultSetExtractor mapper,
	                                DocumentUploadingService documentUploadingService) {
		this.parameterJdbcTemplate = parameterJdbcTemplate;
		this.mapper = mapper;
		this.documentUploadingService = documentUploadingService;
	}

	/*TODO: FIXME!!!*/
	public GetAllDiplomaWorksResponsePayload getAllDiplomaWorks() {
		Set<QualificationWork> query = parameterJdbcTemplate.query(
				"SELECT kd.id, kd.rik1, kd.rik2, kd.sem, predm.id AS disciplineId, predm.nazva AS disciplineName, " +
						"       stud.id AS studentId, stud.pib AS studentFullName, groups.id AS groupId, " +
						"       groups.nazva AS groupName, prep.id AS teacherId, prep.pib AS teacherFullName, " +
						"       kd.tema, kd.kd, kd.papka, kd.ocenka, kd.spec, kd.okr, kd.op, kd.groupn, kd.kurs, " +
						"       kd.fak, kd.galuz, kd.skor, kd.zao, kd.pib, kd.ocenkagos, kd.ocenkaects, kd.ker " +
						"FROM kd " +
						"LEFT OUTER JOIN kerivniki on kerivniki.idrab = kd.id " +
						"LEFT OUTER JOIN prep on kerivniki.idprep = prep.id " +
						"LEFT OUTER JOIN predm ON kd.predmid = predm.id " +
						"JOIN stud ON stud.id = kd.studid " +
						"JOIN sg ON stud.id = sg.idstud " +
						"JOIN groups ON sg.idgroup = groups.id " +
						"WHERE (sg.rik1::integer + 1, sg.rik2::integer + 1, sg.sem::integer) = get_current_academic_year() " +
						"AND kd.kd = 1;", mapper);

		return GetAllDiplomaWorksResponsePayload.builder()
				.diplomaWorks(query)
				.build();
	}

	public GetAllCourseWorksResponsePayload getAllCourseWorks() {
		Set<QualificationWork> query = parameterJdbcTemplate.query(
				"SELECT kd.id, kd.rik1, kd.rik2, kd.sem, predm.id AS disciplineId, predm.nazva AS disciplineName, " +
						"       stud.id AS studentId, stud.pib AS studentFullName, groups.id AS groupId, " +
						"       groups.nazva AS groupName, prep.id AS teacherId, prep.pib AS teacherFullName, " +
						"       kd.tema, kd.kd, kd.papka, kd.ocenka, kd.spec, kd.okr, kd.op, kd.groupn, kd.kurs, " +
						"       kd.fak, kd.galuz, kd.skor, kd.zao, kd.pib, kd.ocenkagos, kd.ocenkaects, kd.ker " +
						"FROM kd " +
						"LEFT OUTER JOIN kerivniki on kerivniki.idrab = kd.id " +
						"LEFT OUTER JOIN prep on kerivniki.idprep = prep.id " +
						"LEFT OUTER JOIN predm ON kd.predmid = predm.id " +
						"JOIN stud ON stud.id = kd.studid " +
						"JOIN sg ON stud.id = sg.idstud " +
						"JOIN groups ON sg.idgroup = groups.id " +
						"WHERE (sg.rik1::integer + 1, sg.rik2::integer + 1, sg.sem::integer) = get_current_academic_year() " +
						"AND kd.kd = 0;", mapper);


		return GetAllCourseWorksResponsePayload.builder()
				.courseWorks(query)
				.build();
	}

	@Transactional
	public InsertNewQualificationWorkResponsePayload insertNewQualificationWork(
			InsertNewQualificationWorkRequestPayload payload, List<MultipartFile> files) {

		UploadFileOptions options = UploadFileOptions.builder()
				.studyYear(payload.getBeginYear() + "-" + payload.getEndYear())
				.educationLevel(payload.getEducationLevel())
				.documentType(DocumentType.fromQualificationWork(payload.getWorkType()))
				.disciplineName(payload.getDiscipline() == null ? null : payload.getDiscipline().getShortName())
				.studentFullName(payload.getStudentFullName())
				.groupName(payload.getGroup().getGroupName())
				.build();
		Path targetPath = null;

		for (MultipartFile file : files) {
			targetPath = documentUploadingService.uploadFile(options, file);
		}

		MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("beginYear", payload.getBeginYear())
				.addValue("endYear", payload.getEndYear())
				.addValue("semesterNumber", payload.getSemesterNumber())
				.addValue("disciplineId", payload.getDiscipline() == null ? null : payload.getDiscipline().getId())
				.addValue("studentId", payload.getStudent().getId())
				.addValue("title", payload.getTitle())
				.addValue("workType", payload.getWorkType().getNumber())
				.addValue("grade", payload.getGrade())
				.addValue("gradeNational", payload.getGradeNational())
				.addValue("gradeECTS", payload.getGradeECTS())
				.addValue("specialty", payload.getSpecialty())
				.addValue("educationLevel", payload.getEducationLevel())
				.addValue("educationProgram", payload.getEducationProgram())
				.addValue("courseNumber", payload.getCourseNumber())
				.addValue("faculty", payload.getFaculty())
				.addValue("branch", payload.getBranch())
				.addValue("studentFullName", payload.getStudentFullName())
				.addValue("workFilePath", Objects.toString(targetPath))
				.addValue("groupName", payload.getGroupName())
				.addValue("isShortened", payload.isShortened() ? 1 : 0)
				.addValue("isExtramural", payload.isExtramural() ? 1 : 0)
				.addValue("teacherNames", payload.getTeacherNames());

		long newWorkId = parameterJdbcTemplate.queryForObject(
				"INSERT INTO kd (rik1, rik2, sem, predmid, studid, tema, " +
						"                kd, papka, ocenka, ocenkagos, ocenkaects, " +
						"                spec, okr, op, kurs, fak, galuz, pib, groupn, " +
						"                skor, zao, ker)" +
						"VALUES (:beginYear, :endYear, :semesterNumber, :disciplineId, " +
						"        :studentId, :title, :workType, :workFilePath, :grade,  " +
						"        :gradeNational, :gradeECTS, :specialty, :educationLevel, " +
						"        :educationProgram, :courseNumber, :faculty, :branch, " +
						"        :studentFullName, :groupName, :isShortened, :isExtramural, " +
						"        :teacherNames) " +
						"RETURNING kd.id;",
				sqlParameterSource,
				new RowMapper<Long>() {
					@Override
					public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getLong("id");
					}
				}
		);

		boolean isSuccessful = true;

		for (TeacherView teacherView : payload.getTeachers()) {
			int update = parameterJdbcTemplate.update(
					"INSERT INTO kerivniki (idrab, idprep)  " +
							"VALUES (:workId, :teacherId);",
					new MapSqlParameterSource()
							.addValue("workId", newWorkId)
							.addValue("teacherId", teacherView.getId())
			);

			isSuccessful = update == 1;
		}

		return InsertNewQualificationWorkResponsePayload.builder()
				.isSuccessful(isSuccessful)
				.build();
	}

	public DeleteQualificationWorkByIdResponsePayload deleteQualificationWorkById(
			DeleteQualificationWorkByIdRequestPayload payload) {
		int update = parameterJdbcTemplate.update("DELETE FROM kerivniki WHERE idrab = :id",
				new MapSqlParameterSource("id", payload.getId()));

		String result = parameterJdbcTemplate.queryForObject(
				"DELETE FROM kd WHERE id = :id RETURNING kd.papka;",
				new BeanPropertySqlParameterSource(payload),
				String.class);


		this.documentUploadingService.deleteFile(Paths.get(result));

		return DeleteQualificationWorkByIdResponsePayload.builder()
				.isSuccessful(update > 0)
				.build();
	}
}
