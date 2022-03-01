package kg.geektech.newsapp40.ui;

import android.app.Application;

import androidx.room.Room;

import kg.geektech.newsapp40.room.AppDatabase;

public class App extends Application {
     public  static AppDatabase appDatabase;

    @Override
    public void onCreate(){
        super.onCreate();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database.db")
                .allowMainThreadQueries()
                .build();
    }


    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }


}
