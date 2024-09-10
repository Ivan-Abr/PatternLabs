package ru.kubsu
import ru.kubsu.lr_1.Student

fun main() {
    val student1 = Student(
        1,
        "Vasiliy",
        "Zubko",
        "Petrovich",
        "+7(987)568-71-23",
        "@vzubko",
        "zub@.mail.ru")

    val student2 = Student(
        2,
        "Maria",
        "Konovalova",
        "Vitalievna",
        "+7(962)454-32-44",
        "@masha",
        "konovalov@yandex.ru"
    )
    println(student1.toString())
    println(student2.toString())
}