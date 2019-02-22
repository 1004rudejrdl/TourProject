package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class chartController implements Initializable{
	public Stage chartStage;
	@FXML private Button chartVisitRankJapan;
	
	@FXML private Button chartVisitRankPrefecture;
	
	@FXML private Button chartVisitRankGuests;
	
	@FXML private Button chartContinue;
	
	HashMap<String, Integer> hashMap = new HashMap<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//일본을 방문한 외국인 수 , 파이차트
		chartVisitRankJapan.setOnAction(e -> {handleBtnRankJapanAction();});
		//일본에 현의 방문 랭킹, 바 차트
		chartVisitRankPrefecture.setOnAction(e -> {handleBtnRankPrefectureAction();});
		//지역별 숙박시설의 누적 랭킹, 바차트
		chartVisitRankGuests.setOnAction(e -> {handleBtnRankGuestAction();});
		//continue 버튼을 누르면 도시선택메뉴 띄우기
		chartContinue.setOnAction(e -> {handleBtnContinueAction();});
		
	}

	//일본을 방문한 외국인 수(파이차트)
	private void handleBtnRankJapanAction() {
		try {
			Stage pieStage = new Stage(StageStyle.UTILITY);
			pieStage.initModality(Modality.WINDOW_MODAL);
			pieStage.initOwner(chartStage);
			pieStage.setTitle("Visitor to Japan");
			Parent root;
			root = FXMLLoader.load(getClass().getResource("../view/visitRankjapan.fxml"));
			PieChart visitRankJapanPieChart = (PieChart)root.lookup("#visitRankJapanPieChart");
			visitRankJapanPieChart.setData(FXCollections.observableArrayList(
					new PieChart.Data("UK", 22900),
					new PieChart.Data("South Korea", 681600),
					new PieChart.Data("China", 599100),
					new PieChart.Data("Taiwan", 335800),
					new PieChart.Data("Hong Kong", 209500),
					new PieChart.Data("Others", 85500)
					));	

			Button btnExit = (Button)root.lookup("#pieChartBtnExit");
			btnExit.setOnAction(e1 ->{pieStage.close();});
			
			Scene scene = new Scene(root);
			pieStage.setScene(scene);
			pieStage.show();
		} catch(Exception e) {}
		
	}

	//일본에 현의 방문 랭킹, 바 차트
	private void handleBtnRankPrefectureAction() {
		try {
			Stage barStage = new Stage(StageStyle.UTILITY);
			barStage.initModality(Modality.WINDOW_MODAL);
			barStage.initOwner(chartStage);
			barStage.setTitle("Visit to Prefecture (%)");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/barChartPrefecture.fxml"));
			Parent root = loader.load();
			NumberAxis xAxis = new NumberAxis();
			CategoryAxis yAxis = new CategoryAxis();
			BarChart visitPrefectureBarChart = (BarChart)root.lookup("#visitPrefectureBarChart");
			visitPrefectureBarChart = new BarChart<Number, String>(xAxis, yAxis);
			xAxis.setLabel("Value");
			//xAxis.setTickLabelRotation(90);
			yAxis.setLabel("Prefecture");
			
			XYChart.Series series = new XYChart.Series();
			series.setName("2018 Visit Rate Ranking");
			series.setData(FXCollections.observableArrayList(
					new XYChart.Data(46.2,"Tokyo"),
					new XYChart.Data(38.7,"Osaka"),
					new XYChart.Data(7.7,"Sapporo"),
					new XYChart.Data(7.3,"Okinawa"),
					new XYChart.Data(9.8,"Fukuoka")
					));
			visitPrefectureBarChart.getData().add(series);
						
			Scene scene = new Scene(visitPrefectureBarChart);
			barStage.setScene(scene);
			barStage.show();
		
			Button btnExit = (Button)root.lookup("#barChartBtnExit");
			btnExit.setOnAction(e1 -> {	barStage.close();});

		} catch(IOException e){
			
		}
		
	}
	
	//지역별 숙박시설의 누적 랭킹, 바차트
	private void handleBtnRankGuestAction() {
		try {
			Stage barStage2 = new Stage(StageStyle.UTILITY);
			barStage2.initModality(Modality.WINDOW_MODAL);
			barStage2.initOwner(chartStage);
			barStage2.setTitle("Accumulated Number of Forign Guests");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/visitGuestRank.fxml"));
			Parent root = loader.load();
			BarChart visitGuesttRank = (BarChart)root.lookup("#visitGuesttRank");
			
			
			XYChart.Series series1 = new XYChart.Series();
			series1.setName("2017");
			series1.setData(FXCollections.observableArrayList(
					new XYChart.Data("Hokkaido",6165450),
					new XYChart.Data("Tohoku",648430),
					new XYChart.Data("Kanto",23864100),
					new XYChart.Data("Kinki",16194820),
					new XYChart.Data("Okinawa",3524440),
					new XYChart.Data("Kyushu",5182610)
					));
			
			XYChart.Series series2 = new XYChart.Series();
			series2.setName("2018");
			series2.setData(FXCollections.observableArrayList(
					new XYChart.Data("Hokkaido",7265810),
					new XYChart.Data("Tohoku",966860),
					new XYChart.Data("Kanto",27606960),
					new XYChart.Data("Kinki",17655640),
					new XYChart.Data("Okinawa",4058380),
					new XYChart.Data("Kyushu",6600110)
					));
			
			visitGuesttRank.getData().addAll(series1,series2);
			
			Button btnExit = (Button)root.lookup("#btnExit");
			btnExit.setOnAction(e1 -> {barStage2.close();});
			
			Scene scene = new Scene(root);
			barStage2.setScene(scene);
			barStage2.show();
			
		} catch(IOException e) {
			
		}
	}

	//continue 버튼을 누르면 도시선택메뉴 띄우기
	private void handleBtnContinueAction() {
		try {
			Stage choiceStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/cityChoice.fxml"));
			Parent root = loader.load();
			cityChoice choiceController = loader.getController();
			choiceController.choiceStage=choiceStage;
			Scene scene = new Scene(root);
			choiceStage.setScene(scene);
			chartStage.close();
			choiceStage.show();
		} catch (IOException e) {	
			callAlert("화면전환오류 : 화면전환에 오류가 발생했습니다.");
		}		
	}
	
	
	//알림창
	public void callAlert(String contentText) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("알림");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":")+1));
		alert.showAndWait();
	}

}
