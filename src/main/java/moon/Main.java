package moon;

import java.io.IOException;
import java.net.URL;

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
            URL fxml = Main.class.getResource("/view/MainWindow.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxml);

            AnchorPane root = fxmlLoader.load();
            MainWindow controller = fxmlLoader.getController();
            controller.setMoon(moon); // inject the Moon instance

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Moon");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.show();

            controller.showGreeting();
            controller.showInitialStorage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
