package anna.poddubnaya.data.rest;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import anna.poddubnaya.data.entity.User;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;


@Singleton
public class RestService {


    private RestApi restApi;

    @Inject
    public RestService(RestApi restApi) {
        this.restApi = restApi;
    }


    public Flowable<List<User>> loadUsers() {
        return restApi.loadUsers();
    }


    public Flowable<User> loadUserById(String id) {
        return restApi.loadUserById(id);
    }

    public Completable save(User user){
        return restApi.save(user);
    }

    public Completable remove(String id){
        return restApi.remove(id);
    }

}