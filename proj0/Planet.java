

public class Planet {
    // Its current x position
    public double xxPos;
    // Its current y position
    public double yyPos;
    // Its current velocity in the x direction
    public double xxVel;
    // Its current velocity in the y direction
    public double yyVel;
    // Its mass
    public double mass;
    // The name of the file that corresponds to the image that depicts the planet
    public String imgFileName;
    // gravitational constant
    private static final double G = 6.67 * Math.pow(10, -11);


    /**
     * creating a basic version of the Planet class
     * with the following 6 instance variables.
     */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    /**
     * take in a Planet object and initialize an identical Planet object
     */
    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    /**
     * calculates the distance between two Planets.
     *
     * @param other another planet
     */
    public double calcDistance(Planet other) {
        double dx = this.xxPos - other.xxPos;
        double dy = this.yyPos - other.yyPos;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * return a double describing the force exerted on this planet by the given planet.
     *
     * @param other another planet
     */
    public double calcForceExertedBy(Planet other) {
        double dx = other.xxPos - this.xxPos;
        double dy = other.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return G * this.mass * other.mass / (r * r);
    }

    /**
     * describe the force exerted in the X.
     *
     * @param other another planet
     */
    public double calcForceExertedByX(Planet other) {
        double dx = other.xxPos - this.xxPos;
        double dy = other.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return calcForceExertedBy(other) * dx / r;
    }

    /**
     * describe the force exerted in the Y.
     *
     * @param other another planet
     */
    public double calcForceExertedByY(Planet other) {
        double dx = other.xxPos - this.xxPos;
        double dy = other.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return calcForceExertedBy(other) * dy / r;
    }

    /**
     * take in an array of Planets and calculate the net X force exerted by all planets
     * in that array upon the current Planet.
     *
     * @param others other planets
     */

    public double calcNetForceExertedByX(Planet[] others) {
        double sum_Fx = 0.0;
        for (Planet other : others) {
            if (this.equals(other))
                continue;
            sum_Fx += calcForceExertedByX(other);
        }
        return sum_Fx;
    }

    /**
     * take in an array of Planets and calculate the net Y force exerted by all planets
     * in that array upon the current Planet.
     *
     * @param others other planets
     */
    public double calcNetForceExertedByY(Planet[] others) {
        double sum_Fy = 0.0;
        for (Planet other : others) {
            if (this.equals(other))
                continue;
            sum_Fy += calcForceExertedByY(other);
        }
        return sum_Fy;
    }

    /**
     * determines how much the forces exerted on the planet will cause that planet to accelerate,
     * and the resulting change in the planet’s velocity and position in a small period of time dt
     */
    public void update(double time, double Fx, double Fy) {
        double ax = Fx / mass;
        double ay = Fy / mass;
        this.xxVel += ax * time;
        this.yyVel += ay * time;
        this.xxPos += this.xxVel * time;
        this.yyPos += this.yyVel * time;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
