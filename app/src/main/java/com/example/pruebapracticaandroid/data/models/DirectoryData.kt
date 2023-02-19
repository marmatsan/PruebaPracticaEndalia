package com.example.pruebapracticaandroid.data.models

import androidx.compose.ui.res.painterResource
import com.example.pruebapracticaandroid.R
import java.text.Normalizer
import kotlin.random.Random

object DirectoryData {

    private val names = listOf(
        "Juan",
        "María",
        "Carlos",
        "Ana",
        "Miguel",
        "Laura",
        "José",
        "Isabel",
        "Francisco",
        "Carmen",
        "Antonio",
        "Marta",
        "David",
        "Lucía",
        "Luis",
        "Patricia",
        "Pedro",
        "Sofía",
        "Gabriel",
        "Elena",
        "Diego",
        "Beatriz",
        "Andrés",
        "Claudia",
        "Raúl",
        "Andrea",
        "Javier",
        "Martina",
        "Daniel",
        "Sara",
        "Pablo",
        "Natalia",
        "Fernando",
        "Julia",
        "Mario",
        "Adriana",
        "Sergio",
        "Paula",
        "Víctor",
        "Ángela",
        "Jorge",
        "Ana Belén",
        "Guillermo",
        "Sandra",
        "Esteban",
        "Belén",
        "Iván",
        "Lorena",
        "Rubén",
        "Cristina",
        "Ricardo",
        "Ainhoa",
        "Jaime",
        "Nuria",
        "Emilio",
        "María José",
        "Javier",
        "Noelia",
        "Ignacio",
        "Rosa",
        "Juan Carlos",
        "Miriam",
        "Alberto",
        "Patricia",
        "Israel",
        "Lorena",
        "Óscar",
        "Elena",
        "Rodrigo",
        "Natalia",
        "Gonzalo",
        "Ana Isabel",
        "Arturo",
        "Carmen María",
        "Ernesto",
        "Laura María",
        "Óscar",
        "Silvia",
        "Tomás",
        "Marina",
        "César",
        "Julia María",
        "Manuel",
        "Alba",
        "Marcos",
        "Marta María",
        "Adolfo",
        "Sofía María",
        "Joaquín",
        "Lucía María",
        "Tomás",
        "Carmen Isabel",
        "Miguel Ángel",
        "Sara María",
        "Santiago",
        "María Teresa",
        "Pedro José",
        "Ana María",
        "Hugo",
        "Elena María"
    )

    private val surnames = listOf(
        "García Pérez",
        "González Rodríguez",
        "Rodríguez García",
        "Fernández Gómez",
        "López Martínez",
        "Pérez Sánchez",
        "Martínez González",
        "Sánchez Romero",
        "Gómez Ruiz",
        "Martín Serrano",
        "Jiménez Flores",
        "Ruiz López",
        "Hernández Torres",
        "Díaz Sánchez",
        "Moreno Álvarez",
        "Álvarez Gutiérrez",
        "Muñoz Romero",
        "Romero Moreno",
        "Gutiérrez García",
        "Navarro Jiménez",
        "Torres Álvarez",
        "Vázquez García",
        "Ramos Sánchez",
        "Ortega Ortiz",
        "Rubio Sánchez",
        "Santiago López",
        "Ortiz Ramos",
        "Molina Gómez",
        "Serrano Romero",
        "Medina Sánchez",
        "Rivas Pérez",
        "Castro Ruiz",
        "Cáceres García",
        "Bravo Torres",
        "Flores López",
        "Vega Muñoz",
        "Aguilar Torres",
        "Luna Sánchez,",
        "Nieto Morales",
        "Delgado Jiménez",
        "Castillo Moreno",
        "Reyes Gutiérrez",
        "Lozano Sánchez",
        "Garrido Ortiz",
        "Carmona Sánchez",
        "Guerrero Romero",
        "Moya García",
        "Cruz Fernández",
        "Fuentes Ortiz",
        "Cortés López",
        "Escudero Sánchez",
        "Roldán Ortiz",
        "Santos García",
        "Aguado Ruiz",
        "Pascual García",
        "Molina Ortiz",
        "Gil Sánchez",
        "Merino García",
        "Cabello López",
        "Hidalgo Pérez",
        "Galán García",
        "Pastor Torres",
        "Román López",
        "Sáez Sánchez",
        "Luque Torres",
        "Salas Jiménez",
        "Gálvez Moreno",
        "Ojeda Ortiz",
        "Blasco Sánchez",
        "Perea García",
        "Soria Torres",
        "Aguilar Moreno",
        "Alcaraz Pérez",
        "Esteban López",
        "Marcos Ruiz",
        "Tejada Ortiz",
        "Montes Sánchez",
        "Núñez Torres",
        "Guerra García",
        "Conde López",
        "Ramos Ortiz",
        "Cazorla Sánchez",
        "Fuentes García",
        "Ortiz Gómez",
        "Soler Torres",
        "Soto Sánchez",
        "Díaz de la Cruz",
        "Alonso Gutiérrez",
        "Blanco García",
        "Caro Sánchez",
        "Cordero Ortiz",
        "Espinosa López",
        "Fernández de la Fuente",
        "Gallego García",
        "Márquez Ortiz",
        "Nieves Sánchez",
        "Palacios García",
        "Pareja López",
        "Pizarro Ortiz",
        "Rojas Sánchez"
    )

    val jobs = listOf(
        "Jefe de Proyectos",
        "Analista de Datos",
        "Diseñador Gráfico",
        "Desarrollador de Software",
        "Especialista en Marketing",
        "Asistente Administrativo",
        "Técnico de RRHH",
        "CEO"
    )

    val listOfEmployees = mutableListOf<Employee>()

    private fun populateData() {
        /*
        En caso de que quisiésemos obtener los datos desde Firebase, podríamos realizarlo de la
        siguiente manera:

        val db = Firebase.firestore

        db.collection("employees")
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
               val employee = document.toObject(Employee::class.java)
               listOfEmployees.add(employee)
            }
        }
        .addOnFailureListener { exception ->
            Toast.makeText(context, "No se han podido obtener los datos", Toast.LENGTH_SHORT).show()
        }
        */
        val shortedSurnames = surnames.sortedBy { it }

        for (number in shortedSurnames.indices) {
            val unnormalizedName = names[number]
            val unnormalizedFirstSurname = shortedSurnames[number].split(" ")[0]
            val regex = Regex("\\p{InCombiningDiacriticalMarks}")

            val name = Normalizer.normalize(unnormalizedName, Normalizer.Form.NFD)
            val firstSurname = Normalizer.normalize(unnormalizedFirstSurname, Normalizer.Form.NFD)

            val normalizedName = name.replace(regex = regex, replacement = "").lowercase()
            val normalizedfirstSurname =
                firstSurname.replace(regex = regex, replacement = "").lowercase()

            listOfEmployees.add(
                Employee(
                    name = names[number],
                    surname = shortedSurnames[number],
                    job = if (number == 99) jobs.last() else jobs[Random.nextInt(7)],
                    phone = Random.nextInt(900000000, 1000000000),
                    mail = normalizedName.plus(".").plus(normalizedfirstSurname).plus("@demo.com"),
                    password = "aA2.bcde",
                    drawableId = when (name) {
                        "Juan" -> R.drawable.abdullah_ali_1w9i6h4aftw_unsplash__1_
                        "Ana" -> R.drawable.art_hauntington_jzy0krjopei_unsplash__1_
                        "Miguel" -> R.drawable.austin_wade_x6uj51n5ce8_unsplash__1_
                        "Laura" -> R.drawable.ayo_ogunseinde_6w4f62sn_yi_unsplash__1_
                        "Francisco" -> R.drawable.christian_buehner_ditylc26zvi_unsplash__1_
                        "Carmen" -> R.drawable.christina_wocintechchat_com_0zx1bdv5bny_unsplash__1_
                        "Marta" -> R.drawable.christopher_campbell_rdeovte7vos_unsplash__1_
                        "Lucía" -> R.drawable.edward_cisneros__h6wpor9mjs_unsplash__1_
                        else -> {
                            Integer.MIN_VALUE
                        }
                    }
                )
            )
        }
    }

    init {
        populateData()
    }

}