package kg.geektech.newsapp40.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import kg.geektech.newsapp40.NewsAdapter;
import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentHomeBinding;
import kg.geektech.newsapp40.models.News;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NewsAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
            return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragmentResultListener();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment();
            }


            private void openFragment() {
                NavController navController = Navigation.findNavController(requireActivity(),
                        R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.newsFragment);

            }
        });

    }

    private void initFragmentResultListener() {
        getParentFragmentManager().setFragmentResultListener("rk_news",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        News news = (News) result.getSerializable("news");
                        adapter.setNewsList(news);
                        binding.recycler.setAdapter(adapter);
                        Log.e("Home", "text:" + news.getTitle());

                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}