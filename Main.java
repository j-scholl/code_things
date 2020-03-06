package sample;
                                                            // im using java 11.0.5, and im using libraries from a javafx 11.0.2 SDK
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    AtomicInteger turns = new AtomicInteger();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));   // enables use of 'root' for GUI
        primaryStage.setTitle("MP1");                                                 // Sets the title of the GUI page
        primaryStage.setScene(new Scene(root, 350, 300));               // Sets the dimensions for the GUI page
        primaryStage.show();                                                          // Now that the GUI page has been made, this line displays it to the user


        // Button instantiations --
        Button randomButton = (Button) root.lookup("#randomButton");

        // Textfield instantiations --
        TextField secretValueTextField = (TextField) root.lookup("#secretValueTextField");
        TextField guessTextField = (TextField) root.lookup("#guessTextField");

        // Label instantiations --
        Label correctGuessLabel = (Label) root.lookup("#correctGuessLabel");
        Label bigMooLabel = (Label) root.lookup("#bigMooLabel");
        Label littleMooLabel = (Label) root.lookup("#littleMooLabel");
        Label laurieMOOLabel = (Label) root.lookup(("#laurieMOOLabel"));

        // Other instantiations --
        RandomMooValue mv = new RandomMooValue();



        // Button action events
        randomButton.setOnAction(event -> {
            mv.setSecretValue();
            secretValueTextField.setText("");
        });


        // Textfield action events
        secretValueTextField.setOnAction(event -> {
            if (!mv.setSecretValue(Integer.parseInt(secretValueTextField.getText()))){
                laurieMOOLabel.setText("try again");
            }
            else {
                mv.setSecretValue(Integer.parseInt(secretValueTextField.getText()));
            }
            secretValueTextField.setText("");
        });

        guessTextField.setOnAction(event ->
        {
            if (mv.isCorrectGuess(Integer.parseInt(guessTextField.getText()))) {
                correctGuessLabel.setText("YES!");
                laurieMOOLabel.setText("LaurieMOO!!!!!");
                turns.set(0);
                secretValueTextField.setText(String.valueOf(RandomMooValue.getSecretValue()));
            }
            else {
                correctGuessLabel.setText("no....");
                turns.getAndIncrement();
                if (turns.get() > 9){
                    laurieMOOLabel.setText("Boo hoo -- no LaurieMOO.");
                    turns.set(0);
                    secretValueTextField.setText(String.valueOf(RandomMooValue.getSecretValue()));
                }
            }
            littleMooLabel.setText(String.valueOf(mv.getLittleMooCount(Integer.parseInt(guessTextField.getText()))));
            bigMooLabel.setText(String.valueOf(mv.getBigMooCount(Integer.parseInt(guessTextField.getText()))));
        });
    }

    public static void main(String[] args) {
        launch(args);

    }
}

