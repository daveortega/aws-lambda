/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aws.lambda;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws.lambda.model.RequestData;
import com.aws.lambda.model.ResponseData;

/**
 *
 * @author David Ortega
 * This code was based on https://github.com/AshokKumarAlapati/AWS-Lambda-java
 */
public class DatabaseImporter implements RequestHandler<RequestData, ResponseData> {

    @Override
    public ResponseData handleRequest(RequestData requestData, Context cntxt) {

        // TODO Auto-generated method stub
        ResponseData responseDetails = new ResponseData();
        try {
            insertDetails(requestData, responseDetails);
        } catch (SQLException sqlException) {
            responseDetails.setSuccess(false);
            responseDetails.setMessage("Unable to Import " + sqlException);
        }
        return responseDetails;
    }

    private void insertDetails(RequestData requestData, ResponseData responseData) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = getquery(requestData);
        int responseCode = statement.executeUpdate(query);
    }

    private String getquery(RequestData requestData) {
        String query = "INSERT INTO ImportDB.LogRecord (Type, Message, Key1, Key2) VALUES (";
        if (requestData != null) {
            query = query.concat("'" + requestData.getType() + "','"
                    + requestData.getMessage() + "','"
                    + requestData.getKey1() + "','"
                    + requestData.getKey2() + "')");
        }
        //System.out.println("the query is " + query);
        return query;
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://importdb.cz8wmibkw3t9.ap-southeast-2.rds.amazonaws.com:3306";
        String username = "admin";
        String password = "someveryimportantsecret";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

}
