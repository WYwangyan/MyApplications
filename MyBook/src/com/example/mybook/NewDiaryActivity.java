package com.example.mybook;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.mybook.database.DBAdapter;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.widget.EditText;

public class NewDiaryActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_diary);
		setTitle("新建日记 ");
	}
	
	public boolean isValuable(String s){
		if(s==null||s.length()==0)
			return false;
		for(int i=0;i<s.length();i++)
			if(s.charAt(i)!=' ')
				return true;
		return false;
	}
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event){
		EditText editText=(EditText)findViewById(R.id.diary);
    	String body=editText.getText().toString();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	if(isValuable(body)){
	            AlertDialog isExit = new AlertDialog.Builder(this).create();
	            isExit.setTitle("提示");
	            isExit.setMessage("是否保存该日记？");
	            isExit.setButton(AlertDialog.BUTTON_POSITIVE,"确定", listener);  
	            isExit.setButton(AlertDialog.BUTTON_NEGATIVE,"取消", listener);
	            isExit.show();
        	}else{
        		finish();
        	}
        }    
        return false;         
    }  
	
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){  
        public void onClick(DialogInterface dialog, int which){  
            switch (which)  
            {
            case AlertDialog.BUTTON_POSITIVE:
            	saveDiary();
            	finish(); 
                break;  
            case AlertDialog.BUTTON_NEGATIVE:
                finish();
                break;  
            default:  
                break;  
            }  
        }  
    };
    
	@SuppressLint("SimpleDateFormat")
	public void saveDiary()   {
    	EditText editText=(EditText)findViewById(R.id.diary);
    	String body=editText.getText().toString();
    	Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String title=sdf.format(date);
		DBAdapter dbAdapter=new DBAdapter(this);
		dbAdapter.open();
		dbAdapter.insertDiary(title, body);
		dbAdapter.close();		
    }
}
