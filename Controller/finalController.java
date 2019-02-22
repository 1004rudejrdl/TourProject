package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.fiNal;

public class finalController implements Initializable{
	public Stage finalStage;
	
	@FXML private Button btnBackTourList;
	@FXML private Button btnCalExchange;
	@FXML private Button btnDecisionExit;
	@FXML private Button btnTotalCharge;
	@FXML private TextField txtTotalCharge;
	
	@FXML private TableView<fiNal> finalTableView;
	ObservableList<fiNal> finalListData = FXCollections.observableArrayList();
	
	ArrayList<fiNal> dbArrayList;
	ArrayList<Integer> dbArrayFinalList;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//최종결정창 테이블뷰 기본 셋팅
		settingFinalTableData();
		//back버튼누르면 투어리스트로 다시 전환
		btnBackTourList.setOnAction(e -> {handleBtnBackTourListAction();});
		//totalcharge버튼을 누르면 합계 나오게 하기
		btnTotalCharge.setOnAction(e -> {handleBtnTotalChargeAction();});
		//calexchange버튼을 누르면 계산기 나오게 하기
		btnCalExchange.setOnAction(e -> {handleBtnCalExchangeAction();});
		//나가기
		btnDecisionExit.setOnAction(e -> {handleBtnExitAction();});
		
	}

	//최종결정창 테이블뷰 기본 셋팅
	private void settingFinalTableData() {
		TableColumn tcFinalTourID = finalTableView.getColumns().get(0);
		tcFinalTourID.setCellValueFactory(new PropertyValueFactory<>("finalTourID"));
		tcFinalTourID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcFinalTourName = finalTableView.getColumns().get(1);
		tcFinalTourName.setCellValueFactory(new PropertyValueFactory<>("finalTourName"));
		tcFinalTourName.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcFinalTourClass = finalTableView.getColumns().get(2);
		tcFinalTourClass.setCellValueFactory(new PropertyValueFactory<>("finalTourClass"));
		tcFinalTourClass.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcFinalTourCharge = finalTableView.getColumns().get(3);
		tcFinalTourCharge.setCellValueFactory(new PropertyValueFactory<>("finalTourCharge"));
		tcFinalTourCharge.setStyle("-fx-alignment : CENTER;");
		
		finalTableView.setItems(finalListData);
		
		dbArrayList = finalDAO.getFinalListData();
		for(fiNal final1 : dbArrayList) {
			finalListData.add(final1);
		}
		
	}
	
	//back버튼누르면 투어리스트로 다시 전환
	private void handleBtnBackTourListAction() {
		try {
			Stage choiceStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/cityChoice.fxml"));
			Parent root = loader.load();
			cityChoice choiceController = loader.getController();
			choiceController.choiceStage=choiceStage;
			Scene scene = new Scene(root);
			choiceStage.setScene(scene);
			finalStage.close();
			choiceStage.show();
		} catch(IOException e) {}
		
	}
	
	//totalcharge버튼을 누르면 합계 나오게 하기
	private void handleBtnTotalChargeAction() {
		 dbArrayFinalList = finalDAO.getFinalListSumData();
		 Integer sum = dbArrayFinalList.get(0);
		 txtTotalCharge.setText(String.valueOf(sum));
//		int sum=0;
//		for(int i=0; i<finalTableView.getItems().size(); i++) {
//			sum = sum + Integer.parseInt(finalTableView.getColumns().get(3).toString());
//		}
//		txtTotalCharge.setText(String.valueOf(sum));
		
	}
	
	
	//calexchange버튼을 누르면 계산기 나오게 하기
	private void handleBtnCalExchangeAction() {
		try {
			Stage chargeStage = new Stage(StageStyle.UTILITY);
			chargeStage.initModality(Modality.WINDOW_MODAL);
			chargeStage.initOwner(finalStage);
			chargeStage.setTitle("Exchange Rate");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ExchangeRateFx.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			chargeStage.setScene(scene); 
			chargeStage.show();
			
		}catch(Exception e1) {}
		
	}

	
	//나가기
	private void handleBtnExitAction() {
		finalStage.close();
	}
	
	//알림창
	public static void callAlert(String contentText) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("알림");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":")+1));
		alert.showAndWait();
	}
	
}
