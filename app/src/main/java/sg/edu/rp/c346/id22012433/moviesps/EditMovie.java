package sg.edu.rp.c346.id22012433.moviesps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditMovie extends AppCompatActivity {
    EditText etMT2, etM2, etY2, genre;
    TextView tvMID;
    Button btnC, btnU, btnD;
    Spinner rating;
    Movie data;


    @Override
    protected void onPause() {
        super.onPause();

        tvMID = findViewById(R.id.tvSID);
        etMT2 = findViewById(R.id.etMovieTitle);
        etM2 = findViewById(R.id.etGenre);
        etY2 = findViewById(R.id.etYear);
        rating = findViewById(R.id.etRating);
        //Step 1a: Get the user input from the EditText and store it in a variable
        int ID = Integer.valueOf(tvMID.getText().toString());
        String MT = etMT2.getText().toString();
        String G = etM2.getText().toString();
        String YString = etY2.getText().toString();
        Intent intent = getIntent();

        data = (Movie) intent.getSerializableExtra("data");
        int Y;
        if (!YString.isEmpty()) {
            try {
                Y = Integer.valueOf(YString);
            } catch (NumberFormatException e) {
                // Handle the case when the input is not a valid integer (non-numeric)
                Toast.makeText(this, "Please enter a valid year.", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            // Handle the case when the input is empty
            // You can set a default value or show an error message
            Y = 0; // or any other default value you want to set
        }

        //Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        //Step 1c: Obtain an instance of the SharedPreferences Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Step 1d: Set a key-value pair in the editor
        prefEdit.putInt("ID", ID);
        prefEdit.putString("Movie Title", MT);
        prefEdit.putString("Genre", G);
        prefEdit.putInt("Year", Y);

        //Step 1e:Call commit() to save the changes made to the SharedPreference
        prefEdit.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //Step 2a:Obtain the SHaredPreferences instance
        SharedPreferences prefs=getPreferences(MODE_PRIVATE);
        //Step 2b: Retrieve the saved data from the SharedPreferences object
        int ID = prefs.getInt("ID", data.getId());
        String ST=prefs.getString("Movie Title", data.getMtitle());
        String S=prefs.getString("Genre", data.getGenre());
        int Y = prefs.getInt("Year", data.getYear());
        //with a default value if no matching data
        tvMID.setText(String.valueOf(ID));
        etM2.setText(S);
        etY2.setText(String.valueOf(Y));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);
        tvMID=findViewById(R.id.tvSID);
        etMT2=findViewById(R.id.etMovieTitle);
        genre = findViewById(R.id.etGenre);
        rating=findViewById(R.id.etRating);
        etY2=findViewById(R.id.etYear);
        btnC=findViewById(R.id.btnCancel);
        btnD=findViewById(R.id.btnDelete);
        btnU=findViewById(R.id.btnUpdate);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");

        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectRating = rating.getSelectedItem().toString();
                DBHelper dbh = new DBHelper(EditMovie.this);
                data.setMtitle(etMT2.getText().toString());
                data.setGenre(etM2.getText().toString());
                data.setYear(Integer.parseInt(etY2.getText().toString()));
                data.setRating(selectRating);

                dbh.updateMovie(data);
                dbh.close();
                finish();
                //Intent i=new Intent(EditActivity.this, ListActivity.class);
                //startActivity(i);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovie.this);
                dbh.deleteMovie(data.getId());
                finish();
                //Intent i=new Intent(EditActivity.this, ListActivity.class);
                //startActivity(i);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}