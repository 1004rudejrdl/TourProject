package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.memberShip;

public class memberController implements Initializable{
	//****************************ȸ������â**************************************
	public Stage memberStage;
	@FXML private TextField memberID;
	@FXML private PasswordField memberPassword;
	@FXML private TextField memberName;
	@FXML private ComboBox<String> memberCmbGenger;
	ObservableList<String> memberComboList = FXCollections.observableArrayList();
	@FXML private TextField memberPhone;
	@FXML private TextField memberEMail;
	@FXML private Button memberCheckOver;	
	@FXML private Button memberBtnFinish;
	@FXML private Button memberBtnCancel;
	//******************************************************************************

	ArrayList<memberShip> dbMember;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Connection con = DBUtility.getConnection();
		//�޺��ڽ� ����
		settingComboBoxGender();	
		//�ߺ�Ȯ��
		memberCheckOver.setOnAction(e ->{handleBtnCheckAction();});
		//finish��ư
		memberBtnFinish.setOnAction(e -> {handleBtnFinishAction();});
		//cancel��ư
		memberBtnCancel.setOnAction(e -> {handelBtnCancelAction();});
	}

	//�޺��ڽ� ����
	private void settingComboBoxGender() {
		memberComboList.addAll("����","����");
		memberCmbGenger.setItems(memberComboList);
	}

	//�ߺ�Ȯ��
	private void handleBtnCheckAction() {
		memberShip checkMemberID = null;
		memberDAO member = new memberDAO();
		
		checkMemberID = new memberShip(memberID.getText().trim()); 
		member.checkMemberID(checkMemberID);		
		
	}
	
	//finish��ư
	private void handleBtnFinishAction() {
		memberShip member = new memberShip(memberID.getText(),
				memberPassword.getText(),
				memberName.getText(),
				memberCmbGenger.getSelectionModel().getSelectedItem(),
				memberPhone.getText(),
				memberEMail.getText());
		int count = memberDAO.insertMemberData(member);		
		
		//if(memberID.getText().equals("") || memberPassword.getText().equals(""))

			if(count != 0) {
				callAlert("ȸ�����Լ��� : ȸ�������� ���ϵ帳�ϴ�.");				
			}
			Stage stage1 = (Stage)memberBtnFinish.getScene().getWindow();
			stage1.close();		
	}	
	
	//cancel��ư
	private void handelBtnCancelAction() {
		Stage stage = (Stage)memberBtnCancel.getScene().getWindow();
		stage.close();		
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
