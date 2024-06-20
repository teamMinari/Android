package com.nohjason.minari.screens.quizstart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.screens.quiz.QuizPoint_box
import com.nohjason.minari.screens.quiz.data.QuestionData
import com.nohjason.minari.screens.quiz.data.TemporaryPoint
import com.nohjason.minari.ui.theme.MinariBlue

//폰트
//val pretendardFamily = FontFamily(
//    Font(R.font.pretendard_regular, FontWeight.Thin),
//    Font(R.font.pretendard_medium, FontWeight.Medium),
//    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
//    Font(R.font.pretendard_bold, FontWeight.Bold)
//)
val point = 1000
fun pont_add_text(){
    val lenum = (point.toString()).length
    for (n in 4..lenum step 2){

    }
}


@Composable
fun QuizScreen_not_start(
    user: TemporaryPoint
){
    val guide_text = buildAnnotatedString {
        append("현재")
        withStyle(style = SpanStyle(color = MinariBlue)) {
            append("자기 단어장")
        }
        append("에 저장된 단어가\n없어서 풀 수 없어요!")
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        QuizPoint_box(user)
//        Image(
//            painterResource(id = R.drawable.emoji_tired), contentDescription = null,
//            modifier = Modifier
//                .fillMaxSize(0.45f)
//                .padding(top = 133.dp))

        Text(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 27.dp),
            text = guide_text,
            textAlign = TextAlign.Center,
//            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold
        )

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .width(305.dp)
                .padding(top = 27.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF767AEE)
            )
        ) {
            Text(text = "단어 저장하러 가기 >",
//                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun QuizStartPreview() {
//    val navController = rememberNavController()
//    val question = QuestionData.getQuestion().first() // 첫 번째 질문을 사용
//    QuizScreen_start() // 질문을 QuizScreen_play 함수에 전달
//}
