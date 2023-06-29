package com.example.vaqui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController


class GestionEmpleadosFragment : Fragment() {
    private lateinit var card_ingresar_empleado : CardView
    private lateinit var card_buscar_emple : CardView
    private lateinit var card_actu_empleado : CardView
    private lateinit var card_eliminar_empleado : CardView
    private lateinit var imagen_atras : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll =  inflater.inflate(R.layout.fragment_gestion_empleados, container, false)
        this.imagen_atras = ll.findViewById(R.id.imagen_atras_gestion_empleados)
        this.card_buscar_emple = ll.findViewById(R.id.card_buscar_emple)
        this.card_ingresar_empleado = ll.findViewById(R.id.card_ingresar_empleado)
        this.card_actu_empleado = ll.findViewById(R.id.card_actu_empleado)
        this.card_eliminar_empleado = ll.findViewById(R.id.card_eliminar_empleado)

        imagen_atras.setOnClickListener {
            findNavController().navigate(R.id.action_gestionEmpleadosFragment_to_inicio)
        }

        card_buscar_emple.setOnClickListener {
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.action_gestionEmpleadosFragment_to_recyclerView_EmpleadosFragment)
        }

        card_ingresar_empleado.setOnClickListener {
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.action_gestionEmpleadosFragment_to_formularioEmpleadoFragment)
        }

        card_actu_empleado.setOnClickListener{
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("¿Quieres actualizar informacion de un empleado?")
            builder.setMessage("Si deseas actualizar la informacion de uno de tus empleados sigue los siguientes pasos: \n1. Dirijete a gestion de empleados. \n2. Busca al empleado. \n3. Dale al boton de actualizar y actualiza su informacion. \nAl terminar guarda y listo.")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        card_eliminar_empleado.setOnClickListener{
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("¿Quieres eliminar a un empleado?")
            builder.setMessage("Si deseas eliminar a uno de tus empleados sigue los siguientes pasos: \n1. Dirijete a gestion de empleados. \n2. Busca al empleado. \n3. Dale al boton de eliminar y acepta la comprobación.")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }


        return ll
    }

}