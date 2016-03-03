package com.gabisan.midtermexam2.APIs;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.gabisan.midtermexam2.Entities.Book;
import com.gabisan.midtermexam2.Utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daleg on 23/02/2016.
 */
public class BookApi {

    public static final String API_URL = "http://joseniandroid.herokuapp.com/api/books";

    private static final String B_ID = "_id";
    private static final String B_TITLE = "title";
    private static final String B_GENRE = "genre";
    private static final String B_AUTHOR = "author";
    private static final String B_ISREAD = "isRead";

    public static List<Book> getBooks() {
        String json = HttpUtils.GET(API_URL);

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        //Parse the json response and convert it into a Book object
        String id;
        String title;
        String genre;
        String author;
        Boolean isRead;

        JSONArray jsonArray;

        JSONObject jsonObject;

        List<Book> booksList = new ArrayList<>();

        try {
            jsonArray = new JSONArray(json);
            Log.d("JSON ARRAY SIZE", "" + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                id = jsonObject.getString(B_ID);
                title = jsonObject.getString(B_TITLE);
                genre = jsonObject.getString(B_GENRE);
                author = jsonObject.getString(B_AUTHOR);
                isRead = jsonObject.getBoolean(B_ISREAD);

                booksList.add(new Book(id, title, genre, author, isRead));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return booksList;
    }

}
