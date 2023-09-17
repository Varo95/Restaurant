package com.restaurant.controllers;

import com.restaurant.Main;
import com.restaurant.models.Client;
import com.restaurant.views.Tools;
import com.restaurant.views._Dialog;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientController extends ASingleController<Client> {

    @FXML
    private TextField tfDNI, tfName, tfAge, tfPoints;
    @FXML
    private Button btnPersist, btnDelete, btnOrders, btnAddAddress, btnDeleteAddress;
    @FXML
    private ListView<String> lvAddresses;

    @FXML
    protected void initialize(){
        Platform.runLater(()-> {
            this.btnPersist.getScene().getWindow().setOnShown(event-> this.initFields());
            this.btnPersist.getScene().getWindow().setOnCloseRequest(event-> Tools.updateMainStage());
        });
    }

    protected void initFields() {
        final boolean noClient = this.productOrClient == null;
        Platform.runLater(()-> ((Stage)this.btnPersist.getScene().getWindow()).setTitle(noClient? Main.bundle.getString("app.client.new") : Main.bundle.getString("app.client.edit")));
        this.btnDelete.setDisable(noClient);
        this.btnOrders.setDisable(noClient);
        this.configureAddress();
        if (!noClient) {
            this.tfDNI.setText(this.productOrClient.getDni());
            this.tfName.setText(this.productOrClient.getName());
            this.tfAge.setText(String.valueOf(this.productOrClient.getAge()));
            this.tfPoints.setText(String.valueOf(this.productOrClient.getPoints()));
            for(final String address : this.productOrClient.getAddress()){
                this.lvAddresses.getItems().add(address);
            }
            this.addDeleteButtonAction();
            this.addOrdersButtonAction();
        }else{
            this.tfDNI.setText("");
            this.tfName.setText("");
            this.tfPoints.setText("");
            this.tfAge.setText("");
            this.lvAddresses.getItems().clear();
        }
        this.initPersistButton(noClient);
    }

    protected void addDeleteButtonAction() {
        this.btnDelete.setOnAction(event -> {
            StringBuilder string = new StringBuilder("app.client.dialog.delete");
            String title = Main.bundle.getString(string + ASingleController.dialogStrings[0]);
            String header = Main.bundle.getString(string + ASingleController.dialogStrings[1]);
            String description = Main.bundle.getString(string + ASingleController.dialogStrings[2]);
            final boolean confirm = _Dialog.showConfirmation(title, header, description);
            if (confirm) {
                ASingleController.repository.delete(productOrClient);
                string = new StringBuilder("app.client.dialog.deleted");
                title = Main.bundle.getString(string + ASingleController.dialogStrings[0]);
                header = Main.bundle.getString(string + ASingleController.dialogStrings[1]);
                description = Main.bundle.getString(string + ASingleController.dialogStrings[2]);
                _Dialog.showInformation(title, header, description);
                Main.hideScene((Stage) this.btnDelete.getScene().getWindow());
                Tools.updateMainStage();
            }
        });
    }

    protected void addOrdersButtonAction(){
        this.btnOrders.setOnAction(event ->{

        });
    }

    protected void configureAddress(){
        this.lvAddresses.getItems().addListener((ListChangeListener<String>) c -> this.btnDeleteAddress.setDisable(this.lvAddresses.getItems().isEmpty()));
        this.btnDeleteAddress.setOnAction(event -> {
            final String address = this.lvAddresses.getSelectionModel().getSelectedItem();
            if(address != null){
                this.lvAddresses.getItems().remove(address);
            }
        });
        this.btnAddAddress.setOnAction(event->{
            final StringBuilder string = new StringBuilder("app.client.dialog.address");
            final String title = Main.bundle.getString(string + ASingleController.dialogStrings[0]);
            final String header = Main.bundle.getString(string + ASingleController.dialogStrings[1]);
            final String description = Main.bundle.getString(string + ASingleController.dialogStrings[2]);
            final String address = _Dialog.showDialogString(title, header, description);
            if(address != null){
                this.lvAddresses.getItems().add(address);
            }
        });
    }

    protected void initPersistButton(final boolean noClient){
        this.btnPersist.setOnAction(event -> {
            if (noClient) {
                super.setProductOrClient(false,new Client());
            }
            this.productOrClient.setDni(this.tfDNI.getText());
            this.productOrClient.setName(this.tfName.getText());
            this.productOrClient.setPoints(Integer.parseInt(this.tfPoints.getText()));
            this.productOrClient.setAge(Integer.parseInt(this.tfAge.getText()));
            this.productOrClient.getAddress().clear();
            for(final String address : this.lvAddresses.getItems()){
                this.productOrClient.getAddress().add(address);
            }
            ASingleController.repository.persist(productOrClient);
            final StringBuilder string = new StringBuilder("app.client.dialog");
            if (noClient) string.append(".add");
            else string.append(".edit");
            final String title = Main.bundle.getString(string + ASingleController.dialogStrings[0]);
            final String header = Main.bundle.getString(string + ASingleController.dialogStrings[1]);
            final String description = Main.bundle.getString(string + ASingleController.dialogStrings[2]);
            _Dialog.showInformation(title, header, description);
            Main.hideScene((Stage) this.btnDelete.getScene().getWindow());
            Tools.updateMainStage();
        });
    }

}
