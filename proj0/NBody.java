public class NBody {

    /**
     * return a double corresponding to the radius of the universe in that file.
     *
     * @param planetsTxtPath file name
     */
    public static double readRadius(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        in.readLine();
        return in.readDouble();
    }

    /**
     * return an array of Planets corresponding to the planets in the file.
     *
     * @param planetsTxtPath file name
     */
    public static Planet[] readPlanets(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        int numOfPlanets = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[numOfPlanets];
        for (int i = 0; i < numOfPlanets; ++i) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planets[i] = planet;
        }
        return planets;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Please supply three command line arguments.");
        }
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String fileName = args[2];
        Planet[] planets = NBody.readPlanets(fileName);
        double radius = NBody.readRadius(fileName);

        StdDraw.setScale(-radius, radius);
        double time = 0.0;
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        StdDraw.enableDoubleBuffering();
        while (time < T) {
            for (int i = 0; i < planets.length; ++i) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                StdDraw.clear();
                StdDraw.picture(0, 0, "images/starfield.jpg");
                planets[i].draw();
                StdDraw.show();
                StdDraw.pause(10);
            }
            for (int i = 0; i < planets.length; ++i) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
