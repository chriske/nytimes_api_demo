package hu.autsoft.nytimes.ui.main;

import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
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
import hu.autsoft.nytimes.ui.BaseActivity;
import hu.autsoft.nytimes.ui.main.adapter.ArticleItemViewHolder;
import hu.autsoft.nytimes.ui.main.adapter.ArticleListAdapter;

public class MainActivity extends BaseActivity implements MainScreen, ArticleItemViewHolder.ArticleClickedListener{

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

        initUIComponents();
    }

    private void initUIComponents() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getArticles();
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        adapter = new ArticleListAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        articlesList.setLayoutManager(mLayoutManager);
        articlesList.setItemAnimator(new DefaultItemAnimator());
        articlesList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachScreen(this);
        showProgressDialog();
        presenter.getArticles();
    }

    @Override
    protected void onPause() {
        presenter.detachScreen();
        super.onPause();
    }

    @Override
    public void onArticleRequestError(int code) {
        hideProgressIndicators();

        switch (code) {
            case MainPresenter.NETWORK_ERROR: {
                showDialog(getString(R.string.error_title), getString(R.string.error));
                break;
            }

            case MainPresenter.RESPONSE_ERROR: {
                showDialog(getString(R.string.response_error_title), getString(R.string.response_error));
                break;
            }
        }
    }

    @Override
    public void onArticlesArrived(ArrayList<Article> articles) {
        hideProgressIndicators();
        adapter.setArticles(articles);
    }

    @Override
    public void onArticleClicked(int pos) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(adapter.getItemAtPosition(pos).getUrl()));
    }

    private void hideProgressIndicators() {
        swipeRefreshLayout.setRefreshing(false);
        removeProgressDialog();
    }
}
