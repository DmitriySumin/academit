public class Range {

    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range getIntersection(Range range) {
        if ((range.getFrom() > from) && (range.getTo() < to)) {
            return new Range(range.getFrom(), range.getTo());
        } else if ((from > range.getFrom()) && (to < range.getTo())) {
            return new Range(from, to);
        } else if ((from < range.getFrom()) && (range.getFrom() < to)) {
            return new Range(range.getFrom(), to);
        } else if ((range.getFrom() < from) && (from < range.getTo())) {
            return new Range(range.getTo(), to);
        } else {
            return null;
        }
    }

    public String toString() {
        return "Начальный элемент диапазона:" + from + " конечный элемент диапазона: " + to;
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
