package com.tree.licensingservice.controller;

import com.tree.licensingservice.model.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseController {

    @Value("${trace.property: 1111}")
    private String traceProperty;

    @GetMapping("/{id}")
    public License get(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("id") String id){
        log.info("traceProperty: {}", traceProperty);
        return License.builder()
                .id(id)
                .organizationId(organizationId)
                .productName("Tesla")
                .type("Seat")
                .build();
    }

}
