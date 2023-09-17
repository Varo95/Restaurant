package com.restaurant.controllers;

import com.restaurant.Main;
import com.restaurant.interfaces.AProduct;
import com.restaurant.models.Drink;
import com.restaurant.models.Food;
import com.restaurant.repositorys.Repository;
import com.restaurant.views.Tools;
import com.restaurant.views._Dialog;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ProductController extends ASingleController<AProduct> {

    @FXML
    private ToggleGroup productType;
    @FXML
    private TextField tfName, tfPrice;
    @FXML
    private CheckBox cbCeliac, cbAlcoholicOrVegan;
    @FXML
    private Button btnPersist, btnDelete;

    @FXML
    protected void initialize() {
        Platform.runLater(()-> {
            this.btnPersist.getScene().getWindow().setOnShown(event-> this.initFields());
            this.btnPersist.getScene().getWindow().setOnCloseRequest(event-> Tools.updateMainStage());
        });
        Tools.onlyDoubleValue(this.tfPrice);
        this.productType.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof RadioButton button) {
                final boolean isDrink = button.getId().equals("rbtnDrink");
                this.cbAlcoholicOrVegan.setText(isDrink ? Main.bundle.getString("app.product.alcoholic") : Main.bundle.getString("app.product.vegan"));
            }
        });
    }

    protected void initFields() {
        final boolean noProduct = this.productOrClient == null;
        Platform.runLater(()-> ((Stage)this.btnPersist.getScene().getWindow()).setTitle(noProduct? Main.bundle.getString("app.product.add") : Main.bundle.getString("app.product.edit")));
        this.btnDelete.setDisable(noProduct);
        if (!noProduct){
            this.tfName.setText(this.productOrClient.getName());
            this.tfPrice.setText(String.valueOf(this.productOrClient.getPrice()));
            this.cbCeliac.setSelected(this.productOrClient.isForCeliac());
            if (this.productOrClient instanceof Food food) {
                this.productType.selectToggle(productType.getToggles().get(0));
                this.cbAlcoholicOrVegan.setSelected(food.isForVegan());
            } else if (this.productOrClient instanceof Drink drink) {
                this.productType.selectToggle(productType.getToggles().get(1));
                this.cbAlcoholicOrVegan.setSelected(drink.isAlcoholic());
            }
            this.addDeleteButtonAction();
        }else{
            this.productType.getToggles().get(0).setSelected(true);
            this.tfName.setText("");
            this.tfPrice.setText("");
            this.cbCeliac.setSelected(false);
            this.cbAlcoholicOrVegan.setSelected(false);
        }
        this.initPersistButton(noProduct);
    }

    protected void addDeleteButtonAction() {
        this.btnDelete.setOnAction(event -> {
            StringBuilder string = new StringBuilder("app.product.dialog.delete");
            String title = Main.bundle.getString(string + dialogStrings[0]);
            String header = Main.bundle.getString(string + dialogStrings[1]);
            String description = Main.bundle.getString(string + dialogStrings[2]);
            final boolean confirm = _Dialog.showConfirmation(title, header, description);
            if (confirm) {
                ASingleController.repository.delete(this.productOrClient);
                string = new StringBuilder("app.product.dialog.deleted");
                title = Main.bundle.getString(string + dialogStrings[0]);
                header = Main.bundle.getString(string + dialogStrings[1]);
                description = Main.bundle.getString(string + dialogStrings[2]);
                _Dialog.showInformation(title, header, description);
                Main.hideScene((Stage) this.btnDelete.getScene().getWindow());
                Tools.updateMainStage();
            }
        });
    }

    protected void initPersistButton(final boolean noProduct){
        this.btnPersist.setOnAction(event -> {
            if (noProduct) {
                super.setProductOrClient(false,((RadioButton) this.productType.getSelectedToggle()).getId().equals("rbtnDrink") ? new Drink() : new Food());
            }
            this.productOrClient.setName(this.tfName.getText());
            this.productOrClient.setPrice(Double.parseDouble(this.tfPrice.getText()));
            this.productOrClient.setForCeliac(this.cbCeliac.isSelected());
            if (this.productOrClient instanceof Food food) {
                food.setForVegan(this.cbAlcoholicOrVegan.isSelected());
            } else if (this.productOrClient instanceof Drink drink) {
                drink.setAlcoholic(this.cbAlcoholicOrVegan.isSelected());
            }
            repository.persist(this.productOrClient);
            final StringBuilder string = new StringBuilder("app.product.dialog");
            if (noProduct) string.append(".add");
            else string.append(".edit");
            final String title = Main.bundle.getString(string + dialogStrings[0]);
            final String header = Main.bundle.getString(string + dialogStrings[1]);
            final String description = Main.bundle.getString(string + dialogStrings[2]);
            _Dialog.showInformation(title, header, description);
            Main.hideScene((Stage) this.btnDelete.getScene().getWindow());
            Tools.updateMainStage();
        });
    }
}
