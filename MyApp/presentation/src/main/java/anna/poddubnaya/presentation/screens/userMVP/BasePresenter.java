package anna.poddubnaya.presentation.screens.userMVP;

import android.support.annotation.Nullable;

import anna.poddubnaya.presentation.base.Router;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<View extends BaseView, R extends Router> {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Nullable
    protected View view;

    @Nullable
    protected R router;

    public void attach( View view,R router) {
        this.view = view;
        this.router = router;
    }

    public void detach() {
        this.view = null;
        this.router = null;

    }

    public abstract void createInject();

    public BasePresenter() {
        super();
        createInject();
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestroy() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

}

