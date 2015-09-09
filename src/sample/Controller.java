package sample;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private TextField screen;
    private double currentNumber;
    private String operation;

    public void handleDigitAction(ActionEvent event) {
        String oldText = screen.getText();
        String newText = ((Button) event.getSource()).getText();
        String displayText = oldText + newText;
        screen.setText(displayText);

    }

    public void handleKeyPressedAction(KeyEvent keyEvent) {
        String oldText = screen.getText();
        String newText = keyEvent.getCharacter();
        String displayText = oldText + newText;
        screen.setText(displayText);
    }


    public void handleDotAction(ActionEvent event) {
        String getText = screen.getText();
        String getDot = ((Button) event.getSource()).getText();
        String displayText = getText+getDot;
        screen.setText(displayText);
    }
    public void handleZeroAction(ActionEvent event) {
        screen.setText("0");
    }

    public void handleNumberSignAction(ActionEvent event) {
        String getDisplay = screen.getText();
        double changeSign = Double.parseDouble(getDisplay);
        changeSign *= -1;
        screen.setText("" + changeSign);
    }

    public void handleClearAction() {
        screen.clear();
    }

    public void handleDeleteAction() {
        String getText = screen.getText();
        screen.setText(removeLastChar(getText));
    }

    public void handleOperation(ActionEvent event) {
        String display = screen.getText();
        currentNumber = Double.parseDouble(display);
        screen.setText("");
        operation = ((Button)event.getSource()).getText();
    }

    public void handlePressedOperation(KeyEvent key) {

        String display = screen.getText();
        currentNumber = Double.parseDouble(display);
        screen.setText("");
        operation = key.getCharacter();
    }

    public void handleEqualOperation(ActionEvent event) {
        String display = screen.getText();
        double newNumber = Double.parseDouble(display);
        double answer = 0;
        switch (operation) {
            case "+":
                answer = currentNumber + newNumber;
                break;
            case "-":
                answer = currentNumber - newNumber;
                break;
            case "*":
                answer = currentNumber * newNumber;
                break;
            case "%":
                try {
                    answer = currentNumber / newNumber;
                    if(newNumber == Double.POSITIVE_INFINITY ||
                            newNumber == Double.NEGATIVE_INFINITY ||
                            newNumber == Double.NaN)
                        throw new ArithmeticException();
                } catch (ArithmeticException e) {
                    screen.setText("Infinity");
                }
                break;
        }
        screen.setText("" + answer);
    }

    public void handleAboutClick() {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About Me");
        aboutAlert.setHeaderText("Alaa Al-Shammaa"+"\n"+"Student of Software Engineering at Yarmouk Private University");
        aboutAlert.setContentText("Student. Entrepreneur. Dreamer. Maker.");
        /*
          Clicking on the icon to open a github page isn't working (yet)..
        */
//        Image githubIcon = new Image("https://goo.gl/y1XD5e");
//        ImageView gbIco = new ImageView(githubIcon);
//        gbIco.setFitWidth(100);
//        gbIco.setFitHeight(100);
//        gbIco.setPreserveRatio(true);
//        gbIco.setSmooth(true);
//        gbIco.setCache(true);
//
//        gbIco.setOnMouseClicked(e -> {
//                    openWebpage("https://github.com/AlaaAlShammaa");
//
//                }
//        );
//        aboutAlert.setGraphic(gbIco);
        aboutAlert.initModality(Modality.NONE);
        aboutAlert.show();
    }

    public void handleReportABugClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Report A Bug");
        dialog.setHeaderText("I will try to fix it ASAP. Thx for reporting.");
        dialog.setContentText("Please write your problem: ");
        Optional<String> result = dialog.showAndWait();
        // This is an example of how to handle reports
        //it's better to be linked to a database
        result.ifPresent(problem -> System.out.println("Problem: " + problem));
    }

    public static void openWebpage(String url) {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(url);
    }

    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
}