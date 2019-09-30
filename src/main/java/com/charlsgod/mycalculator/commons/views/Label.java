package com.charlsgod.mycalculator.commons.views;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.charlsgod.mycalculator.R;


/**
 * Created by dell on 26/06/2017.
 */

@SuppressLint("AppCompatCustomView")
public class Label extends TextView {

    public Label(Context context) {
        super(context);
    }

    public Label(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            parseAttributes(context, attrs);
    }

    public Label(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            parseAttributes(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Label(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isInEditMode())
            parseAttributes(context, attrs);
    }

    /**
     * Parse avenir book typeface
     *
     * @param context view context
     * @param attrs   attribute set
     */
    public void parseAttributes(Context context, AttributeSet attrs) {
        try {
            TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.Label);
            FontType fontType = FontType.values()
                    [values.getInt(R.styleable.Label_fontType, FontType.DIGITAL_7_ITALIC.ordinal())];
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontType.getAssetPath());
            if (typeface != null) {
                setTypeface(typeface);
            }
            values.recycle();
        } catch (Exception ex) {
            ex.printStackTrace();
            setDefaultTypeface(context);
        }
    }

    /**
     * Set default typeface
     *
     * @param context view context
     */
    public void setDefaultTypeface(Context context) {
        Typeface typeface = Typeface
                .createFromAsset(context.getAssets(), FontType.DIGITAL_7_ITALIC.getAssetPath());
        if (typeface != null) {
            setTypeface(typeface);
        }
    }

    /**
     * Set custom font to this view
     *
     * @param fontType FontType to change
     */
    public void setFontType(FontType fontType) {
        Typeface typeface = Typeface
                .createFromAsset(getContext().getAssets(), fontType.getAssetPath());
        if (typeface != null) {
            setTypeface(typeface);
        }
    }
}