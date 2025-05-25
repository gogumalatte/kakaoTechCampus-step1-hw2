package com.kakao.schedule.repository;

import com.kakao.schedule.dto.ScheduleRequest;
import com.kakao.schedule.dto.ScheduleResponse;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;

@Repository
public class ScheduleRepository {

  private final DataSource dataSource;

  public ScheduleRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void insert(ScheduleRequest request) {
    String sql = "INSERT INTO schedule (title, task, author, password, created_at, updated_at) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    LocalDateTime now = LocalDateTime.now();

    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, request.getTitle());
      pstmt.setString(2, request.getTask());
      pstmt.setString(3, request.getAuthor());
      pstmt.setString(4, request.getPassword());
      pstmt.setTimestamp(5, Timestamp.valueOf(now));
      pstmt.setTimestamp(6, Timestamp.valueOf(now));

      pstmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException("일정 저장에 실패했습니다...", e);
    }
  }
}
