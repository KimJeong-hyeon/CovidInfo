package com.jeong.covidinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mgoogleMap;
    private String adress;
    private String clinic;
    private String tel;
    private String time_1, time_2, time_3;
    private List<Address> list = null;
    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        adress = intent.getStringExtra("adress");
        clinic = intent.getStringExtra("clinic");
        tel = intent.getStringExtra("tel");
        time_1 = intent.getStringExtra("time_1");
        time_2 = intent.getStringExtra("time_2");
        time_3 = intent.getStringExtra("time_3");

        Geocoder gecoder = new Geocoder(this);
        try {
            list = gecoder.getFromLocationName(adress,10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(list != null){
            if(list.size() == 0){
                Log.d("_TAG","주소변환 에러");
            }else {
                Address result = list.get(0);
                lat = result.getLatitude();
                lon = result.getLongitude();
            }
        }
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //mgoogleMap = googleMap;
        LatLng cliniclocation = new LatLng(lat, lon);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(cliniclocation);
        markerOptions.title(clinic);
        //팝업창으로 이름 주소 번호 진료시간을 다시 한번띄워주기
        markerOptions.snippet("클릭시 더보기..");
        //.showInfoWindow()를 사용해서 마커를 클릭하지 않아도 마커 설명이 뜨도록 설정
        googleMap.addMarker(markerOptions).showInfoWindow();
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                Intent intent = new Intent(Map.this,MapPopup.class);
                intent.putExtra("adress",adress);
                intent.putExtra("clinic",clinic);
                intent.putExtra("tel",tel);
                intent.putExtra("time_1",time_1);
                intent.putExtra("time_2",time_2);
                intent.putExtra("time_3",time_3);
                Map.this.startActivity(intent);
            }
        });
//        마커중심으로 구글맵이 안보여짐
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(cliniclocation));
//        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
//        마커중심으로 구글맵 보여주기
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cliniclocation, 15f));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cliniclocation, 15f));

    }
}