public class Range {

    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range getIntersection(Range range) {
        if ((from < range.getFrom() && (to < range.getFrom()) ||(range.getFrom() < from) && (range.getTo() < from))){
            return null;
        } else if ((range.getFrom() > from) && (range.getTo() < to)){
            return new Range(range.getFrom(), range.getTo());
        } else if ((from > range.getFrom()) && (to < range.getTo())){
            return new Range(from, to);
        } else if((from < range.getFrom()) && (range.getFrom() < to)){
            return new Range(range.getFrom(), to);
        } else {
            return new Range(from, range.getTo());
        }
    }

    public Object [] getAssociation(Range range){
        Object [] arrayRange = new Object[2];
        if ((from < range.getFrom() && (to < range.getFrom()) ||(range.getFrom() < from) && (range.getTo() < from))){
            arrayRange[0] = new Range(from, to);
            arrayRange[1] = new Range(range.getFrom(), range.getTo());
            return arrayRange;
        } else if ((range.getFrom() > from) && (range.getTo() < to)){
            arrayRange[0] = new Range(from, to);
            arrayRange[1] = null;
            return arrayRange;
        } else if ((from > range.getFrom()) && (to < range.getTo())){
            arrayRange[0] = new Range(range.getFrom(), range.getTo());
            arrayRange[1] = null;
            return arrayRange;
        } else if((from < range.getFrom()) && (range.getFrom() < to)){
            arrayRange[0] = new Range(from, range.getTo());
            arrayRange[1] = null;
            return arrayRange;
        } else {
            arrayRange[0] = new Range(range.getFrom(), to);
            arrayRange[1] = null;
            return arrayRange;
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