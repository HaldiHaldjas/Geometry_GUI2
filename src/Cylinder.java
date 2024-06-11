public class Cylinder {
    private double radius;
    private double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double calculateSurfaceArea() {
        return 2 * Math.PI * radius * (radius + height);
    }

    public double calculateSideSurfaceArea() {
        return 2 * Math.PI * radius * height;
    }

    public double calculateVolume() {
        return Math.PI * radius * radius * height;
    }
}
