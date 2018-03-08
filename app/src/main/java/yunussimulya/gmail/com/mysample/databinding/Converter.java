package yunussimulya.gmail.com.mysample.databinding;

import android.content.res.Resources;
import android.databinding.InverseMethod;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Yunus on 3/8/2018.
 */

public class Converter {

    @InverseMethod("toInt")
    public static String intToString(TextView view, int oldValue, int value) {
        NumberFormat numberFormat = getNumberFormat(view);
        try {
            String inView = view.getText().toString();
            int parsed = numberFormat.parse(inView).intValue();
            if (parsed == value) {
                return view.getText().toString();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numberFormat.format(value);
    }

    public static int toInt(TextView view, int oldValue, String value) {
        NumberFormat numberFormat = getNumberFormat(view);
        try {
            return numberFormat.parse(value).intValue();
        } catch (ParseException e) {
            String errString = "Wrong Value";
            view.setError(errString);
            return oldValue;
        }
    }

    private static NumberFormat getNumberFormat(View view) {
        Resources resources = view.getResources();
        Locale locale = resources.getConfiguration().locale;
        NumberFormat format = NumberFormat.getNumberInstance(locale);
        if (format instanceof DecimalFormat) {
            DecimalFormat decimalFormat = (DecimalFormat) format;
            decimalFormat.setGroupingUsed(false);
        }
        return format;
    }

}
