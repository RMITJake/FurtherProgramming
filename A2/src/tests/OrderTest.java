package src.tests;
import org.junit.*;
import src.models.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrderTest {
    private Event event;
    private Venue venue;
    private Order order;
    private double matchDouble;

///////////
// Setup //
///////////
    @Before()
    public void setUp(){
        // Setup Event in a legible manner
        String client = "Dev Team";
        String title = "Number 1 Test";
        String artist = "Testers";
        String date = "21/04/24";
        String time = "9:20pm";
        int target = 10;
        int duration = 4;
        String type = "Test";
        String category = "Unit Test";
        event = new Event(client, title, artist, date, time, target, duration, type, category);

        // Setup Venue in a legible manner
        String name = "Testing House";
        int capacity = 100;
        String suitableFor = "Test;Unit Test";
        category = "Unit Test";
        int priceperhour = 300;
        venue = new Venue(name, capacity, suitableFor, category, priceperhour);

        order = new Order(event, venue);
    }
// END Setup

///////////////////
// getTotalPrice //
///////////////////
    @Test()
    public void getTotalPriceTrueTest(){
        matchDouble = 1200.00;
        assertTrue(order.getTotalPrice() == matchDouble);
    }

    @Test() // UpperBound False
    public void getTotalPriceUpperBoundFalseTest(){
        matchDouble = 1200.01;
        assertFalse(order.getTotalPrice() == matchDouble);
    }

    @Test() // LowerBound False
    public void getTotalPriceLowerBoundFalseTest(){
        matchDouble = 1199.99;
        assertFalse(order.getTotalPrice() == matchDouble);
    }
// END getTotalPrice

///////////////////////
// calculateDiscount //
///////////////////////
    @Test()
    public void calculateDiscountTrueTest(){
        matchDouble = 12.00;
        assertTrue(order.calculateDiscount() == matchDouble);
    }
    
    @Test() // UpperBound False
    public void calculateDiscountUpperBoundFalseTest(){
        matchDouble = 12.01;
        assertFalse(order.calculateDiscount() == matchDouble);
    }
    
    @Test() // LowerBound False
    public void calculateDiscountLowerBoundFalseTest(){
        matchDouble = 11.99;
        assertFalse(order.calculateDiscount() == matchDouble);
    }
// END calculateDiscount

////////////////////
// calculatePrice //
////////////////////
    @Test()
    public void calculatePriceTrueTest(){
        matchDouble = 1188.00;
        assertTrue(order.calculatePrice() == matchDouble);
    }
    
    @Test() // UpperBound False
    public void calculatePriceUpperBoundFalseTest(){
        matchDouble = 1188.01;
        assertFalse(order.calculatePrice() == matchDouble);
    }
    
    @Test() // LowerBound False
    public void calculatePriceLowerBoundFalseTest(){
        matchDouble = 1187.99;
        assertFalse(order.calculatePrice() == matchDouble);
    }
// END calculatePrice

//////////////////
// getBrokerFee //
//////////////////
    @Test()
    public void getBrokerFeeTrueTest(){
        matchDouble = 118.80;
        assertTrue(order.getBrokerFee() == matchDouble);
    }
    
    @Test() // UpperBound False
    public void getBrokerFeeUpperBoundFalseTest(){
        matchDouble = 120.01;
        assertFalse(order.getBrokerFee() == matchDouble);
    }
    
    @Test() // LowerBound False
    public void getBrokerFeeLowerBoundFalseTest(){
        matchDouble = 119.99;
        assertFalse(order.getBrokerFee() == matchDouble);
    }
}

// END getBrokerFee