package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("DTCC Registration");
        //set icon
        primaryStage.getIcons().add(new Image("sample/images/del_tech.jpg"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            //set alert for leaving
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Leaving already?");
            alert.setHeaderText(null);
            alert.setContentText("Thank you for using this app!");
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
