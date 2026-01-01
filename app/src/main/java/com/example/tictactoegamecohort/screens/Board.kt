package com.example.tictactoegamecohort.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import com.example.tictactoegamecohort.data.CellState
import com.example.tictactoegamecohort.ui.theme.Blue300


@Composable
fun Board(
    board: List<MutableList<CellState>>,
    winningCells: List<Pair<Int, Int>>,
    onCellClick: (row: Int, column: Int) -> Unit
) {
    // for Responsive Board
    val config = LocalConfiguration.current
    val size = if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        (config.screenWidthDp.dp - 64.dp).coerceAtMost(480.dp)
    } else (config.screenWidthDp.dp - 48.dp).coerceAtMost(360.dp)

    Column(
        modifier = Modifier
            .size(size)
            .background(color = Color.White, shape = RoundedCornerShape(25.dp))
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        for (row in 0..2) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (column in 0..2) {
                    val highlight = winningCells.contains(Pair(row, column))
                    Cell(
                        state = board[row][column],
                        onClick = { onCellClick(row, column) },
                        highlights = highlight
                    )
                    if (column != 2) VerticalDivider(thickness = 2.dp, color = Color.LightGray)
                }
            }
            if (row != 2) HorizontalDivider(thickness = 3.dp, color = Color.LightGray)
        }
    }
}