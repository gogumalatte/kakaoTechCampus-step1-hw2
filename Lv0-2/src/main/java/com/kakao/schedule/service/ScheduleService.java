package com.kakao.schedule.service;

import com.kakao.schedule.dto.ScheduleRequest;
import com.kakao.schedule.dto.ScheduleResponse;
import com.kakao.schedule.dto.UpdateScheduleRequest;
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

  public List<ScheduleResponse> getSchedulesByCondition(String author, String updatedAt) {
    return scheduleRepository.findAllByCondition(author, updatedAt);
  }

  public ScheduleResponse getScheduleById(Long id) {
    return scheduleRepository.findById(id);
  }

  public boolean updateSchedule(Long id, UpdateScheduleRequest request) {
    return scheduleRepository.updateById(id, request);
  }

  public boolean deleteSchedule(Long id, String password) {
    return scheduleRepository.deleteById(id, password);
  }
}
