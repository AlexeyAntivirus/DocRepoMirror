package com.revex.docrepo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revex.docrepo.exceptions.DocRepoFilesProblemException;
import com.revex.docrepo.exchange.works.DeleteQualificationWorkByIdRequestPayload;
import com.revex.docrepo.exchange.works.DeleteQualificationWorkByIdResponsePayload;
import com.revex.docrepo.exchange.works.FindAllQualificationWorksByAcademicYearAndWorkTypeRequestPayload;
import com.revex.docrepo.exchange.works.FindAllQualificationWorksByAcademicYearAndWorkTypeResponsePayload;
import com.revex.docrepo.exchange.works.GetAllCourseWorksResponsePayload;
import com.revex.docrepo.exchange.works.GetAllDiplomaWorksResponsePayload;
import com.revex.docrepo.exchange.works.InsertNewQualificationWorkRequestPayload;
import com.revex.docrepo.exchange.works.InsertNewQualificationWorkResponsePayload;
import com.revex.docrepo.exchange.works.SendFilesRequestPayload;
import com.revex.docrepo.exchange.works.SendFilesResponsePayload;
import com.revex.docrepo.exchange.works.UpdateQualificationWorkRequestPayload;
import com.revex.docrepo.exchange.works.UpdateQualificationWorkResponsePayload;
import com.revex.docrepo.services.QualificationWorkService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

@RestController
@RequestMapping("/works")
public class QualificationWorkController {
	private final QualificationWorkService service;

	private Logger logger = LogManager.getLogger(QualificationWorkController.class);

	@Autowired
	public QualificationWorkController(QualificationWorkService service) {
		this.service = service;
	}

	@GetMapping("/diploma/all")
	@ResponseBody
	public GetAllDiplomaWorksResponsePayload getAllDiplomaWorks() {
		return service.getAllDiplomaWorks();
	}

	@GetMapping("/course/all")
	@ResponseBody
	public GetAllCourseWorksResponsePayload getAllCourseWorks() {
		return service.getAllCourseWorks();
	}

	@PostMapping("/find/all-by-academic-year-and-work-type")
	@ResponseBody
	public FindAllQualificationWorksByAcademicYearAndWorkTypeResponsePayload findAllQualificationWorksByAcademicYearAndWorkType(
			@RequestBody FindAllQualificationWorksByAcademicYearAndWorkTypeRequestPayload payload) {
		return this.service.findAllQualificationWorksByAcademicYearAndWorkType(payload);
	}

	@PutMapping(value = "/insert", consumes = {"multipart/form-data"})
	@ResponseBody
	public InsertNewQualificationWorkResponsePayload insertNewQualificationWork(
			@RequestPart("info") String payload,
			@RequestPart("files") List<MultipartFile> files) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		InsertNewQualificationWorkRequestPayload insertNewQualificationWorkRequestPayload
				= mapper.readValue(payload, InsertNewQualificationWorkRequestPayload.class);
		return service.insertNewQualificationWork(insertNewQualificationWorkRequestPayload, files);
	}

	@PostMapping(value = "/update", consumes = {"multipart/form-data"})
	@ResponseBody
	public UpdateQualificationWorkResponsePayload updateQualificationWork(
			@RequestPart("info") String payload,
			@RequestPart("files") List<MultipartFile> files) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		UpdateQualificationWorkRequestPayload insertNewQualificationWorkRequestPayload
				= mapper.readValue(payload, UpdateQualificationWorkRequestPayload.class);
		return service.updateQualificationWork(insertNewQualificationWorkRequestPayload, files);
	}

	@DeleteMapping(value = "/delete")
	@ResponseBody
	public DeleteQualificationWorkByIdResponsePayload deleteQualificationWorkById(
			DeleteQualificationWorkByIdRequestPayload payload) {
		return service.deleteQualificationWorkById(payload);
	}

	@GetMapping("/download-by-id")
	public void downloadFiles(SendFilesRequestPayload payload, HttpServletResponse response) {
		this.service.sendFiles(payload, response);
	}
}
