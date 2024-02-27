package org.fsp.kotlintictactoe

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ListView
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

    private var partie: TimeTravel = TimeTravel()

    @FXML
    private var listeBoutonsVoyageTemporel : ListView<Button> = ListView()

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

        // Mise à jour des textes
        if (gagnant != null) {
            info.text = "Le joueur ${gagnant.getPions()} a gagné"
        } else if (partie.estNul()) {
            info.text = "Match nul"
        } else {
            info.text = "Tour du joueur ${partie.getJoueurCourant().getPions()}"
        }

        // Mise à jour de la grille
        for (colonne in 0..2) {
            for (ligne in 0..2) {
                boutons[colonne][ligne].text = plateau[colonne][ligne]
                boutons[colonne][ligne].isDisable = gagnant != null
            }
        }

        // Mise à jour de la liste des boutons de voyage temporel
        listeBoutonsVoyageTemporel.items.clear()

        var bouton = Button("Retour au début")
        bouton.setOnAction { handleVoyageTemporel(0) }
        listeBoutonsVoyageTemporel.items.add(bouton)

        val etats = partie.getEtats()

        for (i in 1 until etats.size) {
            bouton = Button("Tour $i (${etats[i-1].getJoueurCourant().getPions()})")
            if (i == etats.size - 1) {
                bouton.isDisable = true
            }
            bouton.setOnAction { handleVoyageTemporel(i) }
            listeBoutonsVoyageTemporel.items.add(bouton)
        }
    }

    private fun handleVoyageTemporel(i: Int): ActionEvent? {
        partie.revenirEnArriere(i)
        upudate()
        return null
    }
}