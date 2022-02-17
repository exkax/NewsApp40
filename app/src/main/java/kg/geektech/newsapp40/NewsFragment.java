package kg.geektech.newsapp40;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektech.newsapp40.databinding.FragmentNewsBinding;
import kg.geektech.newsapp40.databinding.FragmentProfileBinding;
import kg.geektech.newsapp40.models.News;


public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                save();
            }

            private void save() {
                Bundle bundle = new Bundle();
                News news = new News( binding.etText.getText().toString(), System.currentTimeMillis());
                bundle.putSerializable("news", news);
                getParentFragmentManager().setFragmentResult("rk_news",bundle );
                close();
            }

            private void close() {
                NavController navController = Navigation.findNavController(requireActivity(),
                        R.id.nav_host_fragment_activity_main);
                navController.navigateUp();

            }
        });
    }
}
