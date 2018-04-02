package anna.poddubnaya.presentation.screens.userList;

import android.databinding.ObservableField;

import anna.poddubnaya.domain.entity.UserEntity;


public class ItemViewModel {


    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> id = new ObservableField<>();

    public void setUserEntity(UserEntity userEntity) {
        name.set(userEntity.getUsername());
        id.set(userEntity.getObjectId());
    }
}

