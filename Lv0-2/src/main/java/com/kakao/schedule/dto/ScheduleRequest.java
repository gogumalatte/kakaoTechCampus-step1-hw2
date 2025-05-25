package com.kakao.schedule.dto;

public class ScheduleRequest {

  private String title;
  private String task;
  private String author;
  private String password;

  public ScheduleRequest() {
  }

  public ScheduleRequest(String title, String task, String author, String password) {
    this.title = title;
    this.task = task;
    this.author = author;
    this.password = password;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
