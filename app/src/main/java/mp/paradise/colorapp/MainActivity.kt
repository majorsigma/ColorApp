package mp.paradise.colorapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var redChannelEditText: TextInputEditText
    lateinit var greenChannelEditText: TextInputEditText
    lateinit var blueChannelEditText: TextInputEditText
    lateinit var createButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        redChannelEditText = findViewById<TextInputEditText>(R.id.redChannelEditText);
        greenChannelEditText = findViewById<TextInputEditText>(R.id.greenChannelEditText);
        blueChannelEditText = findViewById<TextInputEditText>(R.id.blueChannelEditText);
        createButton = findViewById<Button>(R.id.createButton)
        val displaText = findViewById<TextView>(R.id.colorDisplay);

        val editTexts: MutableList<TextInputEditText> = mutableListOf<TextInputEditText>(
            redChannelEditText, greenChannelEditText, blueChannelEditText,
        );

        createButton.setOnClickListener {
            var isValidated: Boolean = validateForm(editTexts)
            if (isValidated) {
                var redText = redChannelEditText.text.toString()
                var greenText = greenChannelEditText.text.toString()
                var blueText = blueChannelEditText.text.toString()

                var colorString = redText.plus(greenText).plus(blueText);
                displaText.text = "#FF$colorString".uppercase()
                displaText.setBackgroundColor(Color.parseColor("#$colorString"))

            }
        }

    }

    private fun validateForm(editTexts: MutableList<TextInputEditText>): Boolean {
        var isFormValid = true

        for (editText in editTexts) {
            if (!validateTextInputEditText(editText)) {
                editText.error = "Invalid input. Only A-F allowed"
                isFormValid = false
            }
        }

        return isFormValid
    }

    private fun validateTextInputEditText(editText: TextInputEditText): Boolean {
        val input = editText.text.toString().trim()
        val regexPattern = "[\\dA-Fa-f]+".toRegex()
        return input.matches(regexPattern);
    }
}