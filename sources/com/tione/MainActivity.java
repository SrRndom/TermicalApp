package com.tione;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import com.google.android.material.timepicker.TimeModel;
import com.tione.ThermalImager;
import com.tione.UsbService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCE_NAME = "ThermalImager";
    private static final String TAG = "MainActivity->";
    private MyHandler mHandler;
    private String mStr;
    private TextView textView_centTemp;
    private TextView textView_emissVal;
    private TextView textView_maxTemp;
    private TextView textView_minTemp;
    private TextView textView_tempHighVal;
    private TextView textView_tempLowVal;
    private UsbService usbService;
    SharedPreferences sharedPreferences = null;
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() { // from class: com.tione.MainActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            action.hashCode();
            char c = 65535;
            switch (action.hashCode()) {
                case -825699441:
                    if (action.equals(UsbService.ACTION_USB_DISCONNECTED)) {
                        c = 0;
                        break;
                    }
                    break;
                case 225209075:
                    if (action.equals(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED)) {
                        c = 1;
                        break;
                    }
                    break;
                case 1136385919:
                    if (action.equals(UsbService.ACTION_NO_USB)) {
                        c = 2;
                        break;
                    }
                    break;
                case 2080044846:
                    if (action.equals(UsbService.ACTION_USB_NOT_SUPPORTED)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    Toast.makeText(context, "USB设备已拔出", 0).show();
                    break;
                case 1:
                    Toast.makeText(context, "USB权限被拒绝", 0).show();
                    break;
                case 2:
                    Toast.makeText(context, "USB设备未连接", 0).show();
                    break;
                case 3:
                    Toast.makeText(context, "不支持的USB设备", 0).show();
                    break;
            }
            MainActivity.this.mUsbState = intent.getAction().equals(UsbService.ACTION_USB_PERMISSION_GRANTED);
        }
    };
    private boolean mUsbState = false;
    private boolean mManuRangeMod = false;
    private int mTempLow = 20;
    private int mTempLow_RangeR = 20;
    private int mTempHigh = 40;
    private int mEmssivity = 95;
    private boolean mIsCheckBox_LR = false;
    private boolean mIsCheckBox_TB = false;
    private boolean mIsCheckBox_LandRev = false;
    private boolean mCheckBox_UnitF = false;
    private int mSpinnerColorType = 0;
    private int mSpinnerTempRange = 0;
    ThermalImager.enum_PSEMETHOD[] PseMethod = {ThermalImager.enum_PSEMETHOD.GCM_Metal2, ThermalImager.enum_PSEMETHOD.GCM_Pseudo2, ThermalImager.enum_PSEMETHOD.GCM_Gray, ThermalImager.enum_PSEMETHOD.GCM_Pseudo1, ThermalImager.enum_PSEMETHOD.GCM_Metal1};
    private ThermalImager.enum_PSEMETHOD mPseMethod = ThermalImager.enum_PSEMETHOD.GCM_Pseudo1;
    private final ServiceConnection usbConnection = new ServiceConnection() { // from class: com.tione.MainActivity.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainActivity.this.usbService = ((UsbService.UsbBinder) iBinder).getService();
            MainActivity.this.usbService.setHandler(MainActivity.this.mHandler);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MainActivity.this.usbService = null;
        }
    };

    private void putString(String str, String str2) {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    private String getString(String str, String str2) {
        return this.sharedPreferences.getString(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, 0);
        this.sharedPreferences = sharedPreferences;
        String string = sharedPreferences.getString("LanguageKey", "zh");
        Log.e("logcat", string);
        LanguageUtils.setLanguage(this, string);
        setContentView(R.layout.activity_main);
        this.mHandler = new MyHandler(this);
        this.textView_maxTemp = (TextView) findViewById(R.id.textView_maxTemp);
        this.textView_minTemp = (TextView) findViewById(R.id.textView_minTemp);
        this.textView_centTemp = (TextView) findViewById(R.id.textView_centTemp);
        this.textView_tempHighVal = (TextView) findViewById(R.id.textView_tempHighVal);
        this.textView_tempLowVal = (TextView) findViewById(R.id.textView_tempLowVal);
        this.textView_emissVal = (TextView) findViewById(R.id.textView_emissVal);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar_tempHigh);
        final SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar_tempLow);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_colorType);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner_tempRange);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox_LR_rev);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox_TB_rev);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.land_rev);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox_TempUnit);
        this.mIsCheckBox_LR = this.sharedPreferences.getBoolean("checkBox_LR_revert", false);
        this.mIsCheckBox_TB = this.sharedPreferences.getBoolean("checkBox_TB_revert", false);
        this.mIsCheckBox_LandRev = this.sharedPreferences.getBoolean("checkBox_land_revert", false);
        this.mCheckBox_UnitF = this.sharedPreferences.getBoolean("checkBox_UnitF", false);
        this.mSpinnerColorType = this.sharedPreferences.getInt("spinner_colorType", 0);
        this.mSpinnerTempRange = this.sharedPreferences.getInt("spinner_tempRange", 0);
        checkBox.setChecked(this.mIsCheckBox_LR);
        checkBox2.setChecked(this.mIsCheckBox_TB);
        checkBox3.setChecked(this.mIsCheckBox_LandRev);
        checkBox4.setChecked(this.mCheckBox_UnitF);
        spinner.setSelection(this.mSpinnerColorType);
        spinner2.setSelection(this.mSpinnerTempRange);
        if (this.sharedPreferences.getInt("seekBar_tempHigh_min", 0) != 0) {
            seekBar.setMin(this.sharedPreferences.getInt("seekBar_tempHigh_min", 0));
            seekBar.setMax(this.sharedPreferences.getInt("seekBar_tempHigh_max", 0));
            seekBar.setProgress(this.sharedPreferences.getInt("seekBar_tempHigh_prg", 0));
            seekBar2.setMin(this.sharedPreferences.getInt("seekBar_tempLow_min", 0));
            seekBar2.setMax(this.sharedPreferences.getInt("seekBar_tempLow_max", 0));
            seekBar2.setProgress(this.sharedPreferences.getInt("seekBar_tempLow_prg", 0));
        }
        ((Button) findViewById(R.id.saveImage)).setOnClickListener(new View.OnClickListener() { // from class: com.tione.MainActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.m9lambda$onCreate$0$comtioneMainActivity(view);
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.tione.MainActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MainActivity.this.m10lambda$onCreate$1$comtioneMainActivity(compoundButton, z);
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.tione.MainActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MainActivity.this.m11lambda$onCreate$2$comtioneMainActivity(compoundButton, z);
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.tione.MainActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MainActivity.this.setRequestedOrientation(9);
                } else {
                    MainActivity.this.setRequestedOrientation(1);
                }
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.tione.MainActivity$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MainActivity.this.m12lambda$onCreate$3$comtioneMainActivity(compoundButton, z);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.tione.MainActivity.4
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.mPseMethod = mainActivity.PseMethod[i];
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.tione.MainActivity.5
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                MainActivity.this.mManuRangeMod = i == 1;
                if (i == 1) {
                    seekBar.setEnabled(true);
                    seekBar2.setEnabled(true);
                } else {
                    seekBar.setEnabled(false);
                    seekBar2.setEnabled(false);
                }
            }
        });
        seekBar.setMin(20);
        seekBar.setMax(999);
        seekBar.setProgress(this.mTempHigh);
        String format = String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(this.mTempHigh));
        this.mStr = format;
        this.textView_tempHighVal.setText(format);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.tione.MainActivity.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar3) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar3) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar3, int i, boolean z) {
                MainActivity.this.mTempHigh = i;
                MainActivity mainActivity = MainActivity.this;
                mainActivity.mStr = String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(mainActivity.mTempHigh));
                MainActivity.this.textView_tempHighVal.setText(MainActivity.this.mStr);
                MainActivity.this.mTempLow_RangeR = r2.mTempHigh - 10;
                seekBar2.setMax(MainActivity.this.mTempLow_RangeR);
                if (MainActivity.this.mTempHigh <= MainActivity.this.mTempLow + 10) {
                    MainActivity.this.mTempLow = r2.mTempHigh - 10;
                    seekBar2.setProgress(MainActivity.this.mTempLow);
                }
            }
        });
        seekBar2.setMin(-20);
        seekBar2.setMax(this.mTempLow_RangeR);
        seekBar2.setProgress(this.mTempLow);
        String format2 = String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(this.mTempLow));
        this.mStr = format2;
        this.textView_tempLowVal.setText(format2);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.tione.MainActivity.7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar3) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar3) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar3, int i, boolean z) {
                MainActivity.this.mStr = String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(i));
                MainActivity.this.textView_tempLowVal.setText(MainActivity.this.mStr);
            }
        });
        final SeekBar seekBar3 = (SeekBar) findViewById(R.id.emissivity);
        seekBar3.setMin(10);
        seekBar3.setMax(100);
        seekBar3.setProgress(95);
        this.mStr = "0.95";
        this.textView_emissVal.setText("0.95");
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.tione.MainActivity.8
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar4) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar4, int i, boolean z) {
                if (i < 10) {
                    MainActivity.this.mStr = "0.0" + i;
                } else if (i >= 100) {
                    MainActivity.this.mStr = "1.00";
                } else {
                    MainActivity.this.mStr = "0." + i;
                }
                MainActivity.this.textView_emissVal.setText(MainActivity.this.mStr);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar4) {
                MainActivity.this.mEmssivity = seekBar3.getProgress();
                byte[] bArr = {85, 1, 0, 0};
                bArr[2] = (byte) MainActivity.this.mEmssivity;
                int i = 0;
                for (int i2 = 0; i2 < 3; i2++) {
                    i += bArr[i2];
                }
                bArr[3] = (byte) i;
                if (MainActivity.this.usbService != null) {
                    MainActivity.this.usbService.write(bArr);
                }
            }
        });
    }

    /* renamed from: lambda$onCreate$0$com-tione-MainActivity, reason: not valid java name */
    public /* synthetic */ void m9lambda$onCreate$0$comtioneMainActivity(View view) {
        String[] strArr = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        if (!this.mUsbState) {
            Toast.makeText(this, "请先连接设备", 0).show();
            return;
        }
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, strArr, 1);
        }
        try {
            new SavePhoto(this).SaveBitmapFromView(findViewById(R.id.TIView));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: lambda$onCreate$1$com-tione-MainActivity, reason: not valid java name */
    public /* synthetic */ void m10lambda$onCreate$1$comtioneMainActivity(CompoundButton compoundButton, boolean z) {
        this.mIsCheckBox_LR = z;
    }

    /* renamed from: lambda$onCreate$2$com-tione-MainActivity, reason: not valid java name */
    public /* synthetic */ void m11lambda$onCreate$2$comtioneMainActivity(CompoundButton compoundButton, boolean z) {
        this.mIsCheckBox_TB = z;
    }

    /* renamed from: lambda$onCreate$3$com-tione-MainActivity, reason: not valid java name */
    public /* synthetic */ void m12lambda$onCreate$3$comtioneMainActivity(CompoundButton compoundButton, boolean z) {
        this.mCheckBox_UnitF = z;
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.chinese) {
            putString("LanguageKey", "zh");
            LanguageUtils.setLanguage(this, "zh");
            LanguageUtils.resetApp(this);
        } else if (itemId == R.id.english) {
            putString("LanguageKey", "en");
            LanguageUtils.setLanguage(this, "en");
            LanguageUtils.resetApp(this);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* loaded from: classes.dex */
    public class SavePhoto {
        Context context;

        public SavePhoto(Context context) {
            this.context = context;
        }

        public void SaveBitmapFromView(View view) throws ParseException {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            view.draw(new Canvas(createBitmap));
            Matrix matrix = new Matrix();
            matrix.postScale(1.0f, 1.0f);
            saveBitmap(Bitmap.createBitmap(createBitmap, 0, 0, createBitmap.getWidth(), createBitmap.getHeight(), matrix, true), new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpeg");
        }

        public void saveBitmap(Bitmap bitmap, String str) {
            String str2 = MainActivity.this.getExternalFilesDir(Environment.DIRECTORY_DCIM).getPath() + "/" + str;
            File file = new File(str2);
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream)) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    MediaStore.Images.Media.insertImage(this.context.getContentResolver(), file.getAbsolutePath(), str, (String) null);
                    Toast.makeText(this.context, "照片保存成功", 0).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + str2)));
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        setFilters();
        startService(this.usbConnection, null);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        unregisterReceiver(this.mUsbReceiver);
        unbindService(this.usbConnection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox_LR_rev);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox_TB_rev);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox_TempUnit);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.land_rev);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_colorType);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner_tempRange);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar_tempHigh);
        SharedPreferences.Editor edit = getSharedPreferences(PREFERENCE_NAME, 0).edit();
        edit.putBoolean("checkBox_LR_revert", checkBox.isChecked());
        edit.putBoolean("checkBox_TB_revert", checkBox2.isChecked());
        edit.putBoolean("checkBox_land_revert", checkBox4.isChecked());
        edit.putBoolean("checkBox_UnitF", checkBox3.isChecked());
        edit.putInt("spinner_colorType", spinner.getSelectedItemPosition());
        edit.putInt("spinner_tempRange", spinner2.getSelectedItemPosition());
        edit.putInt("seekBar_tempHigh_min", seekBar.getMin());
        edit.putInt("seekBar_tempHigh_max", seekBar.getMax());
        edit.putInt("seekBar_tempHigh_prg", seekBar.getProgress());
        edit.putInt("seekBar_tempLow_min", seekBar.getMin());
        edit.putInt("seekBar_tempLow_max", seekBar.getMax());
        edit.putInt("seekBar_tempLow_prg", seekBar.getProgress());
        String language = getResources().getConfiguration().locale.getLanguage();
        edit.putString("LanguageKey", language);
        Log.e("logcat", language);
        edit.commit();
        super.onStop();
    }

    private void startService(ServiceConnection serviceConnection, Bundle bundle) {
        if (!UsbService.SERVICE_CONNECTED) {
            Intent intent = new Intent(this, (Class<?>) UsbService.class);
            if (bundle != null && !bundle.isEmpty()) {
                for (String str : bundle.keySet()) {
                    intent.putExtra(str, bundle.getString(str));
                }
            }
            startService(intent);
        }
        bindService(new Intent(this, (Class<?>) UsbService.class), serviceConnection, 1);
    }

    private void setFilters() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED);
        intentFilter.addAction(UsbService.ACTION_NO_USB);
        intentFilter.addAction(UsbService.ACTION_USB_DISCONNECTED);
        intentFilter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED);
        intentFilter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED);
        registerReceiver(this.mUsbReceiver, intentFilter);
    }

    /* loaded from: classes.dex */
    private class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;
        String str;
        ThermalImager ti = new ThermalImager();
        final int len_data_byte = 2048;

        public MyHandler(MainActivity mainActivity) {
            this.mActivity = new WeakReference<>(mainActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    Toast.makeText(this.mActivity.get(), "CTS_CHANGE", 1).show();
                    return;
                } else {
                    if (i == 2) {
                        Toast.makeText(this.mActivity.get(), "DSR_CHANGE", 1).show();
                        return;
                    }
                    throw new IllegalStateException("Unexpected value: " + message.what);
                }
            }
            byte[] bArr = (byte[]) message.obj;
            if (bArr.length == 2048) {
                this.ti.mPseMethod = MainActivity.this.mPseMethod;
                this.ti.mIsChecked_LR = MainActivity.this.mIsCheckBox_LR;
                this.ti.mIsChecked_TB = MainActivity.this.mIsCheckBox_TB;
                this.ti.mManuRangeMod = MainActivity.this.mManuRangeMod;
                this.ti.mManuRangeHigh = MainActivity.this.mTempHigh;
                this.ti.mManuRangeLow = MainActivity.this.mTempLow;
                this.ti.mIsChecked_UnitF = MainActivity.this.mCheckBox_UnitF;
                this.ti.Temp2Image(bArr);
                TextView textView = this.mActivity.get().textView_maxTemp;
                StringBuilder sb = new StringBuilder();
                sb.append((Object) MainActivity.this.getResources().getText(R.string.maxTemp));
                sb.append(StringUtils.SPACE);
                ThermalImager thermalImager = this.ti;
                sb.append(thermalImager.GetTempStr(thermalImager.fMaxTemp));
                textView.setText(sb.toString());
                TextView textView2 = this.mActivity.get().textView_minTemp;
                StringBuilder sb2 = new StringBuilder();
                sb2.append((Object) MainActivity.this.getResources().getText(R.string.minTemp));
                sb2.append(StringUtils.SPACE);
                ThermalImager thermalImager2 = this.ti;
                sb2.append(thermalImager2.GetTempStr(thermalImager2.fMinTemp));
                textView2.setText(sb2.toString());
                TextView textView3 = this.mActivity.get().textView_centTemp;
                StringBuilder sb3 = new StringBuilder();
                sb3.append((Object) MainActivity.this.getResources().getText(R.string.centTemp));
                sb3.append(StringUtils.SPACE);
                ThermalImager thermalImager3 = this.ti;
                sb3.append(thermalImager3.GetTempStr(thermalImager3.fCentTem));
                textView3.setText(sb3.toString());
                int[] iArr = this.ti.mColorAryInt;
                Objects.requireNonNull(this.ti);
                Objects.requireNonNull(this.ti);
                Bitmap copy = Bitmap.createBitmap(iArr, 768, 768, Bitmap.Config.ARGB_8888).copy(Bitmap.Config.ARGB_8888, true);
                this.ti.DrawInfoToImage(new Canvas(copy));
                ((ImageView) MainActivity.this.findViewById(R.id.TIView)).setImageBitmap(copy);
                String.format("len = %d : ", Integer.valueOf(bArr.length));
                return;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append(String.format("len = %d : ", Integer.valueOf(bArr.length)));
            if (bArr.length >= 4) {
                sb4.append(String.format("%02X,%02X,%02X,%02X : ", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3])));
                sb4.append(String.format("%d, %d\n", Integer.valueOf(((bArr[0] & 255) + ((bArr[1] & 255) * 256)) & SupportMenu.USER_MASK), Integer.valueOf(((bArr[2] & 255) + ((bArr[3] & 255) * 256)) & SupportMenu.USER_MASK)));
                Log.e(MainActivity.TAG, sb4.toString());
            }
        }
    }
}
