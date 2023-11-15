package com.davichois.lukeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davichois.lukeapp.adapter.SessionClassAdapter
import com.davichois.lukeapp.databinding.FragmentChooseLearningBinding
import com.davichois.lukeapp.dto.SessionClassDTO

class ChooseLearningFragment : Fragment(R.layout.fragment_choose_learning) {

    private var _binding: FragmentChooseLearningBinding? = null
    private val binding get() = _binding

    private val viewModel: MainViewModel by activityViewModels()

    private val sessions = arrayOf(
        SessionClassDTO(id = "0", typeCourse = "Trigonometria", nameSession = "Razones trigonométricas (seno, coseno y tangente)", explanation = "El seno (sin), coseno (cos) y tangente (tan) son las razones trigonométricas más básicas y se utilizan para relacionar los ángulos en un triángulo rectángulo con las longitudes de sus lados.", exercise = "En un triángulo rectángulo, si el cateto opuesto mide 5 unidades y el cateto adyacente mide 12 unidades, encuentra el valor de sin(θ), cos(θ) y tan(θ) para el ángulo θ entre los catetos."),
        SessionClassDTO(id = "1", typeCourse = "Trigonometria", nameSession = "Teorema de Pitágoras", explanation = "El teorema de Pitágoras establece que en un triángulo rectángulo, el cuadrado de la hipotenusa es igual a la suma de los cuadrados de los catetos.", exercise = "En un triángulo rectángulo, si los catetos miden 3 y 4 unidades, respectivamente, ¿cuál es la longitud de la hipotenusa?"),
        SessionClassDTO(id = "2", typeCourse = "Trigonometria", nameSession = "Identidades trigonométricas", explanation = "Las identidades trigonométricas son ecuaciones que relacionan las funciones trigonométricas. Algunas de las más comunes incluyen las identidades de suma y diferencia.", exercise = "Demuestra la identidad trigonométrica: sin(α + β) = sin(α)cos(β) + cos(α)sin(β)."),
        SessionClassDTO(id = "3", typeCourse = "Trigonometria", nameSession = "Resolución de triángulos.", explanation = "Resolución de triángulos implica encontrar los ángulos y las longitudes de los lados de un triángulo, dados ciertos datos. Esto puede involucrar el uso de razones trigonométricas y teoremas como el teorema del seno y el teorema del coseno.", exercise = "En un triángulo oblicuángulo, si conoces dos lados y el ángulo entre ellos (SAS), encuentra la longitud del tercer lado y los otros dos ángulos."),
        SessionClassDTO(id = "4", typeCourse = "Trigonometria", nameSession = "Gráficos de funciones trigonométricas.", explanation = "Las funciones trigonométricas, como el seno y el coseno, tienen gráficos periódicos. Comprender cómo se ven estos gráficos es esencial para analizar funciones trigonométricas.", exercise = "Dibuja el gráfico de la función y = 2sin(2x) en el intervalo [0, 2π]."),
        SessionClassDTO(id = "5", typeCourse = "Trigonometria", nameSession = "Identidades trigonométricas avanzadas.", explanation = "Además de las identidades básicas, existen identidades avanzadas, como las identidades de ángulo doble y de ángulo mitad, que son útiles para simplificar expresiones trigonométricas complejas.", exercise = "Utiliza la identidad de ángulo doble para simplificar la expresión sin(2θ)cos(2θ)."),
        SessionClassDTO(id = "6", typeCourse = "Trigonometria", nameSession = "Resolución de ecuaciones trigonométricas.", explanation = "Resolver ecuaciones trigonométricas implica encontrar los valores de θ que satisfacen la ecuación. Puede requerir el uso de identidades trigonométricas y álgebra.", exercise = "Resuelve la ecuación 2cos(θ) - 1 = 0 en el intervalo [0, 2π].")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseLearningBinding.inflate(
            inflater,
            container,
            false
        )
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.rvListSession?.layoutManager = manager

        binding?.rvListSession?.adapter =  SessionClassAdapter(sessionList = sessions){ it -> selectedClass(it)}
    }

    private fun selectedClass(id: String) {
        if (findNavController().currentDestination?.id == R.id.chooseLearningFragment){
            val action = ChooseLearningFragmentDirections.actionChooseLearningFragmentToDetailSessionFragment(idClass = id.toInt())
            findNavController().navigate(action)
        }
        viewModel.isTalkAvatar(true)
    }

}