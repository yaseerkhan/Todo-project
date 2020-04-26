package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import sample.DataBase.DatabaseHandler;
import sample.Model.Task;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ListController {
  @FXML
  private ImageView listRefreshButton;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private JFXListView<Task> listview;

  @FXML
  private JFXTextField listtaskField;

  @FXML
  private JFXTextField listdescriptionField;

  @FXML
  private JFXButton listsavetaskbtn;

  private ObservableList<Task> taskObservableList;

  private ObservableList<Task> refreshedTasks;

private DatabaseHandler databaseHandler;


  @FXML
  void initialize() throws SQLException, ClassNotFoundException {

    taskObservableList =FXCollections.observableArrayList();

   databaseHandler = new DatabaseHandler();
    ResultSet resultSet= databaseHandler.getTaskByUser(5);
    while (resultSet.next()){
      Task task = new Task();
      task.setTaskid(resultSet.getInt("taskid"));
      task.setDateCreated(resultSet.getTimestamp("datecreated"));
      task.setTask(resultSet.getString("tasks"));
      task.setDescription(resultSet.getString("description"));
      taskObservableList.addAll(task);
    }

listview.setItems(taskObservableList);
listview.setCellFactory(CellController -> new CellController());

    listRefreshButton.setOnMouseClicked(event -> {
      try {
        refreshList();
      } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    });

listsavetaskbtn.setOnAction(event -> {
  try {
    addnewTask();
  } catch (SQLException e) {
    e.printStackTrace();
  } catch (ClassNotFoundException e) {
    e.printStackTrace();
  }
});

  }

  public void refreshList() throws SQLException, ClassNotFoundException {


    System.out.println("refreshList in ListCont called");

    refreshedTasks = FXCollections.observableArrayList();


    DatabaseHandler databaseHandler = new DatabaseHandler();
    ResultSet resultSet = databaseHandler.getTaskByUser(AddItemController.userid);

    while (resultSet.next()) {
      Task task = new Task();
      task.setTaskid(resultSet.getInt("taskid"));
      task.setTask(resultSet.getString("tasks"));
      task.setDateCreated(resultSet.getTimestamp("datecreated"));
      task.setDescription(resultSet.getString("description"));

      refreshedTasks.addAll(task);

    }
    listview.setItems(refreshedTasks);
    listview.setCellFactory(CellController -> new CellController());
  }


    public void addnewTask() throws SQLException, ClassNotFoundException {
    if (!listtaskField.getText().equals("") || !listdescriptionField.getText().equals("")){
      Task newTask = new Task();
//      Calendar calendar = Calendar.getInstance();
//      java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());
      newTask.setUserid(AddItemController.userid);
      newTask.setTask(listtaskField.getText().trim());
      newTask.setDescription(listdescriptionField.getText().trim());
//      newTask.setDateCreated(timestamp);
      newTask.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
      databaseHandler.insertTask(newTask);
      listtaskField.setText("");
      listdescriptionField.setText("");

      initialize();

    }
  }


}
