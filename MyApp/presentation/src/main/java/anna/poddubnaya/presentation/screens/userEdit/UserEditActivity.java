package anna.poddubnaya.presentation.screens.userEdit;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import anna.poddubnaya.presentation.R;
import anna.poddubnaya.presentation.base.BaseMvvmActivity;
import anna.poddubnaya.presentation.databinding.ActivityUserEditBinding;


public class UserEditActivity extends BaseMvvmActivity<ActivityUserEditBinding, UserEditViewModel> {
    @Override
    public int provideLayoutId() {
        return R.layout.activity_user_edit;
    }

    @Override
    public UserEditViewModel provideViewModel() {
        UserEditViewModel viewModel = ViewModelProviders.of(this).get(UserEditViewModel.class);
        if (getIntent().getStringExtra(getResources().getString(R.string.edit_user)).equals("EDIT")) {
            viewModel.id.set(getIntent().getStringExtra(getResources().getString(R.string.user_id)));
            viewModel.name.set(getIntent().getStringExtra(getResources().getString(R.string.user_name)));
            viewModel.profileUrl.set(getIntent().getStringExtra(getResources().getString(R.string.user_image)));
            viewModel.age.set(getIntent().getStringExtra(getResources().getString(R.string.user_age)));
            return viewModel;
        } else if (getIntent().getStringExtra(getResources().getString(R.string.edit_user)).equals("ADD")) {
            return viewModel;
        }
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}