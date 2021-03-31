/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogictest;

import javax.swing.JButton;
import javax.swing.JFrame;
import static org.junit.Assert.*;
import org.junit.Test;
import swingamoba.GameLogic;

/**
 *
 * @author Andras Sarro <>
 */
public class GameLogicTest {

    @Test
    public void gameLogicConstructorTest() {
        JFrame testFrame = new JFrame();
        JButton[] testButtons = {new JButton(), new JButton(), new JButton()};
        try {
        new GameLogic(testButtons, testFrame);
        } catch (Exception e) {
            fail(e.getMessage());
        }
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
        gl.getSignBoard()[1][2] = "O";
        gl.getSignBoard()[2][2] = "O";
        gl.getSignBoard()[0][2] = "O";

        assertEquals(true, gl.gameWon());
    }

    @Test
    public void gameEndTest() {
        GameLogic gl = new GameLogic();
        gl.tableInit();

        assertEquals(false, gl.gameEnd());

        gl.setRoundIndex(9);
        assertEquals(true, gl.gameEnd());

        gl.setRoundIndex(6);
        gl.getSignBoard()[1][2] = "O";
        gl.getSignBoard()[2][2] = "O";
        gl.getSignBoard()[0][2] = "O";

        assertEquals(true, gl.gameEnd());

        gl.tableInit();
        gl.getSignBoard()[1][1] = "X";
        gl.getSignBoard()[1][2] = "X";
        gl.getSignBoard()[1][0] = "X";

        assertEquals(true, gl.gameEnd());
    }

}
