/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingamoba;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Andras Sarro <>
 */
public final class GameLogic {

    private int roundIndex = 1;
    private String actualPlayer;
    private String[][] signBoard;
    private JButton[] gameButtons;
    private JFrame gameBoard;
    private final int BUTTONS_PER_SIDE = 3;
    private final Font BUTTON_TEXT_FONT = new Font("Arial", Font.PLAIN, 40);

    public GameLogic() {
    }

    public GameLogic(JButton[] buttons, JFrame frame) {
        this.gameBoard = frame;
        gameButtons = new JButton[buttons.length];
        signBoard = new String[BUTTONS_PER_SIDE][BUTTONS_PER_SIDE];
        gameButtons = buttons;
        actualPlayer = "X";
        
        tableInit();
        
        for (JButton gameButton : gameButtons) {
            gameButton.addActionListener((ActionEvent e) -> {
                JButton clickedButton = (JButton) e.getSource();
                clickedButton.setFont(BUTTON_TEXT_FONT);
                clickedButton.setText(actualPlayer);
                clickedButton.setEnabled(false);
                nextMove(clickedButton);
                switchPlayer();
                roundIndex++;
            });
        }
    }

    public void tableInit() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                signBoard[i][j] = " ";
            }
        }
    }

    private void switchPlayer() {
        if (actualPlayer.equals("X")) {
            actualPlayer = "O";
        } else {
            actualPlayer = "X";
        }
    }

    private void nextMove(JButton button) {
        int row = Integer.parseInt(button.getName()) / BUTTONS_PER_SIDE;
        int column = Integer.parseInt(button.getName()) % BUTTONS_PER_SIDE;

        signBoard[row][column] = actualPlayer;

        if (gameWon()) {
            JOptionPane.showMessageDialog(gameBoard, "A játékot " + actualPlayer + " nyerte!", "Győzelem!", JOptionPane.INFORMATION_MESSAGE);
        }
        if (gameEnd()) {
            String[] options = {"Igen", "Nem"};
            JOptionPane.showMessageDialog(gameBoard, "Vége a játéknak!", "Vége", JOptionPane.INFORMATION_MESSAGE);
            int valasz = JOptionPane.showOptionDialog(gameBoard, "Új játék?", "Új játék?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            if (valasz == JOptionPane.YES_OPTION) {
                FoAblak newGame = new FoAblak();
                newGame.setVisible(true);
            } else {
                System.exit(0);
            }
        }
    }

    private boolean gameWon() {
        boolean won = false;

        for (String[] tabla1 : signBoard) {
            if (tabla1[0].equals(tabla1[1]) && tabla1[1].equals(tabla1[2]) && !tabla1[0].equals(" ")) {
                won = true;
            }
        }
        for (int j = 0; j < signBoard.length; j++) {
            if (signBoard[0][j].equals(signBoard[1][j]) && signBoard[1][j].equals(signBoard[2][j]) && !signBoard[0][j].equals(" ")) {
                won = true;
            }
        }
        if (signBoard[0][0].equals(signBoard[1][1]) && signBoard[1][1].equals(signBoard[2][2]) && !signBoard[0][0].equals(" ")) {
            won = true;
        }

        if (signBoard[0][2].equals(signBoard[1][1]) && signBoard[1][1].equals(signBoard[2][0]) && !signBoard[2][0].equals(" ")) {
            won = true;
        }

        return won;
    }

    private boolean gameEnd() {
        if (gameWon()) {
            return true;
        } else {
            return roundIndex == 9;
        }
    }
}
