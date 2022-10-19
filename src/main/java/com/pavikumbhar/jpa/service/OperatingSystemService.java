package com.pavikumbhar.jpa.service;


import com.pavikumbhar.jpa.genspec.SearchRequest;
import com.pavikumbhar.jpa.genspec.SearchSpecification;
import com.pavikumbhar.jpa.model.OperatingSystem;
import com.pavikumbhar.jpa.repository.OperatingSystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OperatingSystemService {


    private final  OperatingSystemRepository operatingSystemRepository;

    public Page<OperatingSystem> searchOperatingSystem(SearchRequest request) {
        SearchSpecification<OperatingSystem> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<OperatingSystem> c = operatingSystemRepository.findAll(specification, pageable);
        c.getNumberOfElements();
        return operatingSystemRepository.findAll(specification, pageable);
    }

}