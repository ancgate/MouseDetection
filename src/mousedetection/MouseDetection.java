/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousedetection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;

/**
 *
 * @author jeffersonbienaime
 */
public class MouseDetection extends Application {

    List<Coordinate> cors = new ArrayList<>();
    Stack<Date> timesheet = new Stack<>();
    private final int characterTreshhold = 3;

    @Override
    public void start(Stage primaryStage) {

        Group g1 = new Group();
        Group g2 = new Group();
        Scene scene;

        Rectangle rect1 = RectangleBuilder.create().x(50).y(50).width(100).height(100).fill(Color.WHITE).build();

        rect1.setOnMouseMoved((MouseEvent me) -> {
            rect1.setFill(Color.RED);
            System.out.println("X Coordinate : " + me.getSceneX());
            System.out.println("Y Coordinate : " + me.getSceneY());
            Coordinate c = new Coordinate(me.getSceneX(), me.getSceneY());
            setCoordinate(c);
        });

        rect1.setOnMouseExited((MouseEvent me) -> {
            rect1.setFill(Color.BLUE);
        });

        TextArea ta = new TextArea();

        ta.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println("Character");
            if (ta.getText().length() % characterTreshhold == 0) {
                Date d = new Date();
                setTimeSheet(d);
                System.out.println(d);
            }
        });

        VBox vbox = new VBox(g1, g2);
        scene = new Scene(vbox, 1200, 800, Color.GRAY);
        g1.getChildren().add(rect1);
        g2.getChildren().add(ta);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private List<Coordinate> setCoordinate(Coordinate c) {
        cors.add(c);
        return cors;
    }

    private Stack<Date> setTimeSheet(Date d) {
        timesheet.push(d);
        return timesheet;
    }

    private String calculateTimeSpent(Stack<Date> ld) {
        return dateDifference(ld.firstElement(), ld.lastElement());
    }

    private String dateDifference(Date dateStart, Date dateStop) {

        //HH converts hour in 24 hours format (0-23), day calculation
        //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            //in milliseconds
            long diff = dateStop.getTime() - dateStart.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            return diffDays + " days, " + diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds + " seconds.";

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
