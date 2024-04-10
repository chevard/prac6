package com.example.chevmillionaire

import Round
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private var currentRound = 0
    private lateinit var questionTV: TextView
    private lateinit var valueTV: TextView
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.button -> processRound(1)
                R.id.button2 -> processRound(2)
                R.id.button3-> processRound(3)
                R.id.button4 -> processRound(4)
                else -> return
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTV = findViewById(R.id.questionTV)
        valueTV = findViewById(R.id.tvValue)
        option1 = findViewById(R.id.button)
        option2 = findViewById(R.id.button2)
        option3 = findViewById(R.id.button3)
        option4 = findViewById(R.id.button4)

        fillRounds()
        updateUI()

        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)


    }


    private val rounds = mutableListOf<Round>()
    private fun fillRounds(){
        rounds.run{
            add(Round("Из каких плодов можно получить копру?", "Ананас", "Вишня", "Кокос", "Абрикос", 3, 100))
            add(Round("Какую часть тела также называют «атлант»?", "Головной мозг", "Шестая пара ребер", "Шейный позвонок", "Часть плеча", 3, 1000))
            add(Round("Сколько кубиков в кубике Рубика?", "22", "24", "28", "26", 4, 100000))
            add(Round("Какого цвета крайнее правое кольцо в олимпийской символике?", "Красное", "Синее", "Желтое", "Зеленое", 1, 100000))
            add(Round("Какого цвета небо?", "Голубое", "Синее", "Желтое", "Зеленое", 1, 1000000))

        }
    }
    private fun updateUI(){
        questionTV.text = rounds[currentRound].q
        valueTV.text = rounds[currentRound].value.toString()
        option1.text = rounds[currentRound].a1
        option2.text = rounds[currentRound].a2
        option3.text = rounds[currentRound].a3
        option4.text = rounds[currentRound].a4
    }
    private fun checkAnswer(givenId: Int): Boolean {
        return givenId == rounds[currentRound].rightId
    }

    private fun goNextRound() {
        if(currentRound >= rounds.size - 1) {
            Toast.makeText(this, "Поздравляю! Вы прошли все раунды", Toast.LENGTH_SHORT).show()
        } else {
            currentRound++
            updateUI()
        }
    }

    private fun processRound(givenId: Int) {
        if(checkAnswer(givenId)) {
            goNextRound()
        } else {
            Toast.makeText(this, "Неправильный ответ. Игра окончена", Toast.LENGTH_SHORT).show()
        }
    }

//    fun buttonClick(view: View) {
//        try {
//            val id = view.tag.toString().toInt()
//            processRound(id)
//        } catch (e:Exception){
//            e.printStackTrace()
//        }
//    }


}
