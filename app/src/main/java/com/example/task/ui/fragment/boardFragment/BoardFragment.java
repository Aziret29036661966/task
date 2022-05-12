package com.example.task.ui.fragment.boardFragment;


import com.example.task.databinding.FragmentBoardFramentBinding;
import com.example.task.ui.fragment.base.BaseFragment;


public class BoardFragment extends BaseFragment<FragmentBoardFramentBinding> {
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
    }
    private void initAdapter() {
        binding.boardVp.setAdapter(adapter);
    }
}