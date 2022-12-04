package com.holub.life.Container;


import com.holub.life.Cell;
import com.holub.life.Neighborhood;
import com.holub.life.Resident;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellContainerTest {

    private static final int DEFAULT_GRID_SIZE = 8;

    //Case 1: 아무것도 없는데 이전 것 가져오기
    @Test
    void testGetPreviousMethod1(){

        CellContainer cellContainer = CellContainer.getInstance();
        assertEquals(cellContainer.getPrevious(),null);
        assertEquals(cellContainer.getCurrentCount(),0);

        //Case 2:
        Cell testCell = new Neighborhood
            (DEFAULT_GRID_SIZE,
                new Neighborhood
                    (DEFAULT_GRID_SIZE,
                        new Resident(null, null)
                    )
            );
    }

    //Case 2: 여러개 이전 Cell 불러오기
    @Test
    void testGetPreviousMethod2(){
        CellContainer cellContainer = CellContainer.getInstance();

        Cell testCell1 = new Neighborhood
            (DEFAULT_GRID_SIZE,
                new Neighborhood
                    (DEFAULT_GRID_SIZE,
                        new Resident(null, null)
                    )
            );
        Cell testCell2 = new Neighborhood
            (DEFAULT_GRID_SIZE,
                new Neighborhood
                    (DEFAULT_GRID_SIZE,
                        new Resident(null, null)
                    )
            );

        cellContainer.storeCurrent(testCell1);
        cellContainer.storeCurrent(testCell2);

        cellContainer.getPrevious();
        assertEquals(cellContainer.getCurrentCount(),1);

        cellContainer.getPrevious();
        assertEquals(cellContainer.getCurrentCount(),0);


    }
    
    //case 3: 앞으로가고 뒤로가기 여러번
    @Test
    void testStoreCurrentMethod(){
        CellContainer cellContainer = CellContainer.getInstance();

        Cell testCell = new Neighborhood
            (DEFAULT_GRID_SIZE,
                new Neighborhood
                    (DEFAULT_GRID_SIZE,
                        new Resident(null, null)
                    )
            );

        for(int i = 0; i < 10; i++){
            cellContainer.storeCurrent(testCell);
            assertEquals(cellContainer.getCurrentCount(),i+1);
        }

        for(int i = 9; i > -1; i--){
            cellContainer.getPrevious();
            assertEquals(cellContainer.getCurrentCount(),i);
        }

    }
}