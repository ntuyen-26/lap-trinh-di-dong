package com.example.tuan2bt2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tuan2bt2.ui.theme.Tuan2BT2Theme

data class Student(
    val name: String,
    val email: String?,
    val phone: String?
)
val students = listOf(
    Student("Nguyễn Văn An", "an@sv.edu.vn", "0901234567"),
    Student("Trần Thị Bình", null, "0912888999"),
    Student("Lê Minh Chi", "chi@sv.edu.vn",null),
    Student("Phạm Quốc Dũng",null,null),
    Student("Võ Thanh Em", "em@sv.edu.vn","0933111222")
)

@Composable
fun StudentCard(student: Student){
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(16.dp)){
        Text(student.name, fontWeight = FontWeight.Bold)
        Text("Email: " + (student.email ?: "Chưa cập nhật"))
        Text("SĐT: " + (student.phone ?: "Chưa cập nhật"))
    }
}

@Composable
fun StudentList(modifier: Modifier =Modifier){
        Column(modifier = modifier.padding(16.dp)) {
            for (student in students) {
                StudentCard(student)
                Spacer(modifier = Modifier.height(12.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8EEF7))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Tổng: ${students.size} sinh viên")
                Text("Thiếu email: ${countMissingEmail()}")
            }
        }
    }

fun countMissingEmail(): Int {
    var count = 0
    for (student in students) {
        if (student.email == null) {
            count++
        }
    }
    return count
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tuan2BT2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF2C4A7C))
                                .padding(16.dp)
                        ) {
                            Text("Danh sách sinh viên", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                ) { innerPadding ->
                    StudentList(modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
}



