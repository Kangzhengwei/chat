package com.sopho.chat.util;

import android.content.Context;
import android.content.Intent;

import com.sopho.chat.module.ui.MainActivity;

/**
 * author: kang4
 * Date: 2019/4/25
 * Description:
 */
public class IntentUtils {

    public static void goMainActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
