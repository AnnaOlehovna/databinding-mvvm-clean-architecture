package anna.poddubnaya.presentation.screens.userMVP;


import android.app.Activity;
import android.content.Intent;

import anna.poddubnaya.presentation.base.Router;

public class UserListRouter extends Router {

    public UserListRouter(Activity activity) {
        super(activity);
    }

    public void navigateToUser(String id) {
        Intent intent = new Intent(getActivity(), UserMVPActivity.class);
        intent.putExtra("KEY_VALUE", id);
        getActivity().startActivity(intent);

        //или
//        UserMVPActivity.show(getActivity(), id);
    }



}
