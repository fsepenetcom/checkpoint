import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDeviceDao {
    public static void main(String[] args) throws SQLException {
        DeviceDao deviceDao = new DeviceDao();
        List<Device> lista = new ArrayList<>();

            lista = deviceDao.getAllDeviceWIthHearthRateAndGps("Paolo");
            lista.stream().forEach(s ->{
                        System.out.println("----------------------------------------------------------------");
                        System.out.println(s.getDeviceHasGps());
                        System.out.println(s.getDeviceManufacturer().toLowerCase());
                        System.out.println(s.getDeviceModel().toUpperCase());
                        System.out.println(s.getDevicePartNumber());
                        System.out.println(s.getDeviceHasHeartRate());
                        System.out.println(s.getUsername());
            }
                    );


    }
}
