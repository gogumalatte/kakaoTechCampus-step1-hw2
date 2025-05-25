package com.kakao.schedule.repository;

import com.kakao.schedule.dto.ScheduleRequest;
import com.kakao.schedule.dto.ScheduleResponse;
import com.kakao.schedule.dto.UpdateScheduleRequest;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
  public List<ScheduleResponse> findAll() {
    String sql = "SELECT * FROM schedule ORDER BY updated_at DESC";
    List<ScheduleResponse> result = new ArrayList<>();

    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        ScheduleResponse schedule = new ScheduleResponse(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("task"),
            rs.getString("author"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
        );
        result.add(schedule);
      }

    } catch (SQLException e) {
      throw new RuntimeException("일정 조회에 실패했습니다.", e);
    }

    return result;
  }

  public ScheduleResponse findById(Long id) {
    String sql = "SELECT * FROM schedule WHERE id = ?";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setLong(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new ScheduleResponse(
              rs.getLong("id"),
              rs.getString("title"),
              rs.getString("task"),
              rs.getString("author"),
              rs.getTimestamp("created_at").toLocalDateTime(),
              rs.getTimestamp("updated_at").toLocalDateTime()
          );
        } else {
          return null;
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException("일정 단건 조회에 실패했습니다.", e);
    }
  }

  public boolean updateById(Long id, UpdateScheduleRequest request) {
    String passwordSql = "SELECT password FROM schedule WHERE id = ?";
    String updateSql = "UPDATE schedule SET title = ?, task = ?, author = ?, updated_at = ? WHERE id = ?";

    try (Connection conn = dataSource.getConnection();
        PreparedStatement checkStmt = conn.prepareStatement(passwordSql)) {

      checkStmt.setLong(1, id);
      ResultSet rs = checkStmt.executeQuery();

      if (!rs.next()) return false;

      String passwordInDb = rs.getString("password");
      if (!passwordInDb.equals(request.getPassword())) {
        return false;
      }

      try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
        updateStmt.setString(1, request.getTitle());
        updateStmt.setString(2, request.getTask());
        updateStmt.setString(3, request.getAuthor());
        updateStmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        updateStmt.setLong(5, id);
        updateStmt.executeUpdate();
        return true;
      }

    } catch (SQLException e) {
      throw new RuntimeException("일정 수정에 실패했습니다.", e);
    }
  }
}
