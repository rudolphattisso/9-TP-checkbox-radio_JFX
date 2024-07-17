package fr.afpa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    private static Logger logger = LogManager.getLogger(App.class);

    @Override
    public void start(Stage stage) {

        stage.setTitle("Sliders - CheckBox - RadioBox");

        // // ICON
        // Image icon = new Image(getClass().getResourceAsStream("colors.png"));
        // // Add icon in stage
        // stage.getIcons().add(icon);

        // creation des objets
        TextField textField1 = new TextField();
        textField1.setEditable(true);
        textField1.getText();// recupère le texte dans le champs

        Label saisirLabel = new Label("Saississez votre texte");
        Label resultLabel = new Label(); 

        // Mettre à jour le texte du label avec le nouveau texte du TextField
        textField1.textProperty().addListener((observable,
                oldValue, newValue) -> {

            resultLabel.setText(newValue);
        });

        // Les construction des des menus déroulants avec TitlePan,

        // CheckBox,RadioButton,Slider
        CheckBox colorBack = new CheckBox("backgroung");
        CheckBox colorChar = new CheckBox("char color");
        CheckBox majMinChar = new CheckBox("Case");

        VBox vBoxCheckBox = new VBox(colorBack, colorChar, majMinChar);
        TitledPane toolbox = new TitledPane("Parametres", vBoxCheckBox);
        toolbox.setCollapsible(true);
        toolbox.setDisable(true);

        // titlepane BAckGround
        ToggleGroup toggleGroup = new ToggleGroup();// permet de selectionner un seul radiobox à la fois
        RadioButton redRadio = new RadioButton("red");
        redRadio.setToggleGroup(toggleGroup);
        // redRadio.setStyle(STYLESHEET_CASPIAN);
        RadioButton greenRadio = new RadioButton("green");
        greenRadio.setToggleGroup(toggleGroup);
        RadioButton blueRadio = new RadioButton("blue");
        blueRadio.setToggleGroup(toggleGroup);
        VBox vBoxradio = new VBox(redRadio, greenRadio, blueRadio);
        TitledPane toolboxBackround = new TitledPane("Backround", vBoxradio);
        toolboxBackround.setCollapsible(true);

        toolboxBackround.setVisible(false); // Masquer le TitledPane backround par défaut
        colorBack.selectedProperty().addListener((observable, oldValue, newValue) -> {// lors de clique checkbox,la
                                                                                      // section Backround s'affiche
            toolboxBackround.setVisible(newValue);
        });

        // Title ColorSliders;  TODO:rajouter des noms aux differnets sliders 
        Slider redSlider = new Slider(0, 255, 127.5);
        Slider greenSlider = new Slider(0, 255, 127.5);
        Slider blueSlider = new Slider(0, 255, 127.5);
        Label label1 = new Label("red");
        Label label2 = new Label("green");
        Label label3 = new Label("blue");
        VBox sliderBox = new VBox(label1,redSlider,label2,greenSlider,label3 , blueSlider);
        TitledPane toolboxSliders = new TitledPane("CharColor", sliderBox);
        toolboxSliders.setCollapsible(true);

        toolboxSliders.setVisible(false); // Masquer le TitledPane par défaut

        colorChar.selectedProperty().addListener((observable, oldValue, newValue) -> {// lors de clique checkbox,la
                                                                                      // section Backround s'affiche
            toolboxSliders.setVisible(newValue);
        });
        // valueProperty revoie un objet de la classe DoubleProperty;
        // addlistener compris dans la classe DoubleProperty est un écouteur d'évenement
        // qui permet,
        // elle attent un objet la l'interface ChangeListener qui sert à surveiller tous
        // chgts; ChangeListener est interface qui est normalement impossible à
        // instancier
        // création d'une classe interne anonyme(à usage unique) ;pour palier le fait
        // qu'on ne puisse instancier un interface.
        redSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                    Number oldValue, Number newValue) {
                logger.trace(newValue);

                Double redValue = redSlider.getValue();
                Double greenValue = greenSlider.getValue();
                Double blueValue = blueSlider.getValue();

                resultLabel.setStyle("-fx-text-fill: rgb(" + redValue.intValue() + "," + greenValue.intValue() + ","
                        + blueValue.intValue() + ")");
            }
        });
        greenSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                    Number oldValue, Number newValue) {
                logger.trace(newValue);

                Double redValue = redSlider.getValue();
                Double greenValue = greenSlider.getValue();
                Double blueValue = blueSlider.getValue();

                resultLabel.setStyle("-fx-text-fill: rgb(" + redValue.intValue() + "," + greenValue.intValue() + ","
                        + blueValue.intValue() + ")");
            }
        });

        blueSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                    Number oldValue, Number newValue) {
                logger.trace(newValue);

                Double redValue = redSlider.getValue();
                Double greenValue = greenSlider.getValue();
                Double blueValue = blueSlider.getValue();

                resultLabel.setStyle("-fx-text-fill: rgb(" + redValue.intValue() + "," + greenValue.intValue() + ","
                        + blueValue.intValue() + ")");
            }
        });

        // TitlePane Radio Upper-LowerCase
        ToggleGroup toggleGroup2 = new ToggleGroup();// permet de selectionner un seul radiobox
        RadioButton majRadio = new RadioButton("UpperCase");
        majRadio.setToggleGroup(toggleGroup2);
        RadioButton minRadio = new RadioButton("Lowercase");
        minRadio.setToggleGroup(toggleGroup2);
        VBox radioBox2 = new VBox(majRadio, minRadio);
        TitledPane toolboxCases = new TitledPane("Case", radioBox2);
        toolboxCases.setCollapsible(true);

        toolboxCases.setVisible(false); // Masquer le TitledPane par défaut

        majMinChar.selectedProperty().addListener((observable, oldValue, newValue) -> {// lors de clique checkbox,la
                                                                                       // section Backround s'affiche
            toolboxCases.setVisible(newValue);
        });

        // Affctation des action aux Radioboutons et sliders
        // RadioButtons for Backround
        redRadio.setOnAction(e -> resultLabel.setStyle("-fx-background-color: red;"));
        greenRadio.setOnAction(e -> resultLabel.setStyle("-fx-background-color: green;"));
        blueRadio.setOnAction(e -> resultLabel.setStyle("-fx-background-color: blue;"));

        // Slider caratère color;

        // RadoButtons for Cases
        majRadio.setOnAction(e -> resultLabel.setText(resultLabel.getText().toUpperCase()));
        minRadio.setOnAction(e -> resultLabel.setText(resultLabel.getText().toLowerCase()));

        // COnteneurs de menus déroulants du bas
        HBox hBoxToolbox = new HBox(toolboxBackround, toolboxSliders, toolboxCases);

        textField1.textProperty().addListener((observable, oldValue, newValue) -> {
            // Rendre le TitledPane accessible dès qu'un caractère est entré
            if (!newValue.isEmpty()) {
                toolbox.setDisable(false);
            } else {
                toolbox.setDisable(true);
            }
        });

        hBoxToolbox.setSpacing(5);
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(saisirLabel, 0, 0);
        grid.add(textField1, 0, 1);
        grid.add(resultLabel, 0, 3);
        grid.add(toolbox, 4, 1, 1, 2);
        grid.add(hBoxToolbox, 0, 5, 1, 5);

        var scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }

}