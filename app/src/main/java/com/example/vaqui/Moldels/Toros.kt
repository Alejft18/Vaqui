package com.example.vaqui.Moldels

import java.io.Serializable

class Toros : Serializable {
    var ToroID : Int = 0
    var pesoToro : Double = 0.0
    var fecha_extraccion : String = String()
    var vacas_montadas : Int = 0
    var FRevision_Toro : String = String()
    var categoriaToro : String = String()
}