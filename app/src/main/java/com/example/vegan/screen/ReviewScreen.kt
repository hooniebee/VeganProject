package com.example.vegan.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.vegan.AppDatabase
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavOptions
import com.example.vegan.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ReviewDetailScreen(navController: NavController, uid: Int) {
    val context = LocalContext.current
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in) // 직접 리소스 ID로 애니메이션을 지정할 수도 있습니다.
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()
    val db = remember { AppDatabase.getDatabase(context) }
    val recordDao = db.recordDao()
    val scope = rememberCoroutineScope()
    val recordList by recordDao.loadAllByIds(uid).collectAsState(initial = emptyList())
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        )

        {
            val outlineColor = Color.Green.copy(alpha = 0.1f)
            Image(
                painter = painterResource(id = R.drawable.memories),
                contentDescription = "", modifier = Modifier
                    .size(width = 500.dp, height = 150.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))


            Text(text = "이 날의 감상")
            Spacer(modifier = Modifier.height(16.dp))


            recordList.firstOrNull()?.let { record ->
                Text(text =  "${record.date}",fontSize = 30.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = " ${record.diary}", modifier = Modifier
                        .size(width = 400.dp, height = 300.dp)
                        .border(width = 400.dp, color = outlineColor)
                    .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    scope.launch(Dispatchers.IO) {
                        db.recordDao().delete(record)
                    }
                }) {
                    Text(text = "삭제")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }


            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = {
                        navController.navigate("diary", navOptions)
                    },
                    modifier = Modifier.padding(16.dp)

                ) {
                    Text(text = "기록 화면으로")
                }
            }

        }
    }
}



