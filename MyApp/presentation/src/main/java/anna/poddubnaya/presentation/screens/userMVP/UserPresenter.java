package anna.poddubnaya.presentation.screens.userMVP;


import anna.poddubnaya.presentation.screens.userList.recycler.UserAdapter;

public abstract class UserPresenter extends BasePresenter<UserView, UserListRouter> {

    public abstract UserAdapter getUserAdapter();

    public abstract void onUserClick();
}
