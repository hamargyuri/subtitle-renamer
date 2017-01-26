/**
 * Created by hamargyuri on 2017. 01. 24..
 */

import Model.Directory;
import Model.NoFilesFoundException;
import Model.NoMkvException;
import Model.NoSrtException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SubRenamer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage subRenamer) {
        TextField input = new TextField();
        input.setPrefWidth(450);
        Button goButton = new Button("Go!");
        goButton.setOnAction(event -> {
            try {
                Directory directory = new Directory(input.getText());
                directory.renameSrt();
            } catch (NoFilesFoundException | NoMkvException | NoSrtException exception) {
                // TODO: implement this better without long stacktrace
                System.out.println(exception);
            }
        });

        VBox allContent = new VBox(5);
        HBox inputContainer = new HBox();
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.getChildren().add(input);
        allContent.setAlignment(Pos.CENTER);
        allContent.getChildren().addAll(inputContainer, goButton);

        subRenamer.setTitle("Subtitle Renamer");
        subRenamer.setScene(new Scene(allContent, 500, 150));
        subRenamer.show();
    }
}
