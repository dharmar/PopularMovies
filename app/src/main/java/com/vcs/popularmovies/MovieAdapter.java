package com.vcs.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.RecyclerViewHolder> {
    private ArrayList<MovieModel> movieModelList = new ArrayList<MovieModel>();
    private LayoutInflater layoutInflater;
    Context mContext;


    // Now constructor for MovieAdapter
    public MovieAdapter(ArrayList<MovieModel> movieModelList, Context context)
    {
        this.movieModelList = movieModelList;
        mContext = context;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);

        //Now we have to create an object of ViewHolder class
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        //Find Resources for the view components
        //First get an object of MovieModel
        System.out.println("Number of Movies:  " + movieModelList.size());
        MovieModel movieModel = movieModelList.get(position);
        holder.tvMovie.setText(movieModel.getMovie());
        holder.tvTagline.setText(movieModel.getTagline());
        holder.tvYear.setText("Year: " + movieModel.getYear());
        holder.tvDuration.setText("Duration:" + movieModel.getDuration());
        holder.tvDirector.setText("Director:" + movieModel.getDirector());

        System.out.println(movieModel.getMovie());
        System.out.println(movieModel.getTagline());
        System.out.println(movieModel.getYear());
        System.out.println(movieModel.getDuration());
        System.out.println(movieModel.getDirector());

        // rating bar
        holder.rbMovieRating.setRating(movieModel.getRating() / 2);

        StringBuffer stringBuffer = new StringBuffer();
        for (MovieModel.Cast cast : movieModel.getCastList()) {
            stringBuffer.append(cast.getName() + ", ");
        }

        holder.tvCast.setText("Cast:" + stringBuffer);
        holder.tvStory.setText(movieModel.getStory());
        holder.ivMovieIcon.setImageUrl(movieModel.getImage(), Mysingleton.getImageLoader(mContext));
    }


    @Override
    public int getItemCount()
    {
        return movieModelList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView ivMovieIcon;
        TextView tvMovie;
        TextView tvTagline;
        TextView tvYear;
        TextView tvDuration;
        TextView tvDirector;
        RatingBar rbMovieRating;
        TextView tvCast;
        TextView tvStory;

        public RecyclerViewHolder(View view) {
            super(view);

            ivMovieIcon = (NetworkImageView) view.findViewById(R.id.ivIcon);
            tvMovie = (TextView) view.findViewById(R.id.tvMovie);
            tvTagline = (TextView) view.findViewById(R.id.tvTagline);
            tvYear = (TextView) view.findViewById(R.id.tvYear);
            tvDuration = (TextView) view.findViewById(R.id.tvDuration);
            tvDirector = (TextView) view.findViewById(R.id.tvDirector);
            rbMovieRating = (RatingBar) view.findViewById(R.id.rbMovie);
            tvCast = (TextView) view.findViewById(R.id.tvCast);
            tvStory = (TextView) view.findViewById(R.id.tvStory);

        }
    }



}

