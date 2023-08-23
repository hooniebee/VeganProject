package com.example.vegan.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.example.vegan.R

data class SurveyQuestion(
    val question: String
)

@Composable
fun QuestionsScreen(navController: NavController) {

    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in) // 직접 리소스 ID로 애니메이션을 지정할 수도 있습니다.
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()

    val surveyQuestions = listOf(
        SurveyQuestion("나는 비건을 실천하는 중이거나 실천해 본 적이 있다"),
        SurveyQuestion("나는 원하는 식사 메뉴를 자유롭게 선택할 수 있는 환경에 놓여있다"),
        SurveyQuestion("지구 온난화와 같은 환경 문제에 관심이 많은 편이다"),
        SurveyQuestion("공장식 축산의 문제점에 대해 알고 있고 공감하는 편이다"),
        SurveyQuestion("주변에 비건을 실천하고 있는 가족이나 친구, 지인이 있다"),
        SurveyQuestion("평소 비건 음식에 관심이 있거나 비건 식당 등에 방문하는 편이다"),
        SurveyQuestion("한 명의 완전힌 비건보다 다수의 비건 지향자들이 있는 편이 낫다고 생각한다"),
        SurveyQuestion("육류가 과잉 생산, 과잉 소비되고 있다고 생각한다"),
        SurveyQuestion("나는 내가 할 수 있는 선에서 육류 소비를 줄여 볼 생각이 있다"),
        SurveyQuestion("나는 완전한 비건을 실천하고자 하는 확고한 결심이 있다")
    )


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.vegantest),
                contentDescription = "title",
                modifier = Modifier
                    .size(width = 500.dp, height = 150.dp)
                    .align(Alignment.CenterHorizontally)

            )
            var selectedCheckboxCount by remember { mutableStateOf(0) }
            var showResult by remember { mutableStateOf(false) }

            surveyQuestions.forEach { question ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = question.question,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.weight(1f)
                    )
                    var checkedState1 by remember { mutableStateOf(false) }
                    Checkbox(
                        checked = checkedState1,
                        onCheckedChange = {
                            checkedState1 = !checkedState1
                            if (checkedState1) {
                                selectedCheckboxCount++
                            } else {
                                selectedCheckboxCount--
                            }
                        }
                    )
                }
            }


            Button(
                onClick = {
                    showResult = true
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "제출")
            }

            if (showResult) {

                if (selectedCheckboxCount <= 2) {
                    navController.navigate("Flexitarian",navOptions)
                } else if (selectedCheckboxCount in 3..5) {
                    navController.navigate("Pesco",navOptions)
                } else if (selectedCheckboxCount in 6..8) {
                    navController.navigate("LactoOvo",navOptions)
                } else {
                    navController.navigate("Veganism",navOptions)
                }
            }
        }
    }
}

