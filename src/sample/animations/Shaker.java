package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shaker {
  private TranslateTransition translateTransition;
  public Shaker(Node node) {
    translateTransition = new TranslateTransition(Duration.millis(50),node);
    translateTransition.getNode().setStyle("-fx-background-color:red");
    translateTransition.setFromX(0);
    translateTransition.setByX(10f);
    translateTransition.setCycleCount(2);
    translateTransition.setAutoReverse(true);

  }
  public void Shake(){
    translateTransition.playFromStart();
  }

}
