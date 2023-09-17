module Restaurant {
    requires java.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.logging;
    requires jakarta.persistence;
    requires lombok;
    requires org.apache.logging.log4j;
    requires org.jfxtras.styles.jmetro;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens com.restaurant to lombok;
    opens com.restaurant.controllers to javafx.fxml, javafx.controls, javafx.graphics, javafx.base;
    opens com.restaurant.models to org.hibernate.orm.core, org.hibernate.commons.annotations;
    opens com.restaurant.interfaces to org.hibernate.orm.core, org.hibernate.commons.annotations;
    exports com.restaurant;
    exports com.restaurant.interfaces;
}