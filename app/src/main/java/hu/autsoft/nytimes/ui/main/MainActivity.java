package hu.autsoft.nytimes.ui.main;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.autsoft.nytimes.NYTApplication;
import hu.autsoft.nytimes.R;
import hu.autsoft.nytimes.model.Article;
import hu.autsoft.nytimes.ui.main.adapter.ArticleItemViewHolder;
import hu.autsoft.nytimes.ui.main.adapter.ArticleListAdapter;

public class MainActivity extends AppCompatActivity implements MainScreen, ArticleItemViewHolder.ArticleClickedListener{

    @Inject
    MainPresenter presenter;

    @BindView(R.id.articles_swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.articles_recycler_view)
    RecyclerView articlesList;

    private ArticleListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NYTApplication.injector.inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getArticles();
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachScreen(this);
        presenter.getArticles();
    }

    @Override
    protected void onPause() {
        presenter.detachScreen();
        super.onPause();
    }

    @Override
    public void onArticlesArrived(ArrayList<Article> articles) {
        swipeRefreshLayout.setRefreshing(false);

        adapter = new ArticleListAdapter(this);
        adapter.setArticles(articles);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        articlesList.setLayoutManager(mLayoutManager);
        articlesList.setItemAnimator(new DefaultItemAnimator());
        articlesList.setAdapter(adapter);
    }

    @Override
    public void onArticleClicked(int pos) {

    }
}
