package com.example.tuan2bt3

import android.os.Bundle
import androidx.compose.ui.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuan2bt3.ui.theme.Tuan2BT3Theme

val diem = 8.5
fun xepLoai(diem: Double): String {
    if (diem < 0 || diem > 10) {
        return "Không hợp lệ"
    } else if (diem >= 8.0) {
        return "Giỏi"
    } else if (diem >= 6.5) {
        return "Khá"
    } else if (diem >= 5.0) {
        return "Trung bình"
    } else {
        return "Yếu"
    }
}
fun mauChu(loai: String): Color {
    if (loai == "Giỏi") {
        return Color(0xFF2E7D32)
    } else if (loai == "Khá") {
        return Color(0xFF1565C0)
    } else if (loai == "Trung bình") {
        return Color(0xFFEF6C00)
    } else if (loai == "Yếu") {
        return Color(0xFFC62828)
    } else {
        return Color.Gray
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManHinhXepLoai() {
    val loai = xepLoai(diem)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Xếp loại học lực") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2A3F73),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F3F8))
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Điểm trung bình")
                Text(text = diem.toString(), fontSize = 48.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = loai,
                    color = mauChu(loai),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(mauChu(loai).copy(alpha = 0.15f))
                        .padding(12.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "KIỂM TRA THÊM",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                DongKiemTra(9.0)
                DongKiemTra(7.0)
                DongKiemTra(5.5)
                DongKiemTra(3.0)
                DongKiemTra(11.0)
            }
        }
    }
}

@Composable
fun DongKiemTra(d: Double) {
    val loai = xepLoai(d)
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Điểm $d")
        Text(text = loai, color = mauChu(loai), fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tuan2BT3Theme {
                    ManHinhXepLoai()

                }
            }
        }
    }


