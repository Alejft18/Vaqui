package com.example.vaqui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel

class fragment_home_estadisticas_empleado : Fragment() {
    private lateinit var card_gestion_empleados : CardView
    val imageList=ArrayList<SlideModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_home_estadisticas_empleado, container, false)
        this.card_gestion_empleados = ll.findViewById(R.id.card_gestion_empleados)

        val imageSlider = ll.findViewById<ImageSlider>(R.id.image_slider)


        imageList.add(SlideModel("https://definicion.de/wp-content/uploads/2016/05/bovino-1.jpg","vaca 1"))
        imageList.add(SlideModel("https://cdn.shopify.com/s/files/1/0268/6861/files/bulls-219952_960_720_00de38ab-1909-453b-bbc0-090de89dbba4_grande.jpg?v=1559242513","vaca 2"))
        imageList.add(SlideModel("https://t1.ea.ltmcdn.com/es/posts/4/6/2/brucelosis_bovina_sintomas_y_tratamiento_24264_orig.jpg", "vaca 3"))
        imageList.add(SlideModel("https://bmeditores.mx/wp-content/uploads/2021/07/Bienestar-animal-bovinos-lecheros-3.jpg","vaca 4"))

        imageSlider.setImageList(imageList)



        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_gestion_empleados.setOnClickListener{
            findNavController().navigate(R.id.action_inicio_to_gestionEmpleadosFragment)
        }



    }


}