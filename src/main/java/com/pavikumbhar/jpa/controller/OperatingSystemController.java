package com.pavikumbhar.jpa.controller;


import com.pavikumbhar.jpa.genspec.SearchRequest;
import com.pavikumbhar.jpa.model.OperatingSystem;
import com.pavikumbhar.jpa.service.OperatingSystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/operating-system")
public class OperatingSystemController {

    private final OperatingSystemService operatingSystemService;

    @PostMapping(value = "/search")
    public Page<OperatingSystem> search(@RequestBody SearchRequest request) {
        return operatingSystemService.searchOperatingSystem(request);
    }

}