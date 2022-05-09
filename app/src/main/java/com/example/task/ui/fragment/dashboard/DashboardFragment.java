package com.example.task.ui.fragment.dashboard;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.task.R;
import com.example.task.databinding.FragmentDashboardBinding;
import com.example.task.ui.fragment.base.BaseFragment;

public class DashboardFragment extends BaseFragment<FragmentDashboardBinding> {
    static final public String HASH_KEY = "home_key";
    static final public String RESULT_HOME_KEY = "result_home_key";
    @Override
    protected FragmentDashboardBinding getBinding() {
        return FragmentDashboardBinding.inflate(getLayoutInflater());

    }

    @Override
    public void setupUI() {
    }


    @Override
    protected void setupObservers() {
        initListener();
    }


    private void initListener() {
        binding.btnSave.setOnClickListener(v ->{
         sendDataToHomeFragment();
         closeFragment();
        });
    }

    private void closeFragment() {
        NavController navController = Navigation.findNavController
                (requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }

    private void sendDataToHomeFragment() {
        String text = binding.edText.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(HASH_KEY, text);
        getParentFragmentManager().setFragmentResult(RESULT_HOME_KEY, bundle);
    }

}