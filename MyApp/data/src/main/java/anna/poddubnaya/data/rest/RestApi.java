package anna.poddubnaya.data.rest;


import java.util.List;

import anna.poddubnaya.data.entity.User;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestApi {

    @GET("data/User")
    Flowable<List<User>> loadUsers();

    @GET("data/User/{id}")
    Flowable<User> loadUserById(@Path("id") String id);

    @PUT("data/User")
    Completable save(@Body User user);

    @DELETE("data/User/{id}")
    Completable remove(@Path("id") String id);


}