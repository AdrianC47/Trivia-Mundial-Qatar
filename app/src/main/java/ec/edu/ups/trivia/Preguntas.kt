package ec.edu.ups.trivia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class Preguntas : AppCompatActivity() {
    lateinit var listaDePreguntas: ArrayList<ModeloPregunta>
    private var index: Int = 0
    lateinit var questionModel: ModeloPregunta

    private var respuestasCorrectas: Int = 0
    private var respuestasIncorrectas: Int = 0

    lateinit var reloj: TextView
    lateinit var jugador: String;

    /*    El modificador lateinit te permite inicializar una propiedad no anulable por fuera del constructor. Este mecanismo te ayuda cuando deseas asignar el valor de
        una propiedad después y no deseas usar comprobaciones de nulos (expresiones if, operador de acceso seguro o aserciones) una vez inicializada.
        Deben ser propiedades mutables var (es evidente, ya que necesitas cambiar el valor fuera del constructor)*/
    lateinit var pregunta: TextView
    lateinit var opcion1:Button
    lateinit var opcion2 : Button
    lateinit var opcion3: Button
    lateinit var opcion4: Button
    lateinit var imagen: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas)
        jugador = intent.getStringExtra("username").toString()
        pregunta = findViewById(R.id.txtPregunta)
        reloj = findViewById(R.id.reloj)
        opcion1 = findViewById(R.id.btnOpcion1)
        opcion2 = findViewById(R.id.btnOpcion2)
        opcion3 = findViewById(R.id.btnOpcion3)
        opcion4 = findViewById(R.id.btnOpcion4)
        imagen = findViewById(R.id.imgJuego)
        listaDePreguntas = ArrayList()
        listaDePreguntas.add(
            ModeloPregunta(
                R.drawable.edicion,
                "El Mundial de Qatar será la edición número:",
                "20",
                "21",
                "22",
                " 19",
                "22"
            )
        )
        listaDePreguntas.add(
            ModeloPregunta(
                R.drawable.bandera,
                "Qatar se convierte en el país número __ en albergar el Mundial",
                "20",
                "19",
                "18",
                "25",
                "18"
            )
        )
        listaDePreguntas.add(
            ModeloPregunta(
                R.drawable.goleador,
                "¿Cuál es el máximo goleador de Ecuador en la historia?",
                "Gonzalo Plata",
                "Agustin Delgado",
                "Enner Valencia",
                "Iván Kaviedes",
                "Enner Valencia"
            )
        )
        listaDePreguntas.add(
            ModeloPregunta(
                R.drawable.estadio,
                "¿Cuántos estadios habrán en el Mundial de Qatar?",
                "8",
                "10",
                "9",
                "15",
                "8"
            )
        )
        listaDePreguntas.add(
            ModeloPregunta(
                R.drawable.mascota,
                "¿Cuál es el nombre de la mascota del Mundial?",
                "Al Rihla",
                "Kura",
                "La'eeb",
                "Osama bin Laden",
                "La'eeb"
            )
        )
        listaDePreguntas.add(
            ModeloPregunta(
                R.drawable.capital,
                "¿Cuál es la capital de Qatar?",
                "Doha",
                "Dukhan",
                "Lusail",
                "Al Wakrah",
                "Doha"
            )
        )
        listaDePreguntas.add(
            ModeloPregunta(
                R.drawable.uefa,
                "¿Cuántas naciones europeas jugarán el Mundial de Qatar?",
                "10",
                "12",
                "13",
                "14",
                "13"
            )
        )
        listaDePreguntas.add(
            ModeloPregunta(
                R.drawable.harry,
                "Harry Kane fue el máximo goleador de las eliminatorias europeas con 12 goles junto a",
                "Cristiano Ronaldo",
                "Robert Lewandowski",
                "Luka Modrić",
                "Memphis Depay",
                "Memphis Depay"
            )
        )
        listaDePreguntas.add(
            ModeloPregunta(
                R.drawable.argentina,
                "¿Cuál de estos rivales no jugará con Argentina en este Mundial?",
                "Arabia Saudita",
                "Nigeria",
                "Polonia",
                "Mexico",
                "Nigeria"
            )
        )
        listaDePreguntas.add(
            ModeloPregunta(
                R.drawable.ecuadoring,
                "¿En qué Mundial Ecuador jugó ante Inglaterra?",
                "Alemania 2006",
                "Sudafrica 2010",
                "Corea - Japón 2022",
                "Brasil 2014",
                "Alemania 2006"
            )
        )

        listaDePreguntas.shuffle()
        questionModel = listaDePreguntas[index]
        establecerPreguntas()

        reloj()
    }



    fun reloj() {
        var duration: Long = TimeUnit.SECONDS.toMillis(5)

        object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var sDuration: String = String.format(
                    Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                    )
                )

                reloj.text = sDuration

            }

            override fun onFinish() {
                index++
                if (index < listaDePreguntas.size) {
                    questionModel = listaDePreguntas[index]
                    establecerPreguntas()
                    resetearFondo()
                    habiltarBoton()
                    reloj()

                } else {
                    resultadoJuego()
                }
            }

        }.start()


    }


    private fun establecerPreguntas() {
        pregunta.text = questionModel.question
        opcion1.text = questionModel.opcion1
        opcion2.text = questionModel.opcion2
        opcion3.text = questionModel.opcion3
        opcion4.text = questionModel.opcion4
        imagen.setImageResource(questionModel.imagen)
    }

    private fun respuestaCorrecta(option:Button){
        option.setBackgroundColor(Color.rgb(125,250,115))
        respuestasCorrectas++
    }
    private fun respuestaIncorrecta(option:Button){

        option.setBackgroundColor(Color.rgb(245,103,103)) //color rojo
        respuestasIncorrectas++


    }
    private fun resultadoJuego(){
        var intent= Intent(this,Final::class.java)
        val toast = Toast.makeText(
            applicationContext,
            "¡¡Juego Terminado!!",
            Toast.LENGTH_SHORT
        )
        toast.show()
        intent.putExtra("Aciertos",respuestasCorrectas.toString())
        intent.putExtra("jugador",jugador)
        intent.putExtra("total",listaDePreguntas.size.toString())

        startActivity(intent)
    }

    private fun habiltarBoton(){
        opcion1.isClickable = true
        opcion2.isClickable = true
        opcion3.isClickable = true
        opcion4.isClickable = true

    }
    private fun deshabilitarBoton(){
        opcion1.isClickable = false
        opcion2.isClickable = false
        opcion3.isClickable = false
        opcion4.isClickable = false
    }
    private fun resetearFondo(){
        opcion1.setBackgroundColor(Color.WHITE)
        opcion2.setBackgroundColor(Color.WHITE)
        opcion3.setBackgroundColor(Color.WHITE)
        opcion4.setBackgroundColor(Color.WHITE)
    }

    fun btnOpcion1Clicked(view: View){
        deshabilitarBoton()
        if (questionModel.opcion1 == questionModel.answer) {
            opcion1.setBackgroundColor(Color.rgb(125,250,115))
            respuestaCorrecta(opcion1)
            val toast = Toast.makeText(
                applicationContext,
                "¡¡Acertaste!!",
                Toast.LENGTH_SHORT
            )

            toast.show()
        } else {
            respuestaIncorrecta(opcion1)
            val toast = Toast.makeText(
                applicationContext,
                "¡¡Fallaste!!",
                Toast.LENGTH_SHORT
            )

            toast.show()
        }


    }
    fun btnOpcion2Clicked(view: View){
        deshabilitarBoton()
        if (questionModel.opcion2 == questionModel.answer) {
            opcion2.setBackgroundColor(Color.rgb(125,250,115))
            respuestaCorrecta(opcion2)
            val toast = Toast.makeText(
                applicationContext,
                "¡¡Acertaste!!",
                Toast.LENGTH_SHORT
            )

            toast.show()
        } else {
            respuestaIncorrecta(opcion2)
            val toast = Toast.makeText(
                applicationContext,
                "¡¡Fallaste!!",
                Toast.LENGTH_SHORT
            )

            toast.show()
        }


    }

    fun btnOpcion3Clicked(view: View){
        deshabilitarBoton()
        if (questionModel.opcion3 == questionModel.answer) {
            opcion3.setBackgroundColor(Color.rgb(125,250,115))
            respuestaCorrecta(opcion3)
            val toast = Toast.makeText(
                applicationContext,
                "¡¡Acertaste!!",
                Toast.LENGTH_SHORT
            )

            toast.show()
        } else {
            respuestaIncorrecta(opcion3)
            val toast = Toast.makeText(
                applicationContext,
                "¡¡Fallaste!!",
                Toast.LENGTH_SHORT
            )

            toast.show()
        }

    }

    fun btnOpcion4Clicked(view: View){
        deshabilitarBoton()
        if (questionModel.opcion4 == questionModel.answer) {
            opcion4.setBackgroundColor(Color.rgb(125,250,115))
            respuestaCorrecta(opcion4)
            val toast = Toast.makeText(
                applicationContext,
                "¡¡Acertaste!!",
                Toast.LENGTH_SHORT
            )

            toast.show()
        } else {
            respuestaIncorrecta(opcion4)
            val toast = Toast.makeText(
                applicationContext,
                "¡¡Fallaste!!",
                Toast.LENGTH_SHORT
            )

            toast.show()
        }

    }
    //Para Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.game_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.itmReiniciar -> {
                newGame()
                true
            }
            R.id.itmSalir -> {
                exit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun newGame() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun exit() {
        this.finishAffinity()
    }


}