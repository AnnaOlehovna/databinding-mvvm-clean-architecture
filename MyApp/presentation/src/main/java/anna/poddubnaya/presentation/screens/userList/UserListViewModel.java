package anna.poddubnaya.presentation.screens.userList;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import anna.poddubnaya.app.App;
import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.usecases.GetUserListUseCase;
import anna.poddubnaya.presentation.base.BaseAdapter;
import anna.poddubnaya.presentation.base.BaseViewModel;
import anna.poddubnaya.presentation.constants.Constants;
import anna.poddubnaya.presentation.screens.userEdit.UserEditActivity;
import anna.poddubnaya.presentation.screens.userList.recycler.UserAdapter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class UserListViewModel extends BaseViewModel<UserListRouter> {

    protected UserAdapter userAdapter = new UserAdapter();


    @Inject
    public GetUserListUseCase getUserListUseCase;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);

    }

    public UserListViewModel() {
        super();
        userAdapter
                .observeClick()
                .subscribe(new Consumer<BaseAdapter.ItemEntity>() {
                    @Override
                    public void accept(BaseAdapter.ItemEntity itemEntity) throws Exception {
                        UserEntity user = (UserEntity) itemEntity.model;
                        if(router!=null)
                        router.navigateToUser(user.getObjectId());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserListUseCase.get()
                .toObservable()
                .subscribe(new Observer<List<UserEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<UserEntity> userEntities) {
                        userAdapter.setItemList(userEntities);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onAddButtonClick(View view) {
        if(router!=null)
       router.navigateToAddUser();
    }


}