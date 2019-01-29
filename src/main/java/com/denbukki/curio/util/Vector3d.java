package com.denbukki.curio.util;

public class Vector3d {
    private double x, y, z;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d sub(Vector3d vec) {
        return new Vector3d(x - vec.x, y - vec.y, z - vec.z);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3d normalise() {
        double d = this.magnitude();
        if (d != 0.0D) {
            multiply(1.0D / d);
        }
        return this;
    }

    private Vector3d multiply(double d) {
        x *= d;
        y *= d;
        z *= d;
        return this;
    }
}
