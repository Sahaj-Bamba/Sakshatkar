package Utilities;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SavingImage extends Application {

    private String fileName;
    private String extension;
    private Image image;

    public SavingImage(String fileName, String extension, Image image) {
        this.fileName = fileName;
        this.extension = extension;
        this.image = image;
    }

    public String save(){
        File file = new File(fileName + "." + extension);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), extension, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public static void main(String[] args) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
