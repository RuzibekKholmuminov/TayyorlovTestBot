package db;

import org.example.Word;

import java.sql.*;

public class DataBase {
    public static void create_profile_table() {
        try {
            String profile_table = "create table if not exists profile(" +
                    "               id serial primary key," +
                    "               profile_id numeric not null," +
                    "               name varchar(20) not null," +
                    "               surname varchar(25), " +
                    "               phone varchar(13), " +
                    "               date timestamp ," +
                    "               status text not null" +
                    ");";

            Connection con = getConnection();
            Statement statement = con.createStatement();
            int effectRows = statement.executeUpdate(profile_table);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test",
                    "user_123", "user_123");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
            System.exit(-1);
        }
        return null;

    }

    public static void initTable() {

        String word = "create table if not exists word ( \n" +
                "             id serial primary key,\n" +
                "             origin text ,\n" +
                "             translator text, \n" +
                "             translator_id serial)  ";

        execute(word);

    }

    private static void execute(String sql) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getOrigin(int number) {
        Connection connection = DataBase.getConnection();
        Word word = new Word();
        try {
            PreparedStatement statement = connection.prepareStatement("select origin from word where translator_id=?");
            statement.setInt(1, number);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                word.setOrigin(resultSet.getString("origin"));
                System.out.println(word.getOrigin());
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return word.getOrigin();
    }

    public static String getTranslator(int number) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from word where id=?");
            statement.setInt(1, number);

            ResultSet resultSet = statement.executeQuery();

            Word word = new Word();
            while (resultSet.next()) {

                word.setTranslator(resultSet.getString("translator"));
                System.out.println(word.getTranslator());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return "";
    }


}
