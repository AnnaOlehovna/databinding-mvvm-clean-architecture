package anna.poddubnaya.data.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import anna.poddubnaya.data.entity.User;
import anna.poddubnaya.data.rest.RestService;
import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.usecases.UserRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class UserRepositoryImpl implements UserRepository {

    private Context context;
    private RestService restService;

    public UserRepositoryImpl(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    @Override
    public Observable<UserEntity> get(String id) {
        return restService
                .loadUserById(id)
                .map(new Function<User, UserEntity>() {
                    @Override
                    public UserEntity apply(User user) throws Exception {
                        return new UserEntity(user.getUsername(),
                                user.getProfileUrl(),
                                user.getAge(),
                                user.getObjectId());
                    }
                });
    }

    @Override
    public Observable<List<UserEntity>> get() {
        return restService
                .loadUsers()
                .map(new Function<List<User>, List<UserEntity>>() {
                    @Override
                    public List<UserEntity> apply(List<User> users) throws Exception {

                        List<UserEntity> list = new ArrayList<>();
                        for (User user : users) {
                            list.add(
                                    new UserEntity(user.getUsername(),
                                            user.getProfileUrl(),
                                            user.getAge(),
                                            user.getObjectId()));
                        }
                        return list;
                    }
                });
    }

    @Override
    public Completable save(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setAge(userEntity.getAge());
        user.setObjectId(userEntity.getObjectId());
        user.setProfileUrl(userEntity.getProfileUrl());
        return restService.save(user);
    }

    @Override
    public Completable remove(String id) {
        return restService.remove(id);
    }
}
