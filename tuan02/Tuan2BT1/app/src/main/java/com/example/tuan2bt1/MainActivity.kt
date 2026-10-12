package com.example.tuan2bt1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuan2bt1.ui.theme.Tuan2BT1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tuan2BT1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Chỉ đổi dòng này: thay Greeting bằng CalculatorScreen
                    CalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Hàm tính toán: nhận 2 chuỗi số và phép toán, trả về chuỗi kết quả
fun calculate(a: String, b: String, op: Char): String {
    val x = a.toDoubleOrNull()
    val y = b.toDoubleOrNull()

    if (x == null || y == null) return "Vui lòng nhập số hợp lệ"

    val result = when (op) {
        '+' -> x + y
        '-' -> x - y
        '*' -> x * y
        '/' -> {
            if (y == 0.0) return "Không thể chia cho 0"
            x / y
        }
        else -> return ""
    }

    // Kết quả nguyên (vd 8.0) thì bỏ phần thập phân
    return if (result % 1.0 == 0.0) result.toLong().toString() else result.toString()
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    // State: thay đổi thì giao diện tự vẽ lại
    var soA by remember { mutableStateOf("") }
    var soB by remember { mutableStateOf("") }
    var ketQua by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Thực hành 03", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = soA,
            onValueChange = { soA = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OpButton("+", Color(0xFFE53935)) { ketQua = calculate(soA, soB, '+') }
            OpButton("-", Color(0xFFE0A93B)) { ketQua = calculate(soA, soB, '-') }
            OpButton("*", Color(0xFF5E35E0)) { ketQua = calculate(soA, soB, '*') }
            OpButton("/", Color(0xFF212121)) { ketQua = calculate(soA, soB, '/') }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = soB,
            onValueChange = { soB = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Kết quả: $ketQua",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp
        )
    }
}

// Nút phép toán, viết 1 lần dùng 4 lần
@Composable
fun OpButton(label: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(56.dp),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text = label, color = Color.White, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    Tuan2BT1Theme {
        CalculatorScreen()
    }
}