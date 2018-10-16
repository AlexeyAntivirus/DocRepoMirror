package com.revex.docrepo.controllers;

import com.revex.docrepo.exchange.group.*;
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
