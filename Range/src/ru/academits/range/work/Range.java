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
        //пересечения нет
        if ((from < range.from) && (to < range.from)) {
            return new Range[]{new Range(from, to)};
        } else if ((range.from < from) && (range.to < from)) {
            return new Range[]{new Range(from, to)};
            //диапазон А входит в диапазон Б
        } else if ((range.from < from) && (range.to > to)) {
            return new Range[]{};
            //диапазон Б входит в диапазон А
        } else if (((from < range.from) && (to > range.to))) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
            //конечный элемент диапазона А равен начальному элементку дипазона Б
        } else if (to == range.from) {
            return new Range[]{new Range(from, to)};
            //конечный элемент диапазона Б равен начальному элементу диапазона А
        } else if (range.to == from) {
            return new Range[]{new Range(from, to)};
            //начальные элементы диапазонов равны между собой
        } else if (from == range.from) {
            if (to > range.to) {
                return new Range[]{new Range(range.to, to)};
            } else {
                return new Range[]{};
            }
            //конечные элементы диапазонов равны между собой
        } else if (to == range.to) {
            if (from < range.from) {
                return new Range[]{new Range(from, range.from)};
            } else {
                return new Range[]{};
            }
            //конечный элемент диапазона А больше начального элемента диапазона Б
        } else if (((from < range.from) && (to > range.from))) {
            return new Range[]{new Range(from, range.from)};
            //Конечный элемент диапазона Б больше начального элемента диапазона А
        } else {
            return new Range[]{new Range(range.to, to)};
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