package anna.poddubnaya.app;


import android.app.Application;

import com.crashlytics.android.Crashlytics;

import anna.poddubnaya.injection.AppComponent;
import anna.poddubnaya.injection.AppModule;
import anna.poddubnaya.injection.DaggerAppComponent;
import io.fabric.sdk.android.Fabric;

public class App extends Application{


    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();


    }
}