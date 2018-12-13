package com.tree.licensingservice.controller;

import com.tree.licensingservice.model.License;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseController {

    @GetMapping("/{id}")
    public License get(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("id") String id
    ){
        return License.builder()
                .id(id)
                .organizationId(organizationId)
                .productName("Tesla")
                .type("Seat")
                .build();
    }

}
