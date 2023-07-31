package sg.edu.rp.c346.id22012433.moviesps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnIn,btnSl;
    EditText etMovieTitle, etGenre, etYear;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnIn=findViewById(R.id.btninsert);
        btnSl=findViewById(R.id.btnshowlist);
        etMovieTitle=findViewById(R.id.etMovieTitle);
        etGenre=findViewById(R.id.etGenre);
        etYear=findViewById(R.id.etYear);
        spinner = findViewById(R.id.etRating);
        btnIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                String mTitle=etMovieTitle.getText().toString();
                String singers=etGenre.getText().toString();
                int year=Integer.parseInt(etYear.getText().toString());
                String selectRating = spinner.getSelectedItem().toString();
                db.insertMovie(mTitle,singers,year,selectRating);
                Toast.makeText(MainActivity.this,"Movie Added",Toast.LENGTH_SHORT).show();
            }
        });
        btnSl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ListMovie.class);
                startActivity(intent);
            }
        });

    }
}