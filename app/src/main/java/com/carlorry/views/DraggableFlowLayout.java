package com.carlorry.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.carlorry.activity.R;


public class DraggableFlowLayout extends ViewGroup {
    private int mHorizontalSpacing;
    private int mVerticalSpacing;
    private Paint mPaint;

    public DraggableFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        try {
            mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_horizontalSpacing, 0);
            mVerticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_verticalSpacing, 0);
        } finally {
            a.recycle();
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xffff0000);
        mPaint.setStrokeWidth(2.0f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingRight();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        boolean growHeight = widthMode != MeasureSpec.UNSPECIFIED;

        int width = 0;
        int height = getPaddingTop();

        int currentWidth = getPaddingLeft();
        int currentHeight = 0;

        boolean breakLine = false;
        boolean newLine = false;
        int spacing = 0;

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            spacing = mHorizontalSpacing;
            if (lp.horizontalSpacing >= 0) {
                spacing = lp.horizontalSpacing;
            }

            //Home.dewaLog("Processing"+((TextView) child.findViewById(R.id.textView1)).getText());
            if (growHeight && (breakLine || currentWidth + child.getMeasuredWidth() > widthSize)) {
                //fit(i, widthMeasureSpec, heightMeasureSpec, widthSize, currentWidth, growHeight, breakLine);
                boolean fitFound = fitNextAvailableChildView(i, widthMeasureSpec, heightMeasureSpec, widthSize - currentWidth);
                if (fitFound)
                    i++;
                height += currentHeight + mVerticalSpacing;
                currentHeight = 0;
                width = Math.max(width, currentWidth - spacing);
                currentWidth = getPaddingLeft();
                //Home.dewaLog("Item "+i+" placed in new line with CurrentWidth "+currentWidth);
                newLine = true;
            } else {
                newLine = false;
            }

            lp.x = currentWidth;
            lp.y = height;

            currentWidth += child.getMeasuredWidth() + spacing;
            currentHeight = Math.max(currentHeight, child.getMeasuredHeight());

            breakLine = lp.breakLine;
        }

        //if (newLine) {
        height += currentHeight;
        width = Math.max(width, currentWidth - spacing);
        //}

        width += getPaddingRight();
        height += getPaddingBottom();

        //Home.dewaLog("FlowLayout Width on measure:"+FLOWLAYOUT_WIDTH);
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    private boolean fitNextAvailableChildView(int position, int widthMeasureSpec, int heightMeasureSpec, int availableSpace) {
        final int count = getChildCount();
        boolean fitFound = false;
        int fitPosition;
        View fitView = null;
        for (int i = position + 1; i < count; i++) {
            View nextChild = getChildAt(i);
            measureChild(nextChild, widthMeasureSpec, heightMeasureSpec);

            if (availableSpace >= nextChild.getMeasuredWidth()) {
                this.removeView(nextChild);
                this.addView(nextChild, position);
                //TextView textview = (TextView) nextChild.findViewById(R.id.textView1);
                //textview.append("\n moved to "+(position)+" from "+i+"\n"+availableSpace+">="+nextChild.getMeasuredWidth());
                //Home.dewaLog("Fitting child "+i+"  moved to "+position +"\n");
                fitFound = true;
                fitView = nextChild;
                fitPosition = position;
                //fitNextAvailableChildView(i, widthMeasureSpec, heightMeasureSpec, availableSpace);
                return true;
            }
        }
        return false;

		/*if(fitFound){
            this.removeView(fitView);
			this.addView(fitView,position);
		}*/
        //return false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(), lp.y + child.getMeasuredHeight());
        }
    }

    /*	@Override
        protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
            boolean more = super.drawChild(canvas, child, drawingTime);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (lp.horizontalSpacing > 0) {
                float x = child.getRight();
                float y = child.getTop() + child.getHeight() / 2.0f;
                canvas.drawLine(x, y - 4.0f, x, y + 4.0f, mPaint);
                canvas.drawLine(x, y, x + lp.horizontalSpacing, y, mPaint);
                canvas.drawLine(x + lp.horizontalSpacing, y - 4.0f, x + lp.horizontalSpacing, y + 4.0f, mPaint);
            }
            if (lp.breakLine) {
                float x = child.getRight();
                float y = child.getTop() + child.getHeight() / 2.0f;
                canvas.drawLine(x, y, x, y + 6.0f, mPaint);
                canvas.drawLine(x, y + 6.0f, x + 6.0f, y + 6.0f, mPaint);
            }
            return more;
        }
    */
    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p.width, p.height);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        int x;
        int y;

        public int horizontalSpacing;
        public boolean breakLine;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout_LayoutParams);
            try {
                horizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_LayoutParams_layout_horizontalSpacing, -1);
                breakLine = a.getBoolean(R.styleable.FlowLayout_LayoutParams_layout_breakLine, false);
            } finally {
                a.recycle();
            }
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }
    }

    private int stateToSave;

    @Override
    public Parcelable onSaveInstanceState() {

        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("stateToSave", this.stateToSave);
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {

        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.stateToSave = bundle.getInt("stateToSave");
            state = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(state);
    }
}
