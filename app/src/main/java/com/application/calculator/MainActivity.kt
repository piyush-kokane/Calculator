package com.application.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private lateinit var menuBtn: Button
    private lateinit var menuPanel: View

    private lateinit var Solution_txt: TextView
    private lateinit var Result_txt: TextView
    private var Solution: String = ""
    private var Result: Double  = 0.0

    private var Add: Boolean  = false
    private var Sub: Boolean  = false
    private var Mul: Boolean  = false
    private var Div: Boolean  = false
    private var Mod: Boolean  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        Solution_txt = findViewById(R.id.Solution)
        Result_txt = findViewById(R.id.Result)

        menuBtn = findViewById(R.id.Btn_Menu)
        menuPanel = findViewById(R.id.menu_panel)

        menuBtn.setOnClickListener {
            menuPanel.visibility = if (menuPanel.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

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
        val buttonAC: Button = findViewById(R.id.Btn_AC)
        val buttonC: Button = findViewById(R.id.Btn_C)


        button0.setOnClickListener { appendNumber(0.0) }
        button1.setOnClickListener { appendNumber(1.0) }
        button2.setOnClickListener { appendNumber(2.0) }
        button3.setOnClickListener { appendNumber(3.0) }
        button4.setOnClickListener { appendNumber(4.0) }
        button5.setOnClickListener { appendNumber(5.0) }
        button6.setOnClickListener { appendNumber(6.0) }
        button7.setOnClickListener { appendNumber(7.0) }
        button8.setOnClickListener { appendNumber(8.0) }
        button9.setOnClickListener { appendNumber(9.0) }

        buttonAdd.setOnClickListener { Add = true }
        buttonSub.setOnClickListener { Sub = true }
        buttonMul.setOnClickListener { Mul = true }
        buttonDiv.setOnClickListener { Div = true }
        buttonMod.setOnClickListener { Mod = true }

        buttonEq.setOnClickListener {  }
        buttonAC.setOnClickListener {  }
        buttonC.setOnClickListener {  }


    }

    private fun appendNumber(n: Double) {
        Solution += n.toString()
        Solution_txt.text = Solution

        if (Add == true) Result += n
        if (Sub == true) Result -= n
        if (Mul == true) Result *= n
        if (Div == true) Result /= n
        if (Mod == true) Result %= n

        Result_txt.text = Result.toString()
    }

    private fun Add_fn() {
        Add = true
    }





}