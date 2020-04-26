package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.DataBase.DatabaseHandler;
import sample.Model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController {

  private int userId;






  @FXML
  private JFXButton mytodobtn;

  @FXML
  private Label taskaddedlabel;



  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private JFXTextField taskField;

  @FXML
  private JFXTextField descriptionField;

  @FXML
  private JFXButton savetaskbtn;

  DatabaseHandler databaseHandler;

  @FXML
  void initialize() {

    databaseHandler= new DatabaseHandler();

    savetaskbtn.setOnAction(event -> {
      Task task = new Task();
      Calendar calendar = Calendar.getInstance();
      Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());


      try {


        String taskText = taskField.getText().trim();
        String description = descriptionField.getText().trim();


       
        task.setUserid(AddItemController.userid);
        task.setTask(taskText);
        task.setDateCreated(timestamp);
        task.setDescription(description);



        databaseHandler.insertTask(task);
        mytodobtn.setVisible(true);
        mytodobtn.setText("My Todo's " +databaseHandler.getTotalTasks(AddItemController.userid));
        taskaddedlabel.setVisible(true);
        taskField.setText("");
        descriptionField.setText("");

        mytodobtn.setOnAction(event1 -> {

          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/sample/View/list.fxml"));
          try {
            loader.load();
          } catch (IOException e) {
            e.printStackTrace();
          }

          Parent root = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(root));
          stage.showAndWait();
        });


      } catch (SQLException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    });
  }

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
    System.out.println(this.userId);
  }


}
