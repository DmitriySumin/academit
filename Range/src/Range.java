public class Range {

    private double from1;
    private double to1;
    private double from2;
    private double to2;

    public Range(double from1, double to1, double from2, double to2) {
        this.from1 = from1;
        this.to1 = to1;
        this.from2 = from2;
        this.to2 = to2;
    }

    public double[] getNewRangeIntersection() {
        double[] array = new double[2];
        if ((from1 < from2) && (to2 < to1)) {
            array[0] = from2;
            array[1] = to2;
        } else if ((from2 < from1) && (to2 < to1)) {
            array[0] = from1;
            array[1] = to1;
        } else if ((from1 < from2) && (from2 < to1)) {
            array[0] = from2;
            array[1] = to1;
        } else if ((from2 < from1) && (from2 < to2)) {
            array[0] = from1;
            array[1] = to2;
        } else {
            return null;
        }
        return array;
    }

    public double[] getNewRangeCombenation() {
        double[] array = new double[4];
        if ((from1 < from2) && (to2 < to1)) {
            array[0] = from1;
            array[1] = to1;
        } else if ((from2 < from1) && (to2 < to1)) {
            array[0] = from2;
            array[1] = to2;
        } else if ((from1 < from2) && (from2 < to1)) {
            array[0] = from1;
            array[1] = to2;
        } else if ((from2 < from1) && (from2 < to2)) {
            array[0] = from2;
            array[1] = to1;
        } else {
            array[0] = from1;
            array[1] = to1;
            array[2] = from2;
            array[3] = to2;
        }
        return array;
    }

    public double[] getNewDifference() {
        double[] array = new double[4];
        if ((from1 < from2) && (to2 < to1)) {
            array[0] = from1;
            array[1] = from2;
            array[2] = to2;
            array[3] = to1;
        } else if ((from2 < from1) && (to2 < to1)) {
            array[0] = from2;
            array[1] = from1;
            array[2] = to1;
            array[3] = to2;
        } else if ((from1 < from2) && (from2 < to1)) {
            array[0] = from1;
            array[1] = from2;
            array[2] = to1;
            array[3] = to2;
        } else if ((from2 < from1) && (from2 < to2)) {
            array[0] = from2;
            array[1] = from1;
            array[2] = to1;
            array[3] = to2;
        } else {
            return null;
        }
        return array;
    }

    public double[] getLength() {
        double[] array = new double[2];
        array[0] = to1 - from1;
        array[1] = to2 - from2;
        return array;
    }

    public boolean isInside(double numberInRange) {
        return (from1 <= numberInRange && to1 >= numberInRange) || (from2 <= numberInRange && to2 >= numberInRange);
    }
}
