package com.example.dmitriy.rk_1_android;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.mail.park.articlelistlib.Article;
import ru.mail.park.articlelistlib.ArticleListFragment;
import ru.mail.park.articlelistlib.OnArticleClickListener;


public class MainActivity extends AppCompatActivity {

    public static final String ARTICLE = "article";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createList();
    }

    private void createList() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        manager.popBackStack();

        final ArticleListFragment articleList = new ArticleListFragment();
        articleList.setOnArticleClickListener(new OnArticleListener());

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transaction.replace(R.id.container_left, articleList);
        } else {
            transaction.replace(R.id.container, articleList);
        }

        transaction.commit();
    }


    private class OnArticleListener implements OnArticleClickListener {
        @Override
        public void onArticleClick(Article article) {

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            ArticleFragment articleFragment = new ArticleFragment();

            final Bundle bundle = new Bundle();
            bundle.putSerializable(ARTICLE, article);
            articleFragment.setArguments(bundle);

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                transaction.replace(R.id.container_right, articleFragment);
            } else {
                transaction.replace(R.id.container, articleFragment);
                transaction.addToBackStack(null);
            }
            transaction.commit();
        }
    }
}
