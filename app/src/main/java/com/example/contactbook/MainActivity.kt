package com.example.contactbook

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactbook.ui.theme.ContactBookTheme
import javax.security.auth.Subject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactBookTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AnnonsText(
                        ring = stringResource(R.string.ring),
                        email = stringResource(R.string.email),
                        place = stringResource(R.string.place),
                        shareContact = stringResource(R.string.shareContact)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactsInfo(ring: String, email: String, place: String, shareContact: String) {
    val phoneNumber = stringResource(R.string.phoneNumber)
    val mail = stringResource(R.string.mail)
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFf0eddc))
    ) {
        Button(
            onClick = { callPhoneNumber(context, phoneNumber) },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFdfa0aa),
                contentColor = Color(0xFF803535)
            ),
            border = BorderStroke(
                width = 3.dp,
                color = Color(0xFF803535)
            ),
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
        )
        {
            Text(text = ring, fontSize = 25.sp)
        }

        Button(
            onClick = { sendEmail(context,mail, "Обращение в поддержку", "Здравствуйте, \n") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFdfa0aa),
                contentColor = Color(0xFF803535)
            ),
            border = BorderStroke(
                width = 3.dp,
                color = Color(0xFF803535)
            ),
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Text(text = email, fontSize = 25.sp)
        }

        Button(
            onClick = { showOnMap(context, 60.0237, 30.2289, "Наш офис") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFdfa0aa),
                contentColor = Color(0xFF803535)
            ),
            border = BorderStroke(
                width = 3.dp,
                color = Color(0xFF803535)
            ),
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Text(text = place, fontSize = 25.sp)
        }

        Button(
            onClick = { shareText(context, "Контакт: ") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFdfa0aa),
                contentColor = Color(0xFF803535)
            ),
            border = BorderStroke(
                width = 3.dp,
                color = Color(0xFF803535)
            ),
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Text(text = shareContact, fontSize = 25.sp)
        }
    }
}

@Composable
fun AnnonsText(
    ring: String,
    email: String,
    place: String,
    shareContact: String,
) {
    ContactsInfo(
        ring = ring,
        email = email,
        place = place,
        shareContact = shareContact
    )
}

fun callPhoneNumber(context: android.content.Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
}

fun sendEmail(context: Context, address: String, subject: String, body: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

fun showOnMap(context: Context, latitude: Double, longitude: Double, label: String)
{
    val geoUri = Uri.parse("geo:0,0?q=$latitude,$longitude($label)")
    val intent = Intent(Intent.ACTION_VIEW, geoUri)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

fun shareText(context: Context, text: String)
{
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
        val chooser = Intent.createChooser(sendIntent, "Поделиться через...")
        context.startActivity(chooser)
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactBookTheme {
        AnnonsText(
            ring = stringResource(R.string.ring),
            email = stringResource(R.string.email),
            place = stringResource(R.string.place),
            shareContact = stringResource(R.string.shareContact)
        )
    }
}