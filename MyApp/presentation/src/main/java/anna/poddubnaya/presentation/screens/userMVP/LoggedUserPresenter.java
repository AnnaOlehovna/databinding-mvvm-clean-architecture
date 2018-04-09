package anna.poddubnaya.presentation.screens.userMVP;


import android.util.Log;

import javax.inject.Inject;

import anna.poddubnaya.app.App;
import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.usecases.GetUserByIdUseCase;
import anna.poddubnaya.presentation.screens.userList.recycler.UserAdapter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoggedUserPresenter extends UserPresenter {


    protected UserAdapter userAdapter = new UserAdapter();

    @Inject
    public GetUserByIdUseCase getUserByIdUseCase;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    public LoggedUserPresenter() {
        super();

        view.showProgress();

        getUserByIdUseCase
                .get("id")
                .toObservable()
                .subscribe(new Observer<UserEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UserEntity userEntity) {
                        Log.e("AAA", "onNext");
                        //TODO smth
                        view.showUser(userEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("AAA", "onError");

                    }

                    @Override
                    public void onComplete() {
                        view.dismissProgress();
                        Log.e("AAA", "onComplete");
                    }

                });
    }

    @Override
    public UserAdapter getUserAdapter() {
        return userAdapter;
    }

    @Override
   public void onUserClick() {

    }
}
