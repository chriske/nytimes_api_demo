package hu.autsoft.nytimes.ui.main.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.autsoft.nytimes.R;

public class ArticleItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.article_title)
    TextView title;
    @BindView(R.id.article_byline)
    TextView byline;
    @BindView(R.id.article_date)
    TextView date;
    @BindView(R.id.article_image)
    CircularImageView imageView;

    private ArticleClickedListener listener;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public ArticleItemViewHolder(View itemView, ArticleClickedListener listener) {
        super(itemView);
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setByLine(String byLine) {
        this.byline.setText(byLine);
    }

    public void setDate(Date date) {
        this.date.setText(formatter.format(date));
    }

    public void setImageUrl(Context context, String url) {
        Picasso.with(context).load(url).into(imageView);
    }

    @OnClick(R.id.article_next_button)
    public void articleClicked() {
        listener.onArticleClicked(getAdapterPosition());
    }

    public interface ArticleClickedListener {
        void onArticleClicked(int pos);
    }
}
