package anna.poddubnaya.presentation.screens.userList;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import anna.poddubnaya.app.App;
import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.repository.GetUserListUseCase;
import anna.poddubnaya.presentation.R;
import anna.poddubnaya.presentation.base.BaseViewModel;
import anna.poddubnaya.presentation.screens.user.UserActivity;
import anna.poddubnaya.presentation.screens.userEdit.UserEditActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserListViewModel extends BaseViewModel {

    public UserAdapter userAdapter = new UserAdapter();


    @Inject
    public GetUserListUseCase getUserListUseCase;

    @Inject
    public Context context;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);

    }

    public UserListViewModel() {
        super();
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserListUseCase.get()
                .subscribe(new Observer<List<UserEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<UserEntity> userEntities) {
                        userAdapter.setUserList(userEntities);
                        userAdapter.setListener(new UserAdapter.OnUserClickListener() {
                            @Override
                            public void onClick(UserEntity user) {
                                Intent intent = new Intent(context, UserActivity.class);
                                intent.putExtra("ID",user.getObjectId());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onAddButtonClick(View view){
        Intent intent = new Intent(view.getContext(), UserEditActivity.class);
        intent.putExtra(view.getResources().getString(R.string.edit_user),"ADD");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }


}