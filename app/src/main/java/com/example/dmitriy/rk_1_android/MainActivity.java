package com.example.dmitriy.rk_1_android;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
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
        if (savedInstanceState != null && savedInstanceState.getSerializable(ARTICLE) != null) {
            openArticle((Article) savedInstanceState.getSerializable(ARTICLE));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final FragmentManager manager = getSupportFragmentManager();
        ArticleFragment articleFragment = null;

        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            articleFragment = (ArticleFragment) manager.findFragmentById(R.id.container_right);
        } else {
            final Fragment fragment = manager.findFragmentById(R.id.container);
            if (fragment instanceof ArticleFragment) {
                articleFragment = (ArticleFragment) fragment;
            }
        }
        if (articleFragment != null) {
            outState.putSerializable(ARTICLE, articleFragment.getArticle());
        }
    }

    private void createList() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        manager.popBackStack();

        final ArticleListFragment articleList = new ArticleListFragment();
        articleList.setOnArticleClickListener(new OnArticleClickListener() {
            @Override
            public void onArticleClick(Article article) {
                openArticle(article);
            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transaction.replace(R.id.container_left, articleList);
        } else {
            transaction.replace(R.id.container, articleList);
        }

        transaction.commit();
    }

    private void openArticle(Article article) {

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
