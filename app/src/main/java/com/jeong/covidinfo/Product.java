package com.jeong.covidinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Product extends AppCompatActivity {
    // 방역 물품들을 리사이클러뷰로 섹션을 나눠서 띄워줌 (마스크/손소독제/물티슈)
    // 섹션을 나누지 않고 하나의 리사이클러뷰에 종류에 상관없이 방역물품을 띄움
    // db에 저장해서 최초실행에만 리스트를 만들지 vs db에 저장하지 않고 매 실행마다 리스트를 만들지
    // 방역물품 data 클래스는 판매 링크, 이미지, 이름, 평점,
    // 평점순으로 리사이클러뷰에 배치 -> 자바 객체리스트 정렬 comparable 방식...
    // 새롭게 정렬한 객체리스트를 newlist로 만들어서 newlist를 리사이클러뷰의 파라미터로 넘겨줌
    // 아이템 클릭시 해당 아이템 판매 사이트로 이동
    ArrayList<ProductData> productlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_product);
        productlist.add(new ProductData(R.drawable.mask1,"https://smartstore.naver.com/wizmombe/products/5229736104?n_media=11068&n_query=%EC%BD%94%EB%A1%9C%EB%82%98%EB%A7%88%EC%8A%A4%ED%81%AC&n_rank=2&n_ad_group=grp-a001-02-000000019314738&n_ad=nad-a001-02-000000119590579&n_campaign_type=2&n_mall_id=ncp_1nuamf_01&n_mall_pid=5229736104&n_ad_group_type=2&NaPm=ct%3Dkvgilja0%7Cci%3D0uzp000RK7Xvx51gSf36%7Ctr%3Dpla%7Chk%3D94c16bbeb8e5dcc00ba87d200ea37ab6180096f1","위즈맘 KF94", (float) 4.7));
        productlist.add(new ProductData(R.drawable.mask2,"https://smartstore.naver.com/healingshop/products/4819131424?NaPm=ct%3Dkvgiq914%7Cci%3D0A40000hKlXvu7l8QLjq%7Ctr%3Dpla%7Chk%3D625385429c984d52221d7a3e726c871e9f206dcc","닥터로드 KF94 새부리형", (float) 4.7));
        productlist.add(new ProductData(R.drawable.mask3,"https://smartstore.naver.com/healingshop/products/3893612977?NaPm=ct%3Dkvgitleg%7Cci%3D7c4df494653887bcb275a03eba356fa82e0f21f0%7Ctr%3Dslsl%7Csn%3D760841%7Chk%3D26ccc4c8b4cac507cba8b41e5717b33e1a875c7e","새부리형 덴탈 마스크 50매", (float) 4.5));
        productlist.add(new ProductData(R.drawable.mask4,"https://smartstore.naver.com/leatherprince/products/5229827280?NaPm=ct%3Dkvgiw8b4%7Cci%3D0A40000EKBXvr9zzYeZF%7Ctr%3Dpla%7Chk%3Dfa12d430635b2fb32d84539e5105664f8676bebe","클린앤영 KF94 100매", (float) 4.8));
        productlist.add(new ProductData(R.drawable.mask5,"https://smartstore.naver.com/deurimishop/products/5002048123?NaPm=ct%3Dkvgiykew%7Cci%3D02713672215404852f60a32a965e2cfe2d00c9b4%7Ctr%3Dslsl%7Csn%3D550433%7Chk%3D6769391e69dacb43589377a189edbfdc7723a0a0","네퓨어 KF_AD 끈조절 가능 60매", (float) 4.9));
        productlist.add(new ProductData(R.drawable.mask6,"https://smartstore.naver.com/skymedia21/products/2788838013?NaPm=ct%3Dkvgj2czk%7Cci%3Dd766bbd057761d335eb9abc4a499315fb25426e3%7Ctr%3Dslsl%7Csn%3D458931%7Chk%3Df1bc7afc8f99e60d24e354b368e9ade7824b9a0c","3Q새부리형 KF-94", (float) 4.5));
        productlist.add(new ProductData(R.drawable.cleaner1,"https://smartstore.naver.com/angelhouse2/products/5940714210?NaPm=ct%3Dkvjax840%7Cci%3D0zC0001MiC9vwCLe7Ll7%7Ctr%3Dpla%7Chk%3D4e0dc9429bbcdd2339a0435bf4a2e10cd391ad7e","카드형 휴대용손소독제",(float) 5.0 ));
        productlist.add(new ProductData(R.drawable.cleaner2,"https://smartstore.naver.com/labisco_254/products/5226943008?NaPm=ct%3Dkvjaym9k%7Cci%3D78f3678ee684168250f9206eb0cc4d786bc82e0b%7Ctr%3Dslsl%7Csn%3D1546368%7Chk%3D680810472c4bb6158a4815b883c9c6ef97b285bc","닥터포켓 카드형 미니",(float) 4.7 ));
        productlist.add(new ProductData(R.drawable.cleaner3,"https://smartstore.naver.com/tikkun/products/4939559016?NaPm=ct%3Dkvjb274g%7Cci%3D9c4d5014a96f72512c9a4c188b883e1ee707f0cb%7Ctr%3Dslsl%7Csn%3D1207186%7Chk%3D8febe5f844dc0ce50e60fcceb9d1b1f20ec68deb","티쿤 휴대용 스프레이 손소독제 3종",(float) 4.7 ));
        productlist.add(new ProductData(R.drawable.cleaner4,"https://smartstore.naver.com/tamburins/products/5896350099?NaPm=ct%3Dkvjb3gnc%7Cci%3D02b35a3969dcced9b858d36c014531bb26091da2%7Ctr%3Dslsl%7Csn%3D746875%7Chk%3D64997743825d0fbd70abbfd35ab73f8bd743ea79","탬버린즈 손 소독제 7 (30ml)",(float) 4.8 ));
        productlist.add(new ProductData(R.drawable.cleaner5,"https://smartstore.naver.com/pluscrub/products/4814079390?n_media=11068&n_query=%EC%86%90%EC%86%8C%EB%8F%85%EC%A0%9C&n_rank=1&n_ad_group=grp-a001-02-000000021584226&n_ad=nad-a001-02-000000136367926&n_campaign_type=2&n_mall_id=zivoncos&n_mall_pid=4814079390&n_ad_group_type=2&NaPm=ct%3Dkvjb5dbk%7Cci%3D0zi0003IiS9vEaM9Dfji%7Ctr%3Dpla%7Chk%3D718cc48a57907e27f7b61cb6a7040e3261b8e89e","플루 프리미엄 코로나 손 소독제",(float) 4.8 ));
        productlist.add(new ProductData(R.drawable.tissue1,"https://smartstore.naver.com/goldsoon/products/4930687695?n_media=11068&n_query=%EC%86%8C%EB%8F%85%EB%AC%BC%ED%8B%B0%EC%8A%88&n_rank=3&n_ad_group=grp-a001-02-000000020970459&n_ad=nad-a001-02-000000131559612&n_campaign_type=2&n_mall_id=goldsoon80&n_mall_pid=4930687695&n_ad_group_type=2&NaPm=ct%3Dkvjba5e0%7Cci%3D0zy0003bj89vP4iPJuWO%7Ctr%3Dpla%7Chk%3Dd6c0cb9e5710556effcfba3d4d815dc5740b38f4","달곰이 손세정 물티슈 플레인 캡형 10팩",(float) 4.8 ));
        productlist.add(new ProductData(R.drawable.tissue2,"https://smartstore.naver.com/hunife/products/4814967077?NaPm=ct%3Dkvjbb4vs%7Cci%3D0yS0003Vj89vUPSVqeZK%7Ctr%3Dpla%7Chk%3D03c3afd7e478169dc64687835db1b367a615418a","누리케어 손소독티슈 70매입",(float) 4.8 ));
        productlist.add(new ProductData(R.drawable.tissue3,"https://brand.naver.com/akofficial/products/5102591063?NaPm=ct%3Dkvjbcw5k%7Cci%3D6465fec431d516885aff8031d7f73c453771dee5%7Ctr%3Dslsl%7Csn%3D1485316%7Chk%3Dd969c124868d14a78e533d9e6735c0cb883f3bdd","랩신 V3 손소독티슈 60매입",(float) 4.9 ));


        //객체리스트를 rating순으로 정렬
        Collections.sort(productlist);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Product.this);
        recyclerView.setLayoutManager(linearLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Product.this, DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
        productAdapter = new ProductAdapter(productlist,Product.this);
        recyclerView.setAdapter(productAdapter);
    }
}