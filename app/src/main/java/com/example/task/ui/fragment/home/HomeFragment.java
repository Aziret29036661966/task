package com.example.task.ui.fragment.home;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.task.App;
import com.example.task.R;
import com.example.task.databinding.FragmentHomeBinding;
import com.example.task.ui.fragment.base.BaseFragment;
import com.example.task.ui.fragment.dashboard.DashboardFragment;
import com.example.task.ui.fragment.home.adapter.AdapterForTitle;
import com.example.task.ui.model.ModelForTask;



public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements AdapterForTitle.ItsForStoryInterface {

    private NavController controller;
    private AdapterForTitle adapter;
    public static final String ID = "home_key23452";
    public static final String GO_TO_DASHBOARD = "home_ke452";

    @Override
    protected FragmentHomeBinding getBinding() {

        return FragmentHomeBinding.inflate(getLayoutInflater());

    }

    @Override
    public void setupUI() {

        initAdapter();
        transitionButton();
        setStartTrans();
    }

    @Override
    protected void setupObservers() {

        initController();
        initListener();
        update();
        initBTN();

    }

    private void transitionButton() {

        binding.btnTransition.setOnClickListener(v -> controller.navigate(R.id.navigation_dashboard));

    }
    @Override
    public void onResume() {
        super.onResume();
        adapter.addItem(App.getDatabase().dao().getAllList());
    }

    private void initAdapter() {

        adapter = new AdapterForTitle(this, this);
        binding.rvTask.setAdapter(adapter);

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

        getParentFragmentManager().setFragmentResultListener(
                DashboardFragment.RESULT_HOME_KEY,
                getViewLifecycleOwner(),
                (requestKey, result) -> {
                    String text = result.getString(DashboardFragment.HOME_KEY);
                    String text2 = result.getString(DashboardFragment.HOME_KEY_FOR_DATE);
                    App.getDatabase().dao().createInsert(new ModelForTask(text, text2));
                });

    }

    private void update(){
            getParentFragmentManager().setFragmentResultListener(
                    DashboardFragment.RESULT_HOME,
                    this,
                    ((requestKey, result) -> {
                        int id = result.getInt(DashboardFragment.IDR);
                        String text = result.getString(DashboardFragment.HOME_KEY).trim();
                        String date = result.getString(DashboardFragment.HOME_KEY_FOR_DATE);
                        App.getDatabase().dao().update(new ModelForTask(id, text, date));
                    })
            );


    }

    @Override
    public void longClick(int position) {

        App.getDatabase().dao().delete(adapter.getItem(position));

    }

    @Override
    public void click(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID, adapter.getItem(position).getId());
        getParentFragmentManager().setFragmentResult(GO_TO_DASHBOARD, bundle);
        controller.navigate(R.id.navigation_dashboard);

        }

    private void initBTN(){
        binding.imgMoreVert.setOnClickListener(view -> {

              openCont();

        });
    }

    private void openCont() {
        binding.containerForMyCustomView.animate().scaleX(1).setDuration(200).start();
        binding.containerForMyCustomView.animate().scaleY(1).setDuration(200).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                binding.imgAbc.setVisibility(View.VISIBLE);
                binding.imgAbc.setOnClickListener(v -> {
                    adapter.sortByAlphabet();
                });
                binding.imgDate.setVisibility(View.VISIBLE);
                binding.imgDate.setOnClickListener(v -> {
                    adapter.setByDate();
                });
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }

        }).start();

    }

    private void setStartTrans() {
        binding.containerForMyCustomView.setScaleX(0);
        binding.containerForMyCustomView.setScaleY(0);
    }
}