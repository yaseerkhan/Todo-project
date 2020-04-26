package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController {
  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private JFXTextField signupFirstNameText;

  @FXML
  private JFXTextField signupLastNameText;

  @FXML
  private JFXTextField signupUsernameText;

  @FXML
  private JFXTextField signupLocationText;

  @FXML
  private JFXPasswordField signupPassword;

  @FXML
  private JFXCheckBox signupMaleCheckBox;

  @FXML
  private JFXCheckBox signuFemaleCheckBox;

  @FXML
  private JFXButton signupBtn;

  @FXML
  void initialize() {



    signupBtn.setOnAction(event -> {
      loginUser();

    });

  }

  private void loginUser() {
    String firstname = signupFirstNameText.getText();
    String lastname = signupLastNameText.getText();
    String password = signupPassword.getText();
    String username = signupUsernameText.getText();
    String location = signupLocationText.getText();


    String gender ="";
    if (signuFemaleCheckBox.isSelected()){
      gender = "female";
    }else {
      gender = "male";
    }

    User user = new User(firstname,lastname,username,password,location,gender);

    DatabaseHandler databaseHandler = new DatabaseHandler();
    try {
      databaseHandler.writeDb(user);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
