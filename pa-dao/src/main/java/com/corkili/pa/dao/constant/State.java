package com.corkili.pa.dao.constant;

import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.dao.exception.UnsupportedStateException;

public enum State {
    ON_CREATE(0),
    ON_ACTIVE(1),
    ON_READ_ONLY(2),
    ON_DELETE(3),
    ON_LOCK(4);

    protected int state;

    State(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public static State get(int state) {
        for (State value : State.values()) {
            if (value.state == state) {
                return value;
            }
        }
        throw new UnsupportedStateException(IUtils.format("{} is unsupported."));
    }
}
