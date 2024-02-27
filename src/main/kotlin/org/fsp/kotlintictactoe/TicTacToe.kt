package org.fsp.kotlintictactoe

class TicTacToe {

    private var plateau: Array<Array<String>> = Array(3) { Array(3) { " " } }
    private var joueurs: Array<Joueur> = arrayOf( Joueur("X"),  Joueur("O"))
    private var nbTour: Int = 0
    private var gagnant: Joueur? = null
    private var dernierCoup: Pair<Int, Int>? = null

    fun placerPion(x: Int, y: Int) {
        if (plateau[x][y] == " " && gagnant == null) {
            plateau[x][y] = joueurs[nbTour % joueurs.size].getPions()
            nbTour++
        }

        if (estGagnant()) {
            gagnant = joueurs[(nbTour - 1) % joueurs.size]
        }
    }

    fun getPlateau(): Array<Array<String>> {
        return plateau
    }

    private fun estGagnant(): Boolean {
        return (estGagnantLigne() || estGagnantColonne() || estGagnantDiagonale())
    }

    private fun estGagnantLigne(): Boolean {
        for (i in 0..2) {
            if (plateau[i][0] != " " && plateau[i][0] == plateau[i][1] && plateau[i][0] == plateau[i][2]) {
                return true
            }
        }
        return false
    }

    private fun estGagnantColonne(): Boolean {
        for (i in 0..2) {
            if (plateau[0][i] != " " && plateau[0][i] == plateau[1][i] && plateau[0][i] == plateau[2][i]) {
                return true
            }
        }
        return false
    }

    private fun estGagnantDiagonale(): Boolean {
        return  (plateau[0][0] != " " && plateau[0][0] == plateau[1][1] && plateau[0][0] == plateau[2][2]) ||
                (plateau[0][2] != " " && plateau[0][2] == plateau[1][1] && plateau[0][2] == plateau[2][0])
    }

    fun getGagnant(): Joueur? {
        return gagnant
    }

    fun getJoueurCourant() : Joueur {
        return joueurs[nbTour % 2];
    }

    fun clone(): TicTacToe {
        val clone = TicTacToe()
        var nouveauPlateau = Array(3) { Array(3) { " " } }
        for (i in 0..2) {
            for (j in 0..2) {
                nouveauPlateau[i][j] = plateau[i][j]
            }
        }
        clone.plateau = nouveauPlateau
        clone.nbTour = nbTour
        clone.gagnant = gagnant
        return clone
    }

    fun getDernierCoup(): Pair<Int, Int>? {
        return dernierCoup
    }

    fun estNul(): Boolean {
        return nbTour == 9
    }
}