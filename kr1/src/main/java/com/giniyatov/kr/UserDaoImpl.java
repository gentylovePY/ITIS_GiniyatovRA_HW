package com.giniyatov.kr;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.giniyatov.kr.PostgresConnectionUtil.*;


public class UserDaoImpl  {



    public String insert(Member member) {
        loadDriver(DRIVER);
        String result = "successfully";
        String sql = "insert into kr3 values(?,?)";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,member.getEmail());
            preparedStatement.setString(2,member.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            result = "data failed";
            throw new RuntimeException(e);
        }
        return result;

    }




    public boolean validate(UserDto userDto) throws SQLException {
        loadDriver(DRIVER);
        Connection connection = getConnection();
        boolean status = false;
        String sql = "select * from kr3 where email =? and password =?";

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userDto.getUsername());
        preparedStatement.setString(2, userDto.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        status = resultSet.next();



        return status;
    }


}
