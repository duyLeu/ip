package moon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import moon.logic.Moon;
import moon.ui.MainWindow;

public class Main extends Application {
    private final Moon moon = new Moon();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // stage.setMaxWidth(417); // Add this if you didn't automatically resize elements

            fxmlLoader.<MainWindow>getController().setMoon(moon); // inject the Moon instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
