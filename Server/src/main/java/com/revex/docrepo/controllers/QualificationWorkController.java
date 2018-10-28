package com.revex.docrepo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revex.docrepo.exchange.works.DeleteQualificationWorkByIdRequestPayload;
import com.revex.docrepo.exchange.works.DeleteQualificationWorkByIdResponsePayload;
import com.revex.docrepo.exchange.works.GetAllCourseWorksResponsePayload;
import com.revex.docrepo.exchange.works.GetAllDiplomaWorksResponsePayload;
import com.revex.docrepo.exchange.works.InsertNewQualificationWorkRequestPayload;
import com.revex.docrepo.exchange.works.InsertNewQualificationWorkResponsePayload;
import com.revex.docrepo.services.QualificationWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/works")
public class QualificationWorkController {
    private final QualificationWorkService service;

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

    @DeleteMapping(value = "/delete")
	@ResponseBody
	public DeleteQualificationWorkByIdResponsePayload deleteQualificationWorkById(
			DeleteQualificationWorkByIdRequestPayload payload) {
		return service.deleteQualificationWorkById(payload);
    }
}
