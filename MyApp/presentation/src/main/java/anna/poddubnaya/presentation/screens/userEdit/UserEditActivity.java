package anna.poddubnaya.presentation.screens.userEdit;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import anna.poddubnaya.presentation.R;
import anna.poddubnaya.presentation.base.BaseMvvmActivity;
import anna.poddubnaya.presentation.constants.Constants;
import anna.poddubnaya.presentation.databinding.ActivityUserEditBinding;
import anna.poddubnaya.presentation.screens.userList.UserListRouter;


public class UserEditActivity extends BaseMvvmActivity<ActivityUserEditBinding, UserEditViewModel,UserEditRouter> {
    @Override
    public int provideLayoutId() {
        return R.layout.activity_user_edit;
    }

    @Override
    public UserEditViewModel provideViewModel() {
        UserEditViewModel viewModel = ViewModelProviders.of(this).get(UserEditViewModel.class);
        if (getIntent().getStringExtra(Constants.getInstance().USER_EDIT).equals("EDIT")) {
            viewModel.id.set(getIntent().getStringExtra(Constants.getInstance().USER_ID));
            viewModel.name.set(getIntent().getStringExtra(Constants.getInstance().USER_NAME));
            viewModel.profileUrl.set(getIntent().getStringExtra(Constants.getInstance().USER_URL));
            viewModel.age.set(getIntent().getStringExtra(Constants.getInstance().USER_AGE));
            return viewModel;
        } else if (getIntent().getStringExtra(Constants.getInstance().USER_EDIT).equals("ADD")) {
            return viewModel;
        }
        return null;
    }

    @Override
    public UserEditRouter provideRouter() {
        return new UserEditRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding.buttonSaveUser.bringToFront();
      binding.textForButton.bringToFront();
      binding.arrowForButton.bringToFront();
    }


}