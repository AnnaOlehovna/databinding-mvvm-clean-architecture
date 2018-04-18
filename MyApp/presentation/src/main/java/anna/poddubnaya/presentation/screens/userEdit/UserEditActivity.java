package anna.poddubnaya.presentation.screens.userEdit;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import javax.inject.Inject;

import anna.poddubnaya.presentation.R;
import anna.poddubnaya.presentation.base.BaseMvvmActivity;
import anna.poddubnaya.presentation.constants.Constants;
import anna.poddubnaya.presentation.databinding.ActivityUserEditBinding;
import anna.poddubnaya.presentation.screens.userList.UserListRouter;


public class UserEditActivity extends BaseMvvmActivity<ActivityUserEditBinding, UserEditViewModel, UserEditRouter> {
    private int counter = 0;

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
        binding.baseView.bringToFront();
        binding.buttonSaveUser.bringToFront();
        binding.arrowForButton.bringToFront();
        binding.textForButton.bringToFront();
        binding.editUsername.setVisibility(View.INVISIBLE);
        binding.editUserage.setVisibility(View.INVISIBLE);
        binding.editUrl.setVisibility(View.INVISIBLE);

        binding.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (counter) {
                    case 0:
                        binding.baseView.bringToFront();
                        binding.editUsername.setVisibility(View.VISIBLE);
                        binding.editUsername.bringToFront();
                        binding.arrowUsername.bringToFront();
                        binding.textUsername.bringToFront();
                        counter = 1;
                        break;
                    case 1:
                        binding.baseView.bringToFront();
                        binding.editUserage.bringToFront();
                        binding.editUsername.setVisibility(View.INVISIBLE);
                        binding.editUserage.setVisibility(View.VISIBLE);
                        binding.arrowUserage.bringToFront();
                        binding.textUserage.bringToFront();
                        counter = 2;
                        break;
                    case 2:
                        binding.baseView.bringToFront();
                        binding.editUserage.setVisibility(View.INVISIBLE);
                        binding.editUrl.setVisibility(View.VISIBLE);
                        binding.editUrl.bringToFront();
                        binding.arrowUserUrl.bringToFront();
                        binding.textUserUrl.bringToFront();
                        counter = 3;
                        break;
                    case 3:
                        binding.baseView.setVisibility(View.GONE);
                        binding.arrowForButton.setVisibility(View.GONE);
                        binding.textForButton.setVisibility(View.GONE);
                        binding.arrowUsername.setVisibility(View.GONE);
                        binding.textUsername.setVisibility(View.GONE);
                        binding.arrowUserage.setVisibility(View.GONE);
                        binding.textUserage.setVisibility(View.GONE);
                        binding.arrowUserUrl.setVisibility(View.GONE);
                        binding.textUserUrl.setVisibility(View.GONE);
                        binding.editUsername.setVisibility(View.VISIBLE);
                        binding.editUserage.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }

            }
        });


    }


}