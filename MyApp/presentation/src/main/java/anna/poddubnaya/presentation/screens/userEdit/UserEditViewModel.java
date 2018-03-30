package anna.poddubnaya.presentation.screens.userEdit;


import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import javax.inject.Inject;

import anna.poddubnaya.app.App;
import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.repository.SaveUserUseCase;
import anna.poddubnaya.presentation.base.BaseViewModel;
import anna.poddubnaya.presentation.screens.userList.UserListActivity;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class UserEditViewModel extends BaseViewModel {

    @Inject
    public Context context;
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
        UserEntity userEntity = new UserEntity(name.get(), profileUrl.get(),
                Integer.valueOf(age.get()), id.get());
        saveUserUseCase.save(userEntity)
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Intent intent = new Intent(context, UserListActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}