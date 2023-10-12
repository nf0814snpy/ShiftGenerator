package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShiftGeneratorTest {

    private ShiftGenerator generator;

    @BeforeEach
    void runBefore(){
        generator = new ShiftGenerator();
    }

    @Test
    void testCheckBeyondTime(){
        AvailableDay ava1;
        ava1 = new AvailableDay("Sunday",12.0,17.0);
        assertTrue(generator.checkBeyondTime(ava1,13.0,15.0));
    }

}
