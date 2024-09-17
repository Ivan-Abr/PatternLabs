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
        "zub@.mail.ru",
        "github.com/zuba"
        )

    val student2 = Student(
        2,
        "Maria",
        "Konovalova",
        "Vitalievna",
        "+7(962)454-32-44",
        "@masha",
        "konovalov@yandex.ru",
        "github.com/konoval"
    )

    val studentHash : Map<String, Any> = mapOf(
        "id" to 1,
        "firstName" to "Zamir",
        "lastName" to "Zarusov",
        "patronymic" to "Zovovich",
        "telephone" to " +7(800)302-04-56",
        "telegram" to "@goyda",
        "mail" to "prizivud@vk.com",
        "git" to "github.com/zarusov"

    )

    print(Student(studentHash))
    println(student1.toString())
    println(student2.toString())
}