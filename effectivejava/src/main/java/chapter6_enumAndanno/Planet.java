package chapter6_enumAndanno;

/**
 * Created by BG289522 on 2017/6/21.
 */
public enum Planet {

    MERCURY(3.302E+23, 2.439E6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378E6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e27, 7.149e7),
    SATURN(5.685e+26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);

    //kilograms 质量
    private final double mass;
    //meters 半径
    private final double radius;
    // m/s^2 重力
    private final double surfaceGravity;

    public static final double G = 6.67300E-11;

    Planet(double mass, double radius){
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double masss(){
        return mass;
    }

    public double radius(){
        return radius;
    }

    public double surfaceGravity(){
        return surfaceGravity;
    }

    public double surfaceWeight(){
        return surfaceGravity;
    }

    public double surfaceWeight(double mass){
        return mass * surfaceGravity;
    }

    /**
     * 根据某个物体在地球上的重量，打印出该物体在所有8颗行星上的重量
     */
    public static void main(String[] args) {
        double earthWeight = Double.parseDouble("175");
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values()){
            System.out.printf("Weight on %s is %f%n", p, p.surfaceWeight(mass));
        }
    }

}
