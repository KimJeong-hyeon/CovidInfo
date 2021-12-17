package com.jeong.covidinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.ArrayList;

public class News extends AppCompatActivity {
    ArrayList<NewsData> newslist = new ArrayList<>();
    private RecyclerView recyclerView;
    private NewsAdapter news_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_news);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getNews();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 가져온 뉴스정보를 리사이클러뷰로 뿌려줌
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(News.this);
                        recyclerView.setLayoutManager(linearLayoutManager);

                        news_adapter = new NewsAdapter(newslist,News.this);
                        recyclerView.setAdapter(news_adapter);
                    }
                });
            }
        }).start();
    }
    public void getNews() {
        String clientId = "pmYvip2VsGqmSRv5Kbak";
        String clientPw = "o9oP6Q19ev";
        try {
            String text = URLEncoder.encode("코로나","UTF-8");
            String apiUrl = "https://openapi.naver.com/v1/search/news?query="+ text +
                    "&display=20" + "&start=1" + "&sort=sim";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Naver-Client-Id", clientId);
            connection.setRequestProperty("X-Naver-Client-Secret", clientPw);
            int responseCode = connection.getResponseCode();
            BufferedReader br;
            if(responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null){
                response.append(inputLine);
            }
            br.close();
            //Json형식의 데이터 사용하기
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            int index = 0;
            while (index < jsonArray.length()){
                //String filter_1 = Html.fromHtml(jsonArray.get(index).toString()).toString();
                //Log.d("_TAG",""+index+filter_1);
                //NewsData newsData = gson.fromJson(filter_1, NewsData.class);
                NewsData newsData = gson.fromJson(jsonArray.get(index).toString(),NewsData.class);
                newslist.add(newsData);
                index++;
            }
        }catch (Exception e) { Log.d("_TAG"," "+e); }
    }
}