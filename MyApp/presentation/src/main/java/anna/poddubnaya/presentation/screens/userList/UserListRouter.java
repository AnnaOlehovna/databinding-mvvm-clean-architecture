package anna.poddubnaya.presentation.screens.userList;


import android.app.Activity;
import android.content.Intent;

import anna.poddubnaya.presentation.base.Router;
import anna.poddubnaya.presentation.constants.Constants;
import anna.poddubnaya.presentation.screens.user.UserActivity;
import anna.poddubnaya.presentation.screens.userEdit.UserEditActivity;

public class UserListRouter extends Router {

    public UserListRouter(Activity activity) {
        super(activity);
    }

    public void navigateToUser(String id) {
        Intent intent = new Intent(getActivity(), UserActivity.class);
        intent.putExtra(Constants.getInstance().USER_ID, id);
        getActivity().startActivity(intent);
    }

    public void navigateToAddUser(){
        Intent intent = new Intent(getActivity(), UserEditActivity.class);
        intent.putExtra(Constants.getInstance().USER_EDIT,"ADD");
        getActivity().startActivity(intent);

    }



}
