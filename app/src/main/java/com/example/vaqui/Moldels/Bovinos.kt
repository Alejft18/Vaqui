package com.example.vaqui.Moldels
import java.io.Serializable
import java.time.LocalDate
import java.util.Date

class Bovinos : Serializable {
    var BovinoID : Int = 0
    var Raza : String = String()
    var Genero : String = String()
    var Fecha_Nacimiento : LocalDate()
}