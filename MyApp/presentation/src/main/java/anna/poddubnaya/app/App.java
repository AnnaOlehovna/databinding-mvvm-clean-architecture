package anna.poddubnaya.app;


import android.app.Application;

import anna.poddubnaya.injection.AppComponent;
import anna.poddubnaya.injection.AppModule;
import anna.poddubnaya.injection.DaggerAppComponent;

public class App extends Application{


    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();


    }
}