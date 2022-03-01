package kg.geektech.newsapp40;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.util.ArrayList;

import kg.geektech.newsapp40.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,
                R.id.profileFragment).build();
        navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController,
                appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
//        Navigation.setViewNavController(binding.navView, navController);

       Prefs prefs =  new Prefs(this);
       if (!prefs. isBoardShown())
       navController.navigate(R.id.fragmentBoard);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination,
                                             @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.fragmentBoard) {
                  navView.setVisibility(View.GONE);
                    getSupportActionBar().hide();
                } else {
                    navView.setVisibility(View.VISIBLE);
                    getSupportActionBar().show();

                }
            }
        });


        // File file =  new File(Environment.getExternalStorageState(), "note.txt");
       // file.createNewFile();

        //File folder =  new File(getCacheDir(), "Media/Image");
        //folder.mkdirs();
        //folder.listFiles();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    //            ArrayList<Integer> listFragment = new ArrayList<>();
//            listFragment.add(R.id.navigation_home);
//            listFragment.add(R.id.navigation_dashboard);
//            listFragment.add(R.id.navigation_notifications);
//            listFragment.add(R.id.profileFragment);

//            if (navDestination.getId() == R.id.boardFragment) {
//                getSupportActionBar().hide();
//            } else {
//                getSupportActionBar().show();
//            }






        @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

    @Override
    public void finish() {
        super.finish();
    }

 }