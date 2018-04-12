package sample.util;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.Node;
import javafx.util.Duration;

public class Effects {
    private Effects instance;

    public Effects getInstance() {
        return instance;
    }

    public static void sceneAnimator(Node node, double time, Interpolator interpolator){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(time),node);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setInterpolator(interpolator);
        fadeTransition.play();
    }
}
