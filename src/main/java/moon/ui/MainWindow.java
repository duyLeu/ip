package moon.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import moon.logic.Moon;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Moon moon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image moonImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Moon instance */
    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    public void showGreeting(String text) {
        addMoonMessage(text);
        userInput.requestFocus();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Moon's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.moon.getResponse(input);
        addUserMessage(input);
        addMoonMessage(response);

        userInput.clear();
    }

    /**
     * Adds a message from the bot into the dialog container.
     */
    public void addMoonMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getMoonDialog(message, userImage)   // custom class with avatar/bubble
        );
    }

    /**
     * Adds a message from the user into the dialog container.
     */
    public void addUserMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(message, moonImage)
        );
    }
}

