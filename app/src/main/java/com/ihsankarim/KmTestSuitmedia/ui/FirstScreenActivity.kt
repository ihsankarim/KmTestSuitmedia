package com.ihsankarim.KmTestSuitmedia.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AlertDialog
import com.ihsankarim.KmTestSuitmedia.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        binding.btnCheck.setOnClickListener {
            val name = binding.nameTextInputEditText.text.toString()
            val sentence = binding.sentenceTextInputEditText.text.toString()

            val isPalindrome = isPalindrome(sentence)
            val message = if (isPalindrome) "isPalindrome" else "not palindrome"
            showDialog(message)
        }

        binding.btnNext.setOnClickListener {
            val name = binding.nameTextInputEditText.text.toString()
            sharedPreferences.edit().putString("showName", name).apply()
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("showName", name)
            startActivity(intent)
        }
    }

    private fun isPalindrome(sentence: String): Boolean {
        val cleanSentence = sentence.replace("\\s".toRegex(), "").toLowerCase()

        return cleanSentence == cleanSentence.reversed()
    }


    private fun showDialog(message: String) {
        val alertDialog = AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()

        alertDialog.show()
    }

}