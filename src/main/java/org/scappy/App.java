package org.scappy;

import javafx.application.Application;
import javafx.stage.Stage;
import org.scappy.Models.Models;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the Login Window and show it
        Models.getInstance().getViewFactory().showLoginWindow();
    }

}
