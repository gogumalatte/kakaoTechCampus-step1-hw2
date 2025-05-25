package com.kakao.schedule.controller;

import com.kakao.schedule.dto.ScheduleRequest;
import com.kakao.schedule.dto.ScheduleResponse;
import com.kakao.schedule.dto.UpdateScheduleRequest;
import com.kakao.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

  private final ScheduleService scheduleService;

  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  // 일정 생성
  @PostMapping
  public ResponseEntity<Void> createSchedule(@RequestBody ScheduleRequest request) {
    scheduleService.createSchedule(request);
    return ResponseEntity.ok().build();
  }

  // 전체 조회 + 조건 조회
  @GetMapping
  public ResponseEntity<List<ScheduleResponse>> getSchedules(
      @RequestParam(required = false) String author,
      @RequestParam(required = false) String updatedAt
  ) {
    List<ScheduleResponse> schedules = scheduleService.getSchedulesByCondition(author, updatedAt);
    return ResponseEntity.ok(schedules);
  }

  // 단건 조회
  @GetMapping("/{id}")
  public ResponseEntity<ScheduleResponse> getScheduleById(@PathVariable Long id) {
    ScheduleResponse schedule = scheduleService.getScheduleById(id);
    if (schedule == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(schedule);
  }

  // 일정 수정
  @PutMapping("/{id}")
  public ResponseEntity<String> updateSchedule(
      @PathVariable Long id,
      @RequestBody UpdateScheduleRequest request
  ) {
    boolean updated = scheduleService.updateSchedule(id, request);
    if (updated) {
      return ResponseEntity.ok("일정이 수정되었습니다.");
    } else {
      return ResponseEntity.status(403).body("비밀번호가 일치하지 않거나 일정이 존재하지 않습니다.");
    }
  }

  // 일정 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSchedule(
      @PathVariable Long id,
      @RequestBody Map<String, String> request
  ) {
    String password = request.get("password");
    boolean deleted = scheduleService.deleteSchedule(id, password);
    if (deleted) {
      return ResponseEntity.ok("일정이 삭제되었습니다.");
    } else {
      return ResponseEntity.status(403).body("비밀번호가 일치하지 않거나 일정이 존재하지 않습니다.");
    }
  }
}
