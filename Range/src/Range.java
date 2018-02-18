public class Range {

    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double numberInRange) {
        return from <= numberInRange && to >= numberInRange;
    }

    public double[] getRange() {
        double[] array = new double[2];
        array[0] = from;
        array[1] = to;
        return array;
    }
}
