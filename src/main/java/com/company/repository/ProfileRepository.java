package com.company.repository;

import com.company.dto.Profile;
import com.company.step.Profile_Step;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class ProfileRepository {

    public Profile getProfile(Long id) {
        Optional<Profile> list = getAll().stream()
                .filter(profile -> profile.getProfile_id().equals(id))
                .findFirst();

        return list.orElse(null);
    }

    private List<Profile> getAll() {

        List<Profile> profileList = new LinkedList<>();

        try {
            Connection con = getConnection();

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from profile");

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Long profile_id = resultSet.getLong("profile_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone = resultSet.getString("phone");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String status = resultSet.getString("status");

                Profile profile = new Profile();
                profile.setId(id);
                profile.setProfile_id(profile_id);
                profile.setName(name);
                profile.setSurname(surname);
                profile.setPhone(phone);
                profile.setDate(date);
                profile.setStep(Profile_Step.valueOf(status));

                profileList.add(profile);

                con.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profileList;

    }

    public void save(Profile profile, Long chatId) {
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();

            String sql = String.format("insert into profile(profile_id, name, surname, phone, date, status) values( '" + chatId + "' , '" + profile.getName() + "','" + profile.getSurname() + "', '" + profile.getPhone() + "', now(), '" + profile.getStep() + "')");
            statement.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateName(Profile profile) {
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();

            String sql = String.format("Update  profile set name =  '" + profile.getName() + "',  status = '" + profile.getStep() + "'  where status = '" + Profile_Step.ENTER_NAME + "'");
            statement.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSurname(Profile profile) {
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();

            String sql = String.format("Update  profile set surname = '" + profile.getSurname() + "', status =  '" + profile.getStep() + "' where status = '" + Profile_Step.ENTER_SURNAME + "'");
            statement.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePhone(Profile profile) {
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();

            String sql = String.format("Update  profile set phone = '" + profile.getPhone() + "', status = '" + profile.getStep() + "'where status = '" + Profile_Step.ENTER_PHONE + "'");
            statement.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test", "user_123", "user_123");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
