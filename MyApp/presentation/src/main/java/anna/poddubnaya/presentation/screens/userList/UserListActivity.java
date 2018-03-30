package anna.poddubnaya.presentation.screens.userList;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import anna.poddubnaya.presentation.R;
import anna.poddubnaya.presentation.base.BaseMvvmActivity;
import anna.poddubnaya.presentation.databinding.ActivityAllUsersBinding;


public class UserListActivity extends BaseMvvmActivity<ActivityAllUsersBinding,UserListViewModel> {
    @Override
    public int provideLayoutId() {
        return R.layout.activity_all_users;
    }

    @Override
    public UserListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserListViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = findViewById(R.id.recycler_user_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(viewModel.userAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }
}