package anna.poddubnaya.presentation.screens.user;

import android.arch.lifecycle.ViewModelProviders;

import anna.poddubnaya.presentation.R;
import anna.poddubnaya.presentation.base.BaseMvvmActivity;
import anna.poddubnaya.presentation.databinding.ActivityUserBinding;

public class UserActivity extends BaseMvvmActivity<ActivityUserBinding,UserViewModel> {


    @Override
    public int provideLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public UserViewModel provideViewModel() {
        UserViewModel  userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.userId = getIntent().getStringExtra("ID");
        return userViewModel;
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }
}