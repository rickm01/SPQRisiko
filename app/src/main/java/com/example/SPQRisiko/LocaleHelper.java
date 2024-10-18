package com.example.SPQRisiko;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import java.util.Locale;

public class LocaleHelper {

    public static void setLocale(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());

        Log.d("LocaleHelper", "Locale set to: " + lang);
    }

    public static String getLocale(Context context) {
        return Locale.getDefault().getLanguage();
    }
}
