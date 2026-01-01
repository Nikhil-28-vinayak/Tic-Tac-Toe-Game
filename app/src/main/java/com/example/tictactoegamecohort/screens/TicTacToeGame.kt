package com.example.tictactoegamecohort.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoegamecohort.ui.theme.Blue200
import com.example.tictactoegamecohort.ui.theme.Blue300
import com.example.tictactoegamecohort.ui.theme.Blue400
import com.example.tictactoegamecohort.ui.theme.Blue600
import com.example.tictactoegamecohort.ui.theme.Blue800
import com.example.tictactoegamecohort.viewmodel.GameViewModal

@Composable
fun TicTacToeGame(viewModal: GameViewModal) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF2196F3), Color(0xFF21CBF3)),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            "Tic Tac Toe Game",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = viewModal.message,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Board(
            board = viewModal.board,
            winningCells = viewModal.winningCells,
            onCellClick = { r, c ->
                viewModal.makeMove(row = r, column = c)
            }
        )
        Button(
            onClick = { viewModal.reStartGame() },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Blue600),
            elevation = ButtonDefaults.buttonElevation(4.dp)
        ) {
            Text("Restart Game", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
}