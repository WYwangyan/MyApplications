package com.example.mybook;

import com.example.mybook.database.DBAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class DiaryActivity extends Activity {
	
	int id;
	
	boolean changed=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary);
		Intent intent=getIntent();
		id=intent.getIntExtra("id", -1);
		String title=intent.getStringExtra("title");
		String body=intent.getStringExtra("body");
		setTitle(title);
		TextView tv=(TextView)findViewById(R.id.body);
		tv.setText(body);
		tv.addTextChangedListener(new watcher());
	}
	
	class watcher implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			changed=true;
		}
		
	}
	
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event){  
        if (keyCode == KeyEvent.KEYCODE_BACK){
        	if(changed){
	            AlertDialog isExit = new AlertDialog.Builder(this).create();
	            isExit.setTitle("提示");
	            isExit.setMessage("是否保存修改？");
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
            	updateDiary();
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
    
    public void updateDiary(){
    	EditText editText=(EditText)findViewById(R.id.body);
    	String body=editText.getText().toString();
		DBAdapter dbAdapter=new DBAdapter(this);
		dbAdapter.open();
		dbAdapter.updateDiary(id, this.getTitle().toString(), body);
		dbAdapter.close();		
    }

}
