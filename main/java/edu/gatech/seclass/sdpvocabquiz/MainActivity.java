package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orm.SugarContext;
import com.orm.SugarDb;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarDb db = new SugarDb(getApplicationContext());
        try {
            db.getDB();
        }catch (Exception e){
            //do nothing
        }
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
