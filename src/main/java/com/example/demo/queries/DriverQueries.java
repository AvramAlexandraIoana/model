package com.example.demo.queries;

public class DriverQueries {
    public final static String CREATE_SQL = "INSERT INTO drivers(id, name, email, city) values (?, ?, ?, ?)";
    public final static String GETBYEMAIL_SQL = "SELECT * FROM drivers WHERE email = ?";
    public final static String UPDATE_SQL = "UPDATE drivers SET name = ?, city = ? WHERE id = ?";
    public final static String GETBYID_SQL = "SELECT * FROM drivers WHERE id = ?";




}
