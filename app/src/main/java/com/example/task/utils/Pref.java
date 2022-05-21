package com.example.task.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class Pref {
    private final SharedPreferences sharedPreferences;
    private static Pref pref;

    public Pref(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        pref = this;
    }


    public static Pref getPref() {
        return pref;
    }
}
