package com.jeong.covidinfo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.ViewHolder> {
    private ArrayList<ClinicData> clinicDatalist = new ArrayList<>();
    Context context;

    public ClinicAdapter(ArrayList<ClinicData> clinicDatalist, Context context){
        //this.clinicDatalist = clinicDatalist; 을 사용하지 않고 add(clinicDatalist);를 사용함으로써 안에서 clinicDatalist를 삭제해도 밖에
        //clinicDatlist에는 영향이 없다.
        add(clinicDatalist);
        this.context = context;
    }

    @NonNull
    @Override
    public ClinicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicAdapter.ViewHolder holder, int position) {
        holder.onBind(clinicDatalist.get(position));
    }

    @Override
    public int getItemCount() {
        return clinicDatalist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView sido;
        TextView sigun;
        TextView clinic;
        TextView time_1;
        TextView time_2;
        TextView time_3;
        TextView tel;
        Button map;
        String adress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sido = (TextView)itemView.findViewById(R.id.text_sido);
            sigun = (TextView)itemView.findViewById(R.id.text_sigun);
            clinic = (TextView)itemView.findViewById(R.id.text_clinic);
            time_1 = (TextView)itemView.findViewById(R.id.time_1);
            time_2 = (TextView)itemView.findViewById(R.id.time_2);
            time_3 = (TextView)itemView.findViewById(R.id.time_3);
            tel = (TextView)itemView.findViewById(R.id.text_tel);
            map = (Button)itemView.findViewById(R.id.btn_map);
            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("_TAG","클릭");
                    int pos = getAdapterPosition();
                    String adress = clinicDatalist.get(pos).getAdress();
                    String clinic = clinicDatalist.get(pos).getClinic();
                    String tel = clinicDatalist.get(pos).getTel();
                    String time_1 = clinicDatalist.get(pos).getTime_1();
                    String time_2 = clinicDatalist.get(pos).getTime_2();
                    String time_3 = clinicDatalist.get(pos).getTime_3();
                    Intent intent = new Intent(context,Map.class);
                    intent.putExtra("adress",adress);
                    intent.putExtra("clinic",clinic);
                    intent.putExtra("tel",tel);
                    intent.putExtra("time_1",time_1);
                    intent.putExtra("time_2",time_2);
                    intent.putExtra("time_3",time_3);
                    context.startActivity(intent);
                }
            });
        }
        void onBind(ClinicData item){
            sido.setText(item.getSido());
            sigun.setText(item.getSigun());
            clinic.setText(item.getClinic());
            time_1.setText(item.getTime_1());
            time_2.setText(item.getTime_2());
            time_3.setText(item.getTime_3());
            tel.setText(item.getTel());
        }
    }
    public void clear() {
        clinicDatalist.clear();
        notifyDataSetChanged();
    }
    public void add(ArrayList<ClinicData> search_clinic){
        clinicDatalist.addAll(search_clinic);
        notifyDataSetChanged();
    }
}
