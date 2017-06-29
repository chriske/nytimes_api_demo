package hu.autsoft.nytimes.ui.main.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.autsoft.nytimes.R;

public class ArticleItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.article_title)
    TextView title;

    private ArticleClickedListener listener;

    public ArticleItemViewHolder(View itemView, ArticleClickedListener listener) {
        super(itemView);
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public interface ArticleClickedListener {
        void onArticleClicked(int pos);
    }
}
