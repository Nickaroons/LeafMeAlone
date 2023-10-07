package com.MatrixEngine.DataTypes;

import java.util.Objects;

public class Vector2 {
    private double x = 0;
    private double y = 0;

    public Vector2() {
        x = 0;
        y = 0;
    }
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 vector2) {
        x = vector2.getX();
        y = vector2.getY();
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2 vector2 = (Vector2) o;
        return Double.compare(vector2.x, x) == 0 && Double.compare(vector2.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
