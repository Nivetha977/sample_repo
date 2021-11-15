package com.example.sample.common

import android.widget.Toast
import com.example.sample.app.App

/**
 *Created by Nivetha S on 15-11-2021.
 */

fun showToast(message: String) {
    Toast.makeText(App.mInstance, message, Toast.LENGTH_LONG).show()
}