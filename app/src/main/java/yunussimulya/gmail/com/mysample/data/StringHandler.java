package yunussimulya.gmail.com.mysample.data;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;

/**
 * Created by Yunus on 3/7/2018.
 */

public class StringHandler {

    public interface OnClickableSpan {
        public void onClick();
    }

    public static Spannable getClickableSpan(String text, final int color, final OnClickableSpan onClickableSpan) {
        Spannable spannable = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                if (onClickableSpan != null) {
                    onClickableSpan.onClick();
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(color);
                ds.setUnderlineText(false);
            }
        };
        spannable.setSpan(clickableSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public static Spannable getForegroundColorSpan(String text, int color) {
        Spannable span = new SpannableString(text);
        span.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return span;
    }

    public static Spannable getStyleSpan(String text, int style) {
        //style = Typeface
        Spannable span = new SpannableString(text);
        span.setSpan(new StyleSpan(style), 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return span;
    }




}
