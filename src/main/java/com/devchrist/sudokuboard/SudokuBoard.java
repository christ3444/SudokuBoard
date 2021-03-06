package com.devchrist.sudokuboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


    public class SudokuBoard extends View {

        private final int boardColor;
        private final Paint boardColorPaint = new Paint();
        private  int cellSize;


        public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);


            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard,
                    0,0);
            try{

                boardColor = a.getInteger(R.styleable.SudokuBoard_boardColor, 0);
            }finally {
                a.recycle();
            }

        }


        @Override
        protected  void onMeasure(int width, int height){

            super.onMeasure(width,height);

            int dimension = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());

            cellSize = dimension / 9;

            setMeasuredDimension(dimension, dimension);
        }

        @Override
        protected void onDraw(Canvas canvas){

            boardColorPaint.setStyle(Paint.Style.STROKE);
            boardColorPaint.setStrokeWidth(16);
            boardColorPaint.setColor(boardColor);
            boardColorPaint.setAntiAlias(true);

            canvas.drawRect(0,0, getWidth(), getHeight(), boardColorPaint);
            drawBoard(canvas);

        }

        private void drawThickLine(){
            boardColorPaint.setStyle(Paint.Style.STROKE);
            boardColorPaint.setStrokeWidth(16);
            boardColorPaint.setColor(boardColor);
        }

        private void drawThinLine(){
            boardColorPaint.setStyle(Paint.Style.STROKE);
            boardColorPaint.setStrokeWidth(4);
            boardColorPaint.setColor(boardColor);
        }

        private void drawBoard(Canvas canvas){

            for (int c = 0;c < 10;c++ ){
                if( c%3 == 0){
                    drawThickLine();
                }
                else {
                    drawThinLine();
                }
                canvas.drawLine(cellSize * c , 0,
                        cellSize * c, getWidth(), boardColorPaint );
            }

            for (int r = 0;r < 10;r++ ){

                if( r%3 == 0){
                    drawThickLine();
                }
                else {
                    drawThinLine();
                }
                canvas.drawLine(0,cellSize * r ,getWidth(),
                        cellSize * r,  boardColorPaint );


            }

        }


    }
