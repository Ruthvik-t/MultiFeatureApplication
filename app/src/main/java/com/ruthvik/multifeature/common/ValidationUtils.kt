package com.ruthvik.multifeature.common

import android.util.Patterns

object ValidationUtils {

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Validates a password based on the following criteria:
     * - At least 8 characters long
     * - Contains at least one digit
     * - Contains at least one uppercase letter
     * - Contains at least one lowercase letter
     * - Contains at least one special character (e.g., @, #, $, %)
     *
     * @param password The password string to validate.
     * @return True if the password is valid, false otherwise.
     */
    fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (!password.any { it.isDigit() }) return false
        if (!password.any { it.isUpperCase() }) return false
        if (!password.any { it.isLowerCase() }) return false
        if (!password.any { !it.isLetterOrDigit() }) return false // Checks for special characters
        return true
    }

    // You can add more specific error messages if needed
    fun getPasswordErrorMessage(password: String): String? {
        if (password.length < 8) return "Password must be at least 8 characters long."
        if (!password.any { it.isDigit() }) return "Password must contain at least one digit."
        if (!password.any { it.isUpperCase() }) return "Password must contain at least one uppercase letter."
        if (!password.any { it.isLowerCase() }) return "Password must contain at least one lowercase letter."
        if (!password.any { !it.isLetterOrDigit() }) return "Password must contain at least one special character."
        return null // No error
    }
}