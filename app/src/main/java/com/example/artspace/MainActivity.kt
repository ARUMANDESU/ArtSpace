package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout( modifier: Modifier = Modifier) {
    var currentPictureIndex by remember { mutableStateOf(0) }

    val pictures = mutableListOf<Pictures>(
        Pictures(R.drawable._jjk2,"乙骨", "seisyunbot"),
        Pictures(R.drawable.jjk_geto,"Geto", "nn"),
        Pictures(R.drawable._jjk,"Jjk", "Koko"),
        Pictures(R.drawable._cat,"Cat", "Luisa Maria"),
        Pictures(R.drawable.bocchi_the_rock,"Bocchi the Rock", "Akimizu Sakai"),
        Pictures(R.drawable.mushoku_tensei,"乙骨", "seisyunbot"),
        Pictures(R.drawable.nature,"乙骨", "seisyunbot"),
        Pictures(R.drawable.onepunchman,"OnePunchMan", "nn"),
        Pictures(R.drawable._orsted,"Orsted - Muchoku Tensei", "from zerochan"),
        Pictures(R.drawable.cat2,"cat.", "nn"),
        Pictures(R.drawable.jjk_nanami,"Nanami Jjk", "nn"),
        Pictures(R.drawable.onepiece_robin,"Robin - One Piece", "nn"),
    )
    val currentPicture =  pictures[currentPictureIndex]

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(currentPicture.painter),
                contentScale = ContentScale.Crop
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .padding(bottom = 16.dp)
                .height(90.dp)
                .fillMaxWidth(0.75f)
                .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                .offset(x = 10.dp)

        ) {
            Text(
                text = currentPicture.title,
                fontSize = 24.sp,
            )
            Text(
                text = currentPicture.artist,
                fontWeight = FontWeight.Bold,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier
                .fillMaxWidth(.75f)
                .padding(top = 16.dp, bottom = 16.dp),
        ) {

            Button(onClick = {
                if (--currentPictureIndex < 0) {
                    currentPictureIndex = pictures.lastIndex
                } },
                modifier = Modifier.weight(.4f),
            ) {
                Text(
                    text = stringResource(R.string.previous),
                    fontSize = 15.sp,
                )
            }

            Spacer(modifier = modifier.weight(.2f))
            
            Button(onClick = {
                if(++currentPictureIndex > pictures.lastIndex){
                    currentPictureIndex = 0
                } },
                modifier = Modifier.weight(.4f)
            ) {
                Text(
                    text = stringResource(R.string.next),
                    fontSize = 15.sp,
                )
            }
        }

   }
}

//To structure data in clean way
data class Pictures(
    @DrawableRes val painter: Int,
    val title: String,
    val artist: String,
)

@Preview(showBackground = true)
@Composable
fun GalleryPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}