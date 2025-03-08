package com.example.weekoneproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weekoneproject.User
import com.example.weekoneproject.UserPerson
import com.example.weekoneproject.GreetingProvider
import com.example.weekoneproject.Person
import com.example.weekoneproject.FriendlyGreeting
import com.example.weekoneproject.ui.BottomNavBar
import com.example.weekoneproject.ui.WatchListButton
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val greetingProvider: GreetingProvider = FriendlyGreeting()
    val greetingMessage = greetingProvider.provideGreeting()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = greetingMessage,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { BottomNavBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
        ) {
            MovieBanner()
            Spacer(modifier = Modifier.height(8.dp))
            MovieDetails()
        }
    }
}

@Composable
fun MovieBanner() {
    Image(
        painter = painterResource(id = R.drawable.arcane),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

@Composable
fun MovieDetails() {
    val qualities = listOf("720p", "1080p", "4K")

    Column(modifier = Modifier.padding(16.dp)) {
        MovieTitle("Arcane")
        MovieInfoRow("2021", qualities)
        Spacer(modifier = Modifier.height(8.dp))
        MovieButtons()
        Spacer(modifier = Modifier.height(8.dp))
        MovieDescription("Amidst the escalating unrest between the rich, utopian city of Piltover and the seedy, oppressed underbelly of Zaun, sisters Vi and Powder find themselves embroiled in a developing conflict over clashing convictions and arcane technologies.")
        Spacer(modifier = Modifier.height(16.dp))
        WatchListButton()
    }
}

@Composable
fun MovieTitle(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun MovieInfoRow(year: String, qualities: List<String>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = year, fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.width(8.dp))
        Row {
            qualities.forEachIndexed { index, quality ->
                ResolutionTag(quality)
                if (index < qualities.size - 1) Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@Composable
fun ResolutionTag(resolution: String) {
    Surface(
        color = Color.DarkGray,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(2.dp)
    ) {
        Text(
            text = resolution,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@Composable
fun MovieDescription(description: String) {
    Text(
        text = description,
        fontSize = 14.sp,
        color = Color.Gray,
        textAlign = TextAlign.Justify
    )
}

@Composable
fun MovieButtons() {
    Column {
        MovieActionButton("▶ Play")
        Spacer(modifier = Modifier.height(8.dp))
        MovieActionButton("⬇ Download")
    }
}

@Composable
fun MovieActionButton(label: String) {
    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Text(label, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    MyApp()
}
