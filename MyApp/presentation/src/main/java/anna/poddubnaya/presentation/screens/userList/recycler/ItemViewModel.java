package anna.poddubnaya.presentation.screens.userList.recycler;

import android.databinding.ObservableField;

import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.presentation.base.BaseItemViewModel;


public class ItemViewModel extends BaseItemViewModel<UserEntity> {


    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> id = new ObservableField<>();

    @Override
    public void setItem(UserEntity userEntity, int position) {
        name.set(userEntity.getUsername());
        id.set(userEntity.getObjectId());
    }
}

