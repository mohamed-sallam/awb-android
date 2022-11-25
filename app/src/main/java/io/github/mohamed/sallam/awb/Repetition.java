package io.github.mohamed.sallam.awb;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Repetition Rust-like enum.
 *
 * @author Mohamed Sallam
 */
public interface Repetition {
    <R> R accept(RepetitionVisitor<R> visitor);

    class EVERY_DAY implements Repetition {
        public final int numberOfDays;

        public EVERY_DAY(int numberOfDays) {
            this.numberOfDays = numberOfDays;
        }

        @Override
        public <R> R accept(RepetitionVisitor<R> visitor) {
            return visitor.visit(this);
        }

        @NonNull
        @Override
        public String toString() {
            return "\"repetition\": {\n" +
                    "\t\"type\":" + "\"EVERY_DAY\"," +
                    "\t\"body\": {" +
                    "\t\t\"numberOfDays\":" + numberOfDays +
                    "\t}\n}";
        }
    }

    class EVERY_WEEK implements Repetition {
        public final int numberOfWeeks;
        public final ArrayList<Day> days;

        public EVERY_WEEK(int numberOfWeeks, ArrayList<Day> days) {
            this.numberOfWeeks = numberOfWeeks;
            this.days = days;
        }

        @Override
        public <R> R accept(RepetitionVisitor<R> visitor) {
            return visitor.visit(this);
        }

        @NonNull
        @Override
        public String toString() {
            return "\"repetition\": {\n" +
                    "\t\"type\":" + "\"EVERY_WEEK\"," +
                    "\t\"body\": {" +
                    "\t\t\"numberOfDays\":" + numberOfWeeks +
                    "\t\t\"days\":" + days +
                    "\t}\n}";
        }
    }

    class NONE implements Repetition {
        @Override
        public <R> R accept(RepetitionVisitor<R> visitor) {
            return visitor.visit(this);
        }

        @NonNull
        @Override
        public String toString() {
            return "\"repetition\": {\n" +
                    "\t\"type\":" + "\"NONE\"," +
                    "\t\"body\": {" +
                    "\t}\n}";
        }

    }

    interface RepetitionVisitor<R> {
        public R visit(EVERY_DAY repetition);
        public R visit(EVERY_WEEK repetition);
        public R visit(NONE repetition);
    }

    enum Day {
        SATURDAY(1),
        SUNDAY(2),
        MONDAY(3),
        TUESDAY(4),
        WEDNESDAY(5),
        THURSDAY(6),
        FRIDAY(7);

        private final int dayNum;

        Day(int dayNum) {
            this.dayNum = dayNum;
        }

        @NonNull
        @Override
        public String toString() {
            return Integer.toString(dayNum);
        }
    }
}