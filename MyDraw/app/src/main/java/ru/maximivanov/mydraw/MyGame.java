package ru.maximivanov.mydraw;

import android.content.Context;
import android.graphics.*;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.fragment.app.DialogFragment;

public class MyGame extends View {
    private final Paint paint = new Paint();
    private int viewWidth;
    private int viewHeight;
    private Sprite ship;
    private Sprite asteroid[] = new Sprite[3];
    private final Bitmap pic;
    private final Bitmap background;
    private final Bitmap astBit;
    private float touchx;
    private final int picFrameWidth;
    private final int asteroidFrameSize;
    private float myShipX, myShipY;
    private float astX[] = new float[3];
    private float astY[] = new float[3];
    private int astVelocity = 20;
    private final int timerInterval = 50;
    private int score = 0;
    public static boolean isGameOver = false;

    public MyGame(Context context) {
        super(context);
        viewWidth = MainActivity.width;
        viewHeight = MainActivity.height;
        pic = BitmapFactory.decodeResource(getResources(), R.mipmap.ship2);
        astBit = BitmapFactory.decodeResource(getResources(), R.mipmap.asteroid);
        picFrameWidth = pic.getWidth() / 2;
        asteroidFrameSize = astBit.getWidth() / 4;
        int h = pic.getHeight();
        int w = pic.getWidth() / 2;
        Rect firstFrame = new Rect(0, 0, h, w);
        ship = new Sprite(pic, myShipX, myShipY, firstFrame);
        for (int i = 0; i < 2; ++i) {
            ship.addFrame(new Rect(i*w, 0, i*w+w, h));
        }
        h = astBit.getHeight();
        w = astBit.getWidth() / 4;
        firstFrame = new Rect(0, 0, w, h);
        for (int j = 0; j < 3; ++j) {
            astX[j] = myRandomX();
            asteroid[j] = new Sprite(astBit, astX[j], -1 * asteroidFrameSize, firstFrame);
            for (int i = 0; i < 4; ++i) {
                asteroid[j].addFrame(new Rect(i*w, 0, i*w+w, h));
            }

        }
        astY[0] = -1 * asteroidFrameSize;
        for (int i = 1; i < 3; ++i) {
            astY[i]= astY[0] - asteroidFrameSize * 3 * i ;
        }
        background = BitmapFactory.decodeResource(getResources(), R.mipmap.background_space);
        myShipX = 0;
        Timer t = new Timer();
        t.start();
    }

    private float myRandomX() {
        int rand = (int)(Math.random() * 3) + 1;
        switch (rand) {
            case 1:
                return 10.f;
            case 2:
                return (float) (viewWidth - asteroidFrameSize) / 2;
            case 3:
                return viewWidth - asteroidFrameSize - 10.f;
        }
        return 10.f;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        myShipY = viewHeight - pic.getHeight() - 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        canvas.scale(3.2f, 3.2f);
        canvas.drawBitmap(background, 0, 0, paint);
        canvas.scale(0.3125f, 0.3125f);
        ship.draw(canvas, myShipX, myShipY);
        for (int i = 0; i < 3; ++i) {
            astY[i] += astVelocity;
            asteroid[i].draw(canvas, astX[i], astY[i]);
            if (astY[i] > viewHeight) {
                astY[i] = -1 * asteroidFrameSize;
                astX[i] = myRandomX();
                score++;
            }
        }
        invalidate();
    }

    protected void update() {
        ship.update(timerInterval);
        if (astVelocity == 0 && !isGameOver) {
            astVelocity = 20;
        }
        for (int i = 0; i < 3; ++i) {
            asteroid[i].update(timerInterval);
        }
        for (int i = 0; i < 3; ++i) {
            if (ship.intersect(asteroid[i])) {
                gameOver();
            }
        }
        invalidate();
    }

    private void gameOver() {
        isGameOver = true;
        astY[0] = -1 * asteroidFrameSize;
        for (int i = 1; i < 3; ++i) {
            astY[i]= astY[0] - asteroidFrameSize * 3 * i ;
        }
        astVelocity = 0;
        DialogFragment dlg = new MyDialog(score);
        dlg.show(MainActivity.fm, "dlg");
        score = 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        Log.i("info", String.valueOf(event.getX() >= myShipX && event.getX() <= myShipX + picFrameWidth));
        Log.i("eventAction", String.valueOf(eventAction));
        if (event.getX() >= myShipX && event.getX() <= myShipX + picFrameWidth) {
            if (eventAction == MotionEvent.ACTION_MOVE || eventAction == MotionEvent.ACTION_DOWN) {
                touchx = event.getX() - (float) picFrameWidth / 2;
                Log.i("touchx", String.valueOf(touchx));
                if (touchx > viewWidth - (float)picFrameWidth) {
                    myShipX = viewWidth - (float)picFrameWidth;
                }
                else if (touchx < 0) {
                    myShipX = 0;
                }
                else {
                    myShipX = touchx;
                }
            }
        }
        return true;
    }

    class Timer extends CountDownTimer {
        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            update();
        }

        @Override
        public void onFinish() {

        }
    }
}