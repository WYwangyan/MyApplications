package com.example.mybook;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.mybook.database.DBAdapter;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DiaryListActivity extends ListActivity {
	
	AdapterContextMenuInfo adapterContextMenuInfo=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_list);
		
		ListView listview=(ListView)getListView();
		
		listview.setOnItemClickListener(new clickListener());
		listview.setOnCreateContextMenuListener(this);
	}
	
	protected void onResume() {
		super.onResume();
		refresh();		
	}
	
	public void refresh(){
		ListView listview=(ListView)getListView();
		
		ArrayList<HashMap<String, String>> listdata = new ArrayList<HashMap<String, String>>();
		
		DBAdapter dbAdapter=new DBAdapter(this);
		dbAdapter.open();
		Cursor cursor=dbAdapter.getAllDiaries();		
		TextView tv=(TextView)findViewById(R.id.no_record);
		if(cursor.getCount()==0)
			tv.setVisibility(View.VISIBLE);
		else
			tv.setVisibility(View.GONE);
		while(cursor.moveToNext()){
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("title",cursor.getString(cursor.getColumnIndex("title")));
			map.put("info",cursor.getString(cursor.getColumnIndex("body")));
			listdata.add(0,map);
        }
		cursor.close();
		dbAdapter.close();
		
		
		
		SimpleAdapter adapter=new SimpleAdapter(this, listdata, R.layout.diary_listview, new String[]{"title","info"}, new int[]{R.id.ListDiaryTitle,R.id.ListDiaryInfo});
		
		listview.setAdapter(adapter);
	}
	
	class clickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			show(arg2);			
		}
		
	}
	
	public void show(int position){
		DBAdapter dbAdapter=new DBAdapter(DiaryListActivity.this);
		dbAdapter.open();
		Cursor cursor=dbAdapter.getAllDiaries();
		int id=cursor.getCount()-position-1;
		Cursor current=dbAdapter.getDiary(id);
		String title=current.getString(current.getColumnIndex("title"));
		String body=current.getString(current.getColumnIndex("body"));
		cursor.close();
		dbAdapter.close();
		Intent intent=new Intent(DiaryListActivity.this,DiaryActivity.class);
		intent.putExtra("id", id);
		intent.putExtra("title", title);
		intent.putExtra("body", body);
		startActivity(intent);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo mi) {
		 menu.setHeaderTitle("请选择操作 ");
		 //配置上下文菜单选项 
		 //menu.add(0, Menu.FIRST +1, 1, "编辑标题");
		 menu.add(0, Menu.FIRST +1, 1, "编辑");
		 menu.add(0, Menu.FIRST +2, 2, "删除");
		 
		 adapterContextMenuInfo=(AdapterContextMenuInfo) mi;
		 //super.onCreateContextMenu(menu, v, mi);
	}
	 
	 //响应上下文菜单的点击事件
	@Override 
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case Menu.FIRST+1:
			show(adapterContextMenuInfo.position);		
			break;
		
		case Menu.FIRST+2:
			DBAdapter dbAdapter=new DBAdapter(DiaryListActivity.this);
			dbAdapter.open();
			Cursor cursor=dbAdapter.getAllDiaries();
			int id=cursor.getCount()-adapterContextMenuInfo.position-1;
			dbAdapter.deleteDiary(id);
			cursor.close();
			dbAdapter.close();
			refresh();
			break;
		
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.diary_list, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_new:
                newDiary();
                return true;
            case R.id.action_password:
                setPassword();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void newDiary(){
        Intent intent = new Intent(this, NewDiaryActivity.class);
        startActivity(intent);
    }
    
    public void setPassword(){
        Intent intent=null;
		
		switch (MainActivity.passwordType) {
		case 0:
			intent = new Intent(this, SetPasswordActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(this, StringPasswordAuthenticationActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(this, PicturePasswordAuthenticationActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
    }
}
