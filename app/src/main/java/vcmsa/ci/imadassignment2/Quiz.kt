package vcmsa.ci.imadassignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Quiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        val questions = arrayOf(
            "The Declaration of Independence was signed in 1776.",
            "World War I ended in 1939.",
            "The Great Wall of China was built in a single year.",
            "Abraham Lincoln was the 16th President of the United States."
        )

        val answers = booleanArrayOf(
            true,
            false,
            false,
            true
        )

         var currentIndex = 0
         var score = 0
         val wrongQuestions = ArrayList<String>()

         fun updateQuestion() {
            if (currentIndex < questions.size) {
                val questionText: TextView = findViewById(R.id.question_text)
                questionText.text = questions[currentIndex]
            }
        }

         fun checkAnswer(userAnswer: Boolean) {
            val correctAnswer = answers[currentIndex]
            val currentQuestion = questions[currentIndex]

            if (userAnswer == correctAnswer) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                score++
            } else {
                Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
                wrongQuestions.add(currentQuestion)
            }

            currentIndex++
            if (currentIndex < questions.size) {
                updateQuestion()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("score", score)
                intent.putStringArrayListExtra("wrongList", wrongQuestions)
                startActivity(intent)
                finish()
            }
        }



            val trueButton: Button = findViewById(R.id.true_button)
            val falseButton: Button = findViewById(R.id.false_button)

            trueButton.setOnClickListener {
                checkAnswer(true)
            }

            falseButton.setOnClickListener {
                checkAnswer(false)
            }

            updateQuestion()
        }
    }


