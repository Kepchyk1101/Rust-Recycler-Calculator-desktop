package com.kepchyk1101.rustrecyclercalculatordesktop.gui;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object.ComponentArray;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object.Result;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem.Components;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem.Resources;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.RustRecyclerCalculator;
import com.kepchyk1101.rustrecyclercalculatordesktop.utils.utils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class GUI extends Application {

    private static final String PROJECT_NAME = "Rust Recycler Calculator | v1.0.1 | by Kepchyk1101";
    private static final String FONT = "Franklin Gothic Medium";

    private static final double IMAGE_SCALE = 0.35;

    private static final ArrayList<Text> textComponents = new ArrayList<>();
    private static final ArrayList<Text> textResources = new ArrayList<>();

    private static Text scrapText, metalText, highQualityMetalText, clothText, ropeText, techPartsText;

    private static Pane globalPane;
    private static Background paneBackground;
    private static Text logoText, inputText, outputText, controlText, warningText;
    private static Button calculateButton, resetButton;
    private static CheckBox isFullRecycleCheckBox;
    private static ImageView output5BlockIcon, output6BlockIcon;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        preload();

        globalPane = new Pane();

        int firstLineY = 21;
        int secondLineY = 97;
        int thirdLineY = 175;
        int addedIcons = 0;
        int iconX = -35;
        for (Components component : Components.values()) {
            if (addedIcons < 6)
                globalPane.getChildren().add(addImage(component.getImage(), iconX, firstLineY));
            else if (addedIcons > 11)
                globalPane.getChildren().add(addImage(component.getImage(), iconX, thirdLineY));
            else if (addedIcons > 5)
                globalPane.getChildren().add(addImage(component.getImage(), iconX, secondLineY));
            iconX += 79;
            addedIcons++;
            if (addedIcons == 6 || addedIcons == 12)
                iconX = -35;
        }

        iconX = -35;
        for (Resources resources : Resources.values()) {
            globalPane.getChildren().add(addImage(resources.getImage(), iconX, 295));
            iconX += 79;
        }

        veryVeryBadCode();
        someBadCode();

        globalPane.setBackground(paneBackground);
        globalPane.getChildren().addAll(
                logoText, inputText, outputText, controlText, warningText, // Texts
                calculateButton, resetButton, // Buttons
                isFullRecycleCheckBox // CheckBoxes
        );

        Scene scene = new Scene(globalPane, 504, 608);

        stage.setTitle(PROJECT_NAME);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(utils.getImageFromResources("recycler.jpg"));
        stage.show();
    }

    private void componentAmountInput(Text text) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle(PROJECT_NAME);
        textInputDialog.setHeaderText("Введите количество компонента:");
        try {
            Optional<String> amount = textInputDialog.showAndWait();
            String amountString = amount.get();
            if (utils.isNumeric(amountString)) {
                int amountInt = Integer.parseInt(amountString);
                if (amountInt < 100000 && amountInt > -1)
                    setComponentAmountText(text, Integer.parseInt(amountString));
                else
                    openAlertDialog(Alert.AlertType.ERROR, "Число не может быть более 99,999 или меньше 0 !", "Ошибка");
            } else
                openAlertDialog(Alert.AlertType.ERROR, "Введённый текст не является числом/цифрой !", "Ошибка");
        } catch (NoSuchElementException ignored) {}
    }

    private void openAlertDialog(Alert.AlertType alertType, String content, String header) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(PROJECT_NAME);
        alert.show();
    }

    private ImageView addImage(String path, double X, double Y) {
        Image image = utils.getImageFromResources(path);
        ImageView imagev = new ImageView(image);
        imagev.setScaleX(IMAGE_SCALE);
        imagev.setScaleY(IMAGE_SCALE);
        imagev.setScaleZ(IMAGE_SCALE);
        imagev.setX(X);
        imagev.setY(Y);
        return imagev;
    }

    private ImageView addImage(Image image, double X, double Y) {
        ImageView imagev = new ImageView(image);
        imagev.setScaleX(IMAGE_SCALE);
        imagev.setScaleY(IMAGE_SCALE);
        imagev.setScaleZ(IMAGE_SCALE);
        imagev.setX(X);
        imagev.setY(Y);
        return imagev;
    }

    private void setComponentAmountText(Text text, int newAmount) {
        text.setText("x" + newAmount);
    }

    private void preload() {

        InputStream inputStream = GUI.class.getResourceAsStream("/fonts/Rust.ttf");
        output5BlockIcon = addImage("block.png", 281, 295);
        output6BlockIcon = addImage("block.png", 360, 295);

        paneBackground = setBackgroundProperties("background.png");


        logoText = setTextProperties("Rust Recycler Calculator",
                Font.loadFont(inputStream, 17), Color.WHITE, 120, 30);
        inputText = setTextProperties("вход", Font.font(FONT, 16),
                Color.web("#bdb4ac"), 20, 58);
        outputText = setTextProperties("выход", Font.font(FONT, 16),
                Color.web("#bdb4ac"), 20, 330);
        controlText = setTextProperties("управление", Font.font(FONT, 16),
                Color.web("#bdb4ac"), 20, 453);
        warningText = setTextProperties("Для того, чтобы изменить количество предмета - нажмите на его иконку !",
                Font.font(FONT, 13), Color.ORANGERED, 20, 589);


        calculateButton = setButtonProperties("Посчитать", Font.font(FONT, 20),
                Color.web("#9bb46e"), 221, 67, 22, 475, true);
        calculateButton.setOnAction(event -> calculateEvent());

        resetButton = setButtonProperties("Сбросить", Font.font(FONT, 20),
                Color.web("#9bb46e"), 221, 67, 262, 475, true);
        resetButton.setOnAction(event -> {
            resetComponentsEvent();
            resetResourcesEvent();
        });


        isFullRecycleCheckBox = setCheckBoxProperties("Включить полную переработку?", Font.font(FONT, 13),
                Color.web("#bdb4ac"), 20, 550);
        isFullRecycleCheckBox.setOnAction(actionEvent -> {
            if (isFullRecycleCheckBox.isSelected())
                globalPane.getChildren().addAll(output5BlockIcon, output6BlockIcon);
            else
                globalPane.getChildren().removeAll(output5BlockIcon, output6BlockIcon);
        });

    }

    private Background setBackgroundProperties(String iconName) {
        Image image = utils.getImageFromResources(iconName);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }

    private Text setTextProperties(String text, Font font, Color color, double X, double Y) {
        Text textElement = new Text();
        if (text != null) textElement.setText(text);
        if (font != null) textElement.setFont(font);
        if (color != null) textElement.setFill(color);
        textElement.setX(X);
        textElement.setY(Y);
        return textElement;
    }

    private Button setButtonProperties(String text, Font font, Color color, double width, double height, double X, double Y, boolean isUseCSS) {
        Button button = new Button();
        if (text != null) button.setText(text);
        if (font != null) button.setFont(font);
        if (color != null) button.setTextFill(color);
        button.setPrefSize(width, height);
        button.setLayoutX(X);
        button.setLayoutY(Y);
        if (isUseCSS) button.getStylesheets().add("style.css");
        return button;
    }

    private CheckBox setCheckBoxProperties(String text, Font font, Color color, double X, double Y) {
        CheckBox checkBox = new CheckBox(text);
        checkBox.setFont(font);
        checkBox.setTextFill(color);
        checkBox.setLayoutX(X);
        checkBox.setLayoutY(Y);
        return checkBox;
    }

    private void resetComponentsEvent() {
        for (Text text : textComponents)
            setComponentAmountText(text, 0);
    }
    private void resetResourcesEvent() {
        for (Text text : textResources)
            setComponentAmountText(text, 0);
    }

    private void calculateEvent() {
        ComponentArray[] componentsArray = {
                new ComponentArray(Components.ELECTRIC_FUSE, Integer.parseInt(textComponents.get(0).getText().split("x")[1])),
                new ComponentArray(Components.METAL_BLADE, Integer.parseInt(textComponents.get(1).getText().split("x")[1])),
                new ComponentArray(Components.SMG_BODY, Integer.parseInt(textComponents.get(2).getText().split("x")[1])),
                new ComponentArray(Components.TECH_TRASH, Integer.parseInt(textComponents.get(3).getText().split("x")[1])),
                new ComponentArray(Components.EMPTY_PROPANE_TANK, Integer.parseInt(textComponents.get(4).getText().split("x")[1])),
                new ComponentArray(Components.GEARS, Integer.parseInt(textComponents.get(5).getText().split("x")[1])),
                new ComponentArray(Components.METAL_PIPE, Integer.parseInt(textComponents.get(6).getText().split("x")[1])),
                new ComponentArray(Components.RIFLE_BODY, Integer.parseInt(textComponents.get(7).getText().split("x")[1])),
                new ComponentArray(Components.SEMI_AUTOMATIC_BODY, Integer.parseInt(textComponents.get(8).getText().split("x")[1])),
                new ComponentArray(Components.METAL_SPRING, Integer.parseInt(textComponents.get(9).getText().split("x")[1])),
                new ComponentArray(Components.ROAD_SIGNS, Integer.parseInt(textComponents.get(10).getText().split("x")[1])),
                new ComponentArray(Components.SEWING_KIT, Integer.parseInt(textComponents.get(11).getText().split("x")[1])),
                new ComponentArray(Components.TARP, Integer.parseInt(textComponents.get(12).getText().split("x")[1])),
                new ComponentArray(Components.ROPE, Integer.parseInt(textComponents.get(13).getText().split("x")[1])),
                new ComponentArray(Components.SHEET_METAL, Integer.parseInt(textComponents.get(14).getText().split("x")[1])),
                new ComponentArray(Components.TARGETING_COMPUTER, Integer.parseInt(textComponents.get(15).getText().split("x")[1])),
                new ComponentArray(Components.CCTV_CAMERA, Integer.parseInt(textComponents.get(16).getText().split("x")[1]))
        };

        RustRecyclerCalculator rrc = new RustRecyclerCalculator();
        Map<Resources, Result> outputResult = rrc.calculate(componentsArray, isFullRecycleCheckBox.isSelected());
        resetResourcesEvent();

        for (Resources resource : outputResult.keySet()) {
            Result result = outputResult.get(resource);
            switch (result.getResource()) {
                case SCRAP -> setComponentAmountText(scrapText, result.getAmount());
                case METAL_FRAGMENTS -> setComponentAmountText(metalText, result.getAmount());
                case HIGH_QUALITY_METAL -> setComponentAmountText(highQualityMetalText, result.getAmount());
                case CLOTH -> setComponentAmountText(clothText, result.getAmount());
                case TECH_TRASH -> setComponentAmountText(techPartsText, result.getAmount());
                case ROPE -> setComponentAmountText(ropeText, result.getAmount());
            }
        }

    }

    private void veryVeryBadCode() {
        int fistLineBTN_Y = 77;
        int secondLineBTN_Y = 153;
        int thirdLineBTN_Y = 231;
        int fistLineTXT_Y = 143;
        int secondLineTXT_Y = 219;
        int thirdLineTXT_Y = 299;
        Button button1 = setButtonProperties(null, null, null, 72, 69, 19, fistLineBTN_Y, true);
        Button button2 = setButtonProperties(null, null, null, 72, 69, 98, fistLineBTN_Y, true);
        Button button3 = setButtonProperties(null, null, null, 72, 69, 177, fistLineBTN_Y, true);
        Button button4 = setButtonProperties(null, null, null, 72, 69, 256, fistLineBTN_Y, true);
        Button button5 = setButtonProperties(null, null, null, 72, 69, 335, fistLineBTN_Y, true);
        Button button6 = setButtonProperties(null, null, null, 72, 69, 414, fistLineBTN_Y, true);
        Button button7 = setButtonProperties(null, null, null, 72, 69, 19, secondLineBTN_Y, true);
        Button button8 = setButtonProperties(null, null, null, 72, 69, 98, secondLineBTN_Y, true);
        Button button9 = setButtonProperties(null, null, null, 72, 69, 177, secondLineBTN_Y, true);
        Button button10 = setButtonProperties(null, null, null, 72, 69, 256, secondLineBTN_Y, true);
        Button button11 = setButtonProperties(null, null, null, 72, 69, 335, secondLineBTN_Y, true);
        Button button12 = setButtonProperties(null, null, null, 72, 69, 414, secondLineBTN_Y, true);
        Button button13 = setButtonProperties(null, null, null, 72, 69, 19, thirdLineBTN_Y, true);
        Button button14 = setButtonProperties(null, null, null, 72, 69, 98, thirdLineBTN_Y, true);
        Button button15 = setButtonProperties(null, null, null, 72, 69, 177, thirdLineBTN_Y, true);
        Button button16 = setButtonProperties(null, null, null, 72, 69, 256, thirdLineBTN_Y, true);
        Button button17 = setButtonProperties(null, null, null, 72, 69, 335, thirdLineBTN_Y, true);
        Text text1 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 21, fistLineTXT_Y);
        Text text2 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 100, fistLineTXT_Y);
        Text text3 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 179, fistLineTXT_Y);
        Text text4 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 258, fistLineTXT_Y);
        Text text5 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 337, fistLineTXT_Y);
        Text text6 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 416, fistLineTXT_Y);
        Text text7 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 21, secondLineTXT_Y);
        Text text8 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 100, secondLineTXT_Y);
        Text text9 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 179, secondLineTXT_Y);
        Text text10 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 258, secondLineTXT_Y);
        Text text11 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 337, secondLineTXT_Y);
        Text text12 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 416, secondLineTXT_Y);
        Text text13 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 21, thirdLineTXT_Y);
        Text text14 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 100, thirdLineTXT_Y);
        Text text15 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 179, thirdLineTXT_Y);
        Text text16 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 258, thirdLineTXT_Y);
        Text text17 = setTextProperties("x0", Font.font(FONT, 14.5), Color.WHITESMOKE, 337, thirdLineTXT_Y);

        textComponents.add(text1);
        textComponents.add(text2);
        textComponents.add(text3);
        textComponents.add(text4);
        textComponents.add(text5);
        textComponents.add(text6);
        textComponents.add(text7);
        textComponents.add(text8);
        textComponents.add(text9);
        textComponents.add(text10);
        textComponents.add(text11);
        textComponents.add(text12);
        textComponents.add(text13);
        textComponents.add(text14);
        textComponents.add(text15);
        textComponents.add(text16);
        textComponents.add(text17);

        button1.setOnMouseClicked(mouseEvent -> componentAmountInput(text1));
        button2.setOnMouseClicked(mouseEvent -> componentAmountInput(text2));
        button3.setOnMouseClicked(mouseEvent -> componentAmountInput(text3));
        button4.setOnMouseClicked(mouseEvent -> componentAmountInput(text4));
        button5.setOnMouseClicked(mouseEvent -> componentAmountInput(text5));
        button6.setOnMouseClicked(mouseEvent -> componentAmountInput(text6));
        button7.setOnMouseClicked(mouseEvent -> componentAmountInput(text7));
        button8.setOnMouseClicked(mouseEvent -> componentAmountInput(text8));
        button9.setOnMouseClicked(mouseEvent -> componentAmountInput(text9));
        button10.setOnMouseClicked(mouseEvent -> componentAmountInput(text10));
        button11.setOnMouseClicked(mouseEvent -> componentAmountInput(text11));
        button12.setOnMouseClicked(mouseEvent -> componentAmountInput(text12));
        button13.setOnMouseClicked(mouseEvent -> componentAmountInput(text13));
        button14.setOnMouseClicked(mouseEvent -> componentAmountInput(text14));
        button15.setOnMouseClicked(mouseEvent -> componentAmountInput(text15));
        button16.setOnMouseClicked(mouseEvent -> componentAmountInput(text16));
        button17.setOnMouseClicked(mouseEvent -> componentAmountInput(text17));

        globalPane.getChildren().addAll(
                button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15, button16, button17,
                text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15, text16, text17);
    }

    private void someBadCode() {
        int lineResources_Y = 419;
        int scrap_amount = 0;
        int metal_amount = 0;
        int HQM_amount = 0;
        int cloth_amount = 0;
        int techparts_amount = 0;
        int rope_amount = 0;
        scrapText = setTextProperties("x" + scrap_amount, Font.font(FONT, 14.5), Color.WHITESMOKE, 21, lineResources_Y);
        metalText = setTextProperties("x" + metal_amount, Font.font(FONT, 14.5), Color.WHITESMOKE, 100, lineResources_Y);
        highQualityMetalText = setTextProperties("x" + HQM_amount, Font.font(FONT, 14.5), Color.WHITESMOKE, 179, lineResources_Y);
        clothText = setTextProperties("x" + cloth_amount, Font.font(FONT, 14.5), Color.WHITESMOKE, 258, lineResources_Y);
        techPartsText = setTextProperties("x" + techparts_amount, Font.font(FONT, 14.5), Color.WHITESMOKE, 337, lineResources_Y);
        ropeText = setTextProperties("x" + rope_amount, Font.font(FONT, 14.5), Color.WHITESMOKE, 416, lineResources_Y);

        textResources.add(scrapText);
        textResources.add(metalText);
        textResources.add(highQualityMetalText);
        textResources.add(clothText);
        textResources.add(techPartsText);
        textResources.add(ropeText);

        globalPane.getChildren().addAll(scrapText, metalText, highQualityMetalText, clothText, techPartsText, ropeText);
    }

}