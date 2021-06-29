package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isNumeric = false
    var isDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNumberClick(view: View){
        AnswerDisplay.append((view as TextView).text)
        isNumeric = true
    }

    fun onClear(view: View){
        AnswerDisplay.text = ""
        isNumeric = false
        isDot = true
    }

    fun onDecimal(view: View){
        if(isNumeric && !isDot) {
            AnswerDisplay.append(".")
            isDot = true
            isNumeric = false
        }
    }

    fun onOperator(view: View){
        if(isNumeric && !isOperator((AnswerDisplay.text.toString()))){
            AnswerDisplay.append((view as TextView).text)
            isNumeric = false
            isDot = false
        }
    }

    private fun isOperator(value: String): Boolean {
        if(value.startsWith("-")){
            return false
        }
        else{
            if(value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+"))
                return true
        }
        return false
    }

    fun onEqual(view: View){
        if(isNumeric){
            
            var question = AnswerDisplay.text.toString()

            //Handing the Minus case
            var prefix = ""
            if(question.startsWith("-")){
                prefix = "-"
                question = question.substring(1)
            }

            //Handling Subtraction
            if(question.contains("-")){
                var splitValue = question.split("-")

                var one = splitValue[0]
                var two = splitValue[1]

                if(prefix == "-"){
                    one = prefix + one
                }
                AnswerDisplay.text = (one.toDouble() - two.toDouble()).toString()
            }

            //Handling Addition
            if(question.contains("+")){
                var splitValue = question.split("+")

                var one = splitValue[0]
                var two = splitValue[1]

                if(prefix == "-"){
                    one = prefix + one
                }
                AnswerDisplay.text = (one.toDouble() + two.toDouble()).toString()
            }

            //Handling Multiplication
            if(question.contains("*")){
                var splitValue = question.split("*")

                var one = splitValue[0]
                var two = splitValue[1]

                if(prefix == "-"){
                    one = prefix + one
                }
                AnswerDisplay.text = (one.toDouble() * two.toDouble()).toString()
            }

            //Handling Division
            if(question.contains("/")){
                var splitValue = question.split("/")

                var one = splitValue[0]
                var two = splitValue[1]

                if(prefix == "-"){
                    one = prefix + one
                }
                AnswerDisplay.text = (one.toDouble() / two.toDouble()).toString()
            }
        }
    }
}