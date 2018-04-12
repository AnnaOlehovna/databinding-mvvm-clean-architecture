package anna.poddubnaya.presentation.screens.user;


import android.app.Activity;
import android.content.Intent;

import anna.poddubnaya.presentation.base.Router;
import anna.poddubnaya.presentation.constants.Constants;
import anna.poddubnaya.presentation.screens.userEdit.UserEditActivity;

public class UserRouter extends Router {

    public UserRouter(Activity activity) {
        super(activity);
    }

    public void navigateToEditUser(String userId, String userName, String userAge, String userProfileUrl){
        Intent intent = new Intent(getActivity(), UserEditActivity.class);
        intent.putExtra(Constants.getInstance().USER_EDIT, "EDIT");
        intent.putExtra(Constants.getInstance().USER_ID, userId);
        intent.putExtra(Constants.getInstance().USER_NAME, userName);
        intent.putExtra(Constants.getInstance().USER_AGE, userAge);
        intent.putExtra(Constants.getInstance().USER_URL, userProfileUrl);
        getActivity().startActivity(intent);
    }


}
