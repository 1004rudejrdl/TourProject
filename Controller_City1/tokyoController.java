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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.inforTour;
import model.myTokyoTour;
import model.toKyo;

public class tokyoController implements Initializable{
	public Stage tokyoStage;
	
	@FXML private Button tokyoAddButton;
	@FXML private Button tokyoBackButton;
	@FXML private Button tokyoFinalButton;
	@FXML private Button tokyoDeleteButton;
	@FXML private Button tokyoExitButton;
	
	@FXML private TableView<toKyo> tokyoTableView;
	
	@FXML private TableView<myTokyoTour> tokyoMyTourTableView;
	
	ObservableList<toKyo> tokyoListData = FXCollections.observableArrayList();
	ObservableList<myTokyoTour> mytokyoListData = FXCollections.observableArrayList();

	
	ArrayList<toKyo> dbArrayList;
	ArrayList<myTokyoTour> dbArrayList1;
	ArrayList<inforTour> dbinfoArrayList;
	
	private myTokyoTour selectedMytoKyo;
	private int selectedMytoKyoIndex;
	
	private toKyo selectedTokyoTable;
	private int selectedTokyoTableIndex;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Connection con = DBUtility.getConnection();
		//테이블뷰 기본 셋팅
		settingTokyoTableViewData();
		//나의 관광리스트 테이블뷰 기본 셋팅
		settingMyTourListTableViewData();
		//tokyo 테이블을 두번 클릭하면 설명 창 띄우기
		tokyoTableView.setOnMouseClicked(e -> {handleClikedTableViewAction(e);});
		//Add버튼을 누르면 나의 관광리스트에 여행목록 추가
		tokyoAddButton.setOnAction(e -> {handletokyoAddAction();});
		//back버튼을 누르면 다시 도시선택으로 가는 이벤트
		tokyoBackButton.setOnAction(e -> {handletokyoBackAction();});
		//delete버튼을 누르면 myList에서 항목삭제
		tokyoDeleteButton.setOnAction(e -> {handletokyoDeleteAction();});
		//final버튼을 누르면 창전환 -> Final Decision창으로 전환한다.
		tokyoFinalButton.setOnAction(e -> {handletokyoFinalAction();});
		//나가기
		tokyoExitButton.setOnAction(e -> {handletokyoExitAction();});
	}

	//테이블뷰 기본 셋팅
	private void settingTokyoTableViewData() {
		TableColumn tcTokyoID = tokyoTableView.getColumns().get(0);
		tcTokyoID.setCellValueFactory(new PropertyValueFactory<>("tokyoTourID"));
		tcTokyoID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcTokyoName = tokyoTableView.getColumns().get(1);
		tcTokyoName.setCellValueFactory(new PropertyValueFactory<>("tokyoTourName"));
		tcTokyoName.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcTokyoClass = tokyoTableView.getColumns().get(2);
		tcTokyoClass.setCellValueFactory(new PropertyValueFactory<>("tokyoTourClass"));
		tcTokyoClass.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcTokyoCharge = tokyoTableView.getColumns().get(3);
		tcTokyoCharge.setCellValueFactory(new PropertyValueFactory<>("tokyoTourCharge"));
		tcTokyoCharge.setStyle("-fx-alignment : CENTER;");

		tokyoTableView.setItems(tokyoListData);
		
		dbArrayList = tokyoDAO.gettoKyoData();
		for(toKyo tokyo : dbArrayList) {
			tokyoListData.add(tokyo);
		}
	}
	
	//나의 관광리스트 테이블뷰 기본 셋팅
	private void settingMyTourListTableViewData() {
		TableColumn tcMyTokyoID = tokyoMyTourTableView.getColumns().get(0);
		tcMyTokyoID.setCellValueFactory(new PropertyValueFactory<>("myTokyoTourID"));
		tcMyTokyoID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcMyTokyoName = tokyoMyTourTableView.getColumns().get(1);
		tcMyTokyoName.setCellValueFactory(new PropertyValueFactory<>("myTokyoTourName"));
		tcMyTokyoName.setStyle("-fx-alignment : CENTER;");
		
		tokyoMyTourTableView.setItems(mytokyoListData);
		mytokyoListData.clear();
		
		dbArrayList1 = myTokyoTourDAO.getTokyoData();
		for(myTokyoTour mytokyotour : dbArrayList1) {
			mytokyoListData.add(mytokyotour);
		}
		
	}
	
	//tokyo 테이블을 두번 클릭하면 설명 창 띄우기
	private void handleClikedTableViewAction(MouseEvent e) {
		selectedTokyoTable = tokyoTableView.getSelectionModel().getSelectedItem();
		selectedTokyoTableIndex = tokyoTableView.getSelectionModel().getSelectedIndex();
		
		dbinfoArrayList = infoDAO.getInfoData(selectedTokyoTable.getTokyoTourID());
		inforTour info =dbinfoArrayList.get(0);
		
		try {
			if(e.getClickCount() == 2) {
				Stage infoStage = new Stage(StageStyle.UTILITY);
				infoStage.initModality(Modality.WINDOW_MODAL);
				infoStage.initOwner(tokyoStage);
				infoStage.setTitle(selectedTokyoTable.getTokyoTourName() + "의 정보");
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
	private void handletokyoAddAction() {
		toKyo tokyo=tokyoTableView.getSelectionModel().getSelectedItem();
		myTokyoTour my = new myTokyoTour(tokyo.getTokyoTourID(),
		tokyo.getTokyoTourName());
		mytokyoListData.add(my);
		
		int count = myTokyoTourDAO.insertMyTourList(my);
		if(count != 0) {
			callAlert("추가완료:" + selectedTokyoTable.getTokyoTourName() + "을 추가하였습니다.");
			}
	}	
	
	//back버튼을 누르면 다시 도시선택으로 가는 이벤트
	private void handletokyoBackAction() {
		try {
			dbArrayList.clear();
			Stage choiceStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/cityChoice.fxml"));
			Parent root = loader.load();
			cityChoice choiceController = loader.getController();
			choiceController.choiceStage=choiceStage;
			Scene scene = new Scene(root);
			choiceStage.setScene(scene);
			tokyoStage.close();
			choiceStage.show();
		} catch (IOException e) {}	
		
	}
	
	//delete버튼을 누르면 myList에서 항목삭제
	private void handletokyoDeleteAction() {
		selectedMytoKyo = tokyoMyTourTableView.getSelectionModel().getSelectedItem();
		selectedMytoKyoIndex = tokyoMyTourTableView.getSelectionModel().getSelectedIndex();
		
		int count = myTokyoTourDAO.deleteMyTourList(selectedMytoKyo.getMyTokyoTourID());
		if(count != 0) {
			mytokyoListData.remove(selectedMytoKyoIndex);
			dbArrayList.remove(selectedMytoKyo);			
		}		
	}	

	//final버튼을 누르면 창전환 -> Final Decision창으로 전환한다.
	private void handletokyoFinalAction() {
		try {
			Stage finalStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/finalDecision.fxml"));
			Parent root = loader.load();
			finalController finalcontroller = loader.getController();
			finalcontroller.finalStage = finalStage;
			Scene scene = new Scene(root);
			finalStage.setScene(scene);
			tokyoStage.close();
			finalStage.show();			
		} catch(IOException e) {
			
		}		
	}

	//나가기
	private void handletokyoExitAction() {
		tokyoStage.close();		
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
