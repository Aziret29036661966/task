package com.example.task.ui.fragment.home;

import com.example.task.App;
import com.example.task.databinding.FragmentHomeBinding;
import com.example.task.ui.fragment.base.BaseFragment;
import com.example.task.ui.fragment.home.adapter.AdapterForTitle;
import com.example.task.ui.model.ModelForTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private final AdapterForTitle adapter = new AdapterForTitle();

    @Override
    protected FragmentHomeBinding getBinding() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void setupUI() {
        initAdapter();
    }

    @Override
    protected void setupObservers() {
        initListener();
    }

    private void initAdapter() {

        binding.rvTask.setAdapter(adapter);
    }

    private void initListener() {
        adapter.addItem(App.getDatabase().dao().getAllList());
    }
}