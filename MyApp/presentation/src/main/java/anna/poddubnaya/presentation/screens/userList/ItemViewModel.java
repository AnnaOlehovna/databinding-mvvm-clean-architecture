package anna.poddubnaya.presentation.screens.userList;

import android.databinding.ObservableField;

import anna.poddubnaya.domain.entity.UserEntity;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class ItemViewModel {


    PublishSubject<UserEntity> subject = PublishSubject.create();

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> id = new ObservableField<>();

    public void setUserEntity(UserEntity userEntity) {
        subject.subscribe(new Consumer<UserEntity>() {
            @Override
            public void accept(UserEntity userEntity) throws Exception {
                name.set(userEntity.getUsername());
                id.set(userEntity.getObjectId());
            }
        });
        subject.onNext(userEntity);
    }
}

