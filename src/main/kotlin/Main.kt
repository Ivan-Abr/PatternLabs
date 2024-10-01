package ru.kubsu
import ru.kubsu.lr_1.Student

fun main() {
    val st1 = Student.fromStringToStudent("Test; Stringov; Stringovovich; 89604897189; @gelebe; test@mail.com; https://github.com/user/repo")
    println(st1.toString())
}