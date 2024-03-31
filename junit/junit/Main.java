package junit;
import org.junit.*;
import static org.junit.Assert.*;

public class Main{
    public static void main(String[] args){
        CalculatorTest test = new CalculatorTest();
    }
}

class Calculator{
    public boolean isEven(int input){
        if(input % 2 == 0){
            return true;
        }
        return false;
    }
}

class CalculatorTest{
    Calculator calc;

    @BeforeClass
    void setUp(){
        calc = new Calculator();
    }

    @Test
    void testIsEven(){
        assertTrue("4 is even.", calc.isEven(4));
    }
}