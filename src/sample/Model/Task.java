package sample.Model;

import java.sql.Timestamp;

public class Task {


  private int userid;
  private int taskid;

private Timestamp dateCreated;
private String description;
private String task;

  public Task() {
  }

  public Task(Timestamp dateCreated, String description, String task) {
    this.dateCreated = dateCreated;
    this.description = description;
    this.task = task;
  }

  public Timestamp getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Timestamp dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public int getTaskid() {
    return taskid;
  }

  public void setTaskid(int taskid) {
    this.taskid = taskid;
  }
}
