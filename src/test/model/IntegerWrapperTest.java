package model;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerWrapperTest {

    private IntegerWrapper integer;

    @BeforeEach
    void runBefore(){
        integer = new IntegerWrapper(1);
    }

    @Test
    void testClass(){
        assertEquals(1,integer.getValue());
        integer.setValue(2);
        assertEquals(2,integer.getValue());
    }
}
