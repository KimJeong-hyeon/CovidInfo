package com.jeong.covidinfo;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.Rating;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<ProductData> productDatalist;
    private Context context;

    public ProductAdapter(ArrayList<ProductData> productDatalist, Context context){
        this.productDatalist = productDatalist;
        this.context = context;
    }
    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.onBind(productDatalist.get(position));
    }

    @Override
    public int getItemCount() {
        return productDatalist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView score;
        RatingBar ratingBar;
        TextView btn_buy;
        String link;
        public ViewHolder(@NonNull View itmeView){
            super(itmeView);
            img = (ImageView)itmeView.findViewById(R.id.product_img);
            name = (TextView)itemView.findViewById(R.id.product_name);
            score = (TextView)itmeView.findViewById(R.id.product_rating_num);
            ratingBar = (RatingBar)itmeView.findViewById(R.id.product_rating);
            btn_buy = (TextView)itmeView.findViewById(R.id.text_buy);
            //리사이클러뷰 click 이벤트
            btn_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(productDatalist.get(pos).getLink()));
                    context.startActivity(intent);
                }
            });
        }
        void onBind(ProductData item){
            img.setImageResource(item.getImg());
            name.setText(item.getName());
            score.setText(String.valueOf(item.getRate()));
            link = item.getLink();
            ratingBar.setRating(item.getRate());
        }
    }
}
