package com.example.mybook;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class StringPasswordActivity extends Activity {
	
	EditText editText1;
	EditText editText2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_string_password);
		
		editText1=(EditText)findViewById(R.id.editText1);
		editText2=(EditText)findViewById(R.id.editText2);
		
		editText1.setFocusable(true);
		editText1.setFocusableInTouchMode(true);
		editText1.requestFocus();
		
		editText1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editText1.setFocusable(true);
				editText1.setFocusableInTouchMode(true);
				editText1.requestFocus();
				
				editText2.setText(null);
				editText2.setFocusable(false);
			}
		});
		
		editText2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String string1=editText1.getText().toString();
				if(string1.equals("")){
					Toast.makeText(getApplicationContext(), "���벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
					editText1.requestFocus();
				}else {
					editText2.setFocusable(true);
					editText2.setFocusableInTouchMode(true);
					editText2.requestFocus();
					
					editText1.setFocusable(false);
				}
			}
		});
		
	}
	
	public void saveStringPassword(View view){
		String string1=editText1.getText().toString();
		String string2=editText2.getText().toString();
		if(string1.equals(string2)){
			MainActivity.passwordType=1;
			MainActivity.stringPassword=string1;
			Toast.makeText(getApplicationContext(), "密码设置成功！", Toast.LENGTH_SHORT).show();
			this.finish();
		}else {
			Toast.makeText(getApplicationContext(), "确认密码与设置密码不一致！", Toast.LENGTH_SHORT).show();
			editText1.setText(null);
			editText2.setText(null);
			editText1.setFocusable(true);
			editText1.setFocusableInTouchMode(true);
			editText1.requestFocus();
			editText2.setFocusable(false);
		}
	}

}
