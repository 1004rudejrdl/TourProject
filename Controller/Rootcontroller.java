package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Rootcontroller implements Initializable {
	public Stage primaryStage;
	
	//*************************로그인창************************************
	@FXML private TextField loginTextFieldID;
	@FXML private PasswordField loginTextFieldPassword;
	@FXML private Label errorMessage;
	@FXML private Button loginBtnLogIn;
	@FXML private Button loginBtnMemberShip;
	@FXML private Button loginBtnExit;
	@FXML private Button btnSuper;
	@FXML private ImageView imageID;
	@FXML private ImageView imagePass;
	//**********************************************************************

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//로그인창
		loginBtnLogIn.setOnAction(e -> {handleBtnLoginAction();});
		//회원관리창
		btnSuper.setOnAction(e -> {handleBtnSuperAction();});
		//회원가입창 띄우기
		loginBtnMemberShip.setOnAction(e-> {handleBtnMemberShipAction();});
		//종료버튼
		loginBtnExit.setOnAction(e-> {handleBtnExitAction();});
		
		//엔터키누르면 로그인 성공
		loginTextFieldPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER))
				handleBtnLoginAction();
			}
		});
	}

	//로그인창
	//로그인 실패시 알림창 대신 문구로 표시
	private void handleBtnLoginAction() {
		errorMessage.setStyle("-fx-text-fill: red;");
		if(loginTextFieldID.getText().equals("")) {
			errorMessage.setText("Please Check your ID");			
		} else if(loginTextFieldPassword.getText().equals("")) {
			errorMessage.setText("Please Check your PassWord");
		} else if(!(loginTextFieldID.getText().equals("my")) || !(loginTextFieldPassword.getText().equals("1"))) {
			errorMessage.setText("Please Check ID or PassWord");
			loginTextFieldID.clear();
			loginTextFieldPassword.clear();
			return ;
		}
		
		if(loginTextFieldID.getText().equals("my") && loginTextFieldPassword.getText().equals("1")) {
			try {
				Stage chartStage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/chartMain.fxml"));
				Parent root = loader.load();
				chartController chart = loader.getController();
				chart.chartStage=chartStage;
				Scene scene = new Scene(root);
				chartStage.setScene(scene);
				primaryStage.close();
				chartStage.show();
			} catch (IOException e) {	
				callAlert("화면전환오류 : 화면전환에 오류가 발생했습니다.");
			}			
		}
	}
	
	//회원관리창
	private void handleBtnSuperAction() {
		try {
			Stage superStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("../view/superWindow.fxml"));
			Scene scene = new Scene(root);
			superStage.setTitle("Administrator");
			superStage.setScene(scene);
			superStage.show();
		} catch(IOException e) {}
		
	}
	
	//회원가입창 띄우기
	private void handleBtnMemberShipAction() {
		try {
			Stage memberStage=new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("../view/membership.fxml"));
			Scene scene = new Scene(root);
			memberStage.setTitle("MemberShip");
			memberStage.setScene(scene);
			memberStage.show();
		} catch (IOException e) {	}
	}
	
	//종료버튼
	private void handleBtnExitAction() {
		primaryStage.close();		
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
