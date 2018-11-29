/**
 * @author ${hisham_maged10}
 *https://github.com/hisham-maged10
 * ${DesktopApps}
 */
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.animation.Timeline;
import javafx.animation.PathTransition;

import java.util.Date;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
public class ClockFx extends Application{
	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) 
	{
		Pane pane=new Pane();
		pane.setPrefWidth(1289);
		pane.setPrefHeight(720);
		Font myFont=Font.font("", FontWeight.BOLD, 20);
		Circle circ=new Circle();
		circ.setCenterX(pane.getPrefWidth()/2);
		circ.setCenterY(pane.getPrefHeight()/2);
		circ.setRadius(200);
		circ.setFill(Color.WHITE);
		circ.setStroke(Color.BLACK);
		circ.setStrokeWidth(2);
		Circle spendile=new Circle();
		spendile.setCenterX(circ.getCenterX());
		spendile.setCenterY(circ.getCenterY());
		spendile.setRadius(5);
		Text txt12=new Text("12");
		txt12.setX(circ.getCenterX()-15);
		txt12.setY(circ.getCenterY()-circ.getRadius()+20);
		txt12.setFont(myFont);
		Text txt6=new Text("6");
		txt6.setX(circ.getCenterX()-5);
		txt6.setY(circ.getCenterY()+circ.getRadius()-5);
		txt6.setFont(myFont);
		Text txt3=new Text("3");
		txt3.setX(circ.getCenterX()+circ.getRadius()-15);
		txt3.setY(circ.getCenterY());
		txt3.setFont(myFont);
		Text txt9=new Text("9");
		txt9.setX(circ.getCenterX()-circ.getRadius()+5);
		txt9.setY(circ.getCenterY());
		txt9.setFont(myFont);
		Line hrLine=new Line();
		hrLine.setStartX(spendile.getCenterX());
		hrLine.setStartY(spendile.getCenterY());
		hrLine.setEndX(spendile.getCenterX()-3);
		hrLine.setEndY(txt12.getY()+25);
		hrLine.setStrokeWidth(2);
		Line minLine=new Line();
		minLine.setStartX(spendile.getCenterX());
		minLine.setStartY(spendile.getCenterY());
		minLine.setEndX(spendile.getCenterX()-3);
		minLine.setEndY(txt12.getY()+35);
		minLine.setStrokeWidth(2);
		minLine.setStroke(Color.GREEN);
		Line secLine=new Line();
		secLine.setStartX(spendile.getCenterX());
		secLine.setStartY(spendile.getCenterY());
		secLine.setEndX(spendile.getCenterX()-3);
		secLine.setEndY(txt12.getY()+10);
		secLine.setStrokeWidth(2);
		secLine.setStroke(Color.RED);
		Rotate rotationSec = new Rotate();
        rotationSec.pivotXProperty().bind(spendile.centerXProperty());
        rotationSec.pivotYProperty().bind(spendile.centerYProperty());
        secLine.getTransforms().add(rotationSec);
        Rotate rotationMin = new Rotate();
        rotationMin.pivotXProperty().bind(spendile.centerXProperty());
        rotationMin.pivotYProperty().bind(spendile.centerYProperty());
        minLine.getTransforms().add(rotationMin);
        Rotate rotationHr = new Rotate();
        rotationHr.pivotXProperty().bind(spendile.centerXProperty());
        rotationHr.pivotYProperty().bind(spendile.centerYProperty());
        hrLine.getTransforms().add(rotationHr);
        EventHandler<ActionEvent> secTime=e->rotationSec.setAngle(new Date(System.currentTimeMillis()).getSeconds()*(360/60));
		EventHandler<ActionEvent> minTime=e->rotationMin.setAngle(new Date(System.currentTimeMillis()).getMinutes()*(360/60));
		EventHandler<ActionEvent> hrTime=e->rotationHr.setAngle(new Date(System.currentTimeMillis()).getHours()*(360/12));
		Timeline hrTimeline=new Timeline(new KeyFrame(Duration.seconds(1),hrTime));
		hrTimeline.setCycleCount(Timeline.INDEFINITE);
		hrTimeline.play();
		Timeline minTimeline=new Timeline(new KeyFrame(Duration.seconds(1),minTime));
		minTimeline.setCycleCount(Timeline.INDEFINITE);
		minTimeline.play();
		Timeline secTimeline=new Timeline(new KeyFrame(Duration.seconds(1),secTime));
		secTimeline.setCycleCount(Timeline.INDEFINITE);
		secTimeline.play();
		pane.getChildren().addAll(circ,hrLine,minLine,secLine,spendile,txt12,txt6,txt3,txt9);
		Scene scene=new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ClockFx");
		primaryStage.show();
		pane.setOnMouseClicked(e->{
			if(hrTimeline.getStatus()==Animation.Status.RUNNING)
			{
			hrTimeline.stop();
			secTimeline.stop();
			minTimeline.stop();
			}
			else
			{
				hrTimeline.play();
				secTimeline.play();
				minTimeline.play();
			}
		});
	}
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
