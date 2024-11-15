package com.example.kalkulatorbmiuts

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etBerat: EditText = findViewById(R.id.etWeight)
        val etTinggi: EditText = findViewById(R.id.etHeight)
        val btnHitung: Button = findViewById(R.id.btnCalculate)
        val tvKategoriBMI: TextView = findViewById(R.id.tvBMICategory)
        val tvTinggi: TextView = findViewById(R.id.tvHeight)
        val tvBerat: TextView = findViewById(R.id.tvWeight)
        val bmiProgressBar: ProgressBar = findViewById(R.id.bmiProgressBar)
        val tvNilaiBMI: TextView = findViewById(R.id.tvBMIValue)

        btnHitung.setOnClickListener {
            val berat = etBerat.text.toString()
            val tinggi = etTinggi.text.toString()

            if (berat.isEmpty() || tinggi.isEmpty()) {
                tvKategoriBMI.text = "Harap masukkan berat dan tinggi yang valid"
            } else {
                try {
                    val beratValue = berat.toFloat()
                    val tinggiValue = tinggi.toFloat() / 100
                    val bmi = beratValue / (tinggiValue * tinggiValue)

                    tvNilaiBMI.text = "BMI Anda: %.2f".format(bmi)

                    val kategori = when {
                        bmi < 18.5 -> "Kekurangan Berat Badan"
                        bmi in 18.5..24.9 -> "Berat Badan Normal"
                        bmi in 25.0..29.9 -> "Kelebihan Berat Badan"
                        else -> "Obesitas"
                    }
                    tvKategoriBMI.text = kategori
                    tvTinggi.text = "Tinggi (cm): $tinggi"
                    tvBerat.text = "Berat (kg): $berat"

                    bmiProgressBar.progress = bmi.toInt()

                    setProgressBarColor(bmi, bmiProgressBar)

                } catch (e: Exception) {
                    tvKategoriBMI.text = "Input tidak valid. Harap masukkan angka yang valid."
                }
            }
        }
    }

    private fun setProgressBarColor(bmi: Float, progressBar: ProgressBar) {
        when {
            bmi < 18.5 -> progressBar.progressDrawable = ColorDrawable(getColor(R.color.underweight))
            bmi in 18.5..24.9 -> progressBar.progressDrawable = ColorDrawable(getColor(R.color.normalWeight))
            bmi in 25.0..29.9 -> progressBar.progressDrawable = ColorDrawable(getColor(R.color.overweight))
            else -> progressBar.progressDrawable = ColorDrawable(getColor(R.color.obesity))
        }
    }
}
