package com.example.simplecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.simplecalculator.ui.theme.SimpleCalculatorTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNumber1 = findViewById<EditText>(R.id.etNumber1)
        val etNumber2 = findViewById<EditText>(R.id.etNumber2)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSubtract = findViewById<Button>(R.id.btnSubtract)
        val btnMultiply = findViewById<Button>(R.id.btnMultiply)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnAdd.setOnClickListener {
            calculate('+', etNumber1, etNumber2, tvResult)
        }

        btnSubtract.setOnClickListener {
            calculate('-', etNumber1, etNumber2, tvResult)
        }

        btnMultiply.setOnClickListener {
            calculate('*', etNumber1, etNumber2, tvResult)
        }

        btnDivide.setOnClickListener {
            calculate('/', etNumber1, etNumber2, tvResult)
        }
    }

    private fun calculate(
        operation: Char,
        etNumber1: EditText,
        etNumber2: EditText,
        tvResult: TextView
    ) {
        val num1Text = etNumber1.text.toString()
        val num2Text = etNumber2.text.toString()

        if (num1Text.isEmpty() || num2Text.isEmpty()) {
            Toast.makeText(this, "Введите оба числа", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val num1 = num1Text.toDouble()
            val num2 = num2Text.toDouble()
            val result: Double = when (operation) {
                '+' -> num1 + num2
                '-' -> num1 - num2
                '*' -> num1 * num2
                '/' -> {
                    if (num2 == 0.0) {
                        Toast.makeText(this, "Нельзя делить на ноль", Toast.LENGTH_SHORT).show()
                        return
                    }
                    num1 / num2
                }
                else -> return
            }

            tvResult.text = "Результат: $result"
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Некорректный ввод чисел", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleCalculatorTheme {
        Greeting("Android")
    }
}