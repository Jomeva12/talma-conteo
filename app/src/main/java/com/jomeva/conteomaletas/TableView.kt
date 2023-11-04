package com.jomeva.conteomaletas

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.cardview.widget.CardView
import androidx.core.content.FileProvider
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import java.io.File



class TableView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_view)
        val exportar=findViewById<CardView>(R.id.exportar)
        exportar.setOnClickListener {
            exportToExcelAndSendEmail()

            // Luego, puedes enviar el archivo por correo electrónico
        }


    }
    fun exportToExcelAndSendEmail() {
        try {
            val file = File(getExternalFilesDir(null), "jomeva.xls")
            val uri = FileProvider.getUriForFile(this, "${packageName}.provider", file)

            val workbook: WritableWorkbook = Workbook.createWorkbook(file)
            val sheet: WritableSheet = workbook.createSheet("Hoja 1", 0)
            // ... (Resto del código para crear y escribir el archivo Excel)
            // Agrega los encabezados a la hoja
            val headers = arrayOf("Vuelo", "nMaletas", "Fecha")
            for ((index, header) in headers.withIndex()) {
                sheet.addCell(Label(index, 0, header))
            }

            // Agrega datos ficticios a la hoja (reemplaza estos datos con tus datos reales)
            val data = arrayOf(
                arrayOf("Vuelo1", "3", "2023-11-01"),
                arrayOf("Vuelo2", "2", "2023-11-02"),
                arrayOf("Vuelo3", "4", "2023-11-03")
            )

            for ((rowIndex, rowData) in data.withIndex()) {
                for ((columnIndex, cellData) in rowData.withIndex()) {
                    sheet.addCell(Label(columnIndex, rowIndex + 1, cellData))
                }
            }
            // Cierra el archivo Excel
            workbook.write()
            workbook.close()

            // Enviar el archivo por correo
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "application/excel"
            emailIntent.putExtra(Intent.EXTRA_STREAM, uri)
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("correo@example.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Datos de la Tabla")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Aquí tienes los datos de la tabla")

            startActivity(Intent.createChooser(emailIntent, "Enviar correo"))

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
