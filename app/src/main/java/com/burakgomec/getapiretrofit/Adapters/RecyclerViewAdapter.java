package com.burakgomec.getapiretrofit.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burakgomec.getapiretrofit.Models.Datum;
import com.burakgomec.getapiretrofit.R;

import java.util.List;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.PostHolder>{

    private final List<Datum> data;
    private final String[] colors = {"#00FFFF","#808000","#000080","#800000","#00FF00"};

    public RecyclerViewAdapter(List<Datum> data){
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_basketball_team,parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.PostHolder holder, int position) {
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 5]));
        holder.textFullName.setText(data.get(position).fullName);
        holder.textCity.setText(data.get(position).city);
        holder.textConference.setText(data.get(position).conference);
        holder.textAbbreviation.setText(data.get(position).abbreviation);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class PostHolder extends RecyclerView.ViewHolder {
        TextView textFullName;
        TextView textCity;
        TextView textConference;
        TextView textAbbreviation;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            textFullName = itemView.findViewById(R.id.textFullName);
            textCity = itemView.findViewById(R.id.textCity);
            textConference = itemView.findViewById(R.id.textConference);
            textAbbreviation=itemView.findViewById(R.id.textAbbreviation);
        }
    }
}
