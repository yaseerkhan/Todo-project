package sample.DataBase;

import sample.Controller.AddItemFormController;
import sample.Model.Task;
import sample.Model.User;
import sun.plugin2.message.CustomSecurityManagerAckMessage;

import java.sql.*;

public class DatabaseHandler extends Config {

  Connection connection;

  public Connection getConnection() throws ClassNotFoundException, SQLException {
    String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

    Class.forName("com.mysql.jdbc.Driver");
    connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
    return connection;
  }

  //write
  public void writeDb(User user) throws SQLException, ClassNotFoundException {
    String insert = "INSERT INTO "
            + Const.usersTable + "(" + Const.ufirstname + "," + Const.ulastname + "," + Const.uname + "," + Const.upass + "," + Const.location + "," + Const.gender + ")"
            + "VALUES(?,?,?,?,?,?)";

    PreparedStatement preparedStatement = getConnection().prepareStatement(insert);

    preparedStatement.setString(1, user.getFirstname());
    preparedStatement.setString(2, user.getLastname());
    preparedStatement.setString(3, user.getUsername());
    preparedStatement.setString(4, user.getPassword());
    preparedStatement.setString(5, user.getLocation());
    preparedStatement.setString(6, user.getGender());
    preparedStatement.executeUpdate();

  }

  public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {

    ResultSet resultSet = null;

    if (!user.getUsername().equals("") || !user.getPassword().equals("")) {

      String query = "SELECT * FROM " + Const.usersTable + " WHERE "
              + Const.uname + "=?" + " AND " + Const.upass + "=?";

      PreparedStatement preparedStatement = getConnection().prepareStatement(query);
      preparedStatement.setString(1, user.getUsername());
      preparedStatement.setString(2, user.getPassword());
      resultSet = preparedStatement.executeQuery();

    } else {
      System.out.println("enter your crediantials");
    }
    return resultSet;
  }


  public void insertTask(Task task) throws SQLException, ClassNotFoundException {
    String insert = "INSERT INTO "
            + Const.tasksTable + "(" + Const.uid + "," + Const.tasks + "," + Const.datecreated + "," + Const.description + ")"
            + "VALUES(?,?,?,?)";

    PreparedStatement preparedStatement = getConnection().prepareStatement(insert);

    preparedStatement.setInt(1, task.getUserid());
    preparedStatement.setString(2, task.getTask());
    preparedStatement.setTimestamp(3, task.getDateCreated());
    preparedStatement.setString(4, task.getDescription());

    preparedStatement.executeUpdate();

  }

  public ResultSet getTaskByUser(int userid) throws SQLException, ClassNotFoundException {
    ResultSet resultSet = null;


    String query = " Select * from " + Const.tasksTable + " where " + Const.uid + "=?";

    PreparedStatement preparedStatement = getConnection().prepareStatement(query);
    preparedStatement.setInt(1, userid);
    resultSet = preparedStatement.executeQuery();
    return resultSet;
  }

  public int getTotalTasks(int userid) throws SQLException, ClassNotFoundException {

    String query = "Select count(*) from " + Const.tasksTable + " where " + Const.uid + "=?";
    PreparedStatement preparedStatement = getConnection().prepareStatement(query);

    preparedStatement.setInt(1, userid);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      return resultSet.getInt(1);
    }
    return resultSet.getInt(1);
  }

  public void deleteTask(int userId, int taskId) throws SQLException, ClassNotFoundException {
    String query = "delete from " + Const.tasksTable + " where " + Const.uid + "=?" + " and " + Const.tid + "=?";
    PreparedStatement preparedStatement = getConnection().prepareStatement(query);
    preparedStatement.setInt(1, userId);
    preparedStatement.setInt(2, taskId);
    preparedStatement.execute();
    preparedStatement.close();
  }
  //read

  public void updateTask(Timestamp datecreated, String description, String tasks, int taskid) throws SQLException, ClassNotFoundException {

    String query = "UPDATE tasks SET datecreated=?,description=?,tasks=? WHERE taskid=?";

    PreparedStatement preparedStatement = getConnection().prepareStatement(query);
    preparedStatement.setTimestamp(1, datecreated);
    preparedStatement.setString(2, description);
    preparedStatement.setString(3, tasks);
    // preparedStatement.setInt(4, userId);
    preparedStatement.setInt(4, taskid);
    preparedStatement.executeUpdate();
    preparedStatement.close();

  }
  //update
//  public void updatedb(Timestamp datecreated, String description, String tasks, int taskid ) throws SQLException, ClassNotFoundException {
//    String query ="update "+Const.tasksTable+"set datecreated"+"=?,"+Const.description+"=?,"+Const.tasks+"=?"+" where "+Const.tid+"=?";
//    PreparedStatement preparedStatement = getConnection().prepareStatement(query);
//    preparedStatement.setTimestamp(1,datecreated);
//    preparedStatement.setString(2,description);
//    preparedStatement.setString(3,tasks);
//    preparedStatement.setInt(4,taskid);
//    preparedStatement.execute();
//    preparedStatement.close();
//      }


}
