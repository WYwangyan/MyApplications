package com.example.mybook;

import com.example.mybook.view.NinePointLineView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class PicturePasswordInputActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_password_input);
	}

	public void picturePasswordInput(View view){
		NinePointLineView v=(NinePointLineView)findViewById(R.id.nine_point_input);
		StringBuffer sb=v.lockString;
    	if(sb.toString().equals(MainActivity.lock.toString())){
    		Intent intent = new Intent(this, DiaryListActivity.class);
    		startActivity(intent);
    		this.finish();
    	}else{
    		Toast.makeText(getApplicationContext(), "错误！", Toast.LENGTH_SHORT).show();
    		v.redraw();
    	}
	}

}
