package com.example.task.ui.fragment.image;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.bumptech.glide.Glide;
import com.example.task.databinding.FragmentImageFragmnetBinding;
import com.example.task.ui.fragment.base.BaseFragment;

import java.util.Objects;


public class ImageFragment extends BaseFragment<FragmentImageFragmnetBinding> {
    private SharedPreferences pref;
    private final String SAVE_KEY = "save_key";
    private final String SAVE_BOTTOM = "save_bottom";
    private final String SAVE_URI = "save_uri";
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected FragmentImageFragmnetBinding getBinding() {
        return FragmentImageFragmnetBinding.inflate(getLayoutInflater());

    }

    @Override
    public void setupUI() {
        initPref();
        initBtn();
    }


    @Override
    protected void setupObservers() {
        getImages();
    }


    private void initPref() {
        pref = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Objects.requireNonNull(binding.edTextTop.getEditText
                ()).setText(pref.getString(SAVE_KEY, ""));
        Objects.requireNonNull(binding.edTextBottom.getEditText
                ()).setText(pref.getString(SAVE_BOTTOM, ""));
        if(!pref.getString(SAVE_URI,"").trim().isEmpty())
        Glide.with(binding.imgNight).load(pref.getString(SAVE_URI, "")).into(binding.imgNight);
    }

    private void initBtn() {
        binding.imgNight.setOnClickListener(view -> getImage());
        binding.btnSave.setOnClickListener(v -> initData());
    }

    private void initData() {
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = pref.edit();
        editor.putString(SAVE_KEY, Objects.requireNonNull(binding.edTextBottom.getEditText
                ()).getText().toString());
        editor.putString(SAVE_BOTTOM, Objects.requireNonNull(binding.edTextTop.getEditText
                ()).getText().toString());
        editor.apply();
    }

    private void getImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        activityResultLauncher.launch(photoPickerIntent);
    }

    public void getImages() {
        activityResultLauncher = registerForActivityResult
                (new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        assert result.getData() != null;
                        Uri selectedImage = result.getData().getData();
                        Log.e("aboba", "" + selectedImage);
                        Glide.with(binding.imgNight).load(selectedImage.toString()).into(binding.imgNight);
                        String pathData = selectedImage.toString();
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString(SAVE_URI, pathData).apply();
                    }
                });
    }
}