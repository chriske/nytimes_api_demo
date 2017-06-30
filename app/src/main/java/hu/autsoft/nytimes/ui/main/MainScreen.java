package hu.autsoft.nytimes.ui.main;


import java.util.ArrayList;

import hu.autsoft.nytimes.model.Article;

public interface MainScreen {
    void onArticleRequestError(int code);
    void onArticlesArrived(ArrayList<Article> articles);
}
