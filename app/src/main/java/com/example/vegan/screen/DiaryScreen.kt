package com.example.vegan.screen

import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.example.vegan.AppDatabase
import com.example.vegan.R
import com.example.vegan.Record
import com.example.vegan.ui.theme.VeganTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DiaryScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VeganTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color.White
                ) { }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Memo(navController: NavController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val recordDao = db.recordDao()
    var photo: Uri? by remember { mutableStateOf(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            photo = uri
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(uri!!, flag)
        })
    var diary by remember { mutableStateOf("") }
    val today = LocalDate.now()
    val todayyear = today.format(DateTimeFormatter.ofPattern("yyyy"))
    val todaymonth = today.format(DateTimeFormatter.ofPattern("MM"))
    val todaydate = today.format(DateTimeFormatter.ofPattern("dd"))
    var todayrecord = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in)
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.doit),
                contentDescription = "doit",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
            )
            Text(text = todayyear + "년" + todaymonth + "월" + todaydate + "일", fontSize = 30.sp)

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
                value = diary,
                onValueChange = { diary = it },
                placeholder = { Text("오늘의 감상을 기록하세요..") })

            Button(onClick = { launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }) {
                Text(text = "음식 사진 올리기")
            }

            photo?.let {

                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(
                            context.contentResolver,
                            it
                        )
                    )
                } else {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                }

                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp)
                        .shadow(2.dp)
                )
            }


            Button(onClick = {
                val veganRecord =
                    Record(date = todayrecord, diary = diary, photoPath = photo?.toString())

                scope.launch(Dispatchers.IO) {
                    recordDao.insertAll(veganRecord)

                    diary = ""
                    photo = null


                }
            }) {
                Text(text = "추억에 저장")
            }

            Button(onClick = { navController.navigate("memory", navOptions) }) {
                Text(text = "지난 추억 보기")
            }
        }
    }
}

