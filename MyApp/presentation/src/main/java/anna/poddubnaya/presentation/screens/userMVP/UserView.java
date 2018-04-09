package anna.poddubnaya.presentation.screens.userMVP;

import anna.poddubnaya.domain.entity.UserEntity;

interface UserView extends BaseView{

    void showUser(UserEntity userEntity);
}
