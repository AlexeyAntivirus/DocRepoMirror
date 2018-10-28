package com.revex.docrepo.controllers;

import com.revex.docrepo.exchange.discipline.*;
import com.revex.docrepo.services.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {

	private final DisciplineService service;

	@Autowired
	public DisciplineController(DisciplineService service) {
		this.service = service;
	}

	@GetMapping("/view/all")
	@ResponseBody
	public GetAllDisciplineViewsResponsePayload getAllDisciplineViews() {
		return this.service.getAllDisciplineViews();
	}

	@GetMapping("/all")
	@ResponseBody
	public GetAllDisciplinesResponsePayload getAllDisciplines() {
		return this.service.getAllDisciplines();
	}

	@PostMapping("/find")
	@ResponseBody
	public FindDisciplinesByParameterResponsePayload findDisciplinesByParameter(
			@RequestBody FindDisciplinesByParameterRequestPayload payload) {
		return this.service.findDisciplinesByParameter(payload);
	}

	@PostMapping("/view/find")
	@ResponseBody
	public FindDisciplineViewsByParameterResponsePayload findDisciplineViewsByParameter(
			@RequestBody FindDisciplineViewsByParameterRequestPayload payload) {
		return this.service.findDisciplineViewsByParameter(payload);
	}

	@PutMapping("/insert")
	@ResponseBody
	public InsertNewDisciplineResponsePayload insertNewDiscipline(
			@RequestBody InsertNewDisciplineRequestPayload payload) {
		return this.service.insertNewDiscipline(payload);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public DeleteDisciplineByIdResponsePayload deleteDisciplineById(
			DeleteDisciplineByIdRequestPayload payload) {
		return this.service.deleteDisciplineById(payload);
	}

	@PostMapping("/update")
	@ResponseBody
	public UpdateDisciplineByParamResponsePayload updateDisciplineByParam(
			@RequestBody UpdateDisciplineByParamRequestPayload payload) {
		return this.service.updateDisciplineByParam(payload);
	}
}
