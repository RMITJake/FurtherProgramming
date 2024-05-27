package src.handlers;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
// java utils
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// Local imports
import src.models.*;
import src.daos.*;
import src.handlers.DebugHandler;

public class BackupHandler{
  private static String dataBackup = "transactiondata.lmvm";
  private static VenueDao venueDao = new VenueDaoImpl();
  private static EventDao eventDao = new EventDaoImpl();
  private static SuitableForDao suitableForDao = new SuitableForDaoImpl();
  private static BookingDao bookingDao = new BookingDaoImpl();
  private static UserDao userDao = new UserDaoImpl();

  public static void exportBackup() throws FileNotFoundException, IOException, SQLException{
    ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(dataBackup));
    List<Venue> venueList = venueDao.readVenuesTable();
    List<Event> eventList = eventDao.readEventsTable();
    List<SuitableFor> suitableForList = suitableForDao.readSuitableForTable();
    List<ShortBooking> bookingList = bookingDao.readBookingsTable();
    List<User> userList = userDao.readUserTable();
    outStream.writeObject(venueList);
    outStream.writeObject(eventList);
    outStream.writeObject(suitableForList);
    outStream.writeObject(bookingList);
    outStream.writeObject(userList);
    outStream.close();
  }

  public static void importBackup() throws FileNotFoundException, IOException, ClassNotFoundException{
    ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(dataBackup));
    List<Venue> venueList = (ArrayList<Venue>) inStream.readObject();
    List<Event> eventList = (ArrayList<Event>) inStream.readObject();
    List<SuitableFor> suitableForList = (ArrayList<SuitableFor>) inStream.readObject();
    List<ShortBooking> bookingList = (ArrayList<ShortBooking>) inStream.readObject();
    List<User> userList = (ArrayList<User>) inStream.readObject();
    inStream.close();
    DebugHandler.PRINT("PRINTING IMPORT");
    System.out.println(venueList);
    System.out.println(eventList);
    System.out.println(suitableForList);
    System.out.println(bookingList);
    System.out.println(userList);
    DebugHandler.PRINT("IMPORT PRINTED");
  }
}
