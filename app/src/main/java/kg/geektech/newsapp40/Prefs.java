package kg.geektech.newsapp40;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Context context) {
       preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE) ;
    }

    public  void saveBoardState(){
        preferences.edit().putBoolean("board_state", true).apply();
    }

    public  boolean isBoardShown(){
        return  preferences.getBoolean("board_state", false);
    }

    public void saveUserName(String name){
        preferences.edit().putString("name", name).apply();
    }

    public String getName() {
        return preferences.getString("name", "");
    }

    public void saveImageUri(String image) {
        preferences.edit().putString("avatar", image).apply();
    }

    public String getImageUri() {
        return preferences.getString("avatar", null);
    }

}

