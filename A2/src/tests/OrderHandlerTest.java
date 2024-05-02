package src.tests;
import src.handlers.OrderHandler;
import src.models.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderHandlerTest {
    private OrderHandler order;
    private Venue homeVenue;
    private Venue workVenue;
    private Event homeEvent;
    private Event workEvent;
    private Booking homeOrder;
    private Booking workOrder;

    @Before
    public void setUp(){
        order = new OrderHandler();

        homeVenue = new Venue("Home",20, "relaxing; gaming","House",0);
        workVenue = new Venue("Work",40, "troubleshooting; calling","IT",165);

        homeEvent = new Event("Jake@Home", "The Kent's House", "Jake Kent", "01/04/2024", "5:00pm",4,1,"relaxing","house");
        workEvent = new Event("JKentTech", "IT for Everyday", "Tech Lead", "20/02/2025", "8:00am",50,1,"working","IT");

        homeOrder = new Booking(homeEvent, homeVenue);
        workOrder = new Booking(workEvent, workVenue);
    }

    @Test
    public void matchVenueTrueTest(){
        assertTrue(order.matchVenue(homeVenue, homeOrder));
    }

    @Test
    public void matchVenueFailTest(){
        assertFalse(order.matchVenue(homeVenue, workOrder));
    }

    @Test
    public void matchDateTrueTest(){
        assertTrue(order.matchDate(homeEvent, homeOrder));
    }

    @Test
    public void matchDateFailTest(){
        assertFalse(order.matchDate(homeEvent, workOrder));
    }

    @Test
    public void matchTimeTrueTest(){
        assertTrue(order.matchTime(homeEvent, homeOrder));
    }

    @Test
    public void matchTimeFailTest(){
        assertFalse(order.matchTime(homeEvent, workOrder));
    }

    @Test
    public void matchCapacityTrueTest(){
        assertTrue(order.matchCapacity(homeVenue, homeEvent));
    }

    @Test
    public void matchCapacityFailTest(){
        assertFalse(order.matchCapacity(homeVenue, workEvent));
    }

    @Test
    public void matchTypeTrueTest(){
        assertTrue(order.matchType(homeVenue, homeEvent));
    }

    @Test
    public void matchTypeFailTest(){
        assertFalse(order.matchType(homeVenue, workEvent));
    }

    @Test
    public void matchCategoryTrueTest(){
        assertTrue(order.matchCategory(homeVenue, homeEvent));
    }

    @Test
    public void matchCategoryFailTest(){
        assertFalse(order.matchCategory(homeVenue, workEvent));
    }
}
