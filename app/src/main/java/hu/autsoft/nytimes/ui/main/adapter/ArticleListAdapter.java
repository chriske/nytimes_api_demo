package hu.autsoft.nytimes.ui.main.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import hu.autsoft.nytimes.NYTApplication;
import hu.autsoft.nytimes.R;
import hu.autsoft.nytimes.model.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleItemViewHolder> {

    @Inject
    Context context;

    private final ArticleItemViewHolder.ArticleClickedListener listener;
    private ArrayList<Article> articles;

    public ArticleListAdapter(ArticleItemViewHolder.ArticleClickedListener listener) {
        this.listener = listener;
        NYTApplication.injector.inject(this);
    }

    @Override
    public ArticleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_article_item, parent, false);

        return new ArticleItemViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(ArticleItemViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.setTitle(article.getTitle());
        holder.setByLine(article.getByLine());
        holder.setDate(article.getDate());

        if (article.getMedia() != null &&
                article.getMedia().get(0) != null &&
                article.getMedia().get(0) != null &&
                article.getMedia().get(0).getMetaDatas().get(0).getUrl() != null) {

            holder.setImageUrl(context, article.getMedia().get(0).getMetaDatas().get(0).getUrl());
        } else {
            holder.setImageUrl(context, null);
        }
    }

    @Override
    public int getItemCount() {
        if (articles != null) {
            return articles.size();
        }

        return 0;
    }

    public Article getItemAtPosition(int pos) {
        if (articles != null && articles.size() >= pos) {
            return articles.get(pos);
        }

        return null;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }
}
