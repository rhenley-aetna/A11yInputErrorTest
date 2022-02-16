package com.example.a11yinputerrortest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<AppCompatEditText>(R.id.edittext)
        val errorMessage = findViewById<AppCompatTextView>(R.id.error_message)
        val showErrorAndClear = findViewById<AppCompatButton>(R.id.show_error_and_clear)
        val showErrorClearAndRefocus = findViewById<AppCompatButton>(R.id.show_error_clear_and_refocus)
        val clearError = findViewById<AppCompatButton>(R.id.clear_error)

        showErrorAndClear.setOnClickListener { v ->
            v.isEnabled = false
            showErrorClearAndRefocus.isEnabled = false
            errorMessage.apply {
                text = "Oops! I'm in error."
                isVisible = true
            }
            editText.text?.clear()
        }
        showErrorClearAndRefocus.setOnClickListener { v ->
            v.isEnabled = false
            showErrorAndClear.isEnabled = false
            editText.apply {
                text?.clear()
                performAccessibilityAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS, null)
                sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
                post( {
                    errorMessage.apply {
                        text = "Zap! I'm erroring and refocusing."
                        isVisible = true
                    }
                })
            }
        }
        clearError.setOnClickListener { _ ->
            showErrorAndClear.isEnabled = true
            showErrorClearAndRefocus.isEnabled = true
            errorMessage.apply {
                visibility = View.GONE
            }
        }
    }
}