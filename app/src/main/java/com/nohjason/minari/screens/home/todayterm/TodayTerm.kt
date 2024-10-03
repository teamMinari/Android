package com.nohjason.minari.screens.home.todayterm

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nohjason.minari.R
import com.nohjason.minari.network.response.GetAllTermsResponse
import com.nohjason.minari.network.response.Term
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.ui.theme.pretendard_medium
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TodayTerm(
    navController: NavController = rememberNavController(),
    item: Term
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .clickable {
                    navController.navigate(Screens.Term.rout + "/${item.termNm}")
                }
        ) {
            Text(
                text = item.termNm,
                fontSize = 17.sp,
                fontFamily = pretendard_medium
            )
            Spacer(modifier = Modifier.width(10.dp))
            val difficulty = item.termDifficulty[3].digitToInt()
            Log.d("TAG", "HomeScreen: $difficulty")
            for (i in 1..difficulty) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.dp),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.weight(0.1f))
            Icon(
                painter = painterResource(id = R.drawable.book_mark),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}

// SharedPreferences에서 마지막으로 저장된 날짜와 랜덤 리스트를 로드
fun getRandomItems(context: Context, allTerms: GetAllTermsResponse): List<Term> {
    val sharedPref = context.getSharedPreferences("random_items_prefs", Context.MODE_PRIVATE)
    val lastSavedDate = sharedPref.getString("last_saved_date", "") ?: ""
    val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    return if (currentDate != lastSavedDate) {
        // 날짜가 다르면 새로운 랜덤 리스트 생성
        val randomItems = allTerms.data.shuffled().take(5)
        saveRandomItems(context, randomItems, currentDate)
        randomItems
    } else {
        // 날짜가 같으면 저장된 리스트 불러오기
        loadRandomItems(context)
    }
}

// 랜덤 리스트를 SharedPreferences에 저장
fun saveRandomItems(context: Context, randomItems: List<Term>, currentDate: String) {
    val sharedPref = context.getSharedPreferences("random_items_prefs", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()

    // 랜덤 리스트와 현재 날짜를 저장
    val gson = Gson()
    val json = gson.toJson(randomItems)
    editor.putString("random_items", json)
    editor.putString("last_saved_date", currentDate)
    editor.apply()
}

// SharedPreferences에서 랜덤 리스트 불러오기
fun loadRandomItems(context: Context): List<Term> {
    val sharedPref = context.getSharedPreferences("random_items_prefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPref.getString("random_items", "")

    return if (json.isNullOrEmpty()) {
        emptyList()
    } else {
        val type = object : TypeToken<List<Term>>() {}.type
        gson.fromJson(json, type)
    }
}