package com.example.task.ui.fragment.base;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
abstract public class BaseFragment <VB extends ViewBinding> extends Fragment{

    protected VB binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = getBinding();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI();
        setupObservers();
    }

    protected abstract VB getBinding();

    public abstract void setupUI();

    protected abstract void setupObservers();
}