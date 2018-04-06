package anna.poddubnaya.presentation.screens.userList;


import android.app.Activity;
import android.content.Intent;

import anna.poddubnaya.presentation.base.Router;
import anna.poddubnaya.presentation.screens.user.UserActivity;

public class UserListRouter extends Router {

    public UserListRouter(Activity activity) {
        super(activity);
    }

    public void navigateToUser(String id) {
        Intent intent = new Intent(getActivity(), UserActivity.class);
        intent.putExtra("KEY_VALUE", id);
        getActivity().startActivity(intent);

        //или
//        UserActivity.show(getActivity(), id);
    }



}
