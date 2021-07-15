package com.bytedance.camp.chapter4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bytedance.camp.chapter4.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Clock extends View {

    private static final int FULL_CIRCLE_DEGREE = 360;
    private static final int UNIT_DEGREE = 6;

    private static final float UNIT_LINE_WIDTH = 8; // 刻度线的宽度
    private static final int HIGHLIGHT_UNIT_ALPHA = 0xFF;
    private static final int NORMAL_UNIT_ALPHA = 0x80;

    private static final float HOUR_NEEDLE_LENGTH_RATIO = 0.4f; // 时针长度相对表盘半径的比例
    private static final float MINUTE_NEEDLE_LENGTH_RATIO = 0.6f; // 分针长度相对表盘半径的比例
    private static final float SECOND_NEEDLE_LENGTH_RATIO = 0.8f; // 秒针长度相对表盘半径的比例
    private static final float HOUR_NEEDLE_WIDTH = 12; // 时针的宽度
    private static final float MINUTE_NEEDLE_WIDTH = 8; // 分针的宽度
    private static final float SECOND_NEEDLE_WIDTH = 4; // 秒针的宽度

    private Calendar calendar = Calendar.getInstance();

    private int lenth;

    private float radius = 0;
    private float centerX = 0;
    private float centerY = 0;
    private List<RectF> unitLinePositions = new ArrayList<>();
    private Paint unitPaint = new Paint();
    private Paint needlePaint = new Paint();
    private Paint numberPaint = new Paint();
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if(msg.what==1){
                invalidate();
                return true;
            }
            return false;
        }
    });


    public Clock(Context context) {
        this(context, null);
        init();
    }

    public Clock(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
        initCustomAttrs(getContext(), attrs);
    }

    public Clock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initCustomAttrs(getContext(), attrs);
    }

    private void init() {

        unitPaint.setAntiAlias(true);
        unitPaint.setColor(Color.WHITE);
        unitPaint.setStrokeWidth(UNIT_LINE_WIDTH);
        unitPaint.setStrokeCap(Paint.Cap.ROUND);
        unitPaint.setStyle(Paint.Style.STROKE);

        needlePaint.setAntiAlias(true);
        needlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        needlePaint.setStrokeCap(Paint.Cap.ROUND);
        needlePaint.setColor(Color.WHITE);

        numberPaint.setAntiAlias(true);
        numberPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        numberPaint.setTextSize(50);
        numberPaint.setColor(Color.WHITE);
        numberPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        //获取自定义属性。
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Clock);
        //获取字体大小,默认大小是16dp
        int color_unit = ta.getColor(R.styleable.Clock_unit_color, Color.WHITE);
        //获取文字内容
        int color_needle = ta.getColor(R.styleable.Clock_needle_color, Color.WHITE);
        //获取文字颜色，默认颜色是BLUE
        unitPaint.setColor(color_unit);
        needlePaint.setColor(color_needle);
        Log.d("color-----", "initCustomAttrs: "+color_needle+" "+color_unit);
        ta.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        configWhenLayoutChanged();
    }

    private void configWhenLayoutChanged() {
        float newRadius = Math.min(getWidth(), getHeight()) / 2f;
        if (newRadius == radius) {
            return;
        }
        radius = newRadius;
        centerX = getWidth() / 2f;
        centerY = getHeight() / 2f;

        // 当视图的宽高确定后就可以提前计算表盘的刻度线的起止坐标了
        for (int degree = 0; degree < FULL_CIRCLE_DEGREE; degree += UNIT_DEGREE) {
            double radians = Math.toRadians(degree);
            float startX = (float) (centerX + (radius * (1 - 0.05f)) * Math.cos(radians));
            float startY = (float) (centerX + (radius * (1 - 0.05f)) * Math.sin(radians));
            float stopX = (float) (centerX + radius * Math.cos(radians));
            float stopY = (float) (centerY + radius * Math.sin(radians));
            unitLinePositions.add(new RectF(startX, startY, stopX, stopY));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUnit(canvas);
        drawTimeNeedles(canvas);
        drawTimeNumbers(canvas);
        handler.sendEmptyMessage(1);
    }

    private void drawTimeNeedles(Canvas canvas) {
        Time time = getCurrentTime();
        int hour = time.getHours();
        int minute = time.getMinutes();
        int second = time.getSeconds();
        int hourDegree = hour*30;
        int minDegree = minute*6;
        int secDegree = second*6;
        float hourendX = (float) (centerX + radius * HOUR_NEEDLE_LENGTH_RATIO * Math.sin(Math.toRadians(hourDegree)));
        float hourendY = (float) (centerY - radius * HOUR_NEEDLE_LENGTH_RATIO * Math.cos(Math.toRadians(hourDegree)));

        float minendX = (float) (centerX + radius * MINUTE_NEEDLE_LENGTH_RATIO * Math.sin(Math.toRadians(minDegree)));
        float minendY = (float) (centerY - radius * MINUTE_NEEDLE_LENGTH_RATIO * Math.cos(Math.toRadians(minDegree)));
        float secendX = (float) (centerX + radius * SECOND_NEEDLE_LENGTH_RATIO * Math.sin(Math.toRadians(secDegree)));
        float secendY = (float) (centerY - radius * SECOND_NEEDLE_LENGTH_RATIO * Math.cos(Math.toRadians(secDegree)));
        needlePaint.setStrokeWidth(SECOND_NEEDLE_WIDTH);
        canvas.drawLine(centerX, centerY, secendX, secendY, needlePaint);
        needlePaint.setStrokeWidth(MINUTE_NEEDLE_WIDTH);
        canvas.drawLine(centerX, centerY, minendX, minendY, needlePaint);
        needlePaint.setStrokeWidth(HOUR_NEEDLE_WIDTH);
        canvas.drawLine(centerX, centerY, hourendX, hourendY, needlePaint);

    }

    // 绘制表盘上的刻度
    private void drawUnit(Canvas canvas) {
        for (int i = 0; i < unitLinePositions.size(); i++) {
            if (i % 5 == 0) {
                unitPaint.setAlpha(HIGHLIGHT_UNIT_ALPHA);
            } else {
                unitPaint.setAlpha(NORMAL_UNIT_ALPHA);
            }
            RectF linePosition = unitLinePositions.get(i);
            canvas.drawLine(linePosition.left, linePosition.top, linePosition.right, linePosition.bottom, unitPaint);
        }
    }


    private void drawTimeNumbers(Canvas canvas) {
        numberPaint.getFontMetrics(fontMetrics);
        float textHeight = fontMetrics.descent - fontMetrics.ascent;
        // 粗糙绘制
        for (int i = 0; i < 12; i++) {
            float hourDegree = i * 30 - 60;
            String number = String.valueOf(i + 1);
            float textWidth = numberPaint.measureText(number);
            canvas.drawText(
                    number,
                    (float) (centerX + (radius * 0.8f + textWidth / 2f) * Math.cos(Math.toRadians(hourDegree))),
                    (float) (centerY + (radius * 0.8 + textHeight / 2f) * Math.sin(Math.toRadians(hourDegree))),
                    numberPaint
            );
        }
    }
    private Time getCurrentTime() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        return new Time(
                (calendar.get(Calendar.HOUR)+8)%24,
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));
    }

}
