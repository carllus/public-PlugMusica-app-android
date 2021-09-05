package app.forrozapp.meuforroapp.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ArredondarImageView extends ImageView {
    public ArredondarImageView(Context context) {
        super(context);
    }

    public ArredondarImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArredondarImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Bitmap b;
        Drawable drawable = getDrawable();
        if (drawable != null && getWidth() != 0 && getHeight() != 0) {
            if (Build.VERSION.SDK_INT < 21 || !(drawable instanceof VectorDrawable)) {
                b = ((BitmapDrawable) drawable).getBitmap();
            } else {
                ((VectorDrawable) drawable).draw(canvas);
                b = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas();
                c.setBitmap(b);
                drawable.draw(c);
            }
            Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
            int w = getWidth();
            int height = getHeight();
            canvas.drawBitmap(getCroppedBitmap(bitmap, w), 0.0f, 0.0f, (Paint) null);
        }
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() == radius && bmp.getHeight() == radius) {
            sbmp = bmp;
        } else {
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        }
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(((float) (sbmp.getWidth() / 2)) + 0.7f, ((float) (sbmp.getHeight() / 2)) + 0.7f, ((float) (sbmp.getWidth() / 2)) + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);
        return output;
    }
}
