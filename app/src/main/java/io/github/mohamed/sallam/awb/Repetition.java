package io.github.mohamed.sallam.awb;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Repetition Rust-like enum.
 * ```rust
 * enum Repetition {
 *     NONE,
 *     EVERY_DAY{numberOfDays: int},
 *     EVERY_WEEK{numberOfWeeks: int, days: ArrayList<Day>}
 * }
 * ```
 * Each option has `toString()` method that return stringified JSON object.
 * It's used as follow:-
 * ```java
 * repetition.match(new Repetition.RepetitionMatcher<Void> {
 *   @Override
 *   public Void when(Repetition.NONE repetition) {
 *      // Some code in case of `NONE`
 *   }
 *
 *   @Override
 *   public Void when(Repetition.EVERY_DAY repetition) {
 *      // Some code in case of EVERY_DAY, you can use `repetition.numberOfDays`
 *   }
 *
 *   @Override
 *   public Void when(Repetition.EVERY_WEEK repetition) {
 *     // Some code in case of EVERY_WEEK, you can use `repetition.numberOfWeeks` or `repetition.days`
 *   }
 * });
 * ```
 *
 *  @see <a href =”
 *  https://stackoverflow.com/questions/27603813/convert-enum-type-from-rust-to-java/27604405#27604405
 *  ”>Convert enum type from Rust to Java</a>
 *
 * @author Mohamed Sallam
 */
public interface Repetition {
    <R> R match(RepetitionMatcher<R> matcher);

    class NONE implements Repetition {
        @Override
        public <R> R match(RepetitionMatcher<R> matcher) {
            return matcher.when(this);
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

    class EVERY_DAY implements Repetition {
        public final int numberOfDays;

        public EVERY_DAY(int numberOfDays) {
            this.numberOfDays = numberOfDays;
        }

        @Override
        public <R> R match(RepetitionMatcher<R> matcher) {
            return matcher.when(this);
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
        public <R> R match(RepetitionMatcher<R> matcher) {
            return matcher.when(this);
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

    interface RepetitionMatcher<R> {
        public R when(EVERY_DAY repetition);
        public R when(EVERY_WEEK repetition);
        public R when(NONE repetition);
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