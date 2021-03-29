/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogictest;

import static org.junit.Assert.*;
import org.junit.Test;
import swingamoba.GameLogic;

/**
 *
 * @author Andras Sarro <>
 */
public class GameLogicTest {

    public GameLogicTest() {
    }

    @Test
    public void constructorTest() {

    }

    @Test
    public void tableInitTest() {
        GameLogic gl = new GameLogic();
        gl.tableInit();

        for (String[] strings : gl.getSignBoard()) {
            for (String string : strings) {
                assertEquals(" ", string);
            }
        }
    }

    @Test
    public void switchPlayerTest() {
        GameLogic gl = new GameLogic();
        gl.setActualPlayer("X");
        gl.switchPlayer();

        assertEquals("O", gl.getActualPlayer());

        gl.setActualPlayer("O");
        gl.switchPlayer();

        assertEquals("X", gl.getActualPlayer());
    }

    @Test
    public void gameWonTest() {
        GameLogic gl = new GameLogic();
        gl.setRoundIndex(7);
        gl.tableInit();

        assertEquals(false, gl.gameWon());

        gl.getSignBoard()[0][0] = "X";
        gl.getSignBoard()[0][1] = "X";
        gl.getSignBoard()[0][2] = "X";

        assertEquals(true, gl.gameWon());

        gl.tableInit();

        gl.getSignBoard()[1][2] = "O";
        gl.getSignBoard()[2][2] = "O";
        gl.getSignBoard()[0][2] = "O";

        assertEquals(true, gl.gameWon());

        gl.tableInit();
        gl.getSignBoard()[0][1] = "X";
        gl.getSignBoard()[0][1] = "O";
        gl.getSignBoard()[0][1] = "X";

        assertEquals(false, gl.gameWon());

        gl.tableInit();
        gl.setRoundIndex(2);
        gl.getSignBoard()[1][2] = "O";
        gl.getSignBoard()[2][2] = "O";
        gl.getSignBoard()[0][2] = "O";
        
        assertEquals(false, gl.gameWon());
    }

    @Test
    public void gameEndTest() {
        GameLogic gl = new GameLogic();
        gl.tableInit();

        assertEquals(false, gl.gameEnd());

        gl.setRoundIndex(9);
        assertEquals(true, gl.gameEnd());

        gl.setRoundIndex(5);
        gl.getSignBoard()[1][2] = "O";
        gl.getSignBoard()[2][2] = "O";
        gl.getSignBoard()[0][2] = "O";

        assertEquals(true, gl.gameEnd());

        gl.tableInit();
        gl.setRoundIndex(3);
        gl.getSignBoard()[1][1] = "X";
        gl.getSignBoard()[1][2] = "X";
        gl.getSignBoard()[1][0] = "X";

        assertEquals(false, gl.gameEnd());
    }

}
