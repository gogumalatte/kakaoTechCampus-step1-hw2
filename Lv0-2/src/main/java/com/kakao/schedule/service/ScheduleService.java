package com.kakao.schedule.service;

import com.kakao.schedule.dto.ScheduleRequest;
import com.kakao.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;

  public ScheduleService(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  public void createSchedule(ScheduleRequest request) {
    scheduleRepository.insert(request);
  }

  // 나중에 전체 조회, 단건 조회 메서드 추가 예정.
}
