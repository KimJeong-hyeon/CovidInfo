package com.jeong.covidinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

public class Clinic extends AppCompatActivity {
    // 검색창을 만들고 검색을 통해서 리사이클러뷰로 검색 지역의 선별진료소를 보여줌
    // 선별진료소의 대한 정보는 api를 통해서 받아옴
    // 지역/ 선별진료소 이름/ 연락처/ 지도보기 칸으로 리사이클러뷰 아이템을 만듬
    // 초기 화면은
    private ArrayList<ClinicData> cliniclist = new ArrayList<>();
    private ArrayList<ClinicData> search_cliniclist = new ArrayList<>();
    private ClinicData tempClinic = null;
    private RecyclerView recyclerView;
    private ClinicAdapter clinicAdapter;
    private EditText search;
    private ImageView btn_search;
    private String search_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_clinic);
        search = (EditText)findViewById(R.id.search_edit);
        btn_search = (ImageView) findViewById(R.id.btn_search);

        getData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Clinic.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        clinicAdapter = new ClinicAdapter(cliniclist, Clinic.this);
        recyclerView.setAdapter(clinicAdapter);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_result = search.getText().toString();
                for(int i=0; i<cliniclist.size(); i++){
                    if(search_result.equals(cliniclist.get(i).getSido()) || search_result.equals(cliniclist.get(i).getSigun())){
                        search_cliniclist.add(new ClinicData(cliniclist.get(i).getSido(),cliniclist.get(i).getSigun(),cliniclist.get(i).getClinic(),
                                cliniclist.get(i).getAdress(),cliniclist.get(i).getTime_1(),cliniclist.get(i).getTime_2(),
                                cliniclist.get(i).getTime_3(),cliniclist.get(i).getTel()));
                    }
                }
                //Log.d("_TAG","clear전"+search_cliniclist.size());
                clinicAdapter.clear();
                clinicAdapter.add(search_cliniclist);
                search_cliniclist.clear();
               // Log.d("_TAG","clear후"+search_cliniclist.size());
            }
        });
    }
    public void getData() {
        try {
            InputStream inputStream = getBaseContext().getResources().getAssets().open("clinicdata.xls");
            Workbook wb = Workbook.getWorkbook(inputStream);
            if(wb != null){
                Sheet sheet = wb.getSheet(0);
                if(sheet != null){
                    int Totalcol = sheet.getColumns();
                    int Startrowindex = 1;
                    int Totalrow = sheet.getColumn(Totalcol-1).length;

                    StringBuilder stringBuilder;
                    for(int row = Startrowindex; row<Totalrow; row++){
                        stringBuilder = new StringBuilder();
                        for (int col=0; col<Totalcol; col++){
                            String content = sheet.getCell(col,row).getContents();
                            stringBuilder.append(content+"%");
                        }
                        String[] resultsb = stringBuilder.toString().split("%");
                        cliniclist.add(new ClinicData(resultsb[1],resultsb[2],resultsb[3],resultsb[4],resultsb[5],resultsb[6],resultsb[7],resultsb[8]));
                    }
                }
            }
        }catch (Exception e){
            Log.d("_TAG","오류"+e);
        }
    }
}