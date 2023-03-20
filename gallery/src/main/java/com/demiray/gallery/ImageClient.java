package com.demiray.gallery;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "image")
public interface ImageClient {

    @GetMapping("/department/{departmentId}")
    List<Gallery> findByDepartment(@PathVariable("departmentId") String departmentId);

}
