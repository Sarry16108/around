package com.example.lib_generic.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.lib_generic.base.SfyBaseApplication;

public class KeyboardUtils {
    private static KeyboardUtils mInstance = null;

    private KeyboardUtils() {

    }

    public static KeyboardUtils getInstance() {
        if (null == mInstance) {
            synchronized (KeyboardUtils.class) {
                if (null == mInstance) {
                    mInstance = new KeyboardUtils();
                }

                return mInstance;
            }
        }

        return mInstance;
    }

    public void hideKeyboard(EditText editText) {
        InputMethodManager imm =(InputMethodManager)SfyBaseApplication.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
