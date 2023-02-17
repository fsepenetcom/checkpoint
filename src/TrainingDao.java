import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainingDao {

    TrainingSessionWithData getTraningSessionDataByTrainingId(Integer trainingId) throws SQLException {
        TrainingSessionWithData result = new TrainingSessionWithData();
        List<TrainingSessionData> t = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("ldbc:mysql:localhost:3306/mydb", "root", "root");
             PreparedStatement pr = connection.prepareStatement("SELECT T.*,TS.* FROM `training_session` AS T" +
                     "LEFT JOIN `training_session_data` AS TS " +
                     "ON `T.ID_TRAINING_SESSION ` = `TA.TRAINING_SESSION`" +
                     "WHERE `T.ID_TRAINING_SESSION ` = ?"
             )) {
            pr.setInt(1, trainingId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                TrainingSessionData temp = new TrainingSessionData();
                temp.setTrainingSession(rs.getInt("ID_TRAINING_SESSION"));
                temp.setLatitude(rs.getDouble("LATITUDE"));
                temp.setLongitude(rs.getDouble("LONGITUDE"));
                temp.setHeartRate(rs.getDouble("HEART_RATE"));
                temp.setIdTrainingSessionDate(rs.getLong("TRAINING_SESSION"));

                result = new TrainingSessionWithData(t, rs.getInt("ID_TRAINING_SESSION"),
                        rs.getDate("START_TIME"),
                        rs.getDate("END_TIME"),
                        rs.getString("USER_USERNAME"));
            }

        }
        return result;
    }
}
