package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.FixerRate;
public class ExchangeRateFxController {
    @FXML private Label lbfrom;
    @FXML private TextField tffrom;
    @FXML private TextField tfjpy;
    @FXML private Label lbto;
    @FXML private RadioButton rdother;
    @FXML private ComboBox<String> cbfinance;
    @FXML private TextField tfusd;
    @FXML private Button btncon;
    @FXML private TextField tfto;
    @FXML private RadioButton rdkrw;
    @FXML private TextField tfcny;
    @FXML private Label lbboard;
    @FXML private ToggleGroup toggle;
    //static 변환 메서드
	public static double fromKRW(double krwMoney, double exchangeRatio){
		return krwMoney/exchangeRatio;
	}
	public static double toKRW(double otherMoney, double exchangeRatio){
		return otherMoney*exchangeRatio;
	}
	public static double toD(String s){
		if(s==null || s.equals("")){
			return 0.0;
		}else {
			return Double.parseDouble(s.trim());
		}
	}
    @FXML
    void initialize() {
    	cbfinance.getItems().addAll("USD","JPY","CNY");
    }
    //콤보박스의 아이템을 선택
    @FXML
    void cbdistanceOnAction(ActionEvent event) {
    	if(rdother.isSelected()){
    		String value = (String) cbfinance.getValue();
        	lbto.setText(value);
        	lbfrom.setText("KRW");
        	String krwMoney=tffrom.getText().equals("")?"0.0":tffrom.getText().trim();
        	double exchangeRatio=1.0;
        	if(value.equals("USD")){
        		exchangeRatio=toD(tfusd.getText().trim());
        	}else if(value.equals("JPY")){
        		exchangeRatio=toD(tfjpy.getText().trim());
        	}else if(value.equals("CNY")){
        		exchangeRatio=toD(tfcny.getText().trim());
        	}
        	double d=fromKRW(toD(krwMoney),exchangeRatio );
    		tfto.setText(String.format("%.2f", d));
    	}else{
    		String value = (String) cbfinance.getValue();
        	lbfrom.setText(value);
        	lbto.setText("KRW");
        	String otherMoney=tffrom.getText().equals("")?"0.0":tffrom.getText().trim();
        	double exchangeRatio=1.0;
        	if(value.equals("USD")){
        		exchangeRatio=toD(tfusd.getText().trim());
        	}else if(value.equals("JPY")){
        		exchangeRatio=toD(tfjpy.getText().trim());
        	}else if(value.equals("CNY")){
        		exchangeRatio=toD(tfcny.getText().trim());
        	}
        	double d=toKRW(toD(otherMoney),exchangeRatio );
    		tfto.setText(String.format("%.2f", d));
    	}
    }
    @FXML
    void onConvertAction(ActionEvent event) {
    	//미국 달러
    	String you="USD";      //미국 달러 
		String date=RestDay.todates();
		FixerRate finance=FixerConvert.convert(date, you); 
		tfusd.setText(finance.krw+"");   
		//일본 엔
		you="JPY";
		finance=FixerConvert.convert(date, you); 
		tfjpy.setText(finance.krw+"");   
		//중국 위엔
		you="CNY";
		finance=FixerConvert.convert(date, you); 
		tfcny.setText(finance.krw+"");   
    }
    //라디오-다른 국가의 화폐로 환전
    @FXML
    void onOtherAction(ActionEvent event) {}
    //라디오-한화로 환전
    @FXML
    void onKrwAction(ActionEvent event) {}
    //최신 환율 가져오기
}

