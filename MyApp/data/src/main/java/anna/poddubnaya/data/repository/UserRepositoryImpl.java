package anna.poddubnaya.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

import anna.poddubnaya.data.database.AppDatabase;
import anna.poddubnaya.data.database.UserDao;
import anna.poddubnaya.data.entity.User;
import anna.poddubnaya.data.rest.RestService;
import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.repository.UserRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class UserRepositoryImpl implements UserRepository {

    private Context context;
    private RestService restService;
    private UserDao userDao;
    private boolean connected = false;

    public UserRepositoryImpl(Context context, RestService restService, AppDatabase database) {
        this.context = context;
        this.restService = restService;
        this.userDao = database.getUserDao();
    }

    @Override
    public Flowable<UserEntity> get(String id) {
        Flowable<User> user;
        if (checkNetwork()) {
            user = restService.loadUserById(id);
        } else {
            user = userDao.getById(id).take(1).map(new Function<List<User>, User>() {
                @Override
                public User apply(List<User> users) throws Exception {
                    return users.get(0);
                }
            });

        }
        return user
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
    public Flowable<List<UserEntity>> get() {
        Flowable<List<User>> users;
        if (checkNetwork()) {
            users = restService.loadUsers()
                    .doOnNext(new Consumer<List<User>>() {
                        @Override
                        public void accept(List<User> userList) throws Exception {
                            userDao.deleteAll();
                            userDao.insert(userList);
                        }
                    });
        } else {
            users = userDao.getAll().take(1);
        }
        return users
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
        if (checkNetwork()) {
            User user = new User();
            user.setUsername(userEntity.getUsername());
            user.setAge(userEntity.getAge());
            user.setObjectId(userEntity.getObjectId());
            user.setProfileUrl(userEntity.getProfileUrl());
            return restService.save(user);
        } else {
            return Completable.error(new Throwable("no internet connection"));
        }

    }

    @Override
    public Completable remove(String id) {
        if (checkNetwork()) {
            return restService.remove(id);
        } else {
            return Completable.error(new Throwable("no internet connection"));
        }
    }


    public boolean checkNetwork() {
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                isConnected = true;
            }
        }
        return isConnected;
    }
}
