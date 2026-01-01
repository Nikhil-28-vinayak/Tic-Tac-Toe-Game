package com.example.tictactoegamecohort.screens

import android.text.Highlights
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoegamecohort.data.CellState
import com.example.tictactoegamecohort.ui.theme.Blue100
import com.example.tictactoegamecohort.ui.theme.Blue200
import com.example.tictactoegamecohort.ui.theme.Blue300
import com.example.tictactoegamecohort.ui.theme.Blue400
import com.example.tictactoegamecohort.ui.theme.Blue700
import com.example.tictactoegamecohort.ui.theme.Blue800
import com.example.tictactoegamecohort.ui.theme.Blue900
import com.example.tictactoegamecohort.ui.theme.BlueA400
import com.example.tictactoegamecohort.ui.theme.BlueA700
import com.example.tictactoegamecohort.ui.theme.Purple40


@Composable
fun Cell(state: CellState, onClick: () -> Unit, highlights: Boolean) {
    val bgColor = if (highlights) Color.LightGray.copy(alpha = 0.8f) else Color.Transparent
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(enabled = state == CellState.EMPTY) { onClick() }
            .padding(3.dp),
        shape = RoundedCornerShape(10.dp),
//        elevation = CardDefaults.elevatedCardElevation(
//            defaultElevation = 10.dp,
//            pressedElevation = 15.dp
//        ),
        //border = BorderStroke(width = 2.dp, Blue200),
        colors = CardDefaults.cardColors(bgColor)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when (state) {
                    CellState.X -> "X"
                    CellState.O -> "O"
                    else -> ""
                },
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = if (!highlights) {
                    when (state) {
                        CellState.X -> Color.Red
                        CellState.O -> Color.Green
                        else -> Color.Unspecified
                    }
                } else Color.Red,
            )
        }
    }
}
