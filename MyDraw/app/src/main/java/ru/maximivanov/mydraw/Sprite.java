package ru.maximivanov.mydraw;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Sprite {
    private Bitmap pic;
    private List<Rect> frames;
    private int frameWidth;
    private int frameHeight;
    private int currentFrame;
    private double frameTime;
    private double timeForCurrentFrame;
    private double x, y;
    private int padding;

    public Sprite(Bitmap pic, double x, double y, Rect initialFrame) {
        this.pic = pic;
        this.x = x;
        this.y = y;
        this.frames = new ArrayList<Rect>();
        this.frames.add(initialFrame);
        this.timeForCurrentFrame = 0.0;
        this.frameTime = 25;
        this.currentFrame = 0;
        this.frameWidth = initialFrame.width();
        this.frameHeight = initialFrame.height();
        this.padding = 40;
    }

    public void addFrame(Rect frame) {
        frames.add(frame);
    }

    public void update(int ms) {
        timeForCurrentFrame += ms;
        if (timeForCurrentFrame >= frameTime) {
            currentFrame = (currentFrame + 1) % frames.size();
        }
    }

    public void draw(Canvas canvas, float X, float Y) {
        Paint p = new Paint();
        x = X;
        y = Y;
        Rect rect = new Rect((int) X, (int) Y, (int) (X + frameWidth), (int) (Y + frameHeight));
        canvas.drawBitmap(pic, frames.get(currentFrame), rect, p);
    }

    public Rect getBoundingBoxRect() {
        return new Rect((int) x + padding, (int) y + padding,
                (int) (x + frameWidth - 2 * padding),
                (int) (y + frameHeight - 2 * padding));
    }

    public boolean intersect(Sprite s) {
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }
}
