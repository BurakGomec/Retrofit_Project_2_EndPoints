package com.burakgomec.retrofit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.burakgomec.retrofit.Models.MoviesModel.MoviesModel;
import com.burakgomec.retrofit.R;

import java.util.ArrayList;
import java.util.List;

public class MoviesListRecyclerAdapter extends RecyclerView.Adapter<MoviesListRecyclerAdapter.PostHolder> {

    private ArrayList<MoviesModel> moviesList;
    private Context context;

    public MoviesListRecyclerAdapter(ArrayList<MoviesModel> moviesList,Context context){
        this.moviesList = moviesList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_movies_list,parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.title.setText(moviesList.get(position).title);
        holder.rating.setText(String.valueOf(moviesList.get(position).rating));
        holder.releaseYear.setText(String.valueOf(moviesList.get(position).releaseYear));
        List<String> genre = moviesList.get(position).genre;
        StringBuilder genres = new StringBuilder();
        Glide.with(context).load(moviesList.get(position).image).placeholder(R.drawable.ic_launcher_background).centerCrop().error(R.drawable.ic_launcher_foreground).into(holder.imageView);
        for(String s : genre){
            int size = genre.size()-1;
            if(genre.indexOf(s) == size){
                genres.append(s);
            }
            else{
                genres.append(s);
                genres.append("/");
            }

        }
        holder.genre.setText(genres);
        //holder.itemView.setBackgroundColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title,releaseYear,rating,genre;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewMovie);
            title = itemView.findViewById(R.id.textViewTitle);
            releaseYear = itemView.findViewById(R.id.textViewReleaseYear);
            rating = itemView.findViewById(R.id.textViewRating);
            genre = itemView.findViewById(R.id.textViewGenre);
        }
    }
}
