package com.holub.life;

import static org.junit.jupiter.api.Assertions.*;

import com.holub.life.Clock.Listener;
import org.junit.jupiter.api.Test;

class TestObject{
    static int num = 0;
    static void test(){
        num+=1;
    }
    static int getNum(){
        return num;
    }
}

class TestObject2{
    static int num = 100;
    static void test(){
        num-=1;
    }
    static int getNum(){
        return num;
    }
}

class ClockTest {

    //Observer 등록되는지 확인
    @Test
    void observerPatternTest(){
        Clock clock = Clock.instance();

        Listener testListener = new Listener() {
            public void tick() {
                TestObject.test();
            }
        };
        Listener testListener2 = new Listener(){
            public void tick() {
                TestObject2.test();
            }
        };

        clock.addClockListener(testListener);
        clock.addClockListener(testListener2);

        for(int i = 1; i <= 100; i++){
            clock.tick();
            assertEquals(TestObject.getNum(),i);
            assertEquals(TestObject2.getNum(),100-i);
        }
    }

}