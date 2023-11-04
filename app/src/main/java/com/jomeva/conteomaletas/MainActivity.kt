package com.jomeva.conteomaletas

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var colorABlanco: ColorStateList
    private lateinit var colorAzul: ColorStateList
    private var  cantidad:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contador=findViewById<TextView>(R.id.contador)
        val mas=findViewById<Button>(R.id.mas)
        val menos=findViewById<Button>(R.id.menos)

        mas.setOnClickListener {
            cantidad += 1
            contador.text=cantidad.toString()
        }
        menos.setOnClickListener {
            cantidad -= 1
            contador.text=cantidad.toString()
        }
        colorABlanco = this.resources.getColorStateList(
            R.color.white,
            null
        )
        colorAzul = this.resources.getColorStateList(
            R.color.azulclaro,
            null
        )
        val botonMenu = findViewById<FloatingActionButton>(R.id.botonMenu)
        botonMenu.setOnClickListener {
            mensajeMenuro()
        }
    }

    private fun mensajeMenuro() {

        val alertDialog: AlertDialog
        val builder: androidx.appcompat.app.AlertDialog.Builder =
            androidx.appcompat.app.AlertDialog.Builder(this)
        val v: View =
            LayoutInflater.from(this).inflate(R.layout.mensaje_menu, null)

        builder.setView(v)
        builder.setCancelable(true)
        alertDialog = builder.create()
        alertDialog.show()
        var perfil = "Mujer"
        val cardHombre = v.findViewById<CardView>(R.id.cardHombre)
        val cardMujer = v!!.findViewById<CardView>(R.id.cardMujer)


        val checkMujer = v!!.findViewById<ImageView>(R.id.checkMujer)
        val checkHombre = v!!.findViewById<ImageView>(R.id.checkHombre)
        val aceptar = v!!.findViewById<Button>(R.id.aceptar)

        cardHombre.setOnClickListener {
            if (cardHombre.backgroundTintList == colorABlanco) {
                cardMujer.backgroundTintList = colorABlanco
                cardHombre.backgroundTintList = colorAzul
                checkHombre.visibility = View.VISIBLE
                checkMujer.visibility = View.GONE
                perfil = "Hombre"
            }
        }
        cardMujer.setOnClickListener {
            if (cardMujer.backgroundTintList == colorABlanco) {
                cardHombre.backgroundTintList = colorABlanco
                cardMujer.backgroundTintList = colorAzul
                checkHombre.visibility = View.GONE
                checkMujer.visibility = View.VISIBLE
                perfil = "Mujer"
            }
        }
        aceptar.setOnClickListener {

            val intent = Intent(this, TableView::class.java)
            startActivity(intent)
        }

    }
}
