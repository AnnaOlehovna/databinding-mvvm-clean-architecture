package anna.poddubnaya.presentation.screens.user;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import anna.poddubnaya.presentation.R;
import anna.poddubnaya.presentation.base.BaseMvvmActivity;
import anna.poddubnaya.presentation.constants.Constants;
import anna.poddubnaya.presentation.databinding.ActivityUserBinding;
import anna.poddubnaya.presentation.screens.userList.UserListRouter;

public class UserActivity extends BaseMvvmActivity<ActivityUserBinding,UserViewModel,UserListRouter> {

    private static final String KEY_VALUE = "KEY_VALUE";

    public static void show(Activity activity, String id){
        Intent intent = new Intent(activity,UserActivity.class);
        intent.putExtra(KEY_VALUE, id);
       activity.startActivity(intent);
    }

    @Override
    public int provideLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public UserViewModel provideViewModel() {
        UserViewModel  userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.userId = getIntent().getStringExtra(KEY_VALUE);
        return userViewModel;
    }

    @Override
    public UserListRouter provideRouter() {
        return new UserListRouter(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }
}