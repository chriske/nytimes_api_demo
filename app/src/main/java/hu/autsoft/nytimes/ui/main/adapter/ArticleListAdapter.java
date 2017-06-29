package hu.autsoft.nytimes.ui.main.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hu.autsoft.nytimes.R;
import hu.autsoft.nytimes.model.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleItemViewHolder> {

    private final ArticleItemViewHolder.ArticleClickedListener listener;
    private ArrayList<Article> articles;

    public ArticleListAdapter(ArticleItemViewHolder.ArticleClickedListener listener) {
        this.listener = listener;
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
    }

    @Override
    public int getItemCount() {
        if (articles != null) {
            return articles.size();
        }

        return 0;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }
}
