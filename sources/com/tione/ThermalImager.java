package com.tione;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.InputDeviceCompat;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class ThermalImager {
    public static final int BLACK = -16777216;
    public static final int BLUE = -16776961;
    public static final int GREEN = -16711936;
    public static final int LTGRAY = -3355444;
    public static final int RED = -65536;
    private static final String TAG = "TIView->";
    public static final int WHITE = -1;
    public boolean mIsChecked_LR;
    public boolean mIsChecked_TB;
    public boolean mIsChecked_UnitF;
    public int mManuRangeHigh;
    public int mManuRangeLow;
    public boolean mManuRangeMod;
    public byte[] mTempAryByte;
    public final int mSensorSizeX = 32;
    public final int mSensorSizeY = 32;
    public final int mViewSizeX = 768;
    public final int mViewSizeY = 768;
    public int mMinTemp = 0;
    public int mMinTempPosX = 0;
    public int mMinTempPosY = 0;
    public int mMaxTemp = 0;
    public int mMaxTempPosX = 0;
    public int mMaxTempPosY = 0;
    public int mCentTem = 0;
    public int mCentTemPosX = 15;
    public int mCentTemPosY = 15;
    public float fMaxTemp = 0.0f;
    public float fMinTemp = 0.0f;
    public float fCentTem = 0.0f;
    public int[] mTempAryInt = new int[1024];
    public int[] mTempFilterAryInt = new int[1024];
    public int[] mTempScaledAryInt = new int[589824];
    public int[] mColorAryInt = new int[589824];
    public final int mColorBrandSizeX = 32;
    public final int mColorBrandSizeY = 256;
    public int[] mColorBrandAryInt = new int[8192];
    public int mTempRangeHigh = 0;
    public int mTempRangeLow = 0;
    public int mTempMaxx10 = 9999;
    public int mTempMinx10 = -200;
    public enum_PSEMETHOD mPseMethod = enum_PSEMETHOD.GCM_Pseudo1;
    public int[][] PseTab = {new int[]{-16777188, -16777187, -16777186, -16777185, -16777184, -16777183, -16777182, -16777181, -16777180, -16777179, -16777178, -16777176, -16777175, -16777174, -16777173, -16777172, -16777171, -16646098, -16515025, -16383952, -16252879, -16121805, -15990732, -15859659, -15728586, -15597513, -15466440, -15335367, -15204294, -15073221, -14942148, -14811074, -14680001, -14548928, -14352319, -14221246, -14090173, -13959100, -13828027, -13696954, -13565881, -13434807, -13303734, -13172661, -13041588, -12910515, -12779442, -12648369, -12517296, -12386223, -12255150, -12124076, -11927467, -11796394, -11665321, -11534248, -11403175, -11272102, -11141029, -11009956, -10878883, -10747809, -10616736, -10485663, -10354590, -10223517, -10092444, -9961371, -9830298, -9699225, -9502616, -9371542, -9240469, -9109396, -8978323, -8847250, -8716177, -8585104, -8454031, -8322958, -8191885, -8060811, -7929738, -7798665, -7667592, -7536519, -7405446, -7208837, -7077764, -6946691, -6815618, -6684544, -6553477, -6422409, -6291342, -6160274, -6029207, -5898139, -5767071, -5636004, -5504936, -5373869, -5242289, -5110709, -4979130, -4782014, -4650435, -4518599, -4387020, -4255440, -4123860, -3992281, -3860701, -3728866, -3597286, -3465706, -3334127, -3202547, -3070712, -2939132, -2807552, -2675968, -2544384, -2347264, -2215424, -2083840, -1952256, -1820672, -1689088, -1557248, -1425664, -1294080, -1162496, -1030912, -899328, -767488, -635904, -504320, -372736, -241152, -43776, -43264, -42752, -42240, -41728, -41216, -40448, -39936, -39424, -38912, -38400, -37888, -37120, -36608, -36096, -35584, -35072, -34304, -33792, -33280, -32768, -32256, -31744, -30976, -30464, -29952, -29440, -28928, -28160, -27648, -27136, -26624, -26112, -25600, -24832, -24320, -23808, -23296, -22784, -22016, -21504, -20992, -20480, -19968, -19456, -18688, -18176, -17664, -17152, -16640, -16128, -15360, -14848, -14336, -13824, -13312, -12544, -12032, -11520, -11008, -10496, -9984, -9216, -8704, -8192, -7680, -7168, -6400, -5888, -5376, -4864, -4352, -3840, -3072, -2560, -2042, -1524, -1006, -232, -225, -219, -213, -207, -201, -194, -188, -182, -176, -169, -163, -157, -151, -145, -138, -132, -126, -120, -113, -107, -101, -95, -89, -82, -76, -70, -64, -57, -51, -45, -39, -33, -26, -20, -14, -8, -1}, new int[]{-16777216, -16777213, -16777209, -16777205, -16777201, -16777197, -16777193, -16777189, -16777185, -16777181, -16777177, -16777173, -16777169, -16777165, -16777161, -16777157, -16777153, -16777149, -16777145, -16777141, -16777137, -16777133, -16777129, -16777125, -16777121, -16777117, -16777113, -16777109, -16777105, -16777101, -16777097, -16777093, -16777089, -16777085, -16777081, -16777077, -16777073, -16777069, -16777065, -16777061, -16777057, -16777053, -16777049, -16777045, -16777041, -16777037, -16777033, -16777029, -16777025, -16777021, -16777017, -16777013, -16777009, -16777005, -16777001, -16776997, -16776993, -16776989, -16776985, -16776981, -16776977, -16776973, -16776969, -16776965, -16776965, -16776201, -16775181, -16774161, -16773141, -16772121, -16771101, -16770081, -16769061, -16768041, -16767021, -16766001, -16764981, -16763961, -16762941, -16761921, -16760901, -16759881, -16758861, -16757841, -16756821, -16755801, -16754781, -16753761, -16752741, -16751721, -16750701, -16749681, -16748661, -16747641, -16746621, -16745601, -16744581, -16743561, -16742541, -16741521, -16740501, -16739481, -16738461, -16737441, -16736421, -16735401, -16734381, -16733361, -16732341, -16731321, -16730301, -16729281, -16728261, -16727241, -16726221, -16725201, -16724181, -16723161, -16722141, -16721121, -16720101, -16719081, -16718061, -16717041, -16716021, -16715001, -16713981, -16712960, GREEN, -16515328, -16253184, -15991040, -15728896, -15466752, -15204608, -14942464, -14680320, -14418176, -14156032, -13893888, -13631744, -13369600, -13107456, -12845312, -12583168, -12321024, -12058880, -11796736, -11534592, -11272448, -11010304, -10748160, -10486016, -10223872, -9961728, -9699584, -9437440, -9175296, -8913152, -8651008, -8388864, -8126720, -7864576, -7602432, -7340288, -7078144, -6816000, -6553856, -6291712, -6029568, -5767424, -5505280, -5243136, -4980992, -4718848, -4456704, -4194560, -3932416, -3670272, -3408128, -3145984, -2883840, -2621696, -2359552, -2097408, -1835264, -1573120, -1310976, -1048832, -786688, -524544, -262400, -1280, -2304, -3328, -4352, -5376, -6400, -7424, -8448, -9472, -10496, -11520, -12544, -13568, -14592, -15616, -16640, -17664, -18688, -19712, -20736, -21760, -22784, -23808, -24832, -25856, -26880, -27904, -28928, -29952, -30976, -32000, -33024, -34048, -35072, -36096, -37120, -38144, -39168, -40192, -41216, -42240, -43264, -44288, -45312, -46336, -47360, -48384, -49408, -50432, -51456, -52480, -53504, -54528, -55552, -56576, -57600, -58624, -59648, -60672, -61696, -62720, -63744, -64768, -65536}, new int[]{-16777216, -16711423, -16645630, -16579837, -16514044, -16448251, -16382458, -16316665, -16250872, -16185079, -16119286, -16053493, -15987700, -15921907, -15856114, -15790321, -15724528, -15658735, -15592942, -15527149, -15461356, -15395563, -15329770, -15263977, -15198184, -15132391, -15066598, -15000805, -14935012, -14869219, -14803426, -14737633, -14671840, -14606047, -14540254, -14474461, -14408668, -14342875, -14277082, -14211289, -14145496, -14079703, -14013910, -13948117, -13882324, -13816531, -13750738, -13684945, -13619152, -13553359, -13487566, -13421773, -13355980, -13290187, -13224394, -13158601, -13092808, -13027015, -12961222, -12895429, -12829636, -12763843, -12698050, -12632257, -12566464, -12500671, -12434878, -12369085, -12303292, -12237499, -12171706, -12105913, -12040120, -11974327, -11908534, -11842741, -11776948, -11711155, -11645362, -11579569, -11513776, -11447983, -11382190, -11316397, -11250604, -11184811, -11119018, -11053225, -10987432, -10921639, -10855846, -10790053, -10724260, -10658467, -10592674, -10526881, -10461088, -10395295, -10329502, -10263709, -10197916, -10132123, -10066330, -10000537, -9934744, -9868951, -9803158, -9737365, -9671572, -9605779, -9539986, -9474193, -9408400, -9342607, -9276814, -9211021, -9145228, -9079435, -9013642, -8947849, -8882056, -8816263, -8750470, -8684677, -8618884, -8553091, -8487298, -8421505, -8355712, -8289919, -8224126, -8158333, -8092540, -8026747, -7960954, -7895161, -7829368, -7763575, -7697782, -7631989, -7566196, -7500403, -7434610, -7368817, -7303024, -7237231, -7171438, -7105645, -7039852, -6974059, -6908266, -6842473, -6776680, -6710887, -6645094, -6579301, -6513508, -6447715, -6381922, -6316129, -6250336, -6184543, -6118750, -6052957, -5987164, -5921371, -5855578, -5789785, -5723992, -5658199, -5592406, -5526613, -5460820, -5395027, -5329234, -5263441, -5197648, -5131855, -5066062, -5000269, -4934476, -4868683, -4802890, -4737097, -4671304, -4605511, -4539718, -4473925, -4408132, -4342339, -4276546, -4210753, -4144960, -4079167, -4013374, -3947581, -3881788, -3815995, -3750202, -3684409, -3618616, -3552823, -3487030, -3421237, LTGRAY, -3289651, -3223858, -3158065, -3092272, -3026479, -2960686, -2894893, -2829100, -2763307, -2697514, -2631721, -2565928, -2500135, -2434342, -2368549, -2302756, -2236963, -2171170, -2105377, -2039584, -1973791, -1907998, -1842205, -1776412, -1710619, -1644826, -1579033, -1513240, -1447447, -1381654, -1315861, -1250068, -1184275, -1118482, -1052689, -986896, -921103, -855310, -789517, -723724, -657931, -592138, -526345, -460552, -394759, -328966, -263173, -197380, -131587, -65794, -1}, new int[]{-16744449, -16679170, -16613891, -16548612, -16483333, -16418054, -16352775, -16287496, -16222217, -16156938, -16091659, -16026380, -15961101, -15895822, -15830543, -15765264, -15699985, -15634706, -15569427, -15504148, -15438869, -15373590, -15308311, -15243032, -15177753, -15112474, -15047195, -14981916, -14916637, -14851358, -14786079, -14720800, -14655521, -14590242, -14524963, -14459684, -14394405, -14329126, -14263847, -14198568, -14133289, -14068010, -14002731, -13937452, -13872173, -13806894, -13741615, -13676336, -13611057, -13545778, -13480499, -13415220, -13349941, -13284662, -13219383, -13154104, -13088825, -13023546, -12958267, -12892988, -12827709, -12762430, -12697151, -12631872, -12566593, -12501314, -12436035, -12370756, -12305477, -12240198, -12174919, -12109640, -12044361, -11979082, -11913803, -11848524, -11783245, -11717966, -11652687, -11587408, -11522129, -11456850, -11391571, -11326292, -11261013, -11195734, -11130455, -11065176, -10999897, -10934618, -10869339, -10804060, -10738781, -10673502, -10608223, -10542944, -10477665, -10412386, -10347107, -10281828, -10216549, -10151270, -10085991, -10020712, -9955433, -9890154, -9824875, -9759596, -9694317, -9629038, -9563759, -9498480, -9433201, -9367922, -9302643, -9237364, -9172085, -9106806, -9041527, -8976248, -8910969, -8845690, -8780411, -8715132, -8649853, -8584574, -8519295, -8454016, -8388225, -8322434, -8256643, -8190852, -8125061, -8059270, -7993479, -7927688, -7861897, -7796106, -7730315, -7664524, -7598733, -7532942, -7467151, -7401360, -7335569, -7269778, -7203987, -7138196, -7072405, -7006614, -6940823, -6875032, -6809241, -6743450, -6677659, -6611868, -6546077, -6480286, -6414495, -6348704, -6282913, -6217122, -6151331, -6085540, -6019749, -5953958, -5888167, -5822376, -5756585, -5690794, -5625003, -5559212, -5493421, -5427630, -5361839, -5296048, -5230257, -5164466, -5098675, -5032884, -4967093, -4901302, -4835511, -4769720, -4703929, -4638138, -4572347, -4506556, -4440765, -4374974, -4309183, -4243392, -4177601, -4111810, -4046019, -3980228, -3914437, -3848646, -3782855, -3717064, -3651273, -3585482, -3519691, -3453900, -3388109, -3322318, -3256527, -3190736, -3124945, -3059154, -2993363, -2927572, -2861781, -2795990, -2730199, -2664408, -2598617, -2532826, -2467035, -2401244, -2335453, -2269662, -2203871, -2138080, -2072289, -2006498, -1940707, -1874916, -1809125, -1743334, -1677543, -1611752, -1545961, -1480170, -1414379, -1348588, -1282797, -1217006, -1151215, -1085424, -1019633, -953842, -888051, -822260, -756469, -690678, -624887, -559096, -493305, -427514, -361723, -295932, -230141, -164350, -98559, -32768}, new int[]{-16777216, -16777213, -16777209, -16777205, -16777201, -16777197, -16777193, -16777189, -16777185, -16777181, -16777177, -16777173, -16777169, -16777165, -16777161, -16777157, -16777153, -16777149, -16777145, -16777141, -16777137, -16777133, -16777129, -16777125, -16777121, -16777117, -16777113, -16777109, -16777105, -16777101, -16777097, -16777093, -16777089, -16777085, -16777081, -16777077, -16777073, -16777069, -16777065, -16777061, -16777057, -16777053, -16777049, -16777045, -16777041, -16777037, -16777033, -16777029, -16777025, -16777021, -16777017, -16777013, -16777009, -16777005, -16777001, -16776997, -16776993, -16776989, -16776985, -16776981, -16776977, -16776973, -16776969, -16776965, -16579585, -16316417, -16053249, -15790081, -15526913, -15263745, -15000577, -14737409, -14474241, -14211073, -13947905, -13684737, -13421569, -13158401, -12895233, -12632065, -12368897, -12105729, -11842561, -11579393, -11316225, -11053057, -10789889, -10526721, -10263553, -10000385, -9737217, -9474049, -9210881, -8947713, -8684545, -8421377, -8158217, -7895057, -7631897, -7368737, -7105577, -6842417, -6579257, -6316097, -6052937, -5789777, -5526617, -5263457, -5000297, -4737137, -4473977, -4210817, -3947657, -3684497, -3421337, -3158177, -2895017, -2631857, -2368697, -2105537, -1842377, -1579217, -1316057, -1052897, -789737, -526577, -263417, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, InputDeviceCompat.SOURCE_ANY, -253, -249, -245, -241, -237, -233, -229, -225, -221, -217, -213, -209, -205, -201, -197, -193, -189, -185, -181, -177, -173, -169, -165, -161, -157, -153, -149, -145, -141, -137, -133, -129, -125, -121, -117, -113, -109, -105, -101, -97, -93, -89, -85, -81, -77, -73, -69, -65, -61, -57, -53, -49, -45, -41, -37, -33, -29, -25, -21, -17, -13, -9, -5}};

    public float TempC2F(float f) {
        return (f * 1.8f) + 32.0f;
    }

    public int TempC2F(int i) {
        return (int) ((i * 1.8f) + 32.0f);
    }

    public int min(int i, int i2) {
        return i < i2 ? i : i2;
    }

    public float GetTempByUnit(float f) {
        return this.mIsChecked_UnitF ? ((int) (((f * 1.8d) + 32.0d) * 10.0d)) / 10.0f : f;
    }

    public String GetTempStr(float f) {
        String f2 = Float.toString(GetTempByUnit(f));
        if (this.mIsChecked_UnitF) {
            return f2 + "℉";
        }
        return f2 + "℃";
    }

    public void DrawInfoToImage(Canvas canvas) {
        DrawColorBrand(canvas);
        DrawCentTempCross(canvas);
        DrawMaxTempCross(canvas);
    }

    public void DrawColorBrand(Canvas canvas) {
        for (int i = 0; i < 256; i++) {
            for (int i2 = 0; i2 < 32; i2++) {
                this.mColorBrandAryInt[(i * 32) + i2] = GrayToPseColor(this.mPseMethod, 255 - i);
            }
        }
        Paint paint = new Paint();
        canvas.drawBitmap(Bitmap.createBitmap(this.mColorBrandAryInt, 32, 256, Bitmap.Config.ARGB_8888), 704, 256, paint);
        paint.setStrokeWidth(3.0f);
        paint.setTextSize(36.0f);
        paint.setColor(-1);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText(GetTempStr(this.fMaxTemp), 656.0f, 246.0f, paint);
        paint.setColor(-1);
        canvas.drawText(GetTempStr(this.fMinTemp), 656.0f, 557.0f, paint);
    }

    public void DrawCentTempCross(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(36.0f);
        paint.setColor(-1);
        paint.setStrokeWidth(3.0f);
        float f = 384;
        canvas.drawCircle(f, f, 10, paint);
        float f2 = 359;
        float f3 = 374;
        canvas.drawLine(f2, f, f3, f, paint);
        float f4 = 394;
        float f5 = 409;
        canvas.drawLine(f4, f, f5, f, paint);
        canvas.drawLine(f, f2, f, f3, paint);
        canvas.drawLine(f, f4, f, f5, paint);
        canvas.drawText(GetTempStr(this.fCentTem), 409, 434, paint);
    }

    public void DrawMaxTempCross(Canvas canvas) {
        int i = this.mMaxTempPosX;
        int i2 = this.mMaxTempPosY;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(36.0f);
        paint.setColor(-65536);
        paint.setStrokeWidth(3.0f);
        float f = i;
        float f2 = i2;
        canvas.drawCircle(f, f2, 10, paint);
        canvas.drawLine(r2 - 15, f2, i - 10, f2, paint);
        canvas.drawLine(i + 10, f2, r2 + 15, f2, paint);
        canvas.drawLine(f, r2 - 15, f, i2 - 10, paint);
        canvas.drawLine(f, i2 + 10, f, r2 + 15, paint);
        canvas.drawText(GetTempStr(this.fMaxTemp), i + 25, i2 + 50, paint);
    }

    public int TempCheck(int i) {
        int i2 = this.mTempMinx10;
        return (i >= i2 && i <= (i2 = this.mTempMaxx10)) ? i : i2;
    }

    public void Temp2Image(byte[] bArr) {
        this.mTempAryByte = bArr;
        Byte2IntTemp();
        ReOrderData();
        TempFilter();
        TempScale();
        TempSort();
        Temp2Color();
    }

    public int[] Byte2IntTemp() {
        int[] iArr = this.mTempAryInt;
        for (int i = 0; i < iArr.length; i++) {
            byte[] bArr = this.mTempAryByte;
            int i2 = i * 2;
            iArr[i] = (short) (65535 & ((bArr[i2] & 255) + ((bArr[i2 + 1] & 255) * 256)));
        }
        return iArr;
    }

    public void ReOrderData() {
        int[] iArr = this.mTempAryInt;
        if (this.mIsChecked_LR) {
            for (int i = 0; i < 32; i++) {
                for (int i2 = 0; i2 < 16; i2++) {
                    int i3 = i * 32;
                    int i4 = i3 + i2;
                    int i5 = iArr[i4];
                    int i6 = i3 + (31 - i2);
                    iArr[i4] = iArr[i6];
                    iArr[i6] = i5;
                }
            }
        }
        if (this.mIsChecked_TB) {
            for (int i7 = 0; i7 < 16; i7++) {
                for (int i8 = 0; i8 < 32; i8++) {
                    int i9 = (i7 * 32) + i8;
                    int i10 = iArr[i9];
                    int i11 = ((31 - i7) * 32) + i8;
                    iArr[i9] = iArr[i11];
                    iArr[i11] = i10;
                }
            }
        }
    }

    public void TempFilter() {
        for (int i = 0; i < 32; i++) {
            for (int i2 = 0; i2 < 32; i2++) {
                int i3 = 0;
                for (int i4 = -1; i4 < 3; i4++) {
                    int i5 = i + i4;
                    if (i5 < 0) {
                        i5 = 0;
                    } else if (i5 >= 32) {
                        i5 = 31;
                    }
                    for (int i6 = -1; i6 < 3; i6++) {
                        int i7 = i2 + i6;
                        if (i7 < 0) {
                            i7 = 0;
                        } else if (i7 >= 32) {
                            i7 = 31;
                        }
                        i3 += this.mTempAryInt[(i5 * 32) + i7];
                    }
                }
                int[] iArr = this.mTempFilterAryInt;
                iArr[(i * 32) + i2] = i3 / 16;
            }
        }
    }

    public void TempScale() {
        int i = 768;
        float f = 32 / 768;
        float[] fArr = new float[4];
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, 4, 2);
        int[] iArr2 = new int[4];
        for (int i2 = 0; i2 < i; i2++) {
            float f2 = i2 * f;
            int i3 = (int) f2;
            int i4 = 31;
            int min = min(i3 + 1, 31);
            float f3 = f2 - i3;
            int i5 = 0;
            while (i5 < i) {
                float f4 = i5 * f;
                int i6 = (int) f4;
                int min2 = min(i6 + 1, i4);
                float f5 = f4 - i6;
                iArr[0][0] = i6;
                iArr[0][1] = i3;
                iArr[1][0] = min2;
                iArr[1][1] = i3;
                iArr[2][0] = i6;
                iArr[2][1] = min;
                iArr[3][0] = min2;
                iArr[3][1] = min;
                for (int i7 = 0; i7 < 4; i7++) {
                    iArr2[i7] = this.mTempFilterAryInt[(iArr[i7][1] * 32) + iArr[i7][0]];
                }
                float f6 = 1.0f - f3;
                float f7 = 1.0f - f5;
                fArr[0] = f6 * f7;
                fArr[1] = f6 * f5;
                fArr[2] = f7 * f3;
                fArr[3] = f5 * f3;
                i = 768;
                this.mTempScaledAryInt[(768 * i2) + i5] = (int) ((fArr[0] * iArr2[0]) + (fArr[1] * iArr2[1]) + (fArr[2] * iArr2[2]) + (fArr[3] * iArr2[3]));
                i5++;
                i4 = 31;
            }
        }
    }

    public void TempSort() {
        int[] iArr = this.mTempFilterAryInt;
        this.mMinTemp = iArr[0];
        this.mMaxTemp = iArr[0];
        for (int i = 0; i < 32; i++) {
            for (int i2 = 0; i2 < 32; i2++) {
                int i3 = (i * 32) + i2;
                if (iArr[i3] < this.mMinTemp) {
                    this.mMinTemp = iArr[i3];
                    this.mMinTempPosY = i * 24;
                    this.mMinTempPosX = i2 * 24;
                } else if (iArr[i3] > this.mMaxTemp) {
                    this.mMaxTemp = iArr[i3];
                    this.mMaxTempPosY = i * 24;
                    this.mMaxTempPosX = i2 * 24;
                }
            }
        }
        int i4 = this.mMaxTemp;
        this.fMaxTemp = i4 / 10.0f;
        int i5 = this.mMinTemp;
        this.fMinTemp = i5 / 10.0f;
        int i6 = this.mTempAryInt[(this.mCentTemPosY * 32) + this.mCentTemPosX];
        this.mCentTem = i6;
        this.fCentTem = i6 / 10.0f;
        if (this.mManuRangeMod) {
            this.mTempRangeLow = this.mManuRangeLow * 10;
            this.mTempRangeHigh = this.mManuRangeHigh * 10;
        } else {
            this.mTempRangeLow = i5;
            this.mTempRangeHigh = i4;
        }
        this.mMaxTemp = TempCheck(i4);
        this.mMinTemp = TempCheck(this.mMinTemp);
        this.mCentTem = TempCheck(this.mCentTem);
    }

    public int TempCheckIsInRange(int i) {
        int i2 = this.mTempRangeLow;
        return (i >= i2 && i <= (i2 = this.mTempRangeHigh)) ? i : i2;
    }

    public void Temp2Color() {
        int[] iArr = this.mColorAryInt;
        int i = this.mTempRangeHigh - this.mTempRangeLow;
        for (int i2 = 0; i2 < 768; i2++) {
            for (int i3 = 0; i3 < 768; i3++) {
                int i4 = (i2 * 768) + i3;
                iArr[i4] = ((TempCheckIsInRange(this.mTempScaledAryInt[i4]) - this.mTempRangeLow) * 255) / i;
            }
        }
        for (int i5 = 0; i5 < 768; i5++) {
            for (int i6 = 0; i6 < 768; i6++) {
                int i7 = (i5 * 768) + i6;
                iArr[i7] = GrayToPseColor(this.mPseMethod, iArr[i7]);
            }
        }
    }

    /* loaded from: classes.dex */
    public enum enum_PSEMETHOD {
        GCM_Pseudo1,
        GCM_Pseudo2,
        GCM_Metal1,
        GCM_Metal2,
        GCM_Rainbow1,
        GCM_Rainbow2,
        GCM_Rainbow3,
        GCM_Zhou,
        GCM_Ning,
        GCM_Gray;

        private static enum_PSEMETHOD[] vals = values();

        public enum_PSEMETHOD value() {
            return vals[ordinal()];
        }

        public enum_PSEMETHOD previous() {
            return vals[(ordinal() - 1) % vals.length];
        }

        public enum_PSEMETHOD next() {
            return vals[(ordinal() + 1) % vals.length];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.tione.ThermalImager$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$tione$ThermalImager$enum_PSEMETHOD;

        static {
            int[] iArr = new int[enum_PSEMETHOD.values().length];
            $SwitchMap$com$tione$ThermalImager$enum_PSEMETHOD = iArr;
            try {
                iArr[enum_PSEMETHOD.GCM_Metal2.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tione$ThermalImager$enum_PSEMETHOD[enum_PSEMETHOD.GCM_Pseudo2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tione$ThermalImager$enum_PSEMETHOD[enum_PSEMETHOD.GCM_Gray.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tione$ThermalImager$enum_PSEMETHOD[enum_PSEMETHOD.GCM_Pseudo1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tione$ThermalImager$enum_PSEMETHOD[enum_PSEMETHOD.GCM_Metal1.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public int GrayToPseColor(enum_PSEMETHOD enum_psemethod, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$tione$ThermalImager$enum_PSEMETHOD[enum_psemethod.ordinal()];
        if (i2 == 1) {
            return this.PseTab[0][i];
        }
        if (i2 == 2) {
            return this.PseTab[1][i];
        }
        if (i2 == 3) {
            return this.PseTab[2][i];
        }
        if (i2 == 4) {
            return this.PseTab[3][i];
        }
        if (i2 != 5) {
            return 0;
        }
        return this.PseTab[4][i];
    }
}
