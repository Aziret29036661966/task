package com.example.task.ui.activity;

import android.os.Bundle;

import com.example.task.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
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
    }
    private void initController() {
        NavHostFragment navHostController = (NavHostFragment)
                this.getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);
        controller = navHostController.getNavController();
    }

    private void initBottomNavigation() {
        NavigationUI.setupWithNavController(binding.bottomNavigation, controller);
    }
}