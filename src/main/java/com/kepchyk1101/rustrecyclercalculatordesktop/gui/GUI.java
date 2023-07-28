package com.kepchyk1101.rustrecyclercalculatordesktop.gui;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.Objects.ComponentArray;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.Objects.Result;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.RustItems.Components;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.RustItems.Resources;
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
import java.util.NoSuchElementException;
import java.util.Optional;

public class GUI extends Application {

    private static final String PROJECT_NAME = "Rust Recycler Calculator | v1.0 | by Kepchyk1101";
    private static final String fontName = "Franklin Gothic Medium";
    private static final double imagesScale = 0.35;
    private static final int firstLine_Y = 21;
    private static final int secondLine_Y = 97;
    private static final int thirdLine_Y = 175;

    private static ArrayList<Text> texts_components = new ArrayList<>();
    private static ArrayList<Text> texts_resources = new ArrayList<>();

    private static Text scrap_txt, metal_txt, HQM_txt, cloth_txt, rope_txt, techparts_txt;

    private static Pane globalPane;
    private static Background pane_background;
    private static Text logo_txt, input_txt, output_txt, control_txt, warning_txt;
    private static Button calculate_btn, reset_btn;
    private static CheckBox isFullRecycle_cbox;
    private static ImageView output_5_block, output_6_block;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        preload();

        globalPane = new Pane();

        int addedIcons = 0;
        int icon_X = -35;
        for (Components component : Components.values()) {
            if (addedIcons < 6)
                globalPane.getChildren().add(addImage(component.getImage(), icon_X, firstLine_Y));
            else if (addedIcons > 11)
                globalPane.getChildren().add(addImage(component.getImage(), icon_X, thirdLine_Y));
            else if (addedIcons > 5)
                globalPane.getChildren().add(addImage(component.getImage(), icon_X, secondLine_Y));
            icon_X += 79;
            addedIcons++;
            if (addedIcons == 6 || addedIcons == 12)
                icon_X = -35;
        }

        icon_X = -35;
        for (Resources resources : Resources.values()) {
            globalPane.getChildren().add(addImage(resources.getImage(), icon_X, 295));
            icon_X += 79;
        }

        VeryVeryBadCode();
        SomeBadCode();

        globalPane.setBackground(pane_background);
        globalPane.getChildren().addAll(
                logo_txt, input_txt, output_txt, control_txt, warning_txt, // Texts
                calculate_btn, reset_btn, // Buttons
                isFullRecycle_cbox // CheckBoxes
        );

        Scene scene = new Scene(globalPane, 504, 608);

        stage.setTitle(PROJECT_NAME);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(utils.getImageFromResources("recycler.jpg"));
        stage.show();
    }

    private static void componentAmount_input(Text text) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle(PROJECT_NAME);
        textInputDialog.setHeaderText("Введите количество компонента:");
        try {
            Optional<String> amount = textInputDialog.showAndWait();
            String amountString = amount.get();
            if (utils.isDigit(amountString)) {
                int amountInt = Integer.parseInt(amountString);
                if (amountInt < 100000 && amountInt > -1)
                    setComponentAmount_txt(text, Integer.parseInt(amountString));
                else
                    errorAlert("Число не может быть более 99,999 или меньше 0 !", "Ошибка");
            } else
                errorAlert("Введённый текст не является числом/цифрой !", "Ошибка");
        } catch (NoSuchElementException ignored) {}
    }

    private static void errorAlert(String content, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(PROJECT_NAME);
        alert.show();
    }

    private static ImageView addImage(String path, double X, double Y) {
        Image image = utils.getImageFromResources(path);
        ImageView imagev = new ImageView(image);
        imagev.setScaleX(imagesScale);
        imagev.setScaleY(imagesScale);
        imagev.setScaleZ(imagesScale);
        imagev.setX(X);
        imagev.setY(Y);
        return imagev;
    }

    private static ImageView addImage(Image image, double X, double Y) {
        ImageView imagev = new ImageView(image);
        imagev.setScaleX(imagesScale);
        imagev.setScaleY(imagesScale);
        imagev.setScaleZ(imagesScale);
        imagev.setX(X);
        imagev.setY(Y);
        return imagev;
    }

    private static void setComponentAmount_txt(Text text, int newAmount) {
        text.setText("x" + newAmount);
    }

    private static void preload() {

        InputStream inputStream = GUI.class.getResourceAsStream("/fonts/Rust.ttf");
        output_5_block = addImage("block.png", 281, 295);
        output_6_block = addImage("block.png", 360, 295);

        pane_background = setBackgroundProperties("background.png");


        logo_txt = setTextProperties("Rust Recycler Calculator",
                Font.loadFont(inputStream, 17), Color.WHITE, 120, 30);
        input_txt = setTextProperties("вход", Font.font(fontName, 16),
                Color.web("#bdb4ac"), 20, 58);
        output_txt = setTextProperties("выход", Font.font(fontName, 16),
                Color.web("#bdb4ac"), 20, 330);
        control_txt = setTextProperties("управление", Font.font(fontName, 16),
                Color.web("#bdb4ac"), 20, 453);
        warning_txt = setTextProperties("Для того, чтобы изменить количество предмета - нажмите на его иконку !",
                Font.font(fontName, 13), Color.ORANGERED, 20, 589);


        calculate_btn = setButtonProperties("Посчитать", Font.font(fontName, 20),
                Color.web("#9bb46e"), 221, 67, 22, 475, true);
        calculate_btn.setOnAction(event -> calculate_event());

        reset_btn = setButtonProperties("Сбросить", Font.font(fontName, 20),
                Color.web("#9bb46e"), 221, 67, 262, 475, true);
        reset_btn.setOnAction(event -> {
            reset_components_event();
            reset_resources_event();
        });


        isFullRecycle_cbox = setCheckBoxProperties("Включить полную переработку?", Font.font(fontName, 13),
                Color.web("#bdb4ac"), 20, 550);
        isFullRecycle_cbox.setOnAction(actionEvent -> {
            if (isFullRecycle_cbox.isSelected())
                globalPane.getChildren().addAll(output_5_block, output_6_block);
            else
                globalPane.getChildren().removeAll(output_5_block, output_6_block);
        });

    }

    private static Background setBackgroundProperties(String iconName) {
        Image image = utils.getImageFromResources(iconName);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }

    private static Text setTextProperties(String Text, Font font, Color color, double X, double Y) {
        Text text = new Text();
        if (text != null) text.setText(Text);
        if (font != null) text.setFont(font);
        if (color != null) text.setFill(color);
        text.setX(X);
        text.setY(Y);
        return text;
    }

    private static Button setButtonProperties(String text, Font font, Color color,
                                              double width, double height, double X, double Y,
                                              boolean isUseCSS) {
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

    private static CheckBox setCheckBoxProperties(String text, Font font, Color color,
                                                  double X, double Y) {
        CheckBox checkBox = new CheckBox(text);
        checkBox.setFont(font);
        checkBox.setTextFill(color);
        checkBox.setLayoutX(X);
        checkBox.setLayoutY(Y);
        return checkBox;
    }

    private static void reset_components_event() {
        for (Text text : texts_components)
            setComponentAmount_txt(text, 0);
    }
    private static void reset_resources_event() {
        for (Text text : texts_resources)
            setComponentAmount_txt(text, 0);
    }

    private static void calculate_event() {
        ComponentArray[] componentsArray = {
                new ComponentArray(Components.Electric_Fuse, Integer.parseInt(texts_components.get(0).getText().split("x")[1])),
                new ComponentArray(Components.Metal_Blade, Integer.parseInt(texts_components.get(1).getText().split("x")[1])),
                new ComponentArray(Components.SMG_Body, Integer.parseInt(texts_components.get(2).getText().split("x")[1])),
                new ComponentArray(Components.Tech_Trash, Integer.parseInt(texts_components.get(3).getText().split("x")[1])),
                new ComponentArray(Components.Empty_Propane_Tank, Integer.parseInt(texts_components.get(4).getText().split("x")[1])),
                new ComponentArray(Components.Gears, Integer.parseInt(texts_components.get(5).getText().split("x")[1])),
                new ComponentArray(Components.Metal_Pipe, Integer.parseInt(texts_components.get(6).getText().split("x")[1])),
                new ComponentArray(Components.Rifle_Body, Integer.parseInt(texts_components.get(7).getText().split("x")[1])),
                new ComponentArray(Components.Semi_Automatic_Body, Integer.parseInt(texts_components.get(8).getText().split("x")[1])),
                new ComponentArray(Components.Metal_Spring, Integer.parseInt(texts_components.get(9).getText().split("x")[1])),
                new ComponentArray(Components.Road_Signs, Integer.parseInt(texts_components.get(10).getText().split("x")[1])),
                new ComponentArray(Components.Sewing_Kit, Integer.parseInt(texts_components.get(11).getText().split("x")[1])),
                new ComponentArray(Components.Tarp, Integer.parseInt(texts_components.get(12).getText().split("x")[1])),
                new ComponentArray(Components.Rope, Integer.parseInt(texts_components.get(13).getText().split("x")[1])),
                new ComponentArray(Components.SheetMetal, Integer.parseInt(texts_components.get(14).getText().split("x")[1])),
                new ComponentArray(Components.Targeting_Computer, Integer.parseInt(texts_components.get(15).getText().split("x")[1])),
                new ComponentArray(Components.CCTV_Camera, Integer.parseInt(texts_components.get(16).getText().split("x")[1]))
        };
        ArrayList<Result> result = RustRecyclerCalculator.Calculate(componentsArray, isFullRecycle_cbox.isSelected());
        reset_resources_event();

        for (Result res : result) {
            switch (res.getResource()) {
                case Scrap -> setComponentAmount_txt(scrap_txt, res.getAmount());
                case Metal_Fragments -> setComponentAmount_txt(metal_txt, res.getAmount());
                case High_Quality_Metal -> setComponentAmount_txt(HQM_txt, res.getAmount());
                case Cloth -> setComponentAmount_txt(cloth_txt, res.getAmount());
                case Tech_Trash -> setComponentAmount_txt(techparts_txt, res.getAmount());
                case Rope -> setComponentAmount_txt(rope_txt, res.getAmount());
            }
        }
    }

    private static void VeryVeryBadCode() {
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
        Text text1 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 21, fistLineTXT_Y);
        Text text2 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 100, fistLineTXT_Y);
        Text text3 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 179, fistLineTXT_Y);
        Text text4 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 258, fistLineTXT_Y);
        Text text5 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 337, fistLineTXT_Y);
        Text text6 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 416, fistLineTXT_Y);
        Text text7 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 21, secondLineTXT_Y);
        Text text8 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 100, secondLineTXT_Y);
        Text text9 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 179, secondLineTXT_Y);
        Text text10 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 258, secondLineTXT_Y);
        Text text11 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 337, secondLineTXT_Y);
        Text text12 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 416, secondLineTXT_Y);
        Text text13 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 21, thirdLineTXT_Y);
        Text text14 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 100, thirdLineTXT_Y);
        Text text15 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 179, thirdLineTXT_Y);
        Text text16 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 258, thirdLineTXT_Y);
        Text text17 = setTextProperties("x0", Font.font(fontName, 14.5), Color.WHITESMOKE, 337, thirdLineTXT_Y);

        texts_components.add(text1);
        texts_components.add(text2);
        texts_components.add(text3);
        texts_components.add(text4);
        texts_components.add(text5);
        texts_components.add(text6);
        texts_components.add(text7);
        texts_components.add(text8);
        texts_components.add(text9);
        texts_components.add(text10);
        texts_components.add(text11);
        texts_components.add(text12);
        texts_components.add(text13);
        texts_components.add(text14);
        texts_components.add(text15);
        texts_components.add(text16);
        texts_components.add(text17);

        button1.setOnMouseClicked(mouseEvent -> componentAmount_input(text1));
        button2.setOnMouseClicked(mouseEvent -> componentAmount_input(text2));
        button3.setOnMouseClicked(mouseEvent -> componentAmount_input(text3));
        button4.setOnMouseClicked(mouseEvent -> componentAmount_input(text4));
        button5.setOnMouseClicked(mouseEvent -> componentAmount_input(text5));
        button6.setOnMouseClicked(mouseEvent -> componentAmount_input(text6));
        button7.setOnMouseClicked(mouseEvent -> componentAmount_input(text7));
        button8.setOnMouseClicked(mouseEvent -> componentAmount_input(text8));
        button9.setOnMouseClicked(mouseEvent -> componentAmount_input(text9));
        button10.setOnMouseClicked(mouseEvent -> componentAmount_input(text10));
        button11.setOnMouseClicked(mouseEvent -> componentAmount_input(text11));
        button12.setOnMouseClicked(mouseEvent -> componentAmount_input(text12));
        button13.setOnMouseClicked(mouseEvent -> componentAmount_input(text13));
        button14.setOnMouseClicked(mouseEvent -> componentAmount_input(text14));
        button15.setOnMouseClicked(mouseEvent -> componentAmount_input(text15));
        button16.setOnMouseClicked(mouseEvent -> componentAmount_input(text16));
        button17.setOnMouseClicked(mouseEvent -> componentAmount_input(text17));

        globalPane.getChildren().addAll(
                button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15, button16, button17,
                text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15, text16, text17);
    }

    private static void SomeBadCode() {
        int lineResources_Y = 419;
        int scrap_amount = 0;
        int metal_amount = 0;
        int HQM_amount = 0;
        int cloth_amount = 0;
        int techparts_amount = 0;
        int rope_amount = 0;
        scrap_txt = setTextProperties("x" + scrap_amount, Font.font(fontName, 14.5), Color.WHITESMOKE, 21, lineResources_Y);
        metal_txt = setTextProperties("x" + metal_amount, Font.font(fontName, 14.5), Color.WHITESMOKE, 100, lineResources_Y);
        HQM_txt = setTextProperties("x" + HQM_amount, Font.font(fontName, 14.5), Color.WHITESMOKE, 179, lineResources_Y);
        cloth_txt = setTextProperties("x" + cloth_amount, Font.font(fontName, 14.5), Color.WHITESMOKE, 258, lineResources_Y);
        techparts_txt = setTextProperties("x" + techparts_amount, Font.font(fontName, 14.5), Color.WHITESMOKE, 337, lineResources_Y);
        rope_txt = setTextProperties("x" + rope_amount, Font.font(fontName, 14.5), Color.WHITESMOKE, 416, lineResources_Y);

        texts_resources.add(scrap_txt);
        texts_resources.add(metal_txt);
        texts_resources.add(HQM_txt);
        texts_resources.add(cloth_txt);
        texts_resources.add(techparts_txt);
        texts_resources.add(rope_txt);

        globalPane.getChildren().addAll(scrap_txt, metal_txt, HQM_txt, cloth_txt, techparts_txt, rope_txt);
    }

}