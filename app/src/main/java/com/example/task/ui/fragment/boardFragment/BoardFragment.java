package com.example.task.ui.fragment.boardFragment;


import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.task.R;
import com.example.task.databinding.FragmentBoardFramentBinding;
import com.example.task.ui.fragment.base.BaseFragment;


import com.google.android.material.tabs.TabLayoutMediator;



public class BoardFragment extends BaseFragment<FragmentBoardFramentBinding> {

    private NavController controller;
    AdapterForViewPager adapter = new AdapterForViewPager();

    @Override
    protected FragmentBoardFramentBinding getBinding() {

        return FragmentBoardFramentBinding.inflate(getLayoutInflater());

    }

    @Override
    public void setupUI() {

    }

    @Override
    protected void setupObservers() {

        initAdapter();
        funTab();
        skip();
        initController();

    }
    private void initController() {

        NavHostFragment navHostController = (NavHostFragment)
                requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);
        if (navHostController != null) {
            controller = navHostController.getNavController();

        }

    }

    private void funTab() {

        new TabLayoutMediator(binding.tabDots, binding.boardVp, ((tab, position) ->
                tab.setIcon(R.drawable.selector))).attach();

    }

    private void initAdapter() {
        binding.boardVp.setAdapter(adapter);
    }

    private void skip() {
        binding.btnNext.setEnabled(false);
        binding.btnNextBefore.setOnClickListener(view -> {
            binding.btnNextBefore.setVisibility(View.INVISIBLE);
            binding.boardVp.setCurrentItem(getItem(+3), true);
            binding.btnNextBefore.setEnabled(false);
            binding.btnNext.setVisibility(View.VISIBLE);
            binding.btnNext.setEnabled(true);
            binding.btnGoToHome.setVisibility(View.VISIBLE);
            binding.btnGoToHome.setEnabled(true);
        });

         binding.btnGoToHome.setOnClickListener(view -> {
             controller.navigate(R.id.navigation_home);

         });

    }
    private int getItem(int i) {
        return binding.boardVp.getCurrentItem() + i;
    }


}