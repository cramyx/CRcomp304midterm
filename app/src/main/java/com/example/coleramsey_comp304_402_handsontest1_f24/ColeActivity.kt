package com.example.coleramsey_comp304_402_handsontest1_f24
// COLE RAMSEY 301333287

import android.os.Bundle
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.coleramsey_comp304_402_handsontest1_f24.ui.theme.ColeRamsey_COMP304_402_HandsOnTest1_F24Theme


class ColeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColeRamsey_COMP304_402_HandsOnTest1_F24Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            val intent = Intent(context, RamseyActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("My Contacts")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ColeRamsey_COMP304_402_HandsOnTest1_F24Theme {
        MainScreen()
    }
}