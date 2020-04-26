package sample.Controller;

import sample.animations.Shaker;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {


  @FXML
  private AnchorPane rootanchor;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private ImageView addItembtn;

  @FXML
  private Label notasklabel;

  public static int userid;

  @FXML
  void initialize() {

    addItembtn.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
      Shaker shaker = new Shaker(addItembtn);
      shaker.Shake();
      System.out.println("add item clicked");

      FadeTransition btnTransition = new FadeTransition(Duration.millis(2000),addItembtn);
      FadeTransition labelTransition = new FadeTransition(Duration.millis(2000),notasklabel);

      addItembtn.relocate(0,20);
      notasklabel.relocate(0,80);

      addItembtn.setOpacity(0);
      notasklabel.setOpacity(0);

      btnTransition.setFromValue(1f);
      btnTransition.setToValue(0f);
      btnTransition.setFromValue(1f);
      btnTransition.setToValue(0f);
      btnTransition.setCycleCount(2);
      btnTransition.setAutoReverse(false);
      btnTransition.play();

      labelTransition.setFromValue(1f);
      labelTransition.setToValue(0f);
      labelTransition.setCycleCount(2);
      labelTransition.setAutoReverse(false);
      labelTransition.play();

      try {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/sample/View/addItemForm.fxml"));
        rootanchor.getChildren().setAll(anchorPane);


       AddItemController.userid = getuserId();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800),anchorPane);
        fadeTransition.setFromValue(0f);
        fadeTransition.setToValue(1f);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();


      } catch (IOException e) {
        e.printStackTrace();
      }


    });
  }

  public void setuserId(int userid) {
    AddItemController.userid =userid;
    System.out.println("user id  add item" + AddItemController.userid);

  }
  public int getuserId(){
    return userid;

  }
}
