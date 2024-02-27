package org.fsp.kotlintictactoe

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.text.Text

class GameController {

    @FXML
    private lateinit var btn00: Button

    @FXML
    private lateinit var btn01: Button

    @FXML
    private lateinit var btn02: Button

    @FXML
    private lateinit var btn10: Button

    @FXML
    private lateinit var btn11: Button

    @FXML
    private lateinit var btn12: Button

    @FXML
    private lateinit var btn20: Button

    @FXML
    private lateinit var btn21: Button

    @FXML
    private lateinit var btn22: Button

    @FXML
    private lateinit var boutons: Array<Array<Button>>

    @FXML
    private lateinit var grille: GridPane

    @FXML
    private lateinit var info: Text;

    @FXML
    private var partie: TicTacToe = TicTacToe()

    @FXML
    fun initialize() {
        boutons = arrayOf(
            arrayOf(btn00, btn01, btn02),
            arrayOf(btn10, btn11, btn12),
            arrayOf(btn20, btn21, btn22))

        for (colonne in 0..2) {
            for (ligne in 0..2) {
                boutons[colonne][ligne].setOnAction { handleButtonAction(colonne, ligne) }
            }
        }

        upudate()
    }

    @FXML
    fun handleButtonAction(colonne: Int, ligne: Int) {
        println("Button clicked: $colonne $ligne")
        partie.placerPion(colonne, ligne)
        upudate()
    }

    private fun upudate() {
        val plateau = partie.getPlateau()
        var gagnant = partie.getGagnant()

        if (gagnant != null) {
            info.text = "Le joueur ${gagnant.getPions()} a gagné"

        } else {
            info.text = "Tour du joueur ${partie.getJoueurCourant().getPions()}"
        }
        for (colonne in 0..2) {
            for (ligne in 0..2) {
                boutons[colonne][ligne].text = plateau[colonne][ligne]
                boutons[colonne][ligne].isDisable = gagnant != null
            }
        }
    }
}