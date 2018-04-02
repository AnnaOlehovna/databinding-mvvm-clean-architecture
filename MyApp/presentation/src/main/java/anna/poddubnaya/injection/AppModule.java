package anna.poddubnaya.injection;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import anna.poddubnaya.data.database.AppDatabase;
import anna.poddubnaya.data.repository.UserRepositoryImpl;
import anna.poddubnaya.data.rest.RestApi;
import anna.poddubnaya.data.rest.RestService;
import anna.poddubnaya.domain.UIThread;
import anna.poddubnaya.domain.executor.PostExecutionThread;
import anna.poddubnaya.domain.repository.UserRepository;
import anna.poddubnaya.presentation.BuildConfig;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {


    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return context;
    }

    @Provides
    @Singleton
    public PostExecutionThread getUIThread() {
        return new UIThread();
    }

//    то же самое, что и метод выше
//    @Binds
//    public abstract PostExecutionThread getUIThread2(UIThread uiThread);
//

    @Provides
    @Singleton
    public UserRepository getUserRepository(Context context, RestService restService, AppDatabase database) {
        return new UserRepositoryImpl(context, restService,database);
    }


    @Provides
    @Singleton
    public RestApi getRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }



    @Provides
    @Singleton
    public Retrofit getRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))//gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Rx
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient getOkHttp() {
//        OkHttpClient как и HttpUrlConnection
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//
//                .connectTimeout(10, TimeUnit.SECONDS);

        //это позволит в логах печатать какие запросы отправляются на сервер
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        return builder.build();
    }


    @Provides
    @Singleton
    public Gson getGson() {
        return new GsonBuilder()
                //здесь можно регистировать адаптеры и конвертеры для даты и прочего
                .create();
    }

    @Provides
    @Singleton
    public AppDatabase getAppDatabase(Context context){
        AppDatabase appDatabase = Room.databaseBuilder(context,
                AppDatabase.class,
                "database")
                .fallbackToDestructiveMigration()
                .build();
        return appDatabase;
    }



}
