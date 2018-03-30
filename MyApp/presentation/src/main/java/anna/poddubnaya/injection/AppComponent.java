package anna.poddubnaya.injection;

import javax.inject.Singleton;

import anna.poddubnaya.presentation.screens.user.UserViewModel;
import anna.poddubnaya.presentation.screens.userEdit.UserEditViewModel;
import anna.poddubnaya.presentation.screens.userList.UserListViewModel;
import dagger.Component;


@Singleton
@Component(

        modules = {AppModule.class}
)

public interface AppComponent {

    void inject(UserListViewModel userListModel);

    void inject(UserViewModel userViewModel);

    void inject(UserEditViewModel userEditViewModel);


}
