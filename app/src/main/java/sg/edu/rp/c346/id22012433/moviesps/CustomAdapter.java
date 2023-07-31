package sg.edu.rp.c346.id22012433.moviesps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Movie> {

    Context parent_context;
    int layout_id;

    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvmTitle = rowView.findViewById(R.id.tvMovieTitle);
        TextView tvRating = rowView.findViewById(R.id.tvRating);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        ImageView Rating = rowView.findViewById(R.id.imageViewRating);
        // Obtain the Android Version information based on the position
        Movie currentVersion = movieList.get(position);

        if (tvmTitle != null) {
            tvmTitle.setText(currentVersion.getMtitle());
        }
        if (tvGenre != null) {
            tvGenre.setText(currentVersion.getGenre());
        }
        if (tvYear != null) {
            tvYear.setText(String.valueOf(currentVersion.getYear()));
        }

        // Set ImageView based on movie rating
        if (Rating != null) {
            if ("G".equals(currentVersion.getRating())) {
                Rating.setImageResource(R.drawable.rating_g);
            } else if ("PG".equals(currentVersion.getRating())) {
                Rating.setImageResource(R.drawable.rating_pg);
            } else if ("PG13".equals(currentVersion.getRating())) {
                Rating.setImageResource(R.drawable.rating_pg13);
            } else if ("NC16".equals(currentVersion.getRating())) {
                Rating.setImageResource(R.drawable.rating_nc16);
            } else if ("M18".equals(currentVersion.getRating())) {
                Rating.setImageResource(R.drawable.rating_m18);
            } else if ("R21".equals(currentVersion.getRating())) {
                Rating.setImageResource(R.drawable.rating_r21);
            }
        }

        return rowView;
    }
}
