package org.example.student_db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.util.HashMap
import java.util.LinkedList

object DatabaseConnection {
    private var connection: Connection? = null

    fun createConnection() {
        val url = "jdbc:postgresql://localhost:5432/pattern_labs"
        val user = "postgres"
        val password = "3002"
        this.connection = null
        try {
            this.connection = DriverManager.getConnection(url, user, password)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun getConnection() = this.connection

    fun executeSqlSelect(query: String): ResultSet? {
        val res: LinkedList<HashMap<String, Any>>
        try {
            val connection = getConnection()
            val statement = connection?.createStatement()
            if (statement != null) {
                return statement.executeQuery(query)
            }
        } catch (e: java.lang.Exception) {
            println(e.message)
        }
        return null
    }

    fun executeSql(query: String) {
        try {
            val connection = getConnection()
            val affectedRows = connection?.prepareStatement(query)?.executeUpdate()
        } catch (e: java.lang.Exception) {
            println(e)
        }
    }
}
