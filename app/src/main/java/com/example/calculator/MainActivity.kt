package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

     var firstNumber = 0.0
     var operation = ""
     var isNewOperation = true

     lateinit var resultTextView: TextView
     lateinit var previousCalculationTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView) // Make sure this ID matches your XML
        previousCalculationTextView = findViewById(R.id.previousCalculationTextView) // Make sure this ID matches your XML

        val button0: Button = findViewById(R.id.btn0)
        val button1: Button = findViewById(R.id.btn1)
        val button2: Button = findViewById(R.id.btn2)
        val button3: Button = findViewById(R.id.btn3)
        val button4: Button = findViewById(R.id.btn4)
        val button5: Button = findViewById(R.id.btn5)
        val button6: Button = findViewById(R.id.btn6)
        val button7: Button = findViewById(R.id.btn7)
        val button8: Button = findViewById(R.id.btn8)
        val button9: Button = findViewById(R.id.btn9)
        val buttonDot: Button = findViewById(R.id.btnDot)
        val buttonPlus: Button = findViewById(R.id.btnPlus)
        val buttonMinus: Button = findViewById(R.id.btnMinus)
        val buttonMultiply: Button = findViewById(R.id.btnMultiply)
        val buttonDivide: Button = findViewById(R.id.btnDivide)
        val buttonEqual: Button = findViewById(R.id.btnEqual)
        val buttonClear: Button = findViewById(R.id.btnClear)
        val buttonBackspace: Button = findViewById(R.id.btnBackspace)
        val buttonPercentage: Button = findViewById(R.id.btnPercentage)

        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }
        buttonDot.setOnClickListener { appendNumber(".") }

        buttonPlus.setOnClickListener { performOperation("+") }
        buttonMinus.setOnClickListener { performOperation("-") }
        buttonMultiply.setOnClickListener { performOperation("*") }
        buttonDivide.setOnClickListener { performOperation("/") }
        buttonPercentage.setOnClickListener { performOperation("%") }

        buttonEqual.setOnClickListener {calculateResult()}
        buttonClear.setOnClickListener {clearCalculator()}
        buttonBackspace.setOnClickListener {backspace()}







    }
}

private fun MainActivity.backspace() {
    val currentText = resultTextView.text.toString()
    if(currentText.isNotEmpty() && currentText != "0.0" && currentText != "Error" && currentText != "Infinity") {

        if (currentText.length > 1) {
            resultTextView.text = currentText.substring(0, currentText.length - 1)
        } else {
            resultTextView.text = "0"
        }
        if (currentText.isEmpty()) {
            isNewOperation = true
        }
    }

}

private fun MainActivity.clearCalculator() {
    resultTextView.text = "0"
    previousCalculationTextView.text = ""
    isNewOperation = true
}

private fun MainActivity.calculateResult() {
    try {
        val secondNumber = resultTextView.text.toString().toDouble()
        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            "%" -> firstNumber % secondNumber
            else -> secondNumber
            }
        resultTextView.text = result.toString()
        previousCalculationTextView.text = "$firstNumber $operation $secondNumber ="
        isNewOperation = true
    } catch (e: NumberFormatException) {
        resultTextView.text = "Error"
        Toast.makeText(this, "Invalid operation", Toast.LENGTH_SHORT).show()

        }

    }


private fun MainActivity.performOperation(operator: String) {
    firstNumber = resultTextView.text.toString().toDouble()
    operation = operator
    isNewOperation = true
    previousCalculationTextView.text = "$firstNumber $operation"
}

private fun MainActivity.appendNumber(number: String) {
    if (isNewOperation){
        resultTextView.text = number
        isNewOperation = false
    }
    else{
        resultTextView.text="${resultTextView.text}$number"
    }
}
