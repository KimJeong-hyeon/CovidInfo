package com.jeong.covidinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ImageView btn_home;
    TextView txt1, txt2, txt3, txt4;
    TextView sub_txt1, sub_txt2, sub_txt3, sub_txt4;
    TextView main_menu1, main_menu2, main_menu3, main_menu4, main_menu5;
    TextView update_date;
    boolean b_decideCnt = false, b_examCnt = false, b_clearCnt = false, b_deathCnt = false;
    private String key = "vaXH1GPi1Tx19XQNGP2u25wMm5G%2Fr4iAA7OZKcbQz7cVWKx%2BvwA%2BInIc3GcfBNVkF6QdQxiAtDV8%2Bkt%2BTlFZAg%3D%3D";
    String updateday;
    ArrayList<Item> Itemlist = null;
    Item ItemBus = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_home = (ImageView) findViewById(R.id.home_button);
        txt1 = (TextView)findViewById(R.id.main_txt1);
        txt2 = (TextView)findViewById(R.id.main_txt2);
        txt3 = (TextView)findViewById(R.id.main_txt3);
        txt4 = (TextView)findViewById(R.id.main_txt4);
        sub_txt1 = (TextView)findViewById(R.id.sub_txt1);
        sub_txt2 = (TextView)findViewById(R.id.sub_txt2);
        sub_txt3 = (TextView)findViewById(R.id.sub_txt3);
        sub_txt4 = (TextView)findViewById(R.id.sub_txt4);
        main_menu1 = (TextView)findViewById(R.id.main_menu1);
        main_menu2 = (TextView)findViewById(R.id.main_menu2);
        main_menu3 = (TextView)findViewById(R.id.main_menu3);
        main_menu4 = (TextView)findViewById(R.id.main_menu4);
        main_menu5 = (TextView)findViewById(R.id.main_menu5);
        update_date = (TextView)findViewById(R.id.update_date);

        new Thread(){
            @Override
            public void run() {
                getData();
                //Log.d("_TAG",""+Itemlist.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //숫자에 콤마를 찍기 위해서 Format을 사용
                        try {
                            DecimalFormat numberForm = new DecimalFormat("###,###");
                            // 0번째 인덱스가 가장 최근 자료
                            txt1.setText(numberForm.format(Integer.valueOf(Itemlist.get(0).decideCnt)));
                            txt2.setText(numberForm.format(Integer.valueOf(Itemlist.get(0).examCnt)));
                            txt3.setText(numberForm.format(Integer.valueOf(Itemlist.get(0).clearCnt)));
                            txt4.setText(numberForm.format(Integer.valueOf(Itemlist.get(0).deathCnt)));
                            // Itemlist 사이즈 체크!!!!!!!!! 자꾸 여기서 오류가 뜸
                            Integer sub_num1 = Integer.parseInt(Itemlist.get(0).decideCnt) - Integer.parseInt(Itemlist.get(1).decideCnt);
                            Integer sub_num2 = Integer.parseInt(Itemlist.get(0).examCnt) - Integer.parseInt(Itemlist.get(1).examCnt);
                            Integer sub_num3 = Integer.parseInt(Itemlist.get(0).clearCnt) - Integer.parseInt(Itemlist.get(1).clearCnt);
                            Integer sub_num4 = Integer.parseInt(Itemlist.get(0).deathCnt) - Integer.parseInt(Itemlist.get(1).deathCnt);
                            if (sub_num1 > 0) {
                                sub_txt1.setText(numberForm.format(sub_num1) + "△");
                            } else if (sub_num1 == 0) {
                                sub_txt1.setText(numberForm.format(sub_num1) + "-");
                            } else {
                                String sub = Integer.toString(sub_num1).substring(1);
                                Integer result_num1 = Integer.parseInt(sub);
                                numberForm.format(result_num1);
                                sub_txt2.setText(numberForm.format(result_num1) + "▽");
                            }
                            if (sub_num2 > 0) {
                                sub_txt2.setText(numberForm.format(sub_num2) + "△");
                            } else if (sub_num2 == 0) {
                                sub_txt2.setText(numberForm.format(sub_num2) + "-");
                            } else {
                                String sub = Integer.toString(sub_num2).substring(1);
                                Integer result_num2 = Integer.parseInt(sub);
                                numberForm.format(result_num2);
                                sub_txt2.setText(numberForm.format(result_num2) + "▽");
                            }
                            if (sub_num3 > 0) {
                                sub_txt3.setText(numberForm.format(sub_num3) + "△");
                            } else if (sub_num3 == 0) {
                                sub_txt3.setText(numberForm.format(sub_num3) + "-");
                            } else {
                                String sub = Integer.toString(sub_num3).substring(1);
                                Integer result_num3 = Integer.parseInt(sub);
                                numberForm.format(result_num3);
                                sub_txt2.setText(numberForm.format(result_num3) + "▽");
                            }
                            if (sub_num4 > 0) {
                                sub_txt4.setText(numberForm.format(sub_num4) + "△");
                            } else if (sub_num4 == 0) {
                                sub_txt4.setText(numberForm.format(sub_num4) + "-");
                            } else {
                                String sub = Integer.toString(sub_num4).substring(1);
                                Integer result_num4 = Integer.parseInt(sub);
                                numberForm.format(result_num4);
                                sub_txt2.setText(numberForm.format(result_num4) + "▽");
                            }
                            //업데이트 날짜 텍스트뷰 설정
                            update_date.setText("최근 업데이트: " + updateday);
                        } catch (Exception e){
                            Log.d("TAG",""+e);
                        }
                    }
                });
            }
        }.start();

        //백신정보 메뉴버튼
        main_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Vaccine.class);
                startActivity(intent);
            }
        });
        //뉴스 메뉴 버튼
        main_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), News.class);
                startActivity(intent);
            }
        });
        main_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Clinic.class);
                startActivity(intent);
            }
        });
        //방역물품 메뉴 버튼
        main_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Product.class);
                startActivity(intent);
            }
        });
        main_menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YouTubePopup.class);
                startActivity(intent);
            }
        });

    }
    public void getData() {
        Calendar today = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        today.add(Calendar.DATE,-1);
        yesterday.add(Calendar.DATE,-3);
        String getToday = new java.text.SimpleDateFormat("yyyyMMdd").format(today.getTime());
        String getYesterday = new java.text.SimpleDateFormat("yyyyMMdd").format(yesterday.getTime());
        updateday = new java.text.SimpleDateFormat("yyyy.MM.dd").format(today.getTime());

        // StringBuffer buffer= new StringBuffer();
        String queryUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey="
                +key+"&pageNo=1&numOfRows=10&startCreateDt="+getYesterday+"&endCreateDt="+getToday;

        try {
            URL url = new URL(queryUrl);
            InputStream inputStream = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(inputStream,"UTF-8"));

            int eventType = parser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        Itemlist = new ArrayList<Item>();
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item") && ItemBus != null){
                            Itemlist.add(ItemBus);
                        }
                        break;
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("item")) { ItemBus = new Item(); }
                        if(parser.getName().equals("decideCnt")) { b_decideCnt = true; }
                        if(parser.getName().equals("examCnt")) { b_examCnt = true; }
                        if(parser.getName().equals("clearCnt")) { b_clearCnt = true; }
                        if(parser.getName().equals("deathCnt")) { b_deathCnt = true; }
                        break;
                    case XmlPullParser.TEXT:
                        if(b_decideCnt){
                            ItemBus.setDecideCnt(parser.getText());
                            b_decideCnt = false;
                        }else if(b_examCnt){
                            ItemBus.setExamCnt(parser.getText());
                            b_examCnt = false;
                        }else if(b_clearCnt){
                            ItemBus.setClearCnt(parser.getText());
                            b_clearCnt = false;
                        }else if(b_deathCnt){
                            ItemBus.setDeathCnt(parser.getText());
                            b_deathCnt = false;
                        }
                        break;
                }
                eventType = parser.next();
            }

        }catch (Exception e){
            Log.d("TAG","오류"+e);
        }
    }
}