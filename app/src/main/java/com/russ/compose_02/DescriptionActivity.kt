package com.russ.compose_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.russ.compose_02.ui.theme.Compose_02Theme

class DescriptionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LoadData()
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadData(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    val intent = (context as? DescriptionActivity)?.intent

    val name = intent?.getStringExtra("name")
    val des = intent?.getStringExtra("des")
    val image = intent?.getStringExtra("image")

    Column (modifier = Modifier.fillMaxSize().padding(top = 20.dp, start = 10.dp, end = 10.dp), horizontalAlignment = Alignment.CenterHorizontally){

        GlideImage(
            model = image,
            contentDescription = "Pokemon Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(300.dp).fillMaxWidth()
        )

        Text(
            text = "${name}",
            Modifier.padding(5.dp).align(Alignment.Start),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "${des}",
            Modifier.padding(5.dp).align(Alignment.Start),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose_02Theme {
        LoadData()
    }
}