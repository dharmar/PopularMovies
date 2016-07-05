package com.vcs.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String serverUrl = "http://jsonparsing.parseapp.com/jsonData/moviesData.txt";
    private MovieModel movieModel;
    private ArrayList<MovieModel> movieModelList = new ArrayList<MovieModel>();
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //parseJSON(serverUrl);
        //adapter = new MovieAdapter(movieModelList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

   /* }

        public ArrayList<MovieModel> parseJSON(String serverUrl)
    {
        //Get Movie Data from JSON
     */
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, serverUrl, (String)null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray parentArray = response.getJSONArray("movies");
                    ArrayList<MovieModel> movieModelList = new ArrayList<MovieModel>();

                    for (int i = 0; i < parentArray.length(); i++) {
                        JSONObject finalObject = parentArray.getJSONObject(i);
                        MovieModel movieModel = new MovieModel();
                        movieModel.setMovie(finalObject.getString("movie"));
                        String movieName = movieModel.getMovie();
                        //System.out.println(movieName);
                        movieModel.setYear(finalObject.getInt("year"));
                        movieModel.setRating((float) finalObject.getDouble("rating"));
                        movieModel.setDirector(finalObject.getString("director"));
                        String directorName = movieModel.getDirector();
                        //System.out.println(directorName);
                        movieModel.setDuration(finalObject.getString("duration"));
                        movieModel.setTagline(finalObject.getString("tagline"));
                        movieModel.setImage(finalObject.getString("image"));
                        movieModel.setStory(finalObject.getString("story"));

                        List<MovieModel.Cast> castList = new ArrayList<>();
                        for (int j = 0; j < finalObject.getJSONArray("cast").length(); j++) {
                            MovieModel.Cast cast = new MovieModel.Cast();
                            cast.setName(finalObject.getJSONArray("cast").getJSONObject(j).getString("name"));
                            castList.add(cast);
                        }
                        movieModel.setCastList(castList);
                        movieModelList.add(movieModel);
                    }
                    MovieAdapter adapter = new MovieAdapter(movieModelList, MainActivity.this);
                    recyclerView.setAdapter(adapter);

                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getLocalizedMessage();
            }
        });

        Mysingleton.getInstance(this).add(jsonObjectRequest);
    }
}


