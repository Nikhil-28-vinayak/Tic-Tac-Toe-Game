package com.example.tictactoegamecohort.viewmodel

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecohort.data.CellState
import com.example.tictactoegamecohort.data.GameStatus
import com.example.tictactoegamecohort.data.Player

class GameViewModal : ViewModel() {
    //Board(3*3)
    var board = mutableStateListOf(
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
    )
        private set

    var currentPlayer by mutableStateOf(Player.X)
        private set

    var status by mutableStateOf(GameStatus.ONGOING)
        private set

    var message by mutableStateOf("Turn: X")
        private set

    var winningCells = mutableStateListOf<Pair<Int, Int>>()
        private set

    fun makeMove(row: Int, column: Int) {
        if (status != GameStatus.ONGOING) return
        if (board[row][column] != CellState.EMPTY) return

        board[row][column] = if (currentPlayer == Player.X) CellState.X else CellState.O

        //to switch Player
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X


        val winner = checkWinner()

        if (winner != null) {
            // check Win
            status = if (winner == Player.X) GameStatus.X_WON else GameStatus.O_WON
            message = if (winner == Player.X) "Player X Won" else "Player O Won"
            return
        } else {
            //check craw
            status = GameStatus.DRAW
            message = "Draw"
            return
        }

    }

    private fun checkWinner(): Player? {
        // for  row
        for (row in 0..2) {
            if ((board[row][0] != CellState.EMPTY) && (board[row][0] == board[row][1]) && (board[row][1] == board[row][2])) {
                winningCells.addAll(
                    listOf(
                        Pair(row, 0),
                        Pair(row, 1),
                        Pair(row, 2)
                    )
                )
                return if (board[row][0] == CellState.X) Player.X else Player.O
            }
        }
        // for column
        for (column in 0..2) {
            if ((board[0][column] != CellState.EMPTY) && (board[0][column] == board[1][column]) && (board[1][column] == board[2][column])) {
                winningCells.addAll(
                    listOf(
                        Pair(0, column),
                        Pair(1, column),
                        Pair(2, column)
                    )
                )
                return if (board[0][column] == CellState.X) Player.X else Player.O
            }
        }
        // for diagonal
        if ((board[0][2] != CellState.EMPTY) && (board[0][2] == board[1][1]) && (board[1][1] == board[2][0])) {
            winningCells.addAll(
                listOf(
                    Pair(0, 2),
                    Pair(1, 1),
                    Pair(2, 0)
                )
            )
            return if (board[0][0] == CellState.X) Player.X else Player.O
        }

        if ((board[0][0] != CellState.EMPTY) && (board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) {
            winningCells.addAll(
                listOf(
                    Pair(0, 0),
                    Pair(1, 1),
                    Pair(2, 2)
                )
            )
            return if (board[0][0] == CellState.X) Player.X else Player.O
        }

        return null

    }

    fun reStartGame() {
        for (row in 0..2) {
            for (column in 0..2) {
                board[row][column] = CellState.EMPTY
            }
        }
        currentPlayer = Player.X
        status = GameStatus.ONGOING
        message = "Turn X"
        winningCells.clear()
    }

    private fun isBoardFull(): Boolean {
        return board.all { row ->
            row.all {
                it != CellState.EMPTY
            }
        }
    }
}