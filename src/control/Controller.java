package control;

import  application.Main;
import javafx.stage.Stage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Controller {
    protected Main mainApp;

    public void setMainApp(Main main) {
        mainApp = main;
    }

    public void setHandOff(Object handOff) {
        throw new NotImplementedException();
    }

}
