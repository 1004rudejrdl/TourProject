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
import model.myOkinawaTour;
import model.okinawa;

public class okinawaController implements Initializable{
	public Stage okinawaStage;
	
	@FXML private Button okinawaAddButton;
	@FXML private Button okinawaBackButton;
	@FXML private Button okinawaFinalButton;
	@FXML private Button okinawaDeleteButton;
	@FXML private Button okinawaExitButton;
	
	@FXML private TableView<okinawa> okinawaTableView;
	
	@FXML private TableView<myOkinawaTour> okinawaMyTourTableView;
	
	ObservableList<okinawa> okinawaListData = FXCollections.observableArrayList();
	ObservableList<myOkinawaTour> myokinawaListData = FXCollections.observableArrayList();
	
	ArrayList<okinawa> dbArrayList;
	ArrayList<myOkinawaTour> dbArrayList1;
	ArrayList<inforTour> dbinfoArrayList;
	
	private myOkinawaTour selectedMyokinawa;
	private int selectedMyokinawaIndex;
	
	private okinawa selectedOkinawaTable;
	private int selectedOkinawaTableIndex;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Connection con = DBUtility.getConnection();
		//���̺�� �⺻ ����
		settingOkinawaTableViewData();
		//���� ��������Ʈ ���̺�� �⺻ ����
		settingMyTourListTableViewData();
		//���̺� �ι� Ŭ���ϸ� ����â ����
		okinawaTableView.setOnMouseClicked(e -> {handleClikedTableViewAction(e);});
		//Add��ư�� ������ ���� ��������Ʈ�� ������ �߰�
		okinawaAddButton.setOnAction(e -> {handleokinawaAddAction();});
		//back��ư�� ������ �ٽ� ���ü������� ���� �̺�Ʈ
		okinawaBackButton.setOnAction(e -> {handleokinawaBackAction();});
		//delete��ư�� ������ myList���� �׸����
		okinawaDeleteButton.setOnAction(e -> {handleokinawaDeleteAction();});
		//final��ư�� ������ â��ȯ -> Final Decisionâ���� ��ȯ�Ѵ�.
		okinawaFinalButton.setOnAction(e -> {handleokinawaFinalAction();});
		//������
		okinawaExitButton.setOnAction(e -> {handleokinawaExitAction();});
	}

	//���̺�� �⺻ ����
	private void settingOkinawaTableViewData() {
		TableColumn tcOkinawaID = okinawaTableView.getColumns().get(0);
		tcOkinawaID.setCellValueFactory(new PropertyValueFactory<>("okinawaTourID"));
		tcOkinawaID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcOkinawaName = okinawaTableView.getColumns().get(1);
		tcOkinawaName.setCellValueFactory(new PropertyValueFactory<>("okinawaTourName"));
		tcOkinawaName.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcOkinawaClass = okinawaTableView.getColumns().get(2);
		tcOkinawaClass.setCellValueFactory(new PropertyValueFactory<>("okinawaTourClass"));
		tcOkinawaClass.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcOkinawaCharge = okinawaTableView.getColumns().get(3);
		tcOkinawaCharge.setCellValueFactory(new PropertyValueFactory<>("okinawaTourCharge"));
		tcOkinawaCharge.setStyle("-fx-alignment : CENTER;");

		okinawaTableView.setItems(okinawaListData);
		
		dbArrayList = okinawaDAO.getokiNawaData();
		for(okinawa okiNawa : dbArrayList) {
			okinawaListData.add(okiNawa);
		}
	}
	
	//���� ��������Ʈ ���̺�� �⺻ ����
	private void settingMyTourListTableViewData() {
		TableColumn tcMyOkinawaID = okinawaMyTourTableView.getColumns().get(0);
		tcMyOkinawaID.setCellValueFactory(new PropertyValueFactory<>("myOkinawaTourID"));
		tcMyOkinawaID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcMyOkinawaName = okinawaMyTourTableView.getColumns().get(1);
		tcMyOkinawaName.setCellValueFactory(new PropertyValueFactory<>("myOkinawaTourName"));
		tcMyOkinawaName.setStyle("-fx-alignment : CENTER;");
		
		okinawaMyTourTableView.setItems(myokinawaListData);
		myokinawaListData.clear();

		dbArrayList1 = myOkinawaTourDAO.getOkinawaData();
		for(myOkinawaTour myokinawatour : dbArrayList1) {
			myokinawaListData.add(myokinawatour);
		}
		
	}
	
	//���̺� �ι� Ŭ���ϸ� ����â ����
	private void handleClikedTableViewAction(MouseEvent e) {
		selectedOkinawaTable = okinawaTableView.getSelectionModel().getSelectedItem();
		selectedOkinawaTableIndex = okinawaTableView.getSelectionModel().getSelectedIndex();
		
		dbinfoArrayList = infoDAO.getInfoData(selectedOkinawaTable.getOkinawaTourID());
		inforTour info = dbinfoArrayList.get(0);
		
		try {
			if(e.getClickCount() == 2) {
				Stage infoStage = new Stage(StageStyle.UTILITY);
				infoStage.initModality(Modality.WINDOW_MODAL);
				infoStage.initOwner(okinawaStage);
				infoStage.setTitle(selectedOkinawaTable.getOkinawaTourName() + "�� ����");
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

	
	//Add��ư�� ������ ���� ��������Ʈ�� ������ �߰�
	private void handleokinawaAddAction() {
		okinawa okiNawa = okinawaTableView.getSelectionModel().getSelectedItem();
		myOkinawaTour my = new myOkinawaTour(okiNawa.getOkinawaTourID(),
					okiNawa.getOkinawaTourName());
		myokinawaListData.add(my);
		
		
		int count = myOkinawaTourDAO.insertMyTourList(my);
		if(count != 0) {
			callAlert("�߰��Ϸ�:" + selectedOkinawaTable.getOkinawaTourName() + "�� �߰��Ͽ����ϴ�.");
		}
	}
	
	//back��ư�� ������ �ٽ� ���ü������� ���� �̺�Ʈ
	private void handleokinawaBackAction() {
		try {
			dbArrayList.clear();
			Stage choiceStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/cityChoice.fxml"));
			Parent root = loader.load();
			cityChoice choiceController = loader.getController();
			choiceController.choiceStage=choiceStage;
			Scene scene = new Scene(root);
			choiceStage.setScene(scene);
			okinawaStage.close();
			choiceStage.show();
		} catch (IOException e) {}	
		
	}
	
	//delete��ư�� ������ myList���� �׸����
	private void handleokinawaDeleteAction() {
		selectedMyokinawa = okinawaMyTourTableView.getSelectionModel().getSelectedItem();
		selectedMyokinawaIndex = okinawaMyTourTableView.getSelectionModel().getSelectedIndex();
		
		int count = myOkinawaTourDAO.deleteMyTourList(selectedMyokinawa.getMyOkinawaTourID());
		if(count != 0) {
			myokinawaListData.remove(selectedMyokinawaIndex);
			dbArrayList.remove(selectedMyokinawa);			
		}		
	}	

	//final��ư�� ������ â��ȯ -> Final Decisionâ���� ��ȯ�Ѵ�.
	private void handleokinawaFinalAction() {
		try {
			Stage finalStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/finalDecision.fxml"));
			Parent root = loader.load();
			finalController finalcontroller = loader.getController();
			finalcontroller.finalStage = finalStage;
			Scene scene = new Scene(root);
			finalStage.setScene(scene);
			okinawaStage.close();
			finalStage.show();			
		} catch(IOException e) {
			
		}
		
	}

	//������
	private void handleokinawaExitAction() {
		okinawaStage.close();		
	}
	
	//�˸�â
	public static void callAlert(String contentText) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("�˸�");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":")+1));
		alert.showAndWait();
	}
}
