package com.example.dmitriy.rk_1_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.mail.park.articlelistlib.Article;


public class ArticleFragment extends Fragment {

    public ArticleFragment() { }

    private Article article;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        // Inflate the layout for this fragment
        final LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_article, container, false);
        article = (Article) getArguments().getSerializable(MainActivity.ARTICLE);

        TextView titleView = view.findViewById(R.id.title);
        TextView dateView = view.findViewById(R.id.date);
        TextView contentView = view.findViewById(R.id.content);
        if (article == null) {
            Log.w("Serializable is null", "Article from bundle is null");
            return view;
        }
        titleView.setText(article.getTitle());
        dateView.setText(article.getDate().toString());
        contentView.setText(article.getContent());

        return view;
    }

    public Article getArticle() {
        return article;
    }
}
