package com.example.task.ui.fragment.dashboard;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.task.App;
import com.example.task.R;
import com.example.task.databinding.FragmentDashboardBinding;
import com.example.task.ui.fragment.base.BaseFragment;
import com.example.task.ui.model.ModelForTask;

import java.util.Date;

public class DashboardFragment extends BaseFragment<FragmentDashboardBinding> {


    @Override
    protected FragmentDashboardBinding getBinding() {
        return FragmentDashboardBinding.inflate(getLayoutInflater());

    }

    @Override
    public void setupUI() {
    }


    @Override
    protected void setupObservers() {
        initPref();
        initListener();
    }

    private void initPref() {
    }


    private void initListener() {
        binding.btnSave.setOnClickListener(v -> {
            createDataToHomeFragment();
            closeFragment();
        });
    }

    private void closeFragment() {
        NavController navController = Navigation.findNavController
                (requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }

    private void createDataToHomeFragment() {
        String text = binding.edText.getText().toString();
        Date date = new Date();
        App.getDatabase().dao().createInsert(new ModelForTask(text, date.toString()));
    }

}