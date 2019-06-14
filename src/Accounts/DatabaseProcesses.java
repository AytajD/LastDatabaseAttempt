package Accounts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DatabaseProcesses {

    private Connection connection;

    private String url = "jdbc:mysql://localhost/accounts?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    boolean checkForLogin(String usernameD, String pass) {

        String sql = "select username, password from accounts";

        boolean yesOrNo = false;

        try{
            connection = DriverManager.getConnection(url, "root", "12345");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            boolean exit = true;

            while (resultSet.next()) {
                String returnedUser = resultSet.getString("username");
                String returnedPass = resultSet.getString("password");

                if((returnedUser.equals(usernameD)) && (returnedPass.equals(pass))) {

                    yesOrNo = true;
                    exit = false;

                    break;
                }
            }
            if(exit) {
                System.out.println("Username or Password is not correct");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return yesOrNo;
    }

    void addRecord(String username, String password, String email) {

        String sql = "insert into accounts values (?,?,?)";

        boolean yesOrNo = false;

        try {
            connection = DriverManager.getConnection(url, "root", "12345");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.executeUpdate();

            yesOrNo = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(yesOrNo) System.out.println("Registered");
        else System.out.println("It has been found a problem about registration");
    }

    boolean[] checkForRegistration(String registeredUser,String registeredEmail) {

        boolean[] yesOrNos = new boolean [2];
        List<String> usernames =  new ArrayList<>();
        List<String> emails = new ArrayList<>();

        String sql = "select username, email from accounts.accounts";

        try {
            connection = DriverManager.getConnection(url, "root", "12345");

            Statement statement = connection.createStatement();
            ResultSet resultSet1 = statement.executeQuery(sql);

            while (resultSet1.next()) {
                String checkedUser = resultSet1.getString("username");
                String checkedEmail = resultSet1.getString("email");

                usernames.add(checkedUser);
                emails.add(checkedEmail);
            }

            if(!usernames.contains(registeredUser)) yesOrNos[0] = true;
            if(!emails.contains(registeredEmail)) yesOrNos[1] = true;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return yesOrNos;
    }
}