package ec.edu.ups.trivia

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtNombre: EditText = findViewById(R.id.txtNombre);
        val btnJugar: Button = findViewById(R.id.btnJugar);

        btnJugar.setOnClickListener(View.OnClickListener {
            if (txtNombre.getText().toString() =="")  {
                val toast = Toast.makeText(
                    applicationContext,
                    "¡¡Ingrese un nombre!!",
                    Toast.LENGTH_SHORT

                )
                toast.show()
            } else {
                val toast = Toast.makeText(
                    applicationContext,
                    "¡¡Suerte!!",
                    Toast.LENGTH_SHORT
                )

                toast.show()
                val intent = Intent(this, Preguntas::class.java).apply {
                    putExtra("username", txtNombre.getText().toString())
                }
                startActivity(intent)
            }
        })
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