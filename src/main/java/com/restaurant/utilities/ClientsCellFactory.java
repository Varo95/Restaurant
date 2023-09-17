package com.restaurant.utilities;

import com.restaurant.Main;
import com.restaurant.models.Client;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.Optional;

public class ClientsCellFactory<P> implements Callback<P, ListCell<Client>> {
    @Override
    public ListCell<Client> call(final P param) {
        ListCell<Client> cell = new ListCell<>() {
            @Override
            protected void updateItem(final Client item, final boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    final HBox bar = new HBox(new Label(item.getDni()), new Label(item.getName()), new Label(String.valueOf(item.getAge())), new Label(String.valueOf(item.getPoints())));
                    bar.setSpacing(15);
                    bar.setAlignment(Pos.CENTER_LEFT);
                    setGraphic(bar);
                }
            }
        };
        cell.setOnMousePressed(event -> {
            if (cell.isEmpty()) {
                event.consume();
            } else {
                if (event.getClickCount() == 2) {
                    Main.loadScene(Optional.of(cell.getItem()), "views/client", Main.bundle.getString("app.client.edit"), false, true);
                }
            }
        });
        return cell;
    }
}
