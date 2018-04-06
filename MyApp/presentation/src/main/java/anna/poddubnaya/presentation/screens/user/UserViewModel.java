package anna.poddubnaya.presentation.screens.user;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;
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
import anna.poddubnaya.presentation.constants.Constants;
import anna.poddubnaya.presentation.screens.userEdit.UserEditActivity;
import anna.poddubnaya.presentation.screens.userList.UserListActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class UserViewModel extends BaseViewModel {


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

                    }

                    @Override
                    public void onNext(UserEntity userEntity) {
                        Log.e("AAA", "onNext");
                        username.set(userEntity.getUsername());
                        userProfileUrl.set(userEntity.getProfileUrl());
                        age.set(String.valueOf(userEntity.getAge()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("AAA", "onError");
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
                        Log.e("AAA", "onComplete");
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
        Intent intent = new Intent(view.getContext(), UserEditActivity.class);
        intent.putExtra(Constants.getInstance().USER_EDIT, "EDIT");
        intent.putExtra(Constants.getInstance().USER_ID, userId);
        intent.putExtra(Constants.getInstance().USER_NAME, username.get());
        intent.putExtra(Constants.getInstance().USER_AGE, age.get());
        intent.putExtra(Constants.getInstance().USER_URL, userProfileUrl.get());
        view.getContext().startActivity(intent);

    }

    public void onDeleteButtonClick(View view) {
        deleteUserUseCase.remove(userId)
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Intent intent = new Intent(view.getContext(), UserListActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Intent intent = new Intent(view.getContext(), UserListActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent);
                    }
                });

    }


}