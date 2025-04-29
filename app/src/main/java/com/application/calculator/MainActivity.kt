package com.application.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var Solution: TextView
    private lateinit var Result: TextView
    private var currentInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        Solution = findViewById(R.id.Solution)
        Result = findViewById(R.id.Result)

        // Connect number buttons
        val button0: Button = findViewById(R.id.Btn_0)
        val button1: Button = findViewById(R.id.Btn_1)
        val button2: Button = findViewById(R.id.Btn_2)
        val button3: Button = findViewById(R.id.Btn_3)
        val button4: Button = findViewById(R.id.Btn_4)
        val button5: Button = findViewById(R.id.Btn_5)
        val button6: Button = findViewById(R.id.Btn_6)
        val button7: Button = findViewById(R.id.Btn_7)
        val button8: Button = findViewById(R.id.Btn_8)
        val button9: Button = findViewById(R.id.Btn_9)

        // Connect operator buttons
        val buttonAdd: Button = findViewById(R.id.Btn_Add)
        val buttonSub: Button = findViewById(R.id.Btn_Sub)
        val buttonMul: Button = findViewById(R.id.Btn_Mul)
        val buttonDiv: Button = findViewById(R.id.Btn_Div)
        val buttonMod: Button = findViewById(R.id.Btn_Mod)

        val buttonEq: Button = findViewById(R.id.Btn_Eq)


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

        buttonAdd.setOnClickListener {  }
        buttonSub.setOnClickListener {  }
        buttonMul.setOnClickListener {  }
        buttonDiv.setOnClickListener {  }
        buttonMod.setOnClickListener {  }


    }

    private fun appendNumber(number: String) {
        currentInput += number
        Solution.text = currentInput
    }




}