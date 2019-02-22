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
	
	//*************************�α���â************************************
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
		//�α���â
		loginBtnLogIn.setOnAction(e -> {handleBtnLoginAction();});
		//ȸ������â
		btnSuper.setOnAction(e -> {handleBtnSuperAction();});
		//ȸ������â ����
		loginBtnMemberShip.setOnAction(e-> {handleBtnMemberShipAction();});
		//�����ư
		loginBtnExit.setOnAction(e-> {handleBtnExitAction();});
		
		//����Ű������ �α��� ����
		loginTextFieldPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER))
				handleBtnLoginAction();
			}
		});
	}

	//�α���â
	//�α��� ���н� �˸�â ��� ������ ǥ��
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
				callAlert("ȭ����ȯ���� : ȭ����ȯ�� ������ �߻��߽��ϴ�.");
			}			
		}
	}
	
	//ȸ������â
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
	
	//ȸ������â ����
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
	
	//�����ư
	private void handleBtnExitAction() {
		primaryStage.close();		
	}
	
	//�˸�â
	public void callAlert(String contentText) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("�˸�");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":")+1));
		alert.showAndWait();
	}
}
