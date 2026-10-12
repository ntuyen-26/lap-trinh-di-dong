package com.example.tuan2bt4

import android.os.Bundle
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuan2bt4.ui.theme.Tuan2BT4Theme

fun xepLoai(diem: Double): String{
    if (diem < 0 || diem >10) {
        return "Không hợp lệ"
    } else if (diem >= 8.0) {
        return "Giỏi"
    } else if (diem>=6.5){
        return "Khá"
    } else if (diem>=5.0) {
        return "Trung bình"
    } else {
        return "Yếu"
    }
}

fun mauChu(loai: String): Color {
    if (loai == "Giỏi") {
        return Color(0xFF2E7D32)
    } else if(loai == "Khá"){
        return Color (0xFF1565C0)
    } else if (loai == "Trung bình"){
        return Color (0xFFEF6C00)
    } else if (loai == "Yếu"){
        return Color (0xFFC62828)
    } else {
        return Color.Gray
    }
}

class Student(val name: String, val score: Double?)

val danhSach = listOf (
    Student("Nguyễn Văn An", 9.0),
    Student("Trần Thị Bình", 7.2),
    Student ("Lê Minh Chi", null),
    Student ("Phạm Quốc Dũng", 4.5),
    Student ("Võ Thanh Em", 5.8)
)
fun tinhThongKe(): List<Double>{
    var tong = 0.0
    var soCoDiem =0
    var soGioi = 0
    var soChuaCo = 0
    var caoNhat =0.0

    for (sv in danhSach){
        val d=sv.score
        if (d==null){
            soChuaCo = soChuaCo +1
        } else {
            tong = tong +d
            soCoDiem = soCoDiem +1
            if (d>=8.0){
                soGioi = soGioi + 1
            }
            if (d>caoNhat){
                caoNhat  = d
            }
        }
    }
    val trungBinh = tong / soCoDiem
    return listOf(trungBinh, soGioi.toDouble(), soChuaCo.toDouble(), caoNhat)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManHinhBangDiem() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bảng điểm lớp") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2A3F73),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        val tk = tinhThongKe()
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
                    .background(Color(0xFF2A3F73))
                    .padding(16.dp)
            ) {
                Text("Điểm trung bình lớp", color = Color.White, fontSize = 13.sp)
                Text(
                    text = String.format("%.2f", tk[0]),
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Giỏi", color = Color.White, fontSize = 12.sp)
                        Text(tk[1].toInt().toString(), color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Column {
                        Text("Chưa có điểm", color = Color.White, fontSize = 12.sp)
                        Text(tk[2].toInt().toString(), color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Column {
                        Text("Cao nhất", color = Color.White, fontSize = 12.sp)
                        Text(tk[3].toString(), color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("DANH SÁCH · 5 SINH VIÊN", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            for (sv in danhSach) {
                val d = sv.score
                val loai = if (d == null) "Chưa có điểm" else xepLoai(d)
                val diemChu = if (d == null) "—" else d.toString()
                val mau = if (d == null) Color.Gray else mauChu(loai)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(sv.name, fontWeight = FontWeight.Bold)
                        Text(loai, color = mau, fontSize = 13.sp)
                    }
                    Text(diemChu, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tuan2BT4Theme {
                ManHinhBangDiem()
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tuan2BT4Theme {
        Greeting("Android")
    }
}