package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class cityChoice implements Initializable{
	public Stage choiceStage;
	@FXML private Button cityTokyo;
	@FXML private Button cityOsaka;
	@FXML private Button citySapporo;
	@FXML private Button cityOkinawa;
	@FXML private Button cityFukuoka;
	@FXML private Button cityBack;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//tokyo ��ư�� ������ tokyoList â��ȯ
		cityTokyo.setOnAction(e -> {handleBtnCityTokyoAction();});
		//osaka ��ư�� ������ osakaList â��ȯ
		cityOsaka.setOnAction(e ->{handleBtnCityOsakaAction();});
		//Sapporo ��ư�� ������ sapporoListâ��ȯ
		citySapporo.setOnAction(e -> {handleBtnCitySapporoAction();});
		//Okinawa ��ư�� ������ okinawaListâ��ȯ
		cityOkinawa.setOnAction(e -> {handleBtnCityOkinawaAction();});
		//Fukuoka ��ư�� ������ fukuokaListâ��ȯ
		cityFukuoka.setOnAction(e -> {handleBtnCityFukuokaAction();});
		//x��ư�� ������ �α���â���� �ٽ���ȯ
		cityBack.setOnAction(e -> {handleBtnCityBackAction();});
		
	}
	
	//tokyo ��ư�� ������ tokyoList â��ȯ
	private void handleBtnCityTokyoAction() {
		try {
			Stage tokyoStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/tok.fxml"));
			Parent root = loader.load();
			tokyoController tokyocontroller = loader.getController();
			tokyocontroller.tokyoStage = tokyoStage;
			Scene scene = new Scene(root);
			tokyoStage.setScene(scene);
			choiceStage.close();
			tokyoStage.show();
		} catch(IOException e) {}
		
	}
	
	//osaka ��ư�� ������ tokyoList â��ȯ
	private void handleBtnCityOsakaAction() {
		try {
			Stage osakaStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/osaka.fxml"));
			Parent root = loader.load();
			osakaController osakacontroller = loader.getController();
			osakacontroller.osakaStage = osakaStage;
			Scene scene = new Scene(root);
			osakaStage.setScene(scene);
			choiceStage.close();
			osakaStage.show();
		} catch(IOException e) {}
		
	}

	//Sapporo ��ư�� ������ tokyoListâ��ȯ
	private void handleBtnCitySapporoAction() {
		try {
			Stage sapporoStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/sapporo.fxml"));
			Parent root = loader.load();
			sapporoController sapporocontroller = loader.getController();
			sapporocontroller.sapporoStage = sapporoStage;
			Scene scene = new Scene(root);
			sapporoStage.setScene(scene);
			choiceStage.close();
			sapporoStage.show();
		}catch(IOException e) {}
		
	}
	
	//Okinawa ��ư�� ������ okinawaListâ��ȯ
	private void handleBtnCityOkinawaAction() {
		try {
			Stage okinawStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/okinawa.fxml"));
			Parent root = loader.load();
			okinawaController okinawacontroller = loader.getController();
			okinawacontroller.okinawaStage = okinawStage;
			Scene scene = new Scene(root);
			okinawStage.setScene(scene);
			choiceStage.close();
			okinawStage.show();
		}catch(IOException e) {}
	}


	//Fukuoka ��ư�� ������ fukuokaListâ��ȯ
	private void handleBtnCityFukuokaAction() {
		try {
			Stage fukuokaStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/fukuoka.fxml"));
			Parent root = loader.load();
			fukuokaController fukuokacontroller = loader.getController();
			fukuokacontroller.fukuokaStage = fukuokaStage;
			Scene scene = new Scene(root);
			fukuokaStage.setScene(scene);
			choiceStage.close();
			fukuokaStage.show();
		}catch(IOException e) {}
	}
	
	//x��ư�� ������ �α���â���� �ٽ���ȯ
	private void handleBtnCityBackAction() {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
			Parent root = loader.load();
			Rootcontroller rootController = loader.getController();
			rootController.primaryStage = primaryStage;
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			choiceStage.close();
			primaryStage.show();
		} catch (IOException e) {}	
		
	}

}
