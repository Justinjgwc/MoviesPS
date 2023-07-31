package sg.edu.rp.c346.id22012433.moviesps;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListMovie extends AppCompatActivity {
    ListView lvMovies;
    Button btnf,btnback;
    ArrayList<Movie> movieList;
    ArrayList<Movie> selectedMovies;
    //Spinner spnSongs;
    CustomAdapter ca,ca2;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper DBH = new DBHelper(ListMovie.this);
        super.onResume();
        movieList.clear();
        movieList.addAll(DBH.getMovies());
        ca.notifyDataSetChanged();

        selectedMovies.clear();
        for (int i = 0; i < movieList.size(); i++) {
            Movie movieT = movieList.get(i);
            if("PG13".equals(movieT.getRating())){
                selectedMovies.add(movieT);
            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_movie);
        lvMovies=findViewById(R.id.lv);
        btnf=findViewById(R.id.btnfilter);
        btnback=findViewById(R.id.btnback);
        movieList = new ArrayList<Movie>();
        selectedMovies = new ArrayList<Movie>();
        ca = new CustomAdapter(this,R.layout.row,movieList);
        lvMovies.setAdapter(ca);

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie dMovie = movieList.get(position);
                Intent i = new Intent(ListMovie.this,
                        EditMovie.class);
                i.putExtra("song", dMovie);
                startActivity(i);
            }
        });
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ca.clear();
                ca.addAll(selectedMovies);
                ca.notifyDataSetChanged();

            }
        });



        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListMovie.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}


