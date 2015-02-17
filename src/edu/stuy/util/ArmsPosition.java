package edu.stuy.util;

import edu.stuy.Robot;

/**
 * The position of the Arms goes from release to wide to narrow.
 * If the state is narrow, narrow will be the next position, not release.
 * If the state is release, release will be the previous position, not narrow.
 * @author nicoleshin
 *
 */
public enum ArmsPosition {
    RELEASE {
        @Override
        public void goToPosition() {
            Robot.arms.release();
        }
    }, WIDE {
        @Override
        public void goToPosition() {
            Robot.arms.setWide();
        }
    }, NARROW {
        @Override
        public void goToPosition() {
            Robot.arms.setNarrow();
        }
    };

    public abstract void goToPosition();

    public static ArmsPosition narrowerPosition(ArmsPosition a) {
        switch (a) {
        case RELEASE:
            return WIDE;
        case WIDE:
            return NARROW;
        case NARROW:
            return NARROW;
        default:
            throw new IllegalStateException("Invalid Position of Arms");
        }
    }

    public static ArmsPosition widerPosition(ArmsPosition a) {
        switch (a) {
        case RELEASE:
            return RELEASE;
        case WIDE:
            return RELEASE;
        case NARROW:
            return WIDE;
        default:
            throw new IllegalStateException("Invalid Position of Arms");
        }
    }
}
