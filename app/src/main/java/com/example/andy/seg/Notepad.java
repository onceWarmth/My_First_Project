package com.example.andy.seg;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by clover on 2016/2/17.
 */
public class Notepad extends AppCompatActivity{
    // title EditText
    EditText titleTxt;
    // body EditText
    EditText bodyTxt;
    // save Button
    Button btn;
    // Row Id
    Long rowId;
    private DiaryDbAdapter diaryDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notepad);

        // Initialize the DiaryDbAdapter.
        diaryDb = new DiaryDbAdapter(this);

        // Get the screen control
        titleTxt = (EditText)findViewById(R.id.title);
        bodyTxt = (EditText)findViewById(R.id.body_text);
        btn = (Button)findViewById(R.id.button);
        rowId = null ;

        // Get data from the front page
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            Log.i("bund:", bundle.toString());

            // Set data to page
            String title = bundle.getString(DiaryDbAdapter.KEY_TITLE);
            String body = bundle.getString(DiaryDbAdapter.KEY_BODY);
            rowId = bundle.getLong(DiaryDbAdapter.KEY_ROWID);
            if(title!=null)
            {
                titleTxt.setText(title);
            }
            if(body!=null)
            {
                bodyTxt.setText(body);
            }
        }

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String title = titleTxt.getText().toString();
                String body = bodyTxt.getText().toString();
                if(checkInput(title, body)){
                    diaryDb.open();
                    if(rowId!=null){
                        diaryDb.updateDiary(rowId, title, body);
                    }else{
                        diaryDb.createDiary(title, body);
                    }
                    diaryDb.close();

                    Intent mIntent = new Intent();
                    setResult(RESULT_OK, mIntent);

                    finish();
                }
            }
        });
    }

    /**
     * Validate the input.
     * @param title
     * @param body
     * @return
     */
    public boolean checkInput(String title ,String body){

        if(null==title || title.trim().length()==0){
            titleTxt.setError("Please input the title!");
            return false;
        }

        if(null==body || body.trim().length()==0){
            bodyTxt.setError("Please input the content!");
            return false;
        }
        return true;
    }
}


