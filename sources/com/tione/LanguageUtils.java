package com.tione;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import java.util.Locale;

/* loaded from: classes.dex */
public class LanguageUtils {
    public static String getSystemLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= 24) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale.getLanguage();
    }

    public static void setLanguage(Context context, String str) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        str.hashCode();
        if (str.equals("en")) {
            configuration.setLocale(Locale.ENGLISH);
        } else if (str.equals("zh")) {
            configuration.setLocale(Locale.CHINESE);
        } else {
            configuration.setLocale(Locale.CHINESE);
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static void resetApp(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.addFlags(335544320);
        context.startActivity(launchIntentForPackage);
    }
}
