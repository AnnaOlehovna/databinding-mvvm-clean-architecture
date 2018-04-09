package anna.poddubnaya.presentation.screens.userMVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.presentation.R;


public class UserMVPActivity extends BaseMvpActivity<UserPresenter,UserListRouter>
implements UserView{

    @Override
    public int provideLayoutId() {
        return R.layout.activity_all_users;
    }

    @Override
    public UserPresenter providePresenter() {
        return new LoggedUserPresenter();
    }


    @Override
    public UserListRouter provideRouter() {
        return new UserListRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = findViewById(R.id.recycler_user_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
       recyclerView.setAdapter(presenter.getUserAdapter());


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void showUser(UserEntity userEntity) {
        //засунуть в TextView нужные данные
        //TextView через findViewById() определить в onCreate()
    }
}