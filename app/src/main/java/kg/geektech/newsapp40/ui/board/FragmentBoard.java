package kg.geektech.newsapp40.ui.board;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektech.newsapp40.Prefs;
import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.BoardFragmentBinding;


public class FragmentBoard extends Fragment {

    private BoardFragmentBinding binding;
    private BoardAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = BoardFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new BoardAdapter();
        binding.viewPager.setAdapter(adapter);
        binding.wormDotsIndicator.setViewPager2(binding.viewPager);

        binding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close(view);
            }
        });

    }

    private void close(View view) {
        Prefs prefs = new Prefs(requireContext());
        prefs.saveBoardState();
        NavController navController = Navigation.findNavController((Activity) view.getContext(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

}