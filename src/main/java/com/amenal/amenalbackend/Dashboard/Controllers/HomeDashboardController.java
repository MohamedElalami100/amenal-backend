package com.amenal.amenalbackend.Dashboard.Controllers;

import com.amenal.amenalbackend.Dashboard.Dtos.HomeDashboardDataDto;
import com.amenal.amenalbackend.Dashboard.Services.HomeDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/homeDashboard")
@RequiredArgsConstructor
public class HomeDashboardController {
    @Autowired
    private HomeDashboardService homeDashboardService;

    @GetMapping
    public ResponseEntity<HomeDashboardDataDto> getData() {
        return ResponseEntity.ok(homeDashboardService.getData());
    }
}

