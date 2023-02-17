import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceDao {


    public List<Device> getAllDeviceWIthHearthRateAndGps(String username) throws SQLException {
        List<Device> result = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection("ldbc:mysql:localhost:3306/mydb","root","root");
        PreparedStatement pr = connection.prepareStatement("SELECT d.*,u.username FROM `device` AD d" +
                        "LEFT JOIN `user` AS u" +
                        "ON d.username = u.username" +
                        "WHERE d.username = ? AND (NOT ((`DEVICE_HAS_GPS` = 0) OR (`DEVICE_HAS_HEARTRATE` = 0))); ");
        ){
            pr.setString(1,username);
            ResultSet resulSet = pr.getResultSet();
            while(resulSet.next()){
            Device device;
            device = new Device((resulSet.getString("DEVICE_PART_NUMBER")),resulSet.getString("DEVICE_MANUFACTURER"),
                    resulSet.getString("DEVICE_MODEL"),resulSet.getShort("DEVICE_HAS_GPS"),resulSet.getShort("DEVICE_HAS_HEARTRATE"),
                    resulSet.getString("username"));
            result.add(device);
        }
        resulSet.close();
        }
        return result;
    }

}
