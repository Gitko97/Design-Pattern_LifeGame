package com.holub.life.factory;


import com.holub.life.DefaultRule;
import com.holub.life.Neighborhood;
import com.holub.life.Resident;
import com.holub.life.Rule;

import java.util.ArrayList;
import java.util.Arrays;

public class OriginalCell extends GameCell {


    public OriginalCell() {
        stayLiveRule = new DefaultRule(new ArrayList<Integer>(Arrays.asList(2, 3)));
        reLiveRule = new DefaultRule(new ArrayList<Integer>(Arrays.asList(3)));
        outermostCell = new Neighborhood
            (DEFAULT_GRID_SIZE,
                new Neighborhood
                    (DEFAULT_GRID_SIZE,
                        new Resident(stayLiveRule, reLiveRule)
                    )
            );
    }

}
