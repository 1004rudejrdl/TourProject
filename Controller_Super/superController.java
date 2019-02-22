package controller;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.memberShip;

public class superController implements Initializable {
	private Stage superStage;
	
	@FXML private TableView<memberShip> superTableView;
	ObservableList<memberShip> superList = FXCollections.observableArrayList();

	ArrayList<memberShip> dbArrayListSuper;
	
	private memberShip selectedSuper;
	private int selectedSuperIndex;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Connection con = DBUtility.getConnection();
		//테이블뷰 기본셋팅
		settingTableViewMember();
		//테이블뷰 두번 클릭하면 회원정보창 띄우기
		superTableView.setOnMouseClicked(e ->{handleSuperTableAction(e);});
		
		
	}


	private void settingTableViewMember() {
		TableColumn tcID = superTableView.getColumns().get(0);
		tcID.setCellValueFactory(new PropertyValueFactory<>("memberID"));
		tcID.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcPass = superTableView.getColumns().get(1);
		tcPass.setCellValueFactory(new PropertyValueFactory<>("memberPassWord"));
		tcPass.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcName = superTableView.getColumns().get(2);
		tcName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
		tcName.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcGen = superTableView.getColumns().get(3);
		tcGen.setCellValueFactory(new PropertyValueFactory<>("memberGender"));
		tcGen.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcPhone = superTableView.getColumns().get(4);
		tcPhone.setCellValueFactory(new PropertyValueFactory<>("memberPhone"));
		tcPhone.setStyle("-fx-alignment : CENTER;");
		
		TableColumn tcEmail = superTableView.getColumns().get(5);
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("memberEmail"));
		tcEmail.setStyle("-fx-alignment : CENTER;");
		
		superTableView.setItems(superList);
		
		dbArrayListSuper = memberDAO.getMemberTotalData();
		for(memberShip member : dbArrayListSuper) {
			superList.add(member);
		}
		
	}
	
	
	//테이블뷰 두번 클릭하면 회원정보창 띄우기
	private void handleSuperTableAction(MouseEvent e) {
		selectedSuper = superTableView.getSelectionModel().getSelectedItem();
		selectedSuperIndex = superTableView.getSelectionModel().getSelectedIndex();
		
		if(e.getClickCount() == 2) {
			try {
				Stage superInfoStage = new Stage(StageStyle.UTILITY);
				superInfoStage.initModality(Modality.WINDOW_MODAL);
				superInfoStage.initOwner(superStage);
				superInfoStage.setTitle(selectedSuper.getMemberName() + "님의 회원정보창");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/superInfo.fxml"));
				Parent root = loader.load();
				
				TextField superInfoEmail = (TextField)root.lookup("#superInfoEmail");
				TextField superInfoPhone = (TextField)root.lookup("#superInfoPhone");
				TextField superInfoGen = (TextField)root.lookup("#superInfoGen");
				TextField superInfoName = (TextField)root.lookup("#superInfoName");
				PasswordField superInfoPass = (PasswordField)root.lookup("#superInfoPass");
				TextField superInfoID = (TextField)root.lookup("#superInfoID");
				Button superInfoBtnModi = (Button)root.lookup("#superInfoBtnModi");
				Button superInfoBtnDelete = (Button)root.lookup("#superInfoBtnDelete");
				Button superInfoBtnCancel = (Button)root.lookup("#superInfoBtnCancel");				
				
				superInfoEmail.setText(selectedSuper.getMemberEmail());
				superInfoPhone.setText(selectedSuper.getMemberPhone());
				superInfoGen.setDisable(true);
				superInfoGen.setText(selectedSuper.getMemberGender());
				superInfoName.setDisable(true);
				superInfoName.setText(selectedSuper.getMemberName());
				superInfoID.setDisable(true);
				superInfoID.setText(selectedSuper.getMemberID());	
				superInfoPass.setText(selectedSuper.getMemberPassWord());
				
				superInfoBtnModi.setOnAction(e2 ->{
					superList.remove(selectedSuperIndex);
					memberShip member = new memberShip(superInfoID.getText(),
							superInfoPass.getText(),
							superInfoName.getText(),
							superInfoGen.getText(),
							superInfoPhone.getText(),
							superInfoEmail.getText());
					superList.add(member);
					superInfoStage.close();
					int count = memberDAO.updateMemberData(member, selectedSuper);
					if(count !=0) {
						//superList.remove(selectedSuperIndex);
						//superList.add(selectedSuperIndex, selectedSuper);
						dbArrayListSuper.set(selectedSuperIndex, member);
						callAlert("Modi Complete : " + selectedSuper.getMemberName()+"님의 정보수정을 완료하였습니다.");
						superInfoStage.close();
					}else {
						return ;
					}
				});
				
				superInfoBtnDelete.setOnAction(e3 ->{
					int count = memberDAO.deleteMemberData(selectedSuper.getMemberID());
					if(count != 0) {
						superList.remove(selectedSuperIndex);
						dbArrayListSuper.remove(selectedSuperIndex);
						superController.callAlert("삭제 : 삭제완료");
						superInfoStage.close();
					}else {
						return ;
					}
				});
				
				superInfoBtnCancel.setOnAction(e4 -> {superInfoStage.close();});
				
				Scene scene = new Scene(root);
				superInfoStage.setScene(scene);
				superInfoStage.show();
			}catch(Exception e1) {}
			
		}
		
	}


	public static void callAlert(String contentText) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("알림");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":")+1));
		alert.showAndWait();
	}
}
