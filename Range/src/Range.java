public class Range {

    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public void rangeIntersection(double from1, double to1) {
        if ((from1 > from) && (to1 < to)) {
            from = from1;
            to = to1;
            System.out.println("Диапазон пересечения: " + from + "..." + to);
        } else if ((from > from1) && (to < to1)) {
            System.out.println("Диапазон пересечения: " + from + "..." + to);
        } else if ((from < from1) && (from1 < to)) {
            from = from1;
            System.out.println("Диапазон пересечения: " + from + "..." + to);
        } else if ((from1 < from) && (from < to1)) {
            from = to1;
            System.out.println("Диапазон пересечения: " + from + "..." + to);
        } else {
            System.out.println("null");
        }
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
}
