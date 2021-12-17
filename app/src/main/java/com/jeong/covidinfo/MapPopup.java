package com.jeong.covidinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MapPopup extends AppCompatActivity {
    private String adress;
    private String clinic;
    private String tel;
    private String time_1, time_2, time_3;
    TextView clinic_name, clinic_addres, clinic_tiem1, clinic_tiem2, clinic_tiem3, clinic_tel, btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //기본 Activity는 다음을 사용한다.
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        //AppCompatActivity를 상송받은 경우는 다음을 사용한다.
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_map_popup);

        Intent intent = getIntent();
        adress = intent.getStringExtra("adress");
        clinic = intent.getStringExtra("clinic");
        tel = intent.getStringExtra("tel");
        time_1 = intent.getStringExtra("time_1");
        time_2 = intent.getStringExtra("time_2");
        time_3 = intent.getStringExtra("time_3");
        Log.d("_TAG","Pop"+time_1+time_2+time_3);

        clinic_name = (TextView)findViewById(R.id.clinic_name);
        clinic_addres = (TextView)findViewById(R.id.clinic_addres);
        clinic_tiem1 = (TextView)findViewById(R.id.clinic_tiem1);
        clinic_tiem2 = (TextView)findViewById(R.id.clinic_tiem2);
        clinic_tiem3 = (TextView)findViewById(R.id.clinic_tiem3);
        clinic_tel = (TextView)findViewById(R.id.clinic_tel);
        btn_close = (TextView)findViewById(R.id.btn_close);

        clinic_name.setText(clinic);
        clinic_addres.setText(adress);
        clinic_tiem1.setText(time_1);
        clinic_tiem2.setText(time_2);
        clinic_tiem3.setText(time_3);
        clinic_tel.setText(tel);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // 바깥쪽 클릭시 액티비티가 닫히지 않게
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}