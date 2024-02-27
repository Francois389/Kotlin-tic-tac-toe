package org.fsp.kotlintictactoe

/**
 * Stocke les différents états du jeu pour pouvoir revenir en arrière
 */
class TimeTravel {

    private var etats: MutableList<TicTacToe> = mutableListOf(TicTacToe())

    private var etatCourant: Int = 0

    fun placerPion(x: Int, y: Int) {
        val dernierEtat = etats[etatCourant]
        val nouveauEtat = dernierEtat.clone()

        nouveauEtat.placerPion(x, y)

        etats = etats.subList(0, etatCourant + 1)
        etats.add(nouveauEtat)
        etatCourant++
    }

    fun getPlateau(): Array<Array<String>> {
        return etats[etatCourant].getPlateau()
    }

    fun getGagnant(): Joueur? {
        return etats[etatCourant].getGagnant()
    }

    fun getJoueurCourant(): Joueur {
        return etats[etatCourant].getJoueurCourant()
    }

    fun getEtats(): MutableList<TicTacToe> {
        return etats
    }

    fun revenirEnArriere(index : Int) {
        etatCourant = index
    }

    fun estNul(): Boolean {
        return etats[etatCourant].estNul()
    }
}