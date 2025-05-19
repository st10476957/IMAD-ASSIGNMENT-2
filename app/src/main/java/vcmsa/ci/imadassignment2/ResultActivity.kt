package vcmsa.ci.imadassignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)
        val wrongList = intent.getStringArrayListExtra("wrongList")

        val scoreText: TextView = findViewById(R.id.score_text)
        val wrongText: TextView = findViewById(R.id.wrong_text)
        val restartButton: Button = findViewById(R.id.restart_button)

        scoreText.text = "Your Score: $score / 4"

        if (wrongList.isNullOrEmpty()) {
            wrongText.text = "You got all the questions right! 🎉"
        } else {
            val wrongFormatted = wrongList.joinToString("\n• ", prefix = "Questions you got wrong:\n• ")
            wrongText.text = wrongFormatted

            restartButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                // Clear the back stack so user can't press "Back" to return to results
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
    }
}
