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
import model.myOsakaTour;
import model.osaka;

public class osakaController implements Initializable{
	public Stage osakaStage;
	
	@FXML private Button osakaAddButton;
	@FXML private Button osakaBackButton;
	@FXML private Button osakaFinalButton;
	@FXML private Button osakaDeleteButton;
	@FXML private Button osakaExitButton;
	
	@FXML private TableView<osaka> osakaTableView;
	
	@FXML private TableView<myOsakaTour> osakaMyTourTableView;
	
	ObservableList<osaka> osakaListData = FXCollections.observableArrayList();
	ObservableList<myOsakaTour> myosakaListData = FXCollections.observableArrayList();
	
	ArrayList<osaka> dbArrayList;
	ArrayList<myOsakaTour> dbArrayList1;
	ArrayList<inforTour> dbinfoArrayList;
	
	private myOsakaTour selectedMyosaka;
	private int selectedMyosakaIndex;
	
	private osaka selectedOsakaTable;
	private int selectedOsakaTableIndex;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Connection con = DBUtility.getConnection();
		//테이블뷰 기본 셋팅
		settingOsakaTableViewData();
		//나의 관광리스트 테이블뷰 기본 셋팅
		settingMyTourListTableViewData();
		//두번 클릭하면 설명창 띄우기
		osakaTableView.setOnMouseClicked(e ->{handleClikedTableViewAction(e);});
		//Add버튼을 누르면 나의 관광리스트에 여행목록 추가
		osakaAddButton.setOnAction(e -> {handleosakaAddAction();});
		//back버튼을 누르면 다시 도시선택으로 가는 이벤트
		osakaBackButton.setOnAction(e -> {handleosakaBackAction();});
		//delete버튼을 누르면 myList에서 항목삭제
		osakaDeleteButton.setOnAction(e -> {handleosakaDeleteAction();});
		//final버튼을 누르면 창전환 -> Final Decision창으로 전환한다.
		osakaFinalButton.setOnAction(e -> {handleosakaFinalAction();});
		//나가기
		osakaExitButton.setOnAction(e -> {handleosakaExitAction();});
	}

	//테이블뷰 기본 셋팅
	private void settingOsakaTableViewData() {
		TableColumn tcOsakaID = osakaTableView.getColumns().get(0);
		tcOsakaID.setCellValueFactory(new PropertyValueFactory<>("osakaTourID"));
		tcOsakaID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcOsakaName = osakaTableView.getColumns().get(1);
		tcOsakaName.setCellValueFactory(new PropertyValueFactory<>("osakaTourName"));
		tcOsakaName.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcOsakaClass = osakaTableView.getColumns().get(2);
		tcOsakaClass.setCellValueFactory(new PropertyValueFactory<>("osakaTourClass"));
		tcOsakaClass.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcOsakaCharge = osakaTableView.getColumns().get(3);
		tcOsakaCharge.setCellValueFactory(new PropertyValueFactory<>("osakaTourCharge"));
		tcOsakaCharge.setStyle("-fx-alignment : CENTER;");

		osakaTableView.setItems(osakaListData);
		
		dbArrayList = osakaDAO.gettoKyoData();
		for(osaka osAka : dbArrayList) {
			osakaListData.add(osAka);
		}
	}
	
	//나의 관광리스트 테이블뷰 기본 셋팅
	private void settingMyTourListTableViewData() {
		TableColumn tcMyOsakaID = osakaMyTourTableView.getColumns().get(0);
		tcMyOsakaID.setCellValueFactory(new PropertyValueFactory<>("myOsakaTourID"));
		tcMyOsakaID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcMyOsakaName = osakaMyTourTableView.getColumns().get(1);
		tcMyOsakaName.setCellValueFactory(new PropertyValueFactory<>("myOsakaTourName"));
		tcMyOsakaName.setStyle("-fx-alignment : CENTER;");
		
		osakaMyTourTableView.setItems(myosakaListData);
		myosakaListData.clear();
		dbArrayList1 = myOsakaTourDAO.getOsakaData();
		for(myOsakaTour myosakatour : dbArrayList1) {
			myosakaListData.add(myosakatour);
		}
		
	}
	
	//두번 클릭하면 설명창 띄우기
	private void handleClikedTableViewAction(MouseEvent e) {
		selectedOsakaTable = osakaTableView.getSelectionModel().getSelectedItem();
		selectedOsakaTableIndex = osakaTableView.getSelectionModel().getSelectedIndex();
		
		dbinfoArrayList = infoDAO.getInfoData(selectedOsakaTable.getOsakaTourID());
		inforTour info =dbinfoArrayList.get(0);
		
		
		try {
			if(e.getClickCount() == 2) {
				Stage infoStage = new Stage(StageStyle.UTILITY);
				infoStage.initModality(Modality.WINDOW_MODAL);
				infoStage.initOwner(osakaStage);
				infoStage.setTitle(selectedOsakaTable.getOsakaTourName() + "의 정보");
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
	private void handleosakaAddAction() {
		osaka osAka=osakaTableView.getSelectionModel().getSelectedItem();
		myOsakaTour my = new myOsakaTour(osAka.getOsakaTourID(),
				osAka.getOsakaTourName());
		myosakaListData.add(my);
		
		
		int count = myOsakaTourDAO.insertMyTourList(my);
		if(count != 0) {
			callAlert("추가완료:" + selectedOsakaTable.getOsakaTourName() + "을 추가하였습니다.");
		}
	}	
	
	//back버튼을 누르면 다시 도시선택으로 가는 이벤트
	private void handleosakaBackAction() {
		try {
			myosakaListData.clear();
			dbArrayList.clear();
			Stage choiceStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/cityChoice.fxml"));
			Parent root = loader.load();
			cityChoice choiceController = loader.getController();
			choiceController.choiceStage=choiceStage;
			Scene scene = new Scene(root);
			choiceStage.setScene(scene);
			osakaStage.close();
			choiceStage.show();
		} catch (IOException e) {}	
		
	}
	
	//delete버튼을 누르면 myList에서 항목삭제
	private void handleosakaDeleteAction() {
		selectedMyosaka = osakaMyTourTableView.getSelectionModel().getSelectedItem();
		selectedMyosakaIndex = osakaMyTourTableView.getSelectionModel().getSelectedIndex();
		
		int count = myTokyoTourDAO.deleteMyTourList(selectedMyosaka.getMyOsakaTourID());
		if(count != 0) {
			myosakaListData.remove(selectedMyosakaIndex);
			dbArrayList.remove(selectedMyosaka);			
		}		
	}	

	//final버튼을 누르면 창전환 -> Final Decision창으로 전환한다.
	private void handleosakaFinalAction() {
		try {
			Stage finalStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/finalDecision.fxml"));
			Parent root = loader.load();
			finalController finalcontroller = loader.getController();
			finalcontroller.finalStage = finalStage;
			Scene scene = new Scene(root);
			finalStage.setScene(scene);
			osakaStage.close();
			finalStage.show();			
		} catch(IOException e) {
			
		}
		
	}

	//나가기
	private void handleosakaExitAction() {
		osakaStage.close();		
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
