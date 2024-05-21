package com.muen.fruitninja.util;

import android.graphics.Canvas;

import java.util.List;

public interface ProjectileManager {
    void draw(Canvas c);
    void update();
    void setWidthAndHeight(int width, int height);
    int testForCollisions(List<TimedPath> allPaths);
}
