package sample.Controller;

import sample.animations.Shaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DataBase.DatabaseHandler;
import sample.Model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {



  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private JFXTextField loginUserText;

  @FXML
  private JFXPasswordField loginPassText;

  @FXML
  private JFXButton loginBtn;

  @FXML
  private JFXButton loginSignUpBtn;
  private DatabaseHandler databaseHandler;

  private  int  userid;

  @FXML
  void initialize() {




    loginSignUpBtn.setOnAction(event -> {
      loginSignUpBtn.getScene().getWindow().hide();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/sample/view/signUp.fxml"));
      try {
        loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }

      Parent parent = loader.getRoot();
      Stage stage = new Stage();
      stage.setScene( new Scene(parent));
      stage.showAndWait();


    });



    loginBtn.setOnAction(event -> {

      databaseHandler= new DatabaseHandler();

      String username = loginUserText.getText().trim();
      String pass = loginPassText.getText().trim();
      User user = new User();
      user.setUsername(username);
      user.setPassword(pass);


      ResultSet userRow = null;
      try {
        userRow = databaseHandler.getUser(user);
      } catch (SQLException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

      int counter = 0;

   try {
     while (userRow.next()){
       userid = userRow.getInt("userid");

       counter++;
     }
     if (counter==1){
       showAddItem();

     }else {
       Shaker shaker = new Shaker(loginPassText);
       Shaker shaker2 = new Shaker(loginUserText);
       shaker.Shake();
       shaker2.Shake();
     }
   }catch (Exception e){

   }
    });


  }

  private void showAddItem(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/sample/view/addItem.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Parent parent = loader.getRoot();
    Stage stage = new Stage();
    stage.setScene( new Scene(parent));

    AddItemController addItemController = loader.getController();
    addItemController.setuserId(userid);

    stage.showAndWait();
  }

}
