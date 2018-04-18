package anna.poddubnaya.presentation.screens.userEdit;


import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import anna.poddubnaya.app.App;
import anna.poddubnaya.data.entity.MyError;
import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.usecases.SaveUserUseCase;
import anna.poddubnaya.presentation.base.BaseViewModel;
import anna.poddubnaya.presentation.screens.userList.UserListActivity;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class UserEditViewModel extends BaseViewModel<UserEditRouter> {

    @Inject
    public SaveUserUseCase saveUserUseCase;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> id = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();
    public ObservableField<String> profileUrl = new ObservableField<>();

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    public UserEditViewModel() {
    }

    public void onSaveButtonClick(View view) {
        if (age.get() == null) {
            age.set("0");
        }
        UserEntity userEntity = new UserEntity(name.get(), profileUrl.get(),
                Integer.valueOf(age.get()), id.get());
        saveUserUseCase.save(userEntity)
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (router != null)
                            router.back();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (router != null) {
                            if (throwable instanceof MyError) {
                                MyError myError = (MyError) throwable;
                                switch (myError.getMyError()) {
                                    case NO_INTERNET:
                                        Toast.makeText(router.getActivity(), "Sorry, you can not save User without internet connection." +
                                                "Please, check internet", Toast.LENGTH_SHORT).show();
                                        break;
                                    case SERVER_NOT_AVAILABLE:
                                        Toast.makeText(router.getActivity(), "Sorry, smth wrong with server. Please, try later" +
                                                "Please, check internet", Toast.LENGTH_SHORT).show();
                                        break;
                                    case UNKNOWN:
                                        break;
                                }
                            }
                        }
                    }
                });
    }
}