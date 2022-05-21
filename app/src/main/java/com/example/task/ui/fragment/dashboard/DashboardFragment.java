package com.example.task.ui.fragment.dashboard;

import android.os.Bundle;


import androidx.navigation.NavController;

import androidx.navigation.fragment.NavHostFragment;

import com.example.task.R;
import com.example.task.databinding.FragmentDashboardBinding;
import com.example.task.ui.fragment.base.BaseFragment;
import com.example.task.ui.fragment.home.HomeFragment;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DashboardFragment extends BaseFragment<FragmentDashboardBinding> {
    public static final String IDR = "2323";
    private NavController controller;
    public static final String HOME_KEY = "home_key";
    public static final String HOME_KEY_FOR_DATE = "home_key_date";
    public static final String RESULT_HOME_KEY = "home_key3";
    public static final String RESULT_HOME = "home_key2324";
    String date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",
            Locale.getDefault()).format(new Date());

    @Override
    protected FragmentDashboardBinding getBinding() {
        return FragmentDashboardBinding.inflate(getLayoutInflater());

    }

    @Override
    public void setupUI() {
    }

    @Override
    protected void setupObservers() {
        initController();
        initListener();
        sendDateHomeFragment();
    }

    private void initController() {
        NavHostFragment navHostController = (NavHostFragment)
                requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);
        if (navHostController != null) {

            controller = navHostController.getNavController();

        }

    }



    private void initListener() {
        binding.btnSave.setOnClickListener(view ->{
                sendDateToHomeFragment();
                closeFragment();
     });
    }
    private void sendDateToHomeFragment() {
        String text = binding.edText.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString(HOME_KEY, text);
            bundle.putString(HOME_KEY_FOR_DATE, date);
            getParentFragmentManager().setFragmentResult(RESULT_HOME_KEY, bundle);

    }
    private void sendDateHomeFragment() {
                getParentFragmentManager().setFragmentResultListener(
                HomeFragment.GO_TO_DASHBOARD,
                this,
                ((requestKey, result) -> {
                    int id = result.getInt(HomeFragment.ID);
                    binding.btnSave.setOnClickListener(view -> {
                        String text_from_HF = binding.edText.getText().toString();
                            Bundle bundle = new Bundle();
                            bundle.putString(HOME_KEY, text_from_HF);
                            bundle.putString(HOME_KEY_FOR_DATE, date);
                            bundle.putInt(IDR, id);
                            getParentFragmentManager().setFragmentResult(RESULT_HOME, bundle);
                            closeFragment();
                    });

                })
        );
    }

    private void closeFragment() {
     controller.navigateUp();
    }

}