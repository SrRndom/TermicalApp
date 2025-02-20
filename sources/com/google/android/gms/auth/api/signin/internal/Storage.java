package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public class Storage {
    private static final Lock zaa = new ReentrantLock();
    private static Storage zab;
    private final Lock zac = new ReentrantLock();
    private final SharedPreferences zad;

    public static Storage getInstance(Context context) {
        Preconditions.checkNotNull(context);
        Lock lock = zaa;
        lock.lock();
        try {
            if (zab == null) {
                zab = new Storage(context.getApplicationContext());
            }
            Storage storage = zab;
            lock.unlock();
            return storage;
        } catch (Throwable th) {
            zaa.unlock();
            throw th;
        }
    }

    private Storage(Context context) {
        this.zad = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public void saveDefaultGoogleSignInAccount(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        zaa("defaultGoogleSignInAccount", googleSignInAccount.zaa());
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        String zaa2 = googleSignInAccount.zaa();
        zaa(zab("googleSignInAccount", zaa2), googleSignInAccount.zab());
        zaa(zab("googleSignInOptions", zaa2), googleSignInOptions.zaa());
    }

    private final void zaa(String str, String str2) {
        this.zac.lock();
        try {
            this.zad.edit().putString(str, str2).apply();
        } finally {
            this.zac.unlock();
        }
    }

    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        return zaa(zac("defaultGoogleSignInAccount"));
    }

    private final GoogleSignInAccount zaa(String str) {
        String zac;
        if (!TextUtils.isEmpty(str) && (zac = zac(zab("googleSignInAccount", str))) != null) {
            try {
                return GoogleSignInAccount.zaa(zac);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        return zab(zac("defaultGoogleSignInAccount"));
    }

    private final GoogleSignInOptions zab(String str) {
        String zac;
        if (!TextUtils.isEmpty(str) && (zac = zac(zab("googleSignInOptions", str))) != null) {
            try {
                return GoogleSignInOptions.zaa(zac);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public String getSavedRefreshToken() {
        return zac("refreshToken");
    }

    private final String zac(String str) {
        this.zac.lock();
        try {
            return this.zad.getString(str, null);
        } finally {
            this.zac.unlock();
        }
    }

    public final void zaa() {
        String zac = zac("defaultGoogleSignInAccount");
        zad("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(zac)) {
            return;
        }
        zad(zab("googleSignInAccount", zac));
        zad(zab("googleSignInOptions", zac));
    }

    private final void zad(String str) {
        this.zac.lock();
        try {
            this.zad.edit().remove(str).apply();
        } finally {
            this.zac.unlock();
        }
    }

    public void clear() {
        this.zac.lock();
        try {
            this.zad.edit().clear().apply();
        } finally {
            this.zac.unlock();
        }
    }

    private static String zab(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        return sb.toString();
    }
}
