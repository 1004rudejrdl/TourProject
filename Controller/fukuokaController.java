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
import model.fukuoka;
import model.inforTour;
import model.myFukuokaTour;

public class fukuokaController implements Initializable{
	public Stage fukuokaStage;
	
	@FXML private Button fukuokaAddButton;
	@FXML private Button fukuokaBackButton;
	@FXML private Button fukuokaFinalButton;
	@FXML private Button fukuokaDeleteButton;
	@FXML private Button fukuokaExitButton;
	
	@FXML private TableView<fukuoka> fukuokaTableView;
	
	@FXML private TableView<myFukuokaTour> fukuokaMyTourTableView;
	
	ObservableList<fukuoka> fukuokaListData = FXCollections.observableArrayList();
	ObservableList<myFukuokaTour> myfukuokaListData = FXCollections.observableArrayList();
	
	ArrayList<fukuoka> dbArrayList;
	ArrayList<myFukuokaTour> dbArrayList1;
	ArrayList<inforTour> dbinfoArrayList;
	
	private myFukuokaTour selectedMyfukuoka;
	private int selectedMyfukuokaIndex;
	
	private fukuoka selectedFukuokaTable;
	private int selectedFukuokaTableIndex;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Connection con = DBUtility.getConnection();
		//���̺�� �⺻ ����
		settingOkinawaTableViewData();
		//���� ��������Ʈ ���̺�� �⺻ ����
		settingMyTourListTableViewData();
		//���̺� �ι� Ŭ���ϸ� ����â ����
		fukuokaTableView.setOnMouseClicked(e -> {handleClikedFukuokaTableViewAction(e);});
		//Add��ư�� ������ ���� ��������Ʈ�� ������ �߰�
		fukuokaAddButton.setOnAction(e -> {handlefukuokaAddAction();});
		//back��ư�� ������ �ٽ� ���ü������� ���� �̺�Ʈ
		fukuokaBackButton.setOnAction(e -> {handlefukuokaBackAction();});
		//delete��ư�� ������ myList���� �׸����
		fukuokaDeleteButton.setOnAction(e -> {handlefukuokaDeleteAction();});
		//final��ư�� ������ â��ȯ -> Final Decisionâ���� ��ȯ�Ѵ�.
		fukuokaFinalButton.setOnAction(e -> {handlefukuokaFinalAction();});
		//������
		fukuokaExitButton.setOnAction(e -> {handlefukuokaExitAction();});
	}

	//���̺�� �⺻ ����
	private void settingOkinawaTableViewData() {
		TableColumn tcFukuokaID = fukuokaTableView.getColumns().get(0);
		tcFukuokaID.setCellValueFactory(new PropertyValueFactory<>("fukuokaTourID"));
		tcFukuokaID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcFukuokaName = fukuokaTableView.getColumns().get(1);
		tcFukuokaName.setCellValueFactory(new PropertyValueFactory<>("fukuokaTourName"));
		tcFukuokaName.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcFukuokaClass = fukuokaTableView.getColumns().get(2);
		tcFukuokaClass.setCellValueFactory(new PropertyValueFactory<>("fukuokaTourClass"));
		tcFukuokaClass.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcFukuokaCharge = fukuokaTableView.getColumns().get(3);
		tcFukuokaCharge.setCellValueFactory(new PropertyValueFactory<>("fukuokaTourCharge"));
		tcFukuokaCharge.setStyle("-fx-alignment : CENTER;");

		fukuokaTableView.setItems(fukuokaListData);
		
		dbArrayList = fukuokaDAO.getfukuOkaData();
		for(fukuoka fukuOka : dbArrayList) {
			fukuokaListData.add(fukuOka);
		}
	}
	
	//���� ��������Ʈ ���̺�� �⺻ ����
	private void settingMyTourListTableViewData() {
		TableColumn tcMyFukuokaID = fukuokaMyTourTableView.getColumns().get(0);
		tcMyFukuokaID.setCellValueFactory(new PropertyValueFactory<>("myFukuokaTourID"));
		tcMyFukuokaID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcMyFukuokaName = fukuokaMyTourTableView.getColumns().get(1);
		tcMyFukuokaName.setCellValueFactory(new PropertyValueFactory<>("myFukuokaTourName"));
		tcMyFukuokaName.setStyle("-fx-alignment : CENTER;");
		
		fukuokaMyTourTableView.setItems(myfukuokaListData);
		myfukuokaListData.clear();
		dbArrayList1 = myFukuokaTourDAO.getFukuokaData();
		for(myFukuokaTour myfukuokatour : dbArrayList1) {
			myfukuokaListData.add(myfukuokatour);
		}
		
	}
	
	//���̺� �ι� Ŭ���ϸ� ����â ����
	private void handleClikedFukuokaTableViewAction(MouseEvent e) {
		selectedFukuokaTable = fukuokaTableView.getSelectionModel().getSelectedItem();
		selectedFukuokaTableIndex = fukuokaTableView.getSelectionModel().getSelectedIndex();
		
		dbinfoArrayList = infoDAO.getInfoData(selectedFukuokaTable.getFukuokaTourID());
		inforTour info = dbinfoArrayList.get(0);
		
		try {
			if(e.getClickCount() == 2) {
				Stage infoStage = new Stage(StageStyle.UTILITY);
				infoStage.initModality(Modality.WINDOW_MODAL);
				infoStage.initOwner(fukuokaStage);
				infoStage.setTitle(selectedFukuokaTable.getFukuokaTourName() + "�� ����");
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
	private void handlefukuokaAddAction() {
		fukuoka fukuOka = fukuokaTableView.getSelectionModel().getSelectedItem();
		myFukuokaTour my = new myFukuokaTour(fukuOka.getFukuokaTourID(),
				fukuOka.getFukuokaTourName());
		myfukuokaListData.add(my);
		
		
		int count = myFukuokaTourDAO.insertMyTourList(my);
		if(count != 0) {
			callAlert("�߰��Ϸ� :" + selectedFukuokaTable.getFukuokaTourName() + "�� �߰��Ͽ����ϴ�.");
		}
	}	
	
	//back��ư�� ������ �ٽ� ���ü������� ���� �̺�Ʈ
	private void handlefukuokaBackAction() {
		try {
			dbArrayList.clear();
			Stage choiceStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/cityChoice.fxml"));
			Parent root = loader.load();
			cityChoice choiceController = loader.getController();
			choiceController.choiceStage=choiceStage;
			Scene scene = new Scene(root);
			choiceStage.setScene(scene);
			fukuokaStage.close();
			choiceStage.show();
		} catch (IOException e) {}	
		
	}
	
	//delete��ư�� ������ myList���� �׸����
	private void handlefukuokaDeleteAction() {
		selectedMyfukuoka = fukuokaMyTourTableView.getSelectionModel().getSelectedItem();
		selectedMyfukuokaIndex = fukuokaMyTourTableView.getSelectionModel().getSelectedIndex();
		
		int count = myOkinawaTourDAO.deleteMyTourList(selectedMyfukuoka.getMyFukuokaTourID());
		if(count != 0) {
			myfukuokaListData.remove(selectedMyfukuokaIndex);
			dbArrayList.remove(selectedMyfukuoka);			
		}		
	}	

	//final��ư�� ������ â��ȯ -> Final Decisionâ���� ��ȯ�Ѵ�.
	private void handlefukuokaFinalAction() {
		try {
			Stage finalStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/finalDecision.fxml"));
			Parent root = loader.load();
			finalController finalcontroller = loader.getController();
			finalcontroller.finalStage = finalStage;
			Scene scene = new Scene(root);
			finalStage.setScene(scene);
			fukuokaStage.close();
			finalStage.show();			
		} catch(IOException e) {
			
		}
		
	}

	//������
	private void handlefukuokaExitAction() {
		fukuokaStage.close();		
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
