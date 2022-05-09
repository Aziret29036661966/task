package com.example.task.ui.fragment.nonName;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import android.widget.Toast;

import com.example.task.databinding.FragmentImageFragmnetBinding;
import com.example.task.ui.fragment.base.BaseFragment;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageFragment extends BaseFragment<FragmentImageFragmnetBinding> {
    private SharedPreferences pref;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected FragmentImageFragmnetBinding getBinding() {
        return FragmentImageFragmnetBinding.inflate(getLayoutInflater());

    }

    @Override
    public void setupUI() {
        getImages();
    }

    @Override
    protected void setupObservers() {
        takePictureFromGalleryOrAnyOtherFolder();
    }

    private void takePictureFromGalleryOrAnyOtherFolder() {
        binding.imgNight.setOnClickListener(view -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            activityResultLauncher.launch(photoPickerIntent);
        });

    }

    public void getImages() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                try {
                    assert result.getData() != null;
                    final Uri imageUri = result.getData().getData();
                    final InputStream imageStream = requireActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    binding.imgNight.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(requireActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }

        });

    }

}