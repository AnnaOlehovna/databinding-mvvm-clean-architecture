package anna.poddubnaya.presentation.screens.user;

import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import anna.poddubnaya.app.App;
import anna.poddubnaya.data.entity.MyError;
import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.usecases.DeleteUserUseCase;
import anna.poddubnaya.domain.usecases.GetUserByIdUseCase;
import anna.poddubnaya.presentation.base.BaseViewModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class UserViewModel extends BaseViewModel<UserRouter> {


    public String userId = "";

    @Inject
    public GetUserByIdUseCase getUserByIdUseCase;

    @Inject
    public DeleteUserUseCase deleteUserUseCase;


    public ObservableField<String> username = new ObservableField<>("");
    public ObservableField<String> userProfileUrl = new ObservableField<>("");
    public ObservableField<String> age = new ObservableField<>("");
    public ObservableBoolean progressVisible = new ObservableBoolean(false);


    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        progressVisible.set(true);
        getUserByIdUseCase
                .get(userId)
                .toObservable()
                .subscribe(new Observer<UserEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UserEntity userEntity) {
                        username.set(userEntity.getUsername());
                        userProfileUrl.set(userEntity.getProfileUrl());
                        age.set(String.valueOf(userEntity.getAge()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof MyError) {
                            MyError myError = (MyError) e;

                            switch (myError.getMyError()) {
                                case NO_INTERNET:
                                    break;
                                case SERVER_NOT_AVAILABLE:
                                    break;
                                case UNKNOWN:
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                        progressVisible.set(false);
                    }
                });
    }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }

    public void onEditButtonClick(View view) {
        if (router != null)
            router.navigateToEditUser(userId, username.get(), age.get(), userProfileUrl.get());
    }

    public void onDeleteButtonClick(View view) {
        deleteUserUseCase.remove(userId)
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (router != null)
                            router.back();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (router != null)
                            router.back();
                    }
                });

    }


}