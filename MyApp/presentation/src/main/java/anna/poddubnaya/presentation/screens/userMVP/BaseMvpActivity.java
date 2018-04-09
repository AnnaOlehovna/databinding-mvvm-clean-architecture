package anna.poddubnaya.presentation.screens.userMVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import anna.poddubnaya.presentation.base.Router;

public abstract class BaseMvpActivity<Presenter extends BasePresenter,
        R extends Router> extends AppCompatActivity implements BaseView{

    protected Presenter presenter;

    @Inject
    protected R router;

    public abstract int provideLayoutId();

    public abstract Presenter providePresenter();

    public abstract R provideRouter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter =  providePresenter();
        setContentView(provideLayoutId());
        router = provideRouter();
        presenter.attach( this,router);
    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        router=null;
        presenter.detach();
        presenter.onDestroy();

    }

    @Override
    public void showProgress() {
    }

    @Override
    public void dismissProgress() {
    }

    @Override
    public void showError(Exception e) {
    }
}
