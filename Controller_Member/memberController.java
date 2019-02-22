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
	//****************************회원가입창**************************************
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
		//콤보박스 셋팅
		settingComboBoxGender();	
		//중복확인
		memberCheckOver.setOnAction(e ->{handleBtnCheckAction();});
		//finish버튼
		memberBtnFinish.setOnAction(e -> {handleBtnFinishAction();});
		//cancel버튼
		memberBtnCancel.setOnAction(e -> {handelBtnCancelAction();});
	}

	//콤보박스 셋팅
	private void settingComboBoxGender() {
		memberComboList.addAll("남성","여성");
		memberCmbGenger.setItems(memberComboList);
	}

	//중복확인
	private void handleBtnCheckAction() {
		memberShip checkMemberID = null;
		memberDAO member = new memberDAO();
		
		checkMemberID = new memberShip(memberID.getText().trim()); 
		member.checkMemberID(checkMemberID);		
		
	}
	
	//finish버튼
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
				callAlert("회원가입성공 : 회원가입을 축하드립니다.");				
			}
			Stage stage1 = (Stage)memberBtnFinish.getScene().getWindow();
			stage1.close();		
	}	
	
	//cancel버튼
	private void handelBtnCancelAction() {
		Stage stage = (Stage)memberBtnCancel.getScene().getWindow();
		stage.close();		
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
