package anna.poddubnaya.domain.repository;


import java.util.List;

import anna.poddubnaya.domain.entity.UserEntity;
import io.reactivex.Completable;
import io.reactivex.Flowable;


public interface UserRepository {

    Flowable<UserEntity> get(String id);
    Flowable<List<UserEntity>> get();
    Completable save(UserEntity userEntity);
    Completable remove(String id);
}