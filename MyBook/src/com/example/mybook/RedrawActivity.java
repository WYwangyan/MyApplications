package com.example.mybook;

import com.example.mybook.view.NinePointLineView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;

public class RedrawActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redraw);
	}
	
	public void cancel(View view){
		this.finish();
	}

	public void confirm(View view){
		NinePointLineView v=(NinePointLineView)findViewById(R.id.nine_point_redraw);
		MainActivity.temp2=v.lockString;
		if(MainActivity.temp1.toString().equals(MainActivity.temp2.toString())){
			MainActivity.passwordType=2;
			MainActivity.lock=MainActivity.temp1;
			Toast.makeText(getApplicationContext(), "设置成功！", Toast.LENGTH_SHORT).show();
			this.finish();
		}else {
			Toast.makeText(getApplicationContext(), "不一致！", Toast.LENGTH_SHORT).show();
    		v.redraw();
		}
	}

}
