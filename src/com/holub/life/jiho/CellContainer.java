package com.holub.life.jiho;
import com.holub.life.Cell;
import com.holub.life.Storable;
import java.util.ArrayList;
import java.util.List;

public class CellContainer {

    private static CellContainer instance = new CellContainer();

    private List<Storable> cellList;

    private int currentCount;

    private CellContainer(){
        cellList = new ArrayList();
        currentCount = 0;
    }

    public static CellContainer getInstance(){
        return instance;
    }

    public Storable getPrevious(){
        currentCount-=1;
        if(currentCount<0){
            currentCount = 0;
            return null;
        }
        return cellList.get(currentCount);
    }

    public void storeCurrent(Cell cell){
        Storable newCell = cell.createMemento();
        if(cellList.size()>currentCount){
            cellList.set(currentCount,newCell);
        }
        else{
            cellList.add(newCell);
        }
        currentCount+=1;
    }

    public int getCurrentCount(){
        return currentCount;
    }

    public void clear(){
        currentCount = 0;
        cellList = new ArrayList();
    }
}
