package anna.poddubnaya.domain.usecases;


import java.util.List;

import anna.poddubnaya.domain.entity.UserEntity;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface UserRepository {

    Observable<UserEntity> get(String id);
    Observable<List<UserEntity>> get();
    Completable save(UserEntity userEntity);
    Completable remove(String id);
}