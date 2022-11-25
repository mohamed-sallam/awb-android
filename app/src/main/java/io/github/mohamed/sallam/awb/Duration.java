package io.github.mohamed.sallam.awb;

import androidx.annotation.NonNull;

import java.sql.Timestamp;

/**
 * Duration Rust-like enum.
 *
 * @author Mohamed Sallam
 */
public interface Duration {
    <R> R accept(DurationVisitor<R> visitor);

    class UNTIL_DATE implements Duration {
        public final Timestamp date;

        public UNTIL_DATE(Timestamp date) {
            this.date = date;
        }

        @Override
        public <R> R accept(DurationVisitor<R> visitor) {
            return visitor.visit(this);
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
        public <R> R accept(DurationVisitor<R> visitor) {
            return visitor.visit(this);
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
        public <R> R accept(DurationVisitor<R> visitor) {
            return visitor.visit(this);
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

    interface DurationVisitor<R> {
        public R visit(FOREVER duration);
        public R visit(NUMBER_OF_TIMES duration);
        public R visit(UNTIL_DATE duration);
    }
}

