package com.application.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var menuBtn: Button
    private lateinit var menuPanel: View

    private lateinit var Solution_txt: TextView
    private lateinit var Result_txt: TextView
    private var Solution: String = ""
    private var Result: Double = 0.0
    private var currentInput: String = ""

    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        Solution_txt = findViewById(R.id.Solution)
        Result_txt = findViewById(R.id.Result)

        menuBtn = findViewById(R.id.Btn_Menu)
        menuPanel = findViewById(R.id.Menu_Panel)

        menuBtn.setOnClickListener {
            menuPanel.visibility = if (menuPanel.isVisible) View.GONE else View.VISIBLE
        }

        val numberButtons = listOf(
            R.id.Btn_0 to 0.0, R.id.Btn_1 to 1.0, R.id.Btn_2 to 2.0, R.id.Btn_3 to 3.0,
            R.id.Btn_4 to 4.0, R.id.Btn_5 to 5.0, R.id.Btn_6 to 6.0, R.id.Btn_7 to 7.0,
            R.id.Btn_8 to 8.0, R.id.Btn_9 to 9.0
        )

        numberButtons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                appendNumber(value)
            }
        }

        findViewById<Button>(R.id.Btn_Add).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.Btn_Sub).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.Btn_Mul).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.Btn_Div).setOnClickListener { setOperator("/") }
        findViewById<Button>(R.id.Btn_Mod).setOnClickListener { setOperator("%") }

        findViewById<Button>(R.id.Btn_Eq).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.Btn_AC).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.Btn_C).setOnClickListener { clearLast() }
    }

    private fun appendNumber(n: Double) {
        currentInput += n.toInt().toString()
        Solution += n.toInt().toString()
        Solution_txt.text = Solution
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            Result = currentInput.toDouble()
            currentInput = ""
        }
        operator = op
        Solution += " $op "
        Solution_txt.text = Solution
    }

    private fun calculateResult() {
        if (operator != null && currentInput.isNotEmpty()) {
            val number = currentInput.toDouble()
            Result = when (operator) {
                "+" -> Result + number
                "-" -> Result - number
                "*" -> Result * number
                "/" -> Result / number
                "%" -> Result % number
                else -> Result
            }
            Result_txt.text = Result.toString()
            currentInput = ""
            Solution = Result.toString()
            operator = null
        }
    }

    private fun clearAll() {
        Solution = ""
        Result = 0.0
        currentInput = ""
        operator = null
        Solution_txt.text = ""
        Result_txt.text = "0"
    }

    private fun clearLast() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            Solution = Solution.dropLast(1)
            Solution_txt.text = Solution
        }
    }
}

