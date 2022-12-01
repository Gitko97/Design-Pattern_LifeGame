package com.holub.life.factory;

import com.holub.life.*;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class GameCell {

    public Cell outermostCell;
    protected static final int DEFAULT_GRID_SIZE = 8;
    Rule stayLiveRule;
    Rule reLiveRule;

    public Cell getCurrentOuterMostCell() {
        return outermostCell;
    }
}
