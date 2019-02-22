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
		//���̺�� �⺻ ����
		settingOsakaTableViewData();
		//���� ��������Ʈ ���̺�� �⺻ ����
		settingMyTourListTableViewData();
		//�ι� Ŭ���ϸ� ����â ����
		osakaTableView.setOnMouseClicked(e ->{handleClikedTableViewAction(e);});
		//Add��ư�� ������ ���� ��������Ʈ�� ������ �߰�
		osakaAddButton.setOnAction(e -> {handleosakaAddAction();});
		//back��ư�� ������ �ٽ� ���ü������� ���� �̺�Ʈ
		osakaBackButton.setOnAction(e -> {handleosakaBackAction();});
		//delete��ư�� ������ myList���� �׸����
		osakaDeleteButton.setOnAction(e -> {handleosakaDeleteAction();});
		//final��ư�� ������ â��ȯ -> Final Decisionâ���� ��ȯ�Ѵ�.
		osakaFinalButton.setOnAction(e -> {handleosakaFinalAction();});
		//������
		osakaExitButton.setOnAction(e -> {handleosakaExitAction();});
	}

	//���̺�� �⺻ ����
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
	
	//���� ��������Ʈ ���̺�� �⺻ ����
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
	
	//�ι� Ŭ���ϸ� ����â ����
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
				infoStage.setTitle(selectedOsakaTable.getOsakaTourName() + "�� ����");
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
	private void handleosakaAddAction() {
		osaka osAka=osakaTableView.getSelectionModel().getSelectedItem();
		myOsakaTour my = new myOsakaTour(osAka.getOsakaTourID(),
				osAka.getOsakaTourName());
		myosakaListData.add(my);
		
		
		int count = myOsakaTourDAO.insertMyTourList(my);
		if(count != 0) {
			callAlert("�߰��Ϸ�:" + selectedOsakaTable.getOsakaTourName() + "�� �߰��Ͽ����ϴ�.");
		}
	}	
	
	//back��ư�� ������ �ٽ� ���ü������� ���� �̺�Ʈ
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
	
	//delete��ư�� ������ myList���� �׸����
	private void handleosakaDeleteAction() {
		selectedMyosaka = osakaMyTourTableView.getSelectionModel().getSelectedItem();
		selectedMyosakaIndex = osakaMyTourTableView.getSelectionModel().getSelectedIndex();
		
		int count = myTokyoTourDAO.deleteMyTourList(selectedMyosaka.getMyOsakaTourID());
		if(count != 0) {
			myosakaListData.remove(selectedMyosakaIndex);
			dbArrayList.remove(selectedMyosaka);			
		}		
	}	

	//final��ư�� ������ â��ȯ -> Final Decisionâ���� ��ȯ�Ѵ�.
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

	//������
	private void handleosakaExitAction() {
		osakaStage.close();		
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
