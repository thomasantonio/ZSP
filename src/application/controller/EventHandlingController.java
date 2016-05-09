package application.controller;


import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EventHandlingController implements Initializable{


	@FXML private Button btnNumber01;
	@FXML private Button btnNumber02;
	@FXML private Button btnNumber03;
	@FXML private Button btnNumber04;
	@FXML private Button btnNumber05;
	@FXML private Button btnNumber06;
	@FXML private Button btnNumber07;
	@FXML private Button btnNumber08;
	@FXML private Button btnNumber09;
	@FXML private Button btnNumber00;
	@FXML private Button btnNumberDot;
	@FXML private Button btnEnter;
	@FXML private Button btnCorrect;
	@FXML private TextField txtWater;
	@FXML private TextField txtCurrent;
	@FXML private Label lblWater;
	@FXML private Label lblCurrent;
	@FXML private Label lblL;
	@FXML private Label lblKw;

	private Boolean bool1 = false;
	private Boolean bool2 = false;

	@FXML
	private void listenerNumbers(MouseEvent event){
		Button btn = (Button) event.getSource();
		
		if(bool1){
			txtWater.setText(txtWater.getText() + btn.getText());
			System.out.println(txtWater.getText());
		}
		if(bool2){
			txtCurrent.setText(txtCurrent.getText() + btn.getText());
			System.out.println(txtCurrent.getText());
		}
	}

	@FXML
	private void listenerOk(MouseEvent event){
		LocalDateTime now = LocalDateTime.now(); 
		DateTimeFormatter df = DateTimeFormatter.BASIC_ISO_DATE; 
		String url = null;
		
		if (bool1){
			url = "http://192.168.43.111:821/addverbrauch?typ=wasser&date="+now.format(df)+"&value="+txtWater.getText();
			System.out.println(url);
		}
		if(bool2){
			url = "http://192.168.43.111:821/addverbrauch?typ=strom&date="+now.format(df)+"&value="+txtCurrent.getText();
			System.out.println(url);
		}
		
		new Thread(new BackgroundAnsware(url));
	}

	@FXML
	private void listenerCorrect(MouseEvent event){
		if (bool1){
			if(txtWater.getText().length() > 0){
				txtWater.setText(correct(txtWater.getText()));
				System.out.println(txtWater.getText());
			}
		}
		if (bool2){
			if(txtCurrent.getText().length() > 0){
				txtCurrent.setText(correct(txtCurrent.getText()));
				System.out.println(txtCurrent.getText());
			}
		}

	}

	private String correct(String str){
		char[] charArray = str.toCharArray();
		char[] newCharArray = Arrays.copyOf(charArray, charArray.length-1);
		return String.valueOf(newCharArray);
	}

	@FXML
	private void txtWaterListener (MouseEvent event){
		String url = "http://192.168.0.111:821/getconsumption?typ=wasser";
		System.out.println(url);
		new Thread(new BackgroundAnsware(url));
		
		bool1=true;
		bool2=false;
	}

	@FXML
	private void txtCurrentListener (MouseEvent event){
		String url = "http://192.168.0.111:821/getconsumption?typ=strom";
		System.out.println(url);
		new Thread(new BackgroundAnsware(url));
		
		bool1=false;
		bool2=true;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtWater = new TextField();
		txtCurrent = new TextField();
	}

}
