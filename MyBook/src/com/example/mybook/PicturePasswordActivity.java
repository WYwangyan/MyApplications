package com.example.mybook;



import com.example.mybook.view.NinePointLineView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class PicturePasswordActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_password);
	}
	
	public void cancel(View view){
		this.finish();
	}

	public void next(View view){
		NinePointLineView v=(NinePointLineView)findViewById(R.id.nine_point);
		if(v.lockString.toString().equals(""))
			Toast.makeText(getApplicationContext(), "图案密码不能为空！", Toast.LENGTH_SHORT).show();
		else{
			MainActivity.temp1=v.lockString;
			Intent intent = new Intent(this,RedrawActivity.class);
			startActivity(intent);
			this.finish();
		}
	}
}
