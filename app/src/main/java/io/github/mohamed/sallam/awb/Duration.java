package io.github.mohamed.sallam.awb;

import androidx.annotation.NonNull;

import java.sql.Timestamp;

/**
 * Duration Rust-like enum.
 * ```rust
 * enum Duration {
 *     FOREVER,
 *     NUMBER_OF_TIMES{numberOfTimes: int},
 *     UNTIL_DATE{date: Timestamp}
 * }
 * ```
 * Each option has `toString()` method that return stringified JSON object.
 * It's used as follow:-
 * ```java
 * duration.match(new Duration.DurationMatcher<Void> {
 *   @Override
 *   public Void when(Repetition.FOREVER duration) {
 *      // Some code in case of `FOREVER`
 *   }
 *
 *   @Override
 *   public Void when(Repetition.NUMBER_OF_TIMES duration) {
 *      // Some code in case of NUMBER_OF_TIMES, you can use `duration.numberOfDays`
 *   }
 *
 *   @Override
 *   public Void when(Repetition.UNTIL_DATE duration) {
 *     // Some code in case of UNTIL_DATE, you can use `duration.date`
 *   }
 * });
 * ```
 * Source: stackoverflow.com/a/27604405
 * @author Mohamed Sallam
 */
public interface Duration {
    <R> R match(DurationMatcher<R> matcher);

    class UNTIL_DATE implements Duration {
        public final Timestamp date;

        public UNTIL_DATE(Timestamp date) {
            this.date = date;
        }

        @Override
        public <R> R match(DurationMatcher<R> matcher) {
            return matcher.when(this);
        }

        @NonNull
        @Override
        public String toString() {
            return "\"duration\": {\n" +
                    "\t\"type\":" + "\"UNTIL_DATE\"," +
                    "\t\"body\": {" +
                    "\t\t\"date\":" + date.getTime() +
                    "\t}\n}";
        }
    }

    class NUMBER_OF_TIMES implements Duration {
        public final int numberOfTimes;

        public NUMBER_OF_TIMES(int numberOfTimes) {
            this.numberOfTimes = numberOfTimes;
        }

        @Override
        public <R> R match(DurationMatcher<R> matcher) {
            return matcher.when(this);
        }

        @NonNull
        @Override
        public String toString() {
            return "\"duration\": {\n" +
                    "\t\"type\":" + "\"NUMBER_OF_TIMES\"," +
                    "\t\"body\": {" +
                    "\t\t\"numberOfTimes\":" + numberOfTimes +
                    "\t}\n}";
        }
    }

    class FOREVER implements Duration {
        @Override
        public <R> R match(DurationMatcher<R> matcher) {
            return matcher.when(this);
        }

        @NonNull
        @Override
        public String toString() {
            return "\"duration\": {\n" +
                    "\t\"type\":" + "\"FOREVER\"," +
                    "\t\"body\": {" +
                    "\t}\n}";
        }
    }

    interface DurationMatcher<R> {
        public R when(FOREVER duration);
        public R when(NUMBER_OF_TIMES duration);
        public R when(UNTIL_DATE duration);
    }
}

