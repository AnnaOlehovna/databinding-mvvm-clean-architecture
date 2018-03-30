package anna.poddubnaya.data.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import anna.poddubnaya.data.database.AppDatabase;
import anna.poddubnaya.data.database.UserDao;
import anna.poddubnaya.data.entity.User;
import anna.poddubnaya.data.rest.RestService;
import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.usecases.UserRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class UserRepositoryImpl implements UserRepository {

    private Context context;
    private RestService restService;
    private UserDao userDao;

    public UserRepositoryImpl(Context context, RestService restService, AppDatabase database) {
        this.context = context;
        this.restService = restService;
        this.userDao = database.getUserDao();
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
                .doOnNext(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> userList) throws Exception {
                        userDao.deleteAll();
                        userDao.insert(userList);
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<User>>>() {
                    @Override
                    public ObservableSource<? extends List<User>> apply(Throwable throwable) throws Exception {

                        //желательно проверить ошибку - если интернет - то берем из базы данных
                        //если не интернет - то надо выкинуть саму ошибку
                        return userDao.getAll().toObservable().take(1);
                    }
                })
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
                }) ;
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
