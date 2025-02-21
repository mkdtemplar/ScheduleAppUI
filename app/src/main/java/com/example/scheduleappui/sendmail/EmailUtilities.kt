package com.example.scheduleappui.sendmail

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat.getString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import com.example.scheduleappui.R

fun SendEmail(context: Context, from: String, messageText: String) {

    // Create a CoroutineScope using the IO dispatcher to perform IO operations to prevent UI blocking ANRs
    CoroutineScope(Dispatchers.IO).launch {

        // SMTP server details
        val host = "smtp.gmail.com" // smtp.gmail.com
        val port = 587
        val username = "sagitariusim@gmail.com"
        val password = getString(context,R.string.api_key)

        // Email recipient
        val to = "sagitariusim@live.com"

        // Configure SMTP properties
        val props = Properties()
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.smtp.host"] = host
        props["mail.smtp.port"] = port

        // Create a session with authentication
        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })


        try {
            // Create a new MimeMessage
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(username))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
            message.subject = "Al mail test"
            message.setText("Sent from $from\n$messageText")

            // Send the message using the Transport class
            Transport.send(message)

            // Perform UI operations on the Main dispatcher
            CoroutineScope(Dispatchers.Main).launch {
                // Display a success toast message
                Toast.makeText(context, "Sent Successfully.", Toast.LENGTH_LONG).show()
            }
        } catch (e: MessagingException) {
            e.printStackTrace()

            // Perform UI operations on the Main dispatcher
            CoroutineScope(Dispatchers.Main).launch {
                // Display an error toast message
                Toast.makeText(context, "Problem exists: $e", Toast.LENGTH_LONG).show()
            }
        }
    }
}