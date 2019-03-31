package com.effective;

public enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),

    MARS(6.419e+23, 3.393e6),

    JUPITER(1.899e+27, 7.149e7),

    SATURN(5.685e+26, 6.027e7),

    URANUS(8.683e+25, 2.55676),

    MEPTUNE(1.024e+26, 2.477e7);


    private static final double G = 6.67300E-11;
    private final double mass;
    private final double radius;
    private final double surfaceGravity;


    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double getSurfaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;
    }

//    @Override
//    public String toString() {
//        return "Planet{" +
//                "mass=" + mass +
//                ", radius=" + radius +
//                ", surfaceGravity=" + surfaceGravity +
//                '}';
//    }
}
