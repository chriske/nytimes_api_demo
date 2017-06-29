package hu.autsoft.nytimes.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import hu.autsoft.nytimes.NYTApplication;
import hu.autsoft.nytimes.R;

public class MainActivity extends AppCompatActivity implements MainScreen{

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NYTApplication.injector.inject(this);

        setContentView(R.layout.activity_main);
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
}
