package com.example.tictactoegamecohort.data

enum class CellState{
    EMPTY,
    X,
    O;
}
enum class Player{
    X,
    O;
}
enum class GameStatus{
    ONGOING,
    DRAW,
    X_WON,
    O_WON
}