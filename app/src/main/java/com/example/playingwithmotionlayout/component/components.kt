package com.example.playingwithmotionlayout.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val colors = listOf(Color(0xFF420433), Color(0xFF6D0854))

data class Repo(
    val repoName: String,
    val repoDesc: String,
    val repoLanguage: String,
    val repoStars: Int
)

@Composable
fun RepoItem(
    modifier: Modifier = Modifier,
    repo: Repo
) {

    Column(modifier = modifier
        .padding(8.dp)
    ) {

        Text(
            text = repo.repoName,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = repo.repoDesc,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraLight,
            fontFamily = FontFamily.Monospace,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0x80F0A9DE)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = repo.repoStars.toString(),
                    fontFamily = FontFamily.Serif,
                    color = Color.White.copy(0.8f),
                    fontStyle = FontStyle.Italic
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = null,
                    tint = Color(0x80F0A9DE)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = repo.repoLanguage,
                    fontFamily = FontFamily.Serif,
                    color = Color.White.copy(0.8f),
                    fontStyle = FontStyle.Italic
                )
            }

        }
    }
}

//static repos

val repos = listOf(
    Repo(
        "clean_architecture_mvvm_jokes_app",
        "Clean Architecture (single module) MVVM Jokes App With Custom Pagination",
        "Kotlin",
        2
    ),
    Repo(
        "AOU_prototype_indoor_map",
        "Custom indoor map of AOU campus",
        "Kotlin",
        0
    ),
    Repo(
        "ktor_chatting_application",
        "Server/Client Chatting application that allows to choose to chat in a group chat or privately to a specific connected user",
        "Kotlin",
        9
    ),
    Repo(
        "Astroa7m",
        "Config files for my GitHub profile.",
        "Kotlin",
        1
    ),
    Repo(
        "PDFER",
        "App demo that uses WorkManager API to download and schedule PDFs in the background",
        "Kotlin",
        2
    ),
    Repo(
        "Reminder-Worker",
        "App that demonstrates the use of AlarmManager API to remind users of specific tasks",
        "Kotlin",
        1
    ),
    Repo(
        "tutor_tracking",
        "Tutor App that tracks tutor's modules, students and their info",
        "Kotlin",
        1
    ),
    Repo(
        "QR-WIFI-Connector",
        "Connect to WIFI access point using Camera X and ML Kit",
        "Kotlin",
        3
    ),
    Repo(
        "TutorAppBackend ",
        "Tutor app Ktor backend",
        "Kotlin",
        1
    ),
    Repo(
        "Rick-Morty-",
        "A simple app used to demonstrate webservice usage in Android client by using a RESTful api. The project was demonstrated at MEC college for students in Android webservice workshop.",
        "Kotlin",
        2
    ),
    Repo(
        "expandable-outlined-bottom-navigation-bar",
        "Expandable bottom navigation bar made with compose",
        "Kotlin",
        3
    ),
    Repo(
        "jetpack-compose-stairs-layout",
        "Stairs-like layout made with jetpack compose to lay items in order as stairs",
        "Kotlin",
        1
    ),
    Repo(
        "collapsible-app-bar",
        "Collapsible App bar made with Jetpack Compose & Motion Compose",
        "Kotlin",
        10
    )
).sortedByDescending {
    it.repoStars
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RepoItemPreview() {
    RepoItem(repo = repos.first())
}
