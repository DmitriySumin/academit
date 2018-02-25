package ru.academits;

public class Range {

    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range getIntersection(Range range) {
        if ((from < range.from && (to < range.from) || (range.from < from) && (range.to < from) || (to == range.from) || (range.to == from))) {
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
            Range[] range1 = new Range[2];
            range1[0] = new Range(from, to);
            range1[1] = new Range(range.from, range.to);
            return range1;
        } else if ((range.from >= from) && (range.to <= to)) {
            Range[] range1 = new Range[1];
            range1[0] = new Range(from, to);
            return range1;
        } else if ((from >= range.from) && (to <= range.to)) {
            Range[] range1 = new Range[1];
            range1[0] = new Range(range.from, range.to);
            return range1;
        } else if ((from < range.from) && (range.from <= to)) {
            Range[] range1 = new Range[1];
            range1[0] = new Range(from, range.to);
            return range1;
        } else {
            Range[] range1 = new Range[1];
            range1[0] = new Range(range.from, to);
            return range1;
        }
    }

    public Range[] getDifferenceRange(Range range) {
        if (((from < range.from) && (to < range.from)) || ((range.from < from) && (range.to < from))) {
            Range[] range1 = new Range[2];
            range1[0] = new Range(from, to);
            range1[1] = new Range(range.from, range.to);
            return range1;
        } else if ((range.from < from) && (range.to > to)) {
            Range[] range1 = new Range[2];
            range1[0] = new Range(range.from, from);
            range1[1] = new Range(to, range.to);
            return range1;
        } else if (((from < range.from) && (to > range.to))) {
            Range[] range1 = new Range[2];
            range1[0] = new Range(from, range.from);
            range1[1] = new Range(range.to, to);
            return range1;
        } else if ((from == range.from) && ((to > range.to) || (range.to > to))) {
            Range[] range1 = new Range[1];
            if (to > range.to) {
                range1[0] = new Range(range.to, to);
            } else {
                range1[0] = new Range(to, range.to);
            }
            return range1;
        } else if (((from > range.from) || (from < range.from)) && (range.to == to)) {
            Range[] range1 = new Range[1];
            if (from < range.from) {
                range1[0] = new Range(from, range.from);
            } else {
                range1[0] = new Range(range.from, from);
            }
            return range1;
        } else if (((from < range.from) && (to > range.from))) {
            Range[] range1 = new Range[2];
            range1[0] = new Range(from, range.from);
            range1[1] = new Range(to, range.to);
            return range1;
        } else if ((range.from < from) && (range.to < to)) {
            Range[] range1 = new Range[2];
            range1[0] = new Range(range.from, from);
            range1[1] = new Range(range.to, to);
            return range1;
        } else {
            Range[] range1 = new Range[1];
            range1[0] = null;
            return range1;
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