package com.gabisan.midtermexam2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.gabisan.midtermexam2.APIs.BookApi;
import com.gabisan.midtermexam2.Adapters.BooksAdapter;
import com.gabisan.midtermexam2.Entities.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mMainListView;

    getBookCollection getBooks = new getBookCollection();

    BookApi bookApi = new BookApi();

    List<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMainListView = (ListView) findViewById(R.id.mainListView);
        getBooks.execute();
    }

    private class getBookCollection extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            books = bookApi.getBooks();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            BooksAdapter adapter = new BooksAdapter(getApplicationContext(), R.layout.list_item, books);

            mMainListView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
