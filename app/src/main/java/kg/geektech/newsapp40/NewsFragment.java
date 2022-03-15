package kg.geektech.newsapp40;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import kg.geektech.newsapp40.databinding.FragmentNewsBinding;
import kg.geektech.newsapp40.databinding.FragmentProfileBinding;
import kg.geektech.newsapp40.models.News;
import kg.geektech.newsapp40.room.AppDatabase;
import kg.geektech.newsapp40.room.NewsDao;
import kg.geektech.newsapp40.ui.App;

@RequiresApi(api = Build.VERSION_CODES.O)

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private News news;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSave.setOnClickListener(this::save);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(1)
                        .playOn(binding.etText);
            }
        });

}

    private void save(View view1) {
        Bundle bundle = new Bundle();
        String title = binding.etText.getText().toString().trim();
        if (title.isEmpty()) {
            Toast.makeText(requireContext(), "TYPE", Toast.LENGTH_SHORT).show();

        }

        long createdAd = System.currentTimeMillis();
        ZonedDateTime time = Instant.ofEpochMilli(createdAd).atZone(ZoneId.of("Asia/Bishkek"));
        String format = time.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"));
        news = new News(title, format);

        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);





        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();

    }
}
