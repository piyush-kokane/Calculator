package com.application.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var menuBtn: Button
    private lateinit var menuPanel: View

    private lateinit var Solution_txt: EditText
    private lateinit var Result_txt: TextView

    private var Solution: String = ""
    private var Result: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        menuBtn = findViewById(R.id.Btn_Menu)
        menuPanel = findViewById(R.id.Menu_Panel)

        menuBtn.setOnClickListener {
            menuPanel.visibility = if (menuPanel.isVisible) View.GONE else View.VISIBLE
        }

        Solution_txt = findViewById(R.id.Solution)
        Result_txt = findViewById(R.id.Result)

        val numberButtons = listOf(
            R.id.Btn_0 to 0,
            R.id.Btn_1 to 1, R.id.Btn_2 to 2, R.id.Btn_3 to 3,
            R.id.Btn_4 to 4, R.id.Btn_5 to 5, R.id.Btn_6 to 6,
            R.id.Btn_7 to 7, R.id.Btn_8 to 8, R.id.Btn_9 to 9,
        )

        numberButtons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                appendNumber(value)
            }
        }

        val operatorButtons = listOf(
            R.id.Btn_Add to "+", R.id.Btn_Sub to "-", R.id.Btn_Mul to "*", R.id.Btn_Div to "/", R.id.Btn_Mod to "%",
        )

        operatorButtons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                appendOperator(value)
            }
        }

        findViewById<Button>(R.id.Btn_Dot).setOnClickListener { appendDecimal() }

        findViewById<Button>(R.id.Btn_Brac).setOnClickListener { appendBracket() }

        findViewById<Button>(R.id.Btn_Eq).setOnClickListener { calculate() }
        findViewById<Button>(R.id.Btn_AC).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.Btn_C).setOnClickListener { clear() }
    }

    private fun appendNumber(n: Number) {
        val cursorPos = Solution_txt.selectionStart
        val currentText = Solution_txt.text.toString()
        val newText = currentText.substring(0, cursorPos) + n.toString() + currentText.substring(cursorPos)
        Solution_txt.setText(newText)
        Solution_txt.setSelection(cursorPos + 1)
        calculate()
    }

    private fun appendDecimal() {
        val cursorPos = Solution_txt.selectionStart
        val currentText = Solution_txt.text.toString()
        var placeDot = true

        // Find . at left side of current position
        var i = cursorPos - 1
        while ((i >= 0) && (currentText[i] !in "+-*/%()")) {
            if (currentText[i] == '.') {
                placeDot == false
                break
            }
            i--
        }

        // Find . at right side of current position
        i = cursorPos
        while ((i <= currentText.length) && (currentText[i] !in "+-*/%()")) {
            if (currentText[i] == '.') {
                placeDot == false
                break
            }
            i++
        }

        // if placeDot == true put . at cursorPos
        if (placeDot) {
            val newText = currentText.substring(0, cursorPos) + "." + currentText.substring(cursorPos)
            Solution_txt.setText(newText)
            Solution_txt.setSelection(cursorPos + 1)
        }
    }

    private fun appendBracket() {
        val cursorPos = Solution_txt.selectionStart
        val currentText = Solution_txt.text.toString()

        val leftText = currentText.substring(0, cursorPos)
        val openCount = leftText.count { it == '(' }  // Find no of ( at left side of current position
        val closeCount = leftText.count { it == ')' } // Find no of ) at left side of current position

        val bracket = if (openCount > closeCount) ")" else "("

        val newText = currentText.substring(0, cursorPos) + bracket + currentText.substring(cursorPos)
        Solution_txt.setText(newText)
        Solution_txt.setSelection(cursorPos + 1)
    }

    private fun appendOperator(op: String) {
        val cursorPos = Solution_txt.selectionStart
        val currentText = Solution_txt.text.toString()

        val beforeChar = currentText.getOrNull(cursorPos - 1)
        val afterChar = currentText.getOrNull(cursorPos)

        fun isNumber(char: Char?): Boolean = (char != null && char.isDigit())
        fun isOperator(char: Char?): Boolean = (char in listOf('+', '-', '*', '/', '%'))

        val isPlusOrMinus = op == "+" || op == "-"

        fun insertOperator(pos: Int) {
            val updatedText = Solution_txt.text.toString()
            val newText = updatedText.substring(0, pos) + op + updatedText.substring(pos)
            Solution_txt.setText(newText)
            Solution_txt.setSelection(pos + 1)
            calculate()
        }

        when {
            // Allow + or - at beginning
            cursorPos == 0 && isPlusOrMinus -> insertOperator(cursorPos)

            // Allow + or - after an operator
            isOperator(beforeChar) && isPlusOrMinus -> insertOperator(cursorPos)

            // If between numbers
            isNumber(beforeChar) && isNumber(afterChar) -> insertOperator(cursorPos)

            // Replace operator ahead if exists
            isNumber(beforeChar) && isOperator(afterChar) -> {
                val newText = currentText.removeRange(cursorPos, cursorPos + 1)
                Solution_txt.setText(newText)
                Solution_txt.setSelection(cursorPos)
                insertOperator(cursorPos)
            }

            // Replace operator before if exists
            isOperator(beforeChar) -> {
                val newText = currentText.removeRange(cursorPos - 1, cursorPos)
                Solution_txt.setText(newText)
                Solution_txt.setSelection(cursorPos - 1)
                insertOperator(cursorPos - 1)
            }
        }
    }

    private fun calculate() {

    }

    private fun clearAll() {
        Solution = ""
        Result = 0.0
        Solution_txt.setText("")
        Result_txt.text = "0"
    }

    private fun clear() {
        val cursorPos = Solution_txt.selectionStart
        val currentText = Solution_txt.text.toString()

        if (cursorPos > 0) {
            val newText = currentText.removeRange(cursorPos - 1, cursorPos)
            Solution_txt.setText(newText)
            Solution_txt.setSelection(cursorPos - 1)
        }
    }
}

