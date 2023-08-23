package com.example.vegan.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.vegan.R


@Composable
fun FlexitarianScreen(navController: NavController) {

    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in) // 직접 리소스 ID로 애니메이션을 지정할 수도 있습니다.
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = painterResource(id = R.drawable.goflex),
                contentDescription = "플렉시테리언"
            )

            Image(
                painter = painterResource(id = R.drawable.flexitarian),
                contentDescription = "플렉시테리언"
            )

            Text(text = "당신은 '플렉시테리언' 입니다!")
            Text(text = "비건을 지향한다고 해서 모든 육류소비를 반드시 끊어야 하는 것은 아니예요!")
            Text(
                text = " 플렉시테리언은 기본적으로 비건 채식을 지향하지만, 상황에 따라 유연하게 육식도 하는 채식주의자를 뜻합니다. " +
                        " 직장 회식이나 피치 못 할 사교모임 등 선택의 여지가 전혀 없을 때만 육식을 하거나," +
                        " 동물 복지 농장 인증을 하는 농장에서 공급하는 고기를 적은 양만 먹는 등 다양하게 실천해 볼 수 있어요!"
            )
            Text(text = "완벽한 비건 한 명 보다, 10 명의 비건 지향자들이 더 나은 세상을 만듭니다! 주 1회라도 육식을 줄여보고 채식을 실천해 보는 것이 어떨까요?")
            Button(
                onClick = {
                    navController.navigate("Diary",navOptions)
                },
                modifier = Modifier.padding(16.dp)

            ) {
                Text(text = "실천하러 가기")
            }
            Button(
                onClick = {
                    navController.navigate("questions",navOptions)
                },
                modifier = Modifier.padding(16.dp)

            ) {
                Text(text = "테스트 다시하기")
            }
        }
    }
}

@Composable
fun Pesco(navController: NavController) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in) // 직접 리소스 ID로 애니메이션을 지정할 수도 있습니다.
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(painter = painterResource(id = R.drawable.gopesco), contentDescription = "페스코")
            Image(painter = painterResource(id = R.drawable.pesco), contentDescription = "페스코")

            Text(text = "당신은 '페스코 베지테리언' 입니다!")
            Text(text = "효리&상순 부부도 실천하고 있는 페스코 베지테리언!")
            Text(
                text = "상대적으로 더 많은 환경 파괴를 유발하는 육류 섭취는 엄격히 금지하되, 달걀, 유제품, 그리고 해산물까지만 섭취하는 형태의 채식입니다." +
                        " 1주일에 한 번 채식을 하면 1년에 15그루의 나무를 심는 효과가 있다고 합니다"
            )
            Text(text = "완벽한 비건 한 명 보다, 10 명의 비건 지향자들이 더 나은 세상을 만듭니다! 주 1회라도 육식을 줄여 보고 채식을 실천해 보는 것이 어떨까요?")
            Button(
                onClick = {
                    navController.navigate("Diary",navOptions)
                },
                modifier = Modifier.padding(16.dp)

            ) {
                Text(text = "실천하러 가기")
            }
            Button(
                onClick = {
                    navController.navigate("questions",navOptions)
                },
                modifier = Modifier.padding(16.dp)

            ) {
                Text(text = "테스트 다시하기")
            }
        }
    }
}

@Composable
fun LactoOvo(navController: NavController) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in) // 직접 리소스 ID로 애니메이션을 지정할 수도 있습니다.
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(painter = painterResource(id = R.drawable.golactco), contentDescription = "락토")
            Image(
                painter = painterResource(id = R.drawable.lactoovooo),
                contentDescription = "락토오보"
            )
            Text(text = "당신은 '락토오보 베지테리언' 입니다!")
            Text(text = "달걀과 우유, 등 유제품까지 섭취하는 베지테리언입니다.")
            Text(
                text = "모든 종류의 고기나 생선은 먹지 않으나, 달걀과 유제품은 섭취하므로 대부분의 빵과 디저트를 즐길 수 있습니다." +
                        " 1주일에 한 번 채식을 하면 1년에 15그루의 나무를 심는 효과가 있다고 합니다"
            )
            Text(text = "완벽한 비건 한 명 보다, 10 명의 비건 지향자들이 더 나은 세상을 만듭니다! 주 1회라도 육식을 줄여 보고 채식을 실천해 보는 것이 어떨까요?")
            Button(
                onClick = {
                    navController.navigate("Diary",navOptions)
                },
                modifier = Modifier.padding(16.dp)

            ) {
                Text(text = "실천하러 가기")
            }
            Button(
                onClick = {
                    navController.navigate("questions",navOptions)
                },
                modifier = Modifier.padding(16.dp)

            ) {
                Text(text = "테스트 다시하기")
            }
        }
    }
}

@Composable
fun Veganism(navController: NavController) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in) // 직접 리소스 ID로 애니메이션을 지정할 수도 있습니다.
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(painter = painterResource(id = R.drawable.title), contentDescription = "비건")
            Image(painter = painterResource(id = R.drawable.govegannn), contentDescription = "비건")

            Text(text = "당신은 '완전한 비건' 입니다!")
            Text(text = "대단하세요, 당신은 기후 위기를 해결하는 전사이자 동물권의 수호자입니다.")
            Text(text = "채식에 대한 의지와 육류 섭취에 대한 문제의식으로 똘똘 뭉쳐 있는 당신에게.. 비건을 추천해 드립니다!")
            Text(text = "고기 대신 콩고기를, 우유 대신 오트밀이나 두유를, 그밖에도 다양한 대체식품을 즐기며 비건음식의 세계에 빠져보세요")
            Text(text = "음식 외에도 우리가 쓰는 화장품 등에도 다양한 동물성 원료가 들어있답니다. 꼼꼼하게 체크해보고, 함께 실천할 수 있는 동료를 만나는 것이 지속적가능한 비건을 위한 키워드입니다!")

            Text(text = " 하지만 완벽한 비건 한 명 보다, 10 명의 비건 지향자들이 더 나은 세상을 만든다는 것! 완벽하게는 아니더라도 비건지향 라이프로 전환해보시는건 어떨까요?")
            Button(
                onClick = {
                    navController.navigate("Diary",navOptions)
                },
                modifier = Modifier.padding(16.dp)

            ) {
                Text(text = "실천하러 가기")
            }
            Button(
                onClick = {
                    navController.navigate("questions",navOptions)
                },
                modifier = Modifier.padding(16.dp)

            ) {
                Text(text = "테스트 다시하기")
            }
        }
    }
}
