package ru.academits.range.work;

public class Range {

    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range getIntersection(Range range) {
        if ((from < range.from) && (to <= range.from) || (range.from < from) && (range.to <= from)) {
            return null;
        } else if ((range.from >= from) && (range.to <= to)) {
            return new Range(range.from, range.to);
        } else if ((from >= range.from) && (to <= range.to)) {
            return new Range(from, to);
        } else if ((from < range.from) && (range.from < to)) {
            return new Range(range.from, to);
        } else {
            return new Range(from, range.to);
        }
    }

    public Range[] getUnionRange(Range range) {
        if ((from < range.from && (to < range.from) || (range.from < from) && (range.to < from))) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        } else if ((range.from >= from) && (range.to <= to)) {
            return new Range[]{new Range(from, to)};
        } else if ((from >= range.from) && (to <= range.to)) {
            return new Range[]{new Range(range.from, range.to)};
        } else if ((from < range.from) && (range.from <= to)) {
            return new Range[]{new Range(from, range.to)};
        } else {
            return new Range[]{new Range(range.from, to)};
        }
    }

    public Range[] getDifferenceRange(Range range) {
        if (((from < range.from) && (to < range.from)) || ((range.from < from) && (range.to < from))) {
            return new Range[]{};
        } else if ((range.from < from) && (range.to > to)) {
            return new Range[]{new Range(range.from, from), new Range(to, range.to)};
        } else if (((from < range.from) && (to > range.to))) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        } else if (from == range.from) {
            if (to > range.to) {
                return new Range[]{new Range(range.to, to)};
            } else {
                return new Range[]{new Range(to, range.to)};
            }
        } else if (to == range.to) {
            if (from < range.from) {
                return new Range[]{new Range(from, range.from)};
            } else {
                return new Range[]{new Range(range.from, from)};
            }
        } else if (((from < range.from) && (to > range.from))) {
            return new Range[]{new Range(from, range.from), new Range(to, range.to)};
        } else if ((range.from < from) && (range.to > from)) {
            return new Range[]{new Range(range.from, from), new Range(range.to, to)};
        } else {
            return new Range[]{};
        }
    }

    public String toString() {
        return "Начальный элемент диапазона: " + from + " конечный элемент диапазона: " + to;
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