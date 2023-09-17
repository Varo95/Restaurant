package com.restaurant;

import com.restaurant.controllers.ClientController;
import com.restaurant.controllers.ProductController;
import com.restaurant.interfaces.AProduct;
import com.restaurant.models.Client;
import com.restaurant.utilities.PersistenceUnit;
import com.restaurant.views.Tools;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

@Log4j2
public class Main extends Application {

	public static final ResourceBundle bundle = ResourceBundle.getBundle("com.restaurant.i18n.strings");

	@Override
	public void start(final Stage stage) {
		loadScene(Optional.empty(),"main", bundle.getString("app.name"), false, true);
	}

	private static <T> Parent loadFXML(final Optional<T> object, final Stage stage, final String fxml) {
		Optional<Parent> result;
		final FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"), bundle);
		try {
			result = Optional.of(fxmlLoader.load());
			final Object controller = fxmlLoader.getController();
			if (controller instanceof ClientController c) {
				c.setProductOrClient(true,(Client)object.orElse(null));
			}else if(controller instanceof ProductController p){
				p.setProductOrClient(true,(AProduct)object.orElse(null));
			}
			Tools.addStage(fxml, stage, controller);
		} catch (final IOException e) {
			log.error(e);
			result = Optional.empty();
		}
		return result.orElse(null);
	}

	public static <T> void loadScene(final Optional<T> object, final String fxml, final String title, final boolean saw, final boolean isResizable) {
		Stage stage = Tools.getStageAndUpdateObjects(fxml, object).orElse(null);
		if (stage != null) {
			stage.show();
			stage.toFront();
		} else {
			stage = new Stage();
			stage.setScene(new Scene(loadFXML(object,stage,fxml)));
			stage.setTitle(title);
			stage.setResizable(isResizable);
			Tools.addCssAndIcon(stage);
			if (saw) stage.showAndWait();
			else stage.show();
		}
	}

	public static void hideScene(final Stage stage) {
		stage.hide();
	}

	public static void main(final String[] args) {
		PersistenceUnit.init();
		Application.launch(args);
		PersistenceUnit.closeEMF();
	}

}
