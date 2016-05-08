package com.example.andy.seg;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by clover on 2016/2/17.
 */
public class Note_main extends ListActivity {
    private static final int INSERT_ID = Menu.FIRST;

    private static final int DELETE_ID = Menu.FIRST + 1;

    private static final int ACTIVITY_CREATE = 0;

    private static final int ACTIVITY_EDIT = 1;

    private DiaryDbAdapter diaryDb;

    private Cursor cursor;

    private Button btn;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);

        diaryDb =new DiaryDbAdapter(this);
        diaryDb.open();
        showListView();

        btn = (Button) findViewById(R.id.btn_new);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionsMenu();
            }
        });

    }

    private void showListView(){
        cursor = diaryDb.getAllNotes();

        String[] from = new String[]{DiaryDbAdapter.KEY_TITLE,DiaryDbAdapter.KEY_BODY};
        int[] to = new int[]{R.id.text1,R.id.created};

        ListAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.diary_row,cursor,from,to);
        setListAdapter(cursorAdapter);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(Note_main.this, Note_delete.class);
//        String po = "" + position;
//        intent.putExtra("extra_position", po);
//        startActivity(intent);
//        finish();
//    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(Note_main.this, Note_delete.class);
        String po = "" + position;
        intent.putExtra("extra_position", po);
        startActivity(intent);
        finish();
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        // TODO Auto-generated method stub
//        super.onListItemClick(l, v, position, id);
//        Cursor c = cursor;
//        c.move(position);
//        Intent intent = new Intent(this,Notepad.class);
//        intent.putExtra(DiaryDbAdapter.KEY_ROWID, id);
//        intent.putExtra(DiaryDbAdapter.KEY_TITLE, c.getString(c
//                .getColumnIndexOrThrow(DiaryDbAdapter.KEY_TITLE)));
//        intent.putExtra(DiaryDbAdapter.KEY_BODY, c.getString(c
//                .getColumnIndexOrThrow(DiaryDbAdapter.KEY_BODY)));
//        startActivityForResult(intent, ACTIVITY_EDIT);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        showListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu);
        menu.add(0,INSERT_ID,0,R.string.menu_insert);
//        menu.add(0,DELETE_ID,0,R.string.menu_delete);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()){
            case INSERT_ID:
                Log.i("INSERT:", String.valueOf(INSERT_ID));
                createDiary();
                return true;
//            case DELETE_ID:
//                Log.i("DELETE_ID:", String.valueOf(getListView().getSelectedItemId()));
//                diaryDb.deleteDiary(getListView().getSelectedItemId());
//                showListView();
//                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    private void createDiary(){

        Intent intent = new Intent();
        intent.setClass(this, Notepad.class);
        startActivityForResult(intent, ACTIVITY_CREATE);
    }
}


