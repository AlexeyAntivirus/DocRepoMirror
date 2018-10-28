package com.revex.docrepo.controllers;

import com.revex.docrepo.exchange.group.*;
import com.revex.docrepo.exchange.group.GetGroupsByCourseNumberAndAcademicYearResponsePayload;
import com.revex.docrepo.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

	private final GroupService service;

	@Autowired
	public GroupController(GroupService service) {
		this.service = service;
	}

	@ResponseBody
	@GetMapping("/all")
	public GetAllGroupsResponsePayload getAllGroups() {
		return this.service.getAllGroups();
	}

	@ResponseBody
	@GetMapping("/view/all")
	public GetAllGroupViewsResponsePayload getAllGroupViews() {
		return this.service.getAllGroupViews();
	}

	@ResponseBody
	@PostMapping("/find")
	public FindGroupsByParameterResponsePayload findGroupsByParameter(
			@RequestBody FindGroupsByParameterRequestPayload parameterRequestPayload) {
		return this.service.findGroupsByParameter(parameterRequestPayload);
	}

	@ResponseBody
	@PostMapping("/view/find")
	public FindGroupViewsByParameterResponsePayload findGroupsByParameter(
			@RequestBody FindGroupViewsByParameterRequestPayload parameterRequestPayload) {
		return this.service.findGroupViewsByParameter(parameterRequestPayload);
	}

	@ResponseBody
	@PostMapping("/get-by-course-number-and-academic-year")
	public GetGroupsByCourseNumberAndAcademicYearResponsePayload getGroupsByCourseNumberAndAcademicYear(
			@RequestBody GetGroupsByCourseNumberAndAcademicYearRequestPayload payload) {
		return this.service.getGroupsByCourseNumberAndAcademicYear(payload);
	}
	@ResponseBody
	@PostMapping("/get-by-id")
	public GetGroupByIdResponsePayload getGroupById(@RequestBody GetGroupByIdRequestPayload payload) {
		return service.getGroupById(payload);
	}

	@ResponseBody
	@PutMapping("/insert")
	public InsertNewGroupResponsePayload insertNewGroup(
			@RequestBody InsertNewGroupRequestPayload payload) {
		return this.service.insertNewGroup(payload);
	}

	@ResponseBody
	@DeleteMapping("/delete")
	public DeleteGroupByIdResponsePayload deleteGroupById(
			DeleteGroupByIdRequestPayload payload) {
		return this.service.deleteGroupById(payload);
	}

	@ResponseBody
	@PostMapping("/update")
	public UpdateGroupByParameterResponsePayload updateGroupByParameter(
			@RequestBody UpdateGroupByParameterRequestPayload payload) {
		return this.service.updateGroupByParameter(payload);
	}

}
