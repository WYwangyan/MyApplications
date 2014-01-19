package com.example.mybook;

import java.util.ArrayList;
import java.util.HashMap;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.app.ListActivity;
import android.content.Intent;

public class SetPasswordActivity extends ListActivity {
	ArrayList<HashMap<String, String>> listdata = new ArrayList<HashMap<String, String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_password);
		
		ListView listview=(ListView)getListView();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("title", "无密码");
		listdata.add(map);

		map = new HashMap<String, String>();
		map.put("title", "字符密码");
		listdata.add(map);

		map = new HashMap<String, String>();
		map.put("title", "图案密码");
		listdata.add(map);
		
		SimpleAdapter adapter=new SimpleAdapter(this, listdata, R.layout.password_listview, new String[]{"title"}, new int[]{R.id.ListPasswordTitle});
		
		listview.setAdapter(adapter);
	
		listview.setOnItemClickListener(new listener());
	}
	

	class listener implements OnItemClickListener{
		 
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			
			Intent intent=null;
			
			switch (arg2) {
			case 0:
				MainActivity.passwordType=0;
				Toast.makeText(getApplicationContext(), "无密码", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				intent = new Intent(SetPasswordActivity.this, StringPasswordActivity.class);
				startActivity(intent);
				break;
			case 2:
				intent = new Intent(SetPasswordActivity.this, PicturePasswordActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
			SetPasswordActivity.this.finish();
			
		}
		
	}
	
}


