package org.example

import java.sql.Connection
import java.sql.DriverManager

fun main() {
    val databaseURL = "jdbc:postgresql://localhost:5432/pattern_labs"
    val databaseUser = "postgres"
    val databasePassword = "3002"
    Class.forName("org.postgresql.Driver")
    val connection: Connection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword)
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("SELECT * FROM student")
    while (resultSet.next())
    {
        val firstNameValue = resultSet.getString("firstname")
        val lastNameValue = resultSet.getString("lastname")
        println("$firstNameValue $lastNameValue")
    }
}