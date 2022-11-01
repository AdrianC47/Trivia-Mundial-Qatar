package ec.edu.ups.trivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class Final : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)
        //Recupero los valores
        val jugador = intent.getStringExtra("jugador")
        val aciertos = intent.getStringExtra("Aciertos")
        val total = intent.getStringExtra("total")

        val Imagen : ImageView = findViewById(R.id.img1)
        val conclusion : TextView = findViewById(R.id.txtConclusion)
        val txtTotal : TextView = findViewById(R.id.txtTotal)
        val txtAciertos : TextView = findViewById(R.id.txtAciertos)
        txtTotal.text = total
        txtAciertos.text = aciertos
        if (aciertos != null) {
            if (aciertos.toInt() < 7){
                Imagen.setImageResource(R.drawable.perdiste)
                conclusion.text = "Lo siento, " + jugador + " has perdido"
            } else{
                Imagen.setImageResource(R.drawable.ganaste)
                conclusion.text = "Felicidades " + jugador + "  has ganado!!"
            }
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