module org.scappy {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens org.scappy to javafx.fxml;
    exports org.scappy;
    exports org.scappy.Controllers;
    exports org.scappy.Controllers.Admin;
    exports org.scappy.Controllers.Client;
    exports org.scappy.Models;
    exports org.scappy.Views;
}
