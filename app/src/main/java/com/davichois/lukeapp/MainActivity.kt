package com.davichois.lukeapp

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.davichois.lukeapp.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var tts: TextToSpeech

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        this.window.statusBarColor = Color.TRANSPARENT

        val video = binding.video
        val myHandler = Handler(Looper.getMainLooper())

        var uri: Uri = Uri.parse("android.resource://" + packageName + "/raw/artist")
        video.setVideoURI(uri)
        video.requestFocus()
        video.resume()

        video.setOnClickListener {
            binding?.aca?.visibility = View.GONE
            video.start()

            tts = TextToSpeech(applicationContext) {
                if (it == TextToSpeech.SUCCESS) {
                    tts.language = Locale("es", "MEX")
                    tts.setSpeechRate(1.0f)
                    tts.speak("Hola bienvenido, estas listo para empezar, escoge un tema ahora.", TextToSpeech.QUEUE_ADD, null)
                }
            }
        }

        viewModel.isTalk.observe(this) {
            if (it) {
                myHandler.post(object : Runnable {
                    override fun run() {
                        var uri: Uri = Uri.parse("android.resource://" + packageName + "/raw/artist")
                        video.setVideoURI(uri)
                        video.requestFocus()
                        video.resume()
                        video.start()
                        myHandler.postDelayed(this, 5000 /*5 segundos*/)
                    }
                })
            } else {
                video.pause()
                myHandler.removeCallbacksAndMessages(null)
            }
        }

    }
}