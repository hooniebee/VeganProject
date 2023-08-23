package com.example.vegan.screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.example.vegan.AppDatabase
import com.example.vegan.R


fun loadImageBitmap(context: Context, imageUri: String?): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(Uri.parse(imageUri))
        return BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun AlbumScreen(navController: NavController) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in)
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val recordList by db.recordDao().getAll().collectAsState(initial = emptyList())
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    )
    {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(id = R.drawable.album),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 500.dp, height = 100.dp)
                    .fillMaxWidth()
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {

                items(recordList) { record ->
                    val bitmap = loadImageBitmap(context, record.photoPath)
                    val scope = rememberCoroutineScope()
                    bitmap?.let {
                        Card(
                            modifier = Modifier
                                .size(100.dp)
                                .shadow(2.dp)
                                .clickable { navController.navigate("review/${record.uid}", navOptions) },
                            shape = MaterialTheme.shapes.small
                        ) {
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .size(128.dp)
                                    .clip(shape = RoundedCornerShape(16.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}

