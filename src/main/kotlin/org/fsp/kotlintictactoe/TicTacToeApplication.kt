package org.fsp.kotlintictactoe

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class TicTacToeApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(TicTacToeApplication::class.java.getResource("tictactoe.fxml"))
        val scene = Scene(fxmlLoader.load())
        stage.title = "Tic Tac Toe"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(TicTacToeApplication::class.java)
}