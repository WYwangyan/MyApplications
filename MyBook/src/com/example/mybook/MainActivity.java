package com.example.mybook;

import java.util.Timer;
import java.util.TimerTask;

import com.example.mybook.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	
	public static int passwordType=0;
	public static String stringPassword=null;
	public static StringBuffer lock=new StringBuffer();
	public static StringBuffer temp1=new StringBuffer();
	public static StringBuffer temp2=new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Timer timer=new Timer();
		timer.schedule(new enter(), 3000);
	}
	
    class enter extends TimerTask {
        public void run(){
        	Intent intent=null;
        	switch (passwordType) {
			case 0:
				intent = new Intent(MainActivity.this, DiaryListActivity.class);
				break;
			case 1:
				intent = new Intent(MainActivity.this, StringPasswordInputActivity.class);
				break;
			case 2:
				intent = new Intent(MainActivity.this, PicturePasswordInputActivity.class);
				break;
			default:
				break;
			}      		
        	startActivity(intent);
        	MainActivity.this.finish();
        }
    }
    
}
