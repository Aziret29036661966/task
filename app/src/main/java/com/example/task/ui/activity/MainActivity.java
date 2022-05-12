package com.example.task.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.task.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.task.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initController();
        initBottomNavigation();
        openBoard();
        controllerView();
    }

    @SuppressLint("NonConstantResourceId")
    private void controllerView() {
        controller.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            switch (navDestination.getId()) {
                case R.id.navigation_dashboard:
                case  R.id.boardFragment:
                    binding.bottomNavigation.setVisibility(View.GONE);
                    break;
                default:
                    binding.bottomNavigation.setVisibility(View.VISIBLE);
            }
        });
    }

    private void openBoard() {
        controller.navigate(R.id.boardFragment);
    }


    private void initController() {
        NavHostFragment navHostController = (NavHostFragment)
                this.getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);
        if (navHostController != null) {
            controller = navHostController.getNavController();
        }
    }

    private void initBottomNavigation() {
        NavigationUI.setupWithNavController(binding.bottomNavigation, controller);
    }
}