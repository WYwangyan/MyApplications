package com.example.mybook;

import com.example.mybook.view.NinePointLineView;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class PicturePasswordAuthenticationActivity extends PicturePasswordInputActivity {

	/*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_password_authentication);
	}
	*/

	public void picturePasswordInput(View view){
		NinePointLineView v=(NinePointLineView)findViewById(R.id.nine_point_input);
		StringBuffer sb=v.lockString;
    	if(sb.toString().equals(MainActivity.lock.toString())){
    		Intent intent = new Intent(this, SetPasswordActivity.class);
    		startActivity(intent);
    		this.finish();
    	}else{
    		Toast.makeText(getApplicationContext(), "错误！", Toast.LENGTH_SHORT).show();
    		v.redraw();
    	}
	}

}
