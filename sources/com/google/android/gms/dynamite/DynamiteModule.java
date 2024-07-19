package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
/* loaded from: classes.dex */
public final class DynamiteModule {
    private static Boolean zza = null;
    private static zzk zzb = null;
    private static zzm zzc = null;
    private static String zzd = null;
    private static int zze = -1;
    private final Context zzi;
    private static final ThreadLocal<zza> zzf = new ThreadLocal<>();
    private static final VersionPolicy.zzb zzg = new com.google.android.gms.dynamite.zzb();
    public static final VersionPolicy PREFER_REMOTE = new com.google.android.gms.dynamite.zza();
    public static final VersionPolicy PREFER_LOCAL = new zzd();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzc();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzf();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzh();
    private static final VersionPolicy zzh = new zzg();

    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    /* loaded from: classes.dex */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    /* loaded from: classes.dex */
    public interface VersionPolicy {

        /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
        /* loaded from: classes.dex */
        public static class zza {
            public int zza = 0;
            public int zzb = 0;
            public int zzc = 0;
        }

        /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
        /* loaded from: classes.dex */
        public interface zzb {
            int zza(Context context, String str);

            int zza(Context context, String str, boolean z) throws LoadingException;
        }

        zza zza(Context context, String str, zzb zzbVar) throws LoadingException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    /* loaded from: classes.dex */
    public static class zza {
        public Cursor zza;

        private zza() {
        }

        /* synthetic */ zza(com.google.android.gms.dynamite.zzb zzbVar) {
            this();
        }
    }

    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        ThreadLocal<zza> threadLocal = zzf;
        zza zzaVar = threadLocal.get();
        com.google.android.gms.dynamite.zzb zzbVar = null;
        zza zzaVar2 = new zza(zzbVar);
        threadLocal.set(zzaVar2);
        try {
            VersionPolicy.zza zza2 = versionPolicy.zza(context, str, zzg);
            int i = zza2.zza;
            int i2 = zza2.zzb;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            Log.i("DynamiteModule", sb.toString());
            if (zza2.zzc == 0 || ((zza2.zzc == -1 && zza2.zza == 0) || (zza2.zzc == 1 && zza2.zzb == 0))) {
                int i3 = zza2.zza;
                int i4 = zza2.zzb;
                StringBuilder sb2 = new StringBuilder(91);
                sb2.append("No acceptable module found. Local version is ");
                sb2.append(i3);
                sb2.append(" and remote version is ");
                sb2.append(i4);
                sb2.append(".");
                throw new LoadingException(sb2.toString(), zzbVar);
            }
            if (zza2.zzc == -1) {
                DynamiteModule zza3 = zza(context, str);
                if (zzaVar2.zza != null) {
                    zzaVar2.zza.close();
                }
                threadLocal.set(zzaVar);
                return zza3;
            }
            if (zza2.zzc == 1) {
                try {
                    DynamiteModule zza4 = zza(context, str, zza2.zzb);
                    if (zzaVar2.zza != null) {
                        zzaVar2.zza.close();
                    }
                    threadLocal.set(zzaVar);
                    return zza4;
                } catch (LoadingException e) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
                    if (zza2.zza != 0 && versionPolicy.zza(context, str, new zzb(zza2.zza, 0)).zzc == -1) {
                        DynamiteModule zza5 = zza(context, str);
                        if (zzaVar2.zza != null) {
                            zzaVar2.zza.close();
                        }
                        zzf.set(zzaVar);
                        return zza5;
                    }
                    throw new LoadingException("Remote load failed. No local fallback found.", e, zzbVar);
                }
            }
            int i5 = zza2.zzc;
            StringBuilder sb3 = new StringBuilder(47);
            sb3.append("VersionPolicy returned invalid code:");
            sb3.append(i5);
            throw new LoadingException(sb3.toString(), zzbVar);
        } catch (Throwable th) {
            if (zzaVar2.zza != null) {
                zzaVar2.zza.close();
            }
            zzf.set(zzaVar);
            throw th;
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    /* loaded from: classes.dex */
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, com.google.android.gms.dynamite.zzb zzbVar) {
            this(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, com.google.android.gms.dynamite.zzb zzbVar) {
            this(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    /* loaded from: classes.dex */
    public static class zzb implements VersionPolicy.zzb {
        private final int zza;
        private final int zzb = 0;

        public zzb(int i, int i2) {
            this.zza = i;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb
        public final int zza(Context context, String str, boolean z) {
            return 0;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb
        public final int zza(Context context, String str) {
            return this.zza;
        }
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (!Objects.equal(declaredField.get(null), str)) {
                String valueOf = String.valueOf(declaredField.get(null));
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length());
                sb2.append("Module descriptor id '");
                sb2.append(valueOf);
                sb2.append("' didn't match expected id '");
                sb2.append(str);
                sb2.append("'");
                Log.e("DynamiteModule", sb2.toString());
                return 0;
            }
            return declaredField2.getInt(null);
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e) {
            String valueOf2 = String.valueOf(e.getMessage());
            Log.e("DynamiteModule", valueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int zza(Context context, String str, boolean z) {
        Field declaredField;
        ClassLoader zziVar;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = zza;
                if (bool == null) {
                    try {
                        declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                        String valueOf = String.valueOf(e);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
                        sb.append("Failed to load module via V2: ");
                        sb.append(valueOf);
                        Log.w("DynamiteModule", sb.toString());
                        bool = Boolean.FALSE;
                    }
                    synchronized (declaredField.getDeclaringClass()) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader != null) {
                            if (classLoader == ClassLoader.getSystemClassLoader()) {
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    zza(classLoader);
                                } catch (LoadingException unused) {
                                }
                                bool = Boolean.TRUE;
                            }
                        } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                            bool = Boolean.FALSE;
                        } else {
                            try {
                                int zzc2 = zzc(context, str, z);
                                String str2 = zzd;
                                if (str2 != null && !str2.isEmpty()) {
                                    if (Build.VERSION.SDK_INT >= 29) {
                                        zziVar = new DelegateLastClassLoader((String) Preconditions.checkNotNull(zzd), ClassLoader.getSystemClassLoader());
                                    } else {
                                        zziVar = new zzi((String) Preconditions.checkNotNull(zzd), ClassLoader.getSystemClassLoader());
                                    }
                                    zza(zziVar);
                                    declaredField.set(null, zziVar);
                                    zza = Boolean.TRUE;
                                    return zzc2;
                                }
                                return zzc2;
                            } catch (LoadingException unused2) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            }
                        }
                        zza = bool;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return zzc(context, str, z);
                    } catch (LoadingException e2) {
                        String valueOf2 = String.valueOf(e2.getMessage());
                        Log.w("DynamiteModule", valueOf2.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf2) : new String("Failed to retrieve remote module version: "));
                        return 0;
                    }
                }
                return zzb(context, str, z);
            }
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw th;
        }
    }

    private static int zzb(Context context, String str, boolean z) {
        zzk zza2 = zza(context);
        if (zza2 == null) {
            return 0;
        }
        try {
            try {
                int zzb2 = zza2.zzb();
                if (zzb2 < 3) {
                    if (zzb2 == 2) {
                        Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                        return zza2.zzb(ObjectWrapper.wrap(context), str, z);
                    }
                    Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                    return zza2.zza(ObjectWrapper.wrap(context), str, z);
                }
                Cursor cursor = (Cursor) ObjectWrapper.unwrap(zza2.zzc(ObjectWrapper.wrap(context), str, z));
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            int i = cursor.getInt(0);
                            r3 = (i <= 0 || !zza(cursor)) ? cursor : null;
                            if (r3 != null) {
                                r3.close();
                            }
                            return i;
                        }
                    } catch (RemoteException e) {
                        e = e;
                        r3 = cursor;
                        String valueOf = String.valueOf(e.getMessage());
                        Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
                        if (r3 != null) {
                            r3.close();
                        }
                        return 0;
                    } catch (Throwable th) {
                        th = th;
                        r3 = cursor;
                        if (r3 != null) {
                            r3.close();
                        }
                        throw th;
                    }
                }
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                if (cursor != null) {
                    cursor.close();
                }
                return 0;
            } catch (RemoteException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x006c, code lost:
    
        if (zza(r8) != false) goto L27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a6  */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.dynamite.zzb] */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int zzc(android.content.Context r8, java.lang.String r9, boolean r10) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            if (r10 == 0) goto La
            java.lang.String r8 = "api_force_staging"
            goto Lc
        La:
            java.lang.String r8 = "api"
        Lc:
            int r10 = r8.length()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            int r10 = r10 + 42
            java.lang.String r2 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            int r2 = r2.length()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            int r10 = r10 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r2.<init>(r10)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r10 = "content://com.google.android.gms.chimera/"
            r2.append(r10)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r2.append(r8)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r8 = "/"
            r2.append(r8)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r2.append(r9)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            java.lang.String r8 = r2.toString()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            android.net.Uri r2 = android.net.Uri.parse(r8)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            if (r8 == 0) goto L79
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8c
            if (r9 == 0) goto L79
            r9 = 0
            int r9 = r8.getInt(r9)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8c
            if (r9 <= 0) goto L72
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r10 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r10)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8c
            r1 = 2
            java.lang.String r1 = r8.getString(r1)     // Catch: java.lang.Throwable -> L6f
            com.google.android.gms.dynamite.DynamiteModule.zzd = r1     // Catch: java.lang.Throwable -> L6f
            java.lang.String r1 = "loaderVersion"
            int r1 = r8.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L6f
            if (r1 < 0) goto L67
            int r1 = r8.getInt(r1)     // Catch: java.lang.Throwable -> L6f
            com.google.android.gms.dynamite.DynamiteModule.zze = r1     // Catch: java.lang.Throwable -> L6f
        L67:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L6f
            boolean r10 = zza(r8)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8c
            if (r10 == 0) goto L72
            goto L73
        L6f:
            r9 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L6f
            throw r9     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8c
        L72:
            r0 = r8
        L73:
            if (r0 == 0) goto L78
            r0.close()
        L78:
            return r9
        L79:
            java.lang.String r9 = "DynamiteModule"
            java.lang.String r10 = "Failed to retrieve remote module version."
            android.util.Log.w(r9, r10)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8c
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r9 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8c
            java.lang.String r10 = "Failed to connect to dynamite module ContentResolver."
            r9.<init>(r10, r0)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8c
            throw r9     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8c
        L88:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto La4
        L8c:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto L95
        L91:
            r8 = move-exception
            goto La4
        L93:
            r8 = move-exception
            r9 = r0
        L95:
            boolean r10 = r8 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch: java.lang.Throwable -> La2
            if (r10 == 0) goto L9a
            throw r8     // Catch: java.lang.Throwable -> La2
        L9a:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: java.lang.Throwable -> La2
            java.lang.String r1 = "V2 version check failed"
            r10.<init>(r1, r8, r0)     // Catch: java.lang.Throwable -> La2
            throw r10     // Catch: java.lang.Throwable -> La2
        La2:
            r8 = move-exception
            r0 = r9
        La4:
            if (r0 == 0) goto La9
            r0.close()
        La9:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzc(android.content.Context, java.lang.String, boolean):int");
    }

    private static boolean zza(Cursor cursor) {
        zza zzaVar = zzf.get();
        if (zzaVar == null || zzaVar.zza != null) {
            return false;
        }
        zzaVar.zza = cursor;
        return true;
    }

    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    private static DynamiteModule zza(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule zza(Context context, String str, int i) throws LoadingException {
        Boolean bool;
        IObjectWrapper zza2;
        com.google.android.gms.dynamite.zzb zzbVar = null;
        try {
            synchronized (DynamiteModule.class) {
                bool = zza;
            }
            if (bool == null) {
                throw new LoadingException("Failed to determine which loading route to use.", zzbVar);
            }
            if (bool.booleanValue()) {
                return zzb(context, str, i);
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
            sb.append("Selected remote version of ");
            sb.append(str);
            sb.append(", version >= ");
            sb.append(i);
            Log.i("DynamiteModule", sb.toString());
            zzk zza3 = zza(context);
            if (zza3 == null) {
                throw new LoadingException("Failed to create IDynamiteLoader.", zzbVar);
            }
            int zzb2 = zza3.zzb();
            if (zzb2 >= 3) {
                zza zzaVar = zzf.get();
                if (zzaVar == null) {
                    throw new LoadingException("No cached result cursor holder", zzbVar);
                }
                zza2 = zza3.zza(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(zzaVar.zza));
            } else if (zzb2 == 2) {
                Log.w("DynamiteModule", "IDynamite loader version = 2");
                zza2 = zza3.zzb(ObjectWrapper.wrap(context), str, i);
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                zza2 = zza3.zza(ObjectWrapper.wrap(context), str, i);
            }
            if (ObjectWrapper.unwrap(zza2) == null) {
                throw new LoadingException("Failed to load remote module.", zzbVar);
            }
            return new DynamiteModule((Context) ObjectWrapper.unwrap(zza2));
        } catch (RemoteException e) {
            throw new LoadingException("Failed to load remote module.", e, zzbVar);
        } catch (LoadingException e2) {
            throw e2;
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw new LoadingException("Failed to load remote module.", th, zzbVar);
        }
    }

    private static zzk zza(Context context) {
        zzk zzjVar;
        synchronized (DynamiteModule.class) {
            zzk zzkVar = zzb;
            if (zzkVar != null) {
                return zzkVar;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzjVar = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    if (queryLocalInterface instanceof zzk) {
                        zzjVar = (zzk) queryLocalInterface;
                    } else {
                        zzjVar = new zzj(iBinder);
                    }
                }
                if (zzjVar != null) {
                    zzb = zzjVar;
                    return zzjVar;
                }
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    public final Context getModuleContext() {
        return this.zzi;
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException, RemoteException {
        zzm zzmVar;
        IObjectWrapper zza2;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            zzmVar = zzc;
        }
        com.google.android.gms.dynamite.zzb zzbVar = null;
        if (zzmVar == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.", zzbVar);
        }
        zza zzaVar = zzf.get();
        if (zzaVar == null || zzaVar.zza == null) {
            throw new LoadingException("No result cursor", zzbVar);
        }
        Context applicationContext = context.getApplicationContext();
        Cursor cursor = zzaVar.zza;
        ObjectWrapper.wrap(null);
        if (zza().booleanValue()) {
            Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
            zza2 = zzmVar.zzb(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
        } else {
            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
            zza2 = zzmVar.zza(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
        }
        Context context2 = (Context) ObjectWrapper.unwrap(zza2);
        if (context2 == null) {
            throw new LoadingException("Failed to get module context", zzbVar);
        }
        return new DynamiteModule(context2);
    }

    private static Boolean zza() {
        Boolean valueOf;
        synchronized (DynamiteModule.class) {
            valueOf = Boolean.valueOf(zze >= 2);
        }
        return valueOf;
    }

    private static void zza(ClassLoader classLoader) throws LoadingException {
        zzm zzlVar;
        com.google.android.gms.dynamite.zzb zzbVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzlVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzm) {
                    zzlVar = (zzm) queryLocalInterface;
                } else {
                    zzlVar = new zzl(iBinder);
                }
            }
            zzc = zzlVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzbVar);
        }
    }

    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzi.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new LoadingException(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e, null);
        }
    }

    private DynamiteModule(Context context) {
        this.zzi = (Context) Preconditions.checkNotNull(context);
    }
}
