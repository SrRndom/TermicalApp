package com.google.android.gms.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
/* loaded from: classes.dex */
public final class AccountPicker {

    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    /* loaded from: classes.dex */
    public static class AccountChooserOptions {
        private Account zza;
        private boolean zzb;
        private ArrayList<Account> zzc;
        private ArrayList<String> zzd;
        private boolean zze;
        private String zzf;
        private Bundle zzg;
        private boolean zzh;
        private int zzi;
        private String zzj;
        private boolean zzk;
        private zza zzl;
        private String zzm;
        private boolean zzn;

        /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
        /* loaded from: classes.dex */
        public static class zza {
        }

        /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
        /* loaded from: classes.dex */
        public static class Builder {
            private Account zza;
            private ArrayList<Account> zzb;
            private ArrayList<String> zzc;
            private String zze;
            private Bundle zzf;
            private boolean zzd = false;
            private boolean zzg = false;
            private int zzh = 0;
            private boolean zzi = false;

            public Builder setSelectedAccount(Account account) {
                this.zza = account;
                return this;
            }

            public Builder setAllowableAccounts(List<Account> list) {
                this.zzb = list == null ? null : new ArrayList<>(list);
                return this;
            }

            public Builder setAllowableAccountsTypes(List<String> list) {
                this.zzc = list == null ? null : new ArrayList<>(list);
                return this;
            }

            public Builder setAlwaysShowAccountPicker(boolean z) {
                this.zzd = z;
                return this;
            }

            public Builder setTitleOverrideText(String str) {
                this.zze = str;
                return this;
            }

            public Builder setOptionsForAddingAccount(Bundle bundle) {
                this.zzf = bundle;
                return this;
            }

            public AccountChooserOptions build() {
                Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
                Preconditions.checkArgument(true, "Consent is only valid for account chip styled account picker");
                AccountChooserOptions accountChooserOptions = new AccountChooserOptions();
                accountChooserOptions.zzd = this.zzc;
                accountChooserOptions.zzc = this.zzb;
                accountChooserOptions.zze = this.zzd;
                AccountChooserOptions.zza(accountChooserOptions, (zza) null);
                AccountChooserOptions.zza(accountChooserOptions, (String) null);
                accountChooserOptions.zzg = this.zzf;
                accountChooserOptions.zza = this.zza;
                AccountChooserOptions.zzb(accountChooserOptions, false);
                AccountChooserOptions.zzc(accountChooserOptions, false);
                AccountChooserOptions.zzb(accountChooserOptions, (String) null);
                AccountChooserOptions.zza(accountChooserOptions, 0);
                accountChooserOptions.zzf = this.zze;
                AccountChooserOptions.zzd(accountChooserOptions, false);
                AccountChooserOptions.zze(accountChooserOptions, false);
                return accountChooserOptions;
            }
        }

        static /* synthetic */ zza zza(AccountChooserOptions accountChooserOptions, zza zzaVar) {
            accountChooserOptions.zzl = null;
            return null;
        }

        static /* synthetic */ String zza(AccountChooserOptions accountChooserOptions, String str) {
            accountChooserOptions.zzj = null;
            return null;
        }

        static /* synthetic */ boolean zzb(AccountChooserOptions accountChooserOptions, boolean z) {
            accountChooserOptions.zzb = false;
            return false;
        }

        static /* synthetic */ boolean zzc(AccountChooserOptions accountChooserOptions, boolean z) {
            accountChooserOptions.zzh = false;
            return false;
        }

        static /* synthetic */ String zzb(AccountChooserOptions accountChooserOptions, String str) {
            accountChooserOptions.zzm = null;
            return null;
        }

        static /* synthetic */ int zza(AccountChooserOptions accountChooserOptions, int i) {
            accountChooserOptions.zzi = 0;
            return 0;
        }

        static /* synthetic */ boolean zzd(AccountChooserOptions accountChooserOptions, boolean z) {
            accountChooserOptions.zzk = false;
            return false;
        }

        static /* synthetic */ boolean zze(AccountChooserOptions accountChooserOptions, boolean z) {
            accountChooserOptions.zzn = false;
            return false;
        }
    }

    private AccountPicker() {
    }

    @Deprecated
    public static Intent newChooseAccountIntent(Account account, ArrayList<Account> arrayList, String[] strArr, boolean z, String str, String str2, String[] strArr2, Bundle bundle) {
        Intent intent = new Intent();
        Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
        intent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", arrayList);
        intent.putExtra("allowableAccountTypes", strArr);
        intent.putExtra("addAccountOptions", bundle);
        intent.putExtra("selectedAccount", account);
        intent.putExtra("alwaysPromptForAccount", z);
        intent.putExtra("descriptionTextOverride", str);
        intent.putExtra("authTokenType", str2);
        intent.putExtra("addAccountRequiredFeatures", strArr2);
        intent.putExtra("setGmsCoreAccount", false);
        intent.putExtra("overrideTheme", 0);
        intent.putExtra("overrideCustomTheme", 0);
        intent.putExtra("hostedDomainFilter", (String) null);
        return intent;
    }

    public static Intent newChooseAccountIntent(AccountChooserOptions accountChooserOptions) {
        Intent intent = new Intent();
        Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
        Preconditions.checkArgument(true, "Consent is only valid for account chip styled account picker");
        Preconditions.checkArgument(true, "Making the selected account non-clickable is only supported for the theme THEME_DAY_NIGHT_GOOGLE_MATERIAL2");
        intent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", accountChooserOptions.zzc);
        if (accountChooserOptions.zzd != null) {
            intent.putExtra("allowableAccountTypes", (String[]) accountChooserOptions.zzd.toArray(new String[0]));
        }
        intent.putExtra("addAccountOptions", accountChooserOptions.zzg);
        intent.putExtra("selectedAccount", accountChooserOptions.zza);
        intent.putExtra("selectedAccountIsNotClickable", false);
        intent.putExtra("alwaysPromptForAccount", accountChooserOptions.zze);
        intent.putExtra("descriptionTextOverride", accountChooserOptions.zzf);
        intent.putExtra("setGmsCoreAccount", false);
        intent.putExtra("realClientPackage", (String) null);
        intent.putExtra("overrideTheme", 0);
        intent.putExtra("overrideCustomTheme", 0);
        intent.putExtra("hostedDomainFilter", (String) null);
        Bundle bundle = new Bundle();
        if (!bundle.isEmpty()) {
            intent.putExtra("first_party_options_bundle", bundle);
        }
        return intent;
    }
}
