package com.restaurant.views;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.MDL2IconCollection;
import jfxtras.styles.jmetro.MDL2IconFont;

import java.util.Optional;

public class _Dialog {

    private _Dialog() {
    }
    public static void showError(final String title, final String header, final String description) {
        showDialog(Alert.AlertType.ERROR, title, header, description);
    }

    public static void showWarning(final String title, final String header, final String description) {
        showDialog(Alert.AlertType.WARNING, title, header, description);
    }

    public static void showInformation(final String title, final String header, final String description) {
        showDialog(Alert.AlertType.INFORMATION, title, header, description);
    }

    public static boolean showConfirmation(final String title, final String header, final String description) {
        return showDialogBoolean(title, header, description);
    }

    private static void showDialog(final Alert.AlertType type, final String title, final String header, String description) {
        final Alert alert = new Alert(type);
        Tools.addCssAndIcon((Stage) alert.getDialogPane().getScene().getWindow());
        setGraphic(alert.getAlertType(),alert);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

    private static boolean showDialogBoolean(final String title, final String header, final String description) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Tools.addCssAndIcon((Stage) alert.getDialogPane().getScene().getWindow());
        setGraphic(alert.getAlertType(),alert);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
        return alert.getResult().getButtonData().isDefaultButton();
    }

    public static String showDialogString(final String title, final String header, final String description){
        final TextInputDialog dialog = new TextInputDialog();
        Tools.addCssAndIcon((Stage) dialog.getDialogPane().getScene().getWindow());
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(description);
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private static void setGraphic(final Alert.AlertType type, final Alert alert) {
        MDL2IconFont cover, fill;
        switch (type) {
            case ERROR -> {
                cover = new MDL2IconFont("\uEA39");
                fill = new MDL2IconFont("\uEB90");
                fill.setStyle("-fx-text-fill: #FF0000;");
            }
            case WARNING -> {
                cover = new MDL2IconFont("\uE7BA");
                fill = new MDL2IconFont("\uE814");
                fill.setStyle("-fx-text-fill: #FFA500; -fx-font-size:50px; -fx-padding: 0 0 7 0;");
            }
            case INFORMATION -> {
                cover = new MDL2IconFont("\uE946");
                fill = new MDL2IconFont("\uF167");
                fill.setStyle("-fx-text-fill: #00BFFF; -fx-font-size:50px;");
            }
            case CONFIRMATION -> {
                cover = new MDL2IconFont("\uE9CE");
                fill = new MDL2IconFont("\uEA3B");
                fill.setStyle("-fx-text-fill: #00BFFF; -fx-font-size:48px;-fx-padding: 0 0 3 0;");
            }
            default -> {
                cover = new MDL2IconFont("\uE946");
                fill = new MDL2IconFont("\uF167");
            }
        }
        cover.setStyle("-fx-font-size: 50px; -fx-text-fill: #ffffff;");
        cover.setPrefWidth(80);cover.setPrefHeight(80);
        fill.setPrefWidth(80);fill.setPrefHeight(80);
        final MDL2IconCollection collection = new MDL2IconCollection(fill, cover);
        collection.setStyle("-fx-font-size: 30px;");
        collection.setPrefHeight(50);collection.setPrefWidth(50);
        alert.setGraphic(collection);
    }
}
