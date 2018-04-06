package anna.poddubnaya.presentation.screens.userList;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;


import anna.poddubnaya.presentation.R;
import anna.poddubnaya.presentation.base.BaseMvvmActivity;
import anna.poddubnaya.presentation.databinding.ActivityAllUsersBinding;


public class UserListActivity extends BaseMvvmActivity<ActivityAllUsersBinding,UserListViewModel,UserListRouter> {
    @Override
    public int provideLayoutId() {
        return R.layout.activity_all_users;
    }

    @Override
    public UserListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserListViewModel.class);
    }

    @Override
    public UserListRouter provideRouter() {
        return new UserListRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding в себе содержит ссылки на объекты в xml по id
        binding.recyclerUserList.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerUserList.setHasFixedSize(true);
        binding.recyclerUserList.setAdapter(viewModel.userAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }
}