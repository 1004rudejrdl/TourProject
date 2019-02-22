package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.inforTour;
import model.mySapporoTour;
import model.sapporo;

public class sapporoController implements Initializable{
	public Stage sapporoStage;
	
	@FXML private Button sapporoAddButton;
	@FXML private Button sapporoBackButton;
	@FXML private Button sapporoFinalButton;
	@FXML private Button sapporoDeleteButton;
	@FXML private Button sapporoExitButton;
	
	@FXML private TableView<sapporo> sapporoTableView;
	
	@FXML private TableView<mySapporoTour> sapporoMyTourTableView;
	
	ObservableList<sapporo> sapporoListData = FXCollections.observableArrayList();
	ObservableList<mySapporoTour> mysapporoListData = FXCollections.observableArrayList();
	
	ArrayList<sapporo> dbArrayList;
	ArrayList<mySapporoTour> dbArrayList1;
	ArrayList<inforTour> dbinfoArrayList;
	
	private mySapporoTour selectedMysapporo;
	private int selectedMysapporoIndex;
	
	private sapporo selectedSapporoTable;
	private int selectedSapporoTableIndex;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Connection con = DBUtility.getConnection();
		//테이블뷰 기본 셋팅
		settingSapporoTableViewData();
		//나의 관광리스트 테이블뷰 기본 셋팅
		settingMyTourListTableViewData();
		//테이블 두번 클릭하면 설명창 띄우기
		sapporoTableView.setOnMouseClicked(e -> {handleClikedTableViewAction(e);});
		//Add버튼을 누르면 나의 관광리스트에 여행목록 추가
		sapporoAddButton.setOnAction(e -> {handlesapporoAddAction();});
		//back버튼을 누르면 다시 도시선택으로 가는 이벤트
		sapporoBackButton.setOnAction(e -> {handlesapporoBackAction();});
		//delete버튼을 누르면 myList에서 항목삭제
		sapporoDeleteButton.setOnAction(e -> {handlesapporoDeleteAction();});
		//final버튼을 누르면 창전환 -> Final Decision창으로 전환한다.
		sapporoFinalButton.setOnAction(e -> {handlesapporoFinalAction();});
		//나가기
		sapporoExitButton.setOnAction(e -> {handlesapporoExitAction();});
	}

	//테이블뷰 기본 셋팅
	private void settingSapporoTableViewData() {
		TableColumn tcSapporoID = sapporoTableView.getColumns().get(0);
		tcSapporoID.setCellValueFactory(new PropertyValueFactory<>("sapporoTourID"));
		tcSapporoID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcSapporoName = sapporoTableView.getColumns().get(1);
		tcSapporoName.setCellValueFactory(new PropertyValueFactory<>("sapporoTourName"));
		tcSapporoName.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcSapporoClass = sapporoTableView.getColumns().get(2);
		tcSapporoClass.setCellValueFactory(new PropertyValueFactory<>("sapporoTourClass"));
		tcSapporoClass.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcSapporoCharge = sapporoTableView.getColumns().get(3);
		tcSapporoCharge.setCellValueFactory(new PropertyValueFactory<>("sapporoTourCharge"));
		tcSapporoCharge.setStyle("-fx-alignment : CENTER;");

		sapporoTableView.setItems(sapporoListData);
		
		dbArrayList = sapporoDAO.getsaPPoroData();
		for(sapporo saPPoro : dbArrayList) {
			sapporoListData.add(saPPoro);
		}
	}
	
	//나의 관광리스트 테이블뷰 기본 셋팅
	private void settingMyTourListTableViewData() {
		TableColumn tcMySapporoID = sapporoMyTourTableView.getColumns().get(0);
		tcMySapporoID.setCellValueFactory(new PropertyValueFactory<>("mySapporoTourID"));
		tcMySapporoID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcMySapporoName = sapporoMyTourTableView.getColumns().get(1);
		tcMySapporoName.setCellValueFactory(new PropertyValueFactory<>("mySapporoTourName"));
		tcMySapporoName.setStyle("-fx-alignment : CENTER;");
		
		sapporoMyTourTableView.setItems(mysapporoListData);
		mysapporoListData.clear();
		dbArrayList1 = mySapporoTourDAO.getSapporoData();
		for(mySapporoTour mysapporotour : dbArrayList1) {
			mysapporoListData.add(mysapporotour);
		}
		
	}
	
	//테이블 두번 클릭하면 설명창 띄우기
	private void handleClikedTableViewAction(MouseEvent e) {
		selectedSapporoTable = sapporoTableView.getSelectionModel().getSelectedItem();
		selectedSapporoTableIndex = sapporoTableView.getSelectionModel().getSelectedIndex();
		
		dbinfoArrayList = infoDAO.getInfoData(selectedSapporoTable.getSapporoTourID());
		inforTour info = dbinfoArrayList.get(0);
		
		try {
			if(e.getClickCount() == 2) {
				Stage infoStage = new Stage(StageStyle.UTILITY);
				infoStage.initModality(Modality.WINDOW_MODAL);
				infoStage.initOwner(sapporoStage);
				infoStage.setTitle(selectedSapporoTable.getSapporoTourName() + "의 정보");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/infor.fxml"));
				Parent root = loader.load();
				
				ImageView infoImage = (ImageView)root.lookup("#infoImage");
				TextField infotxtName = (TextField)root.lookup("#infotxtName");
				TextField infotxtAddr = (TextField)root.lookup("#infotxtAddr");				
				TextArea infoteaWay = (TextArea)root.lookup("#infoteaWay");
				TextArea infoteaExplain = (TextArea)root.lookup("#infoteaExplain");
				
				infoImage.setImage(new Image(getClass().getResource("../images/" + info.getTourImage()).toString()));
				infotxtName.setText(info.getTourName());
				infotxtAddr.setText(info.getTourAddr());
				infoteaWay.setText(info.getTourWay());
				infoteaExplain.setText(info.getTourExplain());				
				
				Scene scene = new Scene(root);
				infoStage.setScene(scene);
				infoStage.show();
			}
		} catch(Exception e1) {
			
		}
		
	}
	
	
	//Add버튼을 누르면 나의 관광리스트에 여행목록 추가
	private void handlesapporoAddAction() {
		sapporo saPPoro = sapporoTableView.getSelectionModel().getSelectedItem();
		mySapporoTour my = new mySapporoTour(saPPoro.getSapporoTourID(),
		saPPoro.getSapporoTourName());
		mysapporoListData.add(my);
		
		
		int count = mySapporoTourDAO.insertMyTourList(my);
		if(count != 0) {
			callAlert("추가완료:" + selectedSapporoTable.getSapporoTourName() + "을 추가하였습니다.");
		}
	}	
	
	//back버튼을 누르면 다시 도시선택으로 가는 이벤트
	private void handlesapporoBackAction() {
		try {
			
			dbArrayList.clear();
			Stage choiceStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/cityChoice.fxml"));
			Parent root = loader.load();
			cityChoice choiceController = loader.getController();
			choiceController.choiceStage=choiceStage;
			Scene scene = new Scene(root);
			choiceStage.setScene(scene);
			sapporoStage.close();
			choiceStage.show();
		} catch (IOException e) {}	
		
	}
	
	//delete버튼을 누르면 myList에서 항목삭제
	private void handlesapporoDeleteAction() {
		selectedMysapporo = sapporoMyTourTableView.getSelectionModel().getSelectedItem();
		selectedMysapporoIndex = sapporoMyTourTableView.getSelectionModel().getSelectedIndex();
		
		int count = myTokyoTourDAO.deleteMyTourList(selectedMysapporo.getMySapporoTourID());
		if(count != 0) {
			mysapporoListData.remove(selectedMysapporoIndex);
			dbArrayList.remove(selectedMysapporo);			
		}		
	}	

	//final버튼을 누르면 창전환 -> Final Decision창으로 전환한다.
	private void handlesapporoFinalAction() {
		try {
			Stage finalStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/finalDecision.fxml"));
			Parent root = loader.load();
			finalController finalcontroller = loader.getController();
			finalcontroller.finalStage = finalStage;
			Scene scene = new Scene(root);
			finalStage.setScene(scene);
			sapporoStage.close();
			finalStage.show();			
		} catch(IOException e) {
			
		}
		
	}

	//나가기
	private void handlesapporoExitAction() {
		sapporoStage.close();		
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
