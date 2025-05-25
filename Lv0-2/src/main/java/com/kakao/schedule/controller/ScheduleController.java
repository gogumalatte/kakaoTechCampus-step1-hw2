package com.kakao.schedule.controller;

import com.kakao.schedule.dto.ScheduleRequest;
import com.kakao.schedule.dto.ScheduleResponse;
import com.kakao.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

  private final ScheduleService scheduleService;

  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  @PostMapping
  public ResponseEntity<Void> createSchedule(@RequestBody ScheduleRequest request) {
    scheduleService.createSchedule(request);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
    List<ScheduleResponse> schedules = scheduleService.getAllSchedules();
    return ResponseEntity.ok(schedules);
  }
}
