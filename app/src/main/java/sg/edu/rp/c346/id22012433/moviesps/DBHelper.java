package sg.edu.rp.c346.id22012433.moviesps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    // Start version with 1
    // increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 2;
    // Filename of the database
    private static final String DATABASE_NAME = "Movies.db";
    private static final String TABLE_MOVIE = "movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "mtitle";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_RATING + " TEXT)";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);

    }

    public void insertMovie(String mtitle, String genre, int year, String rating) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, mtitle);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        db.insert(TABLE_MOVIE, null, values);

        db.close();
    }

    public ArrayList<String> getMovieContent() {
        // Create an ArrayList that holds String objects
        ArrayList<String> movies = new ArrayList<String>();
        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                movies.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return movies;
    }

    public int updateMovie(Movie data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getMtitle());
        values.put(COLUMN_GENRE, data.getGenre());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_RATING, data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_MOVIE, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIE, condition, args);
        db.close();
        return result;
    }

public ArrayList<Movie> getMovies() {
    ArrayList<Movie> movies = new ArrayList<Movie>();
    SQLiteDatabase db = this.getReadableDatabase();
    String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};
    Cursor cursor = db.query(TABLE_MOVIE, columns, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
        do {
            int id  = cursor.getInt(0);
            String title = cursor.getString(1);
            String genre = cursor.getString(2);
            int year = cursor.getInt(3);
            String rating = cursor.getString(4);


            Movie obj = new Movie(id, title, genre, year,rating);
            movies.add(obj);
        } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return movies;
}
    public ArrayList<Movie> getAllMovies(int keyword) {
        ArrayList<Movie> notes = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};
        String condition = COLUMN_RATING + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_MOVIE, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title= cursor.getString(1);
                String genre= cursor.getString(2);
                int years = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie note = new Movie(id, title,genre,years,rating);
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

}