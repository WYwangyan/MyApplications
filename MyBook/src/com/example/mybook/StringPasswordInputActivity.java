package com.example.mybook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StringPasswordInputActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_string_password_input);
	}

	public void stringPasswordInput(View view){
    	EditText editText = (EditText) findViewById(R.id.string_password_input);
    	String stringPasswordInput = editText.getText().toString();
    	if(stringPasswordInput.equals(MainActivity.stringPassword)){
    		Intent intent = new Intent(this, DiaryListActivity.class);
    		startActivity(intent);
    		this.finish();
    	}else{
    		Toast.makeText(getApplicationContext(), "密码错误！", Toast.LENGTH_SHORT).show();
    		editText.setText(null);
    	}
	}
}
