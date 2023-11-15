package com.davichois.lukeapp

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.davichois.lukeapp.databinding.FragmentDetailSessionBinding
import com.davichois.lukeapp.dto.SessionClassDTO
import java.util.Locale
import kotlin.math.exp

class DetailSessionFragment : Fragment(R.layout.fragment_detail_session) {

    private var _binding: FragmentDetailSessionBinding? = null
    private val binding get() = _binding

    private val viewModel: MainViewModel by activityViewModels()

    lateinit var tts: TextToSpeech

    private val sessions = arrayOf(
        SessionClassDTO(id = "0", typeCourse = "Trigonometria", nameSession = "Razones trigonométricas (seno, coseno y tangente)", explanation = "El seno (sin), coseno (cos) y tangente (tan) son las razones trigonométricas más básicas y se utilizan para relacionar los ángulos en un triángulo rectángulo con las longitudes de sus lados.", exercise = "En un triángulo rectángulo, si el cateto opuesto mide 5 unidades y el cateto adyacente mide 12 unidades, encuentra el valor de sin(θ), cos(θ) y tan(θ) para el ángulo θ entre los catetos."),
        SessionClassDTO(id = "1", typeCourse = "Trigonometria", nameSession = "Teorema de Pitágoras", explanation = "El teorema de Pitágoras establece que en un triángulo rectángulo, el cuadrado de la hipotenusa es igual a la suma de los cuadrados de los catetos.", exercise = "En un triángulo rectángulo, si los catetos miden 3 y 4 unidades, respectivamente, ¿cuál es la longitud de la hipotenusa?"),
        SessionClassDTO(id = "2", typeCourse = "Trigonometria", nameSession = "Identidades trigonométricas", explanation = "Las identidades trigonométricas son ecuaciones que relacionan las funciones trigonométricas. Algunas de las más comunes incluyen las identidades de suma y diferencia.", exercise = "Demuestra la identidad trigonométrica: sin(α + β) = sin(α)cos(β) + cos(α)sin(β)."),
        SessionClassDTO(id = "3", typeCourse = "Trigonometria", nameSession = "Resolución de triángulos.", explanation = "Resolución de triángulos implica encontrar los ángulos y las longitudes de los lados de un triángulo, dados ciertos datos. Esto puede involucrar el uso de razones trigonométricas y teoremas como el teorema del seno y el teorema del coseno.", exercise = "En un triángulo oblicuángulo, si conoces dos lados y el ángulo entre ellos (SAS), encuentra la longitud del tercer lado y los otros dos ángulos."),
        SessionClassDTO(id = "4", typeCourse = "Trigonometria", nameSession = "Gráficos de funciones trigonométricas.", explanation = "Las funciones trigonométricas, como el seno y el coseno, tienen gráficos periódicos. Comprender cómo se ven estos gráficos es esencial para analizar funciones trigonométricas.", exercise = "Dibuja el gráfico de la función y = 2sin(2x) en el intervalo [0, 2π]."),
        SessionClassDTO(id = "5", typeCourse = "Trigonometria", nameSession = "Identidades trigonométricas avanzadas.", explanation = "Además de las identidades básicas, existen identidades avanzadas, como las identidades de ángulo doble y de ángulo mitad, que son útiles para simplificar expresiones trigonométricas complejas.", exercise = "Utiliza la identidad de ángulo doble para simplificar la expresión sin(2θ)cos(2θ)."),
        SessionClassDTO(id = "6", typeCourse = "Trigonometria", nameSession = "Resolución de ecuaciones trigonométricas.", explanation = "Resolver ecuaciones trigonométricas implica encontrar los valores de θ que satisfacen la ecuación. Puede requerir el uso de identidades trigonométricas y álgebra.", exercise = "Resuelve la ecuación 2cos(θ) - 1 = 0 en el intervalo [0, 2π].")
    )

    // args - view
    private val args: DetailSessionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailSessionBinding.inflate(
            inflater,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.backSession?.setOnClickListener {
            findNavController().popBackStack()
            viewModel.isTalkAvatar(false)
            tts.stop()
        }

        binding?.tvContentExplanation?.text = sessions[args.idClass].explanation
        binding?.tvContentExercise?.text = sessions[args.idClass].exercise

        tts = TextToSpeech(requireContext()) {
            if (it == TextToSpeech.SUCCESS) {
                tts.language = Locale("es", "MEX")
                tts.setSpeechRate(1.0f)
                tts.speak("Bien, escogiste la clase de: ${sessions[args.idClass].nameSession}, empecemos con la explicacion, ${sessions[args.idClass].explanation}, aca abajo te dejamos un ejercicio para que puedas afianzar tus conocimientos: ${sessions[args.idClass].exercise}", TextToSpeech.QUEUE_ADD, null)

            }

        }

    }

    override fun onDetach() {
        super.onDetach()
        viewModel.isTalkAvatar(false)
        tts.stop()
    }

}
