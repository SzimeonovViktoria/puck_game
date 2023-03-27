package puckgame.javafx.main;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

/**
 * The class that controls the application GUI.
 */
@Slf4j
public class PuckGameApp extends Application {

    private GuiceContext context = new GuiceContext(this, () -> List.of(
            new AbstractModule() {

            }
    ));

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("Starting application...");
        context.init();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"));
        primaryStage.setTitle("Viki játék");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
