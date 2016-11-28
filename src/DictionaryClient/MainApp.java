package DictionaryClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // �����root��FXML�ļ��м��ؽ��г�ʼ��������FXMLLoader�����ڼ���FXML�ļ�
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("LoginPane.fxml"));
            Scene scene = new Scene(root, 500, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
