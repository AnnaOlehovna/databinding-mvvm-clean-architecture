package anna.poddubnaya.data.rest;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import anna.poddubnaya.data.entity.MyError;
import anna.poddubnaya.data.entity.User;
import io.reactivex.Completable;
import io.reactivex.Flowable;


@Singleton
public class RestService {


    private RestApi restApi;
    private ErrorTransformers errorTransformers;

    @Inject
    public RestService(RestApi restApi, ErrorTransformers errorTransformers ) {
        this.restApi = restApi;
        this.errorTransformers=errorTransformers;
    }


    public Flowable<List<User>> loadUsers() {
        return restApi
                .loadUsers()
                .compose(errorTransformers.<List<User>, MyError>parseHttpError());
    }


    public Flowable<User> loadUserById(String id) {
        return restApi
                .loadUserById(id)
                .compose(errorTransformers.<User, MyError>parseHttpError());
    }

    public Completable save(User user){
        return restApi
                .save(user)
                .compose(errorTransformers.parseError());

    }

    public Completable remove(String id){
        return restApi.remove(id)
                .compose(errorTransformers.parseError());
    }

}