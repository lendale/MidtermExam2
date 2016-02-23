package com.gabisan.midtermexam2.APIs;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.gabisan.midtermexam2.Entities.Book;
import com.gabisan.midtermexam2.Utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by daleg on 23/02/2016.
 */
public class BookApi {

    public static final String BASE_URL = "http://joseniandroid.herokuapp.com";

    public static final String PARAM_API = "api";
    public static final String PARAM_BOOKS = "books";

    private static final String B_ID = "_id";
    private static final String B_TITLE = "title";
    private static final String B_GENRE = "genre";
    private static final String B_AUTHOR = "author";
    private static final String B_ISREAD = "isRead";

    public static List getBooks(Uri uri, @NonNull String requestMethod) {
        String json = HttpUtils.getResponse(uri, requestMethod);

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        //Parse the json response and convert it into a Book object
        String id;
        String title;
        String genre;
        String author;
        Boolean isRead;

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();

        Book book;

        List booksList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                id = jsonObject.getString(B_ID);
                title = jsonObject.getString(B_TITLE);
                genre = jsonObject.getString(B_GENRE);
                author = jsonObject.getString(B_AUTHOR);
                isRead = jsonObject.getBoolean(B_ISREAD);

                book = new Book();

                book.setmId(id);
                book.setmTitle(title);
                book.setmGenre(genre);
                book.setmAuthor(author);
                book.setmIsRead(isRead);

                booksList.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return booksList;
    }

}
