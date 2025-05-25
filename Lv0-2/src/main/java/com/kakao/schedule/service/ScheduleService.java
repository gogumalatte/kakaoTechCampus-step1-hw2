package com.kakao.schedule.service;

import com.kakao.schedule.dto.ScheduleRequest;
import com.kakao.schedule.dto.ScheduleResponse;
import com.kakao.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;

  public ScheduleService(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  public void createSchedule(ScheduleRequest request) {
    scheduleRepository.insert(request);
  }

  public List<ScheduleResponse> getAllSchedules() {
    return scheduleRepository.findAll();
  }
}
