package com.jeong.covidinfo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
//날짜 없애도 되고 내용 글씨 크기 좀더 크게 본문안에 태그 없애기
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<NewsData> newsDataList;
    Context context;

    public NewsAdapter(ArrayList<NewsData> list, Context context){
        //News의 Context를 받아와서 click 이벤트를 구현할시에 startActivity를 사용했다.
        newsDataList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.onBind(newsDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView pubdate;
        String link;
        public ViewHolder(@NonNull View itmeView){
            super(itmeView);
            title = (TextView)itmeView.findViewById(R.id.news_title);
            content = (TextView)itemView.findViewById(R.id.news_content);
            pubdate = (TextView)itemView.findViewById(R.id.news_date);
            //리사이클러뷰 click 이벤트
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsDataList.get(pos).getLink()));
                    context.startActivity(intent);
                }
            });
        }
        void onBind(NewsData item){
            //gson으로 파싱한 json에 있는 Html 태그를 없애기 위해서 Html.fromHtml을 사용
            title.setText(Html.fromHtml(item.getTitle()).toString());
            content.setText(Html.fromHtml(item.getDescription()).toString());
            pubdate.setText(item.getPubDate());
            link = item.getLink();
        }
    }
}
