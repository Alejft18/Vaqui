package com.example.vaqui.Moldels

import java.io.Serializable

class Lecheras : Serializable {
    var lecheraID : Int = 0
    var litros_producidos : Double = 0.0
    var fecha_ordeño : String = String()
    var pesoLecheras : Double = 0.0
    var FRevision_lechera : String = String()
    var FParto_lechera : String = String()
    var CPartos_lecheras : Int = 0
    var categoriaLechera : String = String()
}