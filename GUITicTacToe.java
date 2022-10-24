/* Xander Siruno-Nebel
 * Java Programming Period 6
 * 9/30/21
 * GUITicTacToe
 */
package GUITicTacToe;
//imports
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
public class GUITicTacToe implements ActionListener {
//vars
int[][] board = new int[3][3];
final int BLANK = 0;
final int X_MOVE = 1;
final int O_MOVE = 2;
final int X_TURN = 0;
final int O_TURN = 1;
int turn = X_TURN;
String xPlayerName = "X";
String oPlayerName = "O";
int xWins = 0;
int oWins = 0;
//containers
Container center = new Container();
Container north = new Container();
//JThings
JFrame frame = new JFrame();
JButton[][] button = new JButton[3][3];
JLabel xLabel = new JLabel("X's wins: 0");
JLabel oLabel = new JLabel("O's wins: 0");
JButton xChangeName = new JButton("Change X's Name.");
JButton oChangeName = new JButton("Change O's Name.");
JButton current;
JTextField xChangeField = new JTextField();
JTextField oChangeField = new JTextField();
public GUITicTacToe() {
frame.setSize(400,400);
// Center Grid Container
frame.setLayout(new BorderLayout());
center.setLayout(new GridLayout(3,3));
for (int i = 0; i < button.length; i++) {
for (int j = 0; j < button[0].length; j++) {
button[j][i] = new JButton(j+ ", " +i);
center.add(button[j][i]);
button[j][i].addActionListener(this);
}
}
frame.add(center, BorderLayout.CENTER);
//North Container
north.setLayout(new GridLayout(1,2));
north.add(xLabel);
north.add(oLabel);
north.add(xChangeName);
xChangeName.addActionListener(this);
north.add(xChangeField);
north.add(oChangeName);
oChangeName.addActionListener(this);
north.add(oChangeField);
frame.add(north, BorderLayout.NORTH);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
}
public static void main(String[] args) {
new GUITicTacToe();
}
@Override
public void actionPerformed(ActionEvent event) { //plays the game
boolean gridButton = false;
for (int i = 0; i < button.length; i++) {
for (int j = 0; j < button[0].length; j++) {
if(event.getSource().equals(button[j][i])) {
gridButton = true;
current = button[j][i];
if(board[j][i] == BLANK) {
if(turn == X_TURN) {
current.setText("X");
board[j][i] = X_MOVE;
turn = O_TURN;
current.setEnabled(false);
} else {
current.setText("O");
board[j][i] = O_MOVE;
turn = X_TURN;
current.setEnabled(false);
}
//Check for wins and ties
if(checkWin(X_MOVE) == true) {
//X wins!
xWins++;
clearBoard();
xLabel.setText(xPlayerName + " wins: " +
xWins);
}else if (checkWin(O_MOVE) == true) {
//O wins!
oWins++;
clearBoard();
oLabel.setText(oPlayerName + " wins: " +
oWins);
}else if(checkTie() == true) {
//Tie
}
}
}
}
}
if (gridButton == false) { //changes player name
if(event.getSource().equals(xChangeName) == true) { //changes x
player name
xPlayerName = xChangeField.getText();
xLabel.setText(xPlayerName + "'s wins: " + xWins);
} else if (event.getSource().equals(oChangeName) == true)
{//changes o player name
oPlayerName = oChangeField.getText();
oLabel.setText(oPlayerName + "'s wins: " + oWins);
}
}
}
public boolean checkWin(int player) { // checks if a player has won
if(board[0][0] == player && board[0][1] == player && board[0][2] ==
player) {
return true;
} else if(board[1][0] == player && board[1][1] == player && board[1][2]
== player) {
return true;
} else if(board[2][0] == player && board[2][1] == player && board[2][2]
== player) {
return true;
} else if(board[0][0] == player && board[1][0] == player && board[2][0]
== player) {
return true;
}else if(board[0][1] == player && board[1][1] == player && board[2][1]
== player) {
return true;
}else if(board[0][2] == player && board[1][2] == player && board[2][2]
== player) {
return true;
} else if(board[0][0] == player && board[1][1] == player && board[2][2]
== player) {
return true;
} else if(board[2][0] == player && board[1][1] == player && board[0][2]
== player) {
return true;
}
return false;
}
public boolean checkTie() { // checks if there is a tie
for (int column = 0; column < board.length; column++) {
for (int row = 0; row < board.length; row++) {
if(board[row][column] == BLANK) {
return false;
}
}
}
clearBoard(); // Kushal Rao also helped with this
return true;
}
public void clearBoard() {
for (int a = 0; a < board.length; a++) {
for (int b = 0; b < board[0].length; b++) {
board[a][b] = BLANK;
button[a][b].setText("");
button[a][b].setEnabled(true); // Kushal Rao helped me on
this give him an A
}
}
turn = X_TURN;
}
}
