import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {
    private final CustomBtn[] BUTTONS;
    private boolean step = false;
    private int winnerSide = 0;
    private JLabel turnLbl;

    public TicTacToe() {
        super("CAZ (2 players)");
        setBounds(0, 0, 215, 220);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        setLocation(x, y);

        setLayout(null);

        this.BUTTONS = new CustomBtn[9];
        setButtons();
        setVisible(true);
        setLabel();

        getContentPane().add(turnLbl);
        tap(this.BUTTONS);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setLabel() {
        Font font = new Font("Century Gothic", Font.BOLD, 14);
        turnLbl = new JLabel("Turn: Crosses");
        turnLbl.setFont(font);
        turnLbl.setBounds(45, 80, 200, 170);
    }

    private void setButtons() {
        BUTTONS[0] = new CustomBtn();
        BUTTONS[0].setBounds(20, 20, 50, 30);
        getContentPane().add(BUTTONS[0]);
        BUTTONS[1] = new CustomBtn();
        BUTTONS[1].setBounds(80, 20, 50, 30);
        getContentPane().add(BUTTONS[1]);
        BUTTONS[2] = new CustomBtn();
        BUTTONS[2].setBounds(140, 20, 50, 30);
        getContentPane().add(BUTTONS[2]);

        BUTTONS[3] = new CustomBtn();
        BUTTONS[3].setBounds(20, 60, 50, 30);
        getContentPane().add(BUTTONS[3]);
        BUTTONS[4] = new CustomBtn();
        BUTTONS[4].setBounds(80, 60, 50, 30);
        getContentPane().add(BUTTONS[4]);
        BUTTONS[5] = new CustomBtn();
        BUTTONS[5].setBounds(140, 60, 50, 30);
        getContentPane().add(BUTTONS[5]);

        BUTTONS[6] = new CustomBtn();
        BUTTONS[6].setBounds(20, 100, 50, 30);
        getContentPane().add(BUTTONS[6]);
        BUTTONS[7] = new CustomBtn();
        BUTTONS[7].setBounds(80, 100, 50, 30);
        getContentPane().add(BUTTONS[7]);
        BUTTONS[8] = new CustomBtn();
        BUTTONS[8].setBounds(140, 100, 50, 30);
        getContentPane().add(BUTTONS[8]);
    }

    private void tap(CustomBtn[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            int index = i;
            buttons[i].addActionListener(e -> {
                if (!step) {
                    turnLbl.setText("Turn: Zeroes");
                    buttons[index].setText("x");
                    buttons[index].setEnabled(false);
                    buttons[index].isTapByCross = true;
                    buttons[index].isTapByZero = false;
                    analyzeStates(index);
                    step = true;
                } else {
                    turnLbl.setText("Turn: Crosses");
                    buttons[index].setText("o");
                    buttons[index].setEnabled(false);
                    buttons[index].isTapByZero = true;
                    buttons[index].isTapByCross = false;
                    analyzeStates(index);
                    step = false;
                }
            });
        }
    }

    // оценка последовательностей постановок крестиков и ноликов
    private void analyzeStates(int index) {
        if (BUTTONS[index].isTapByCross) {
            switchingCross(index);
        } else if (BUTTONS[index].isTapByZero) {
            switchingZero(index);
        }
        if (checkForDraw()) {
            winnerSide = 3;
            showWindow();
        }
    }

    private void showWindow() {
        DialogWin dialog = new DialogWin(TicTacToe.this, winnerSide);
        dialog.setVisible(true);
        clearButtons();
    }

    private boolean checkForDraw() {
        int cnt = 0;
        for (CustomBtn btn : BUTTONS) {
            if (btn.isTapByCross || btn.isTapByZero) {
                cnt++;
            }
        }
        return (cnt == BUTTONS.length);
    }

    private void switchingCross(int index) {
        switch (index) {
            case 0:
                if (BUTTONS[index + 1].isTapByCross && BUTTONS[index + 2].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index + 3].isTapByCross && BUTTONS[index + 6].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index + 4].isTapByCross && BUTTONS[index + 8].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                }
                break;
            case 1:
                if (BUTTONS[index - 1].isTapByCross && BUTTONS[index + 1].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index + 3].isTapByCross && BUTTONS[index + 6].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                }
                break;
            case 2:
                if (BUTTONS[index - 1].isTapByCross && BUTTONS[index - 2].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index + 3].isTapByCross && BUTTONS[index + 6].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index + 2].isTapByCross && BUTTONS[index + 4].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                }
                break;
            case 3:
                if (BUTTONS[index + 1].isTapByCross && BUTTONS[index + 2].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByCross && BUTTONS[index + 3].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                }
                break;
            case 4:
                if (BUTTONS[index - 1].isTapByCross && BUTTONS[index + 1].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByCross && BUTTONS[index + 3].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index - 4].isTapByCross && BUTTONS[index + 4].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index - 2].isTapByCross && BUTTONS[index + 2].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                }
                break;
            case 5:
                if (BUTTONS[index - 1].isTapByCross && BUTTONS[index - 2].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByCross && BUTTONS[index + 3].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                }
                break;
            case 6:
                if (BUTTONS[index + 1].isTapByCross && BUTTONS[index + 2].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByCross && BUTTONS[index - 6].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index - 2].isTapByCross && BUTTONS[index - 4].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                }
                break;
            case 7:
                if (BUTTONS[index - 1].isTapByCross && BUTTONS[index + 1].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByCross && BUTTONS[index - 6].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                }
                break;
            case 8:
                if (BUTTONS[index - 1].isTapByCross && BUTTONS[index - 2].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByCross && BUTTONS[index - 6].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                } else if (BUTTONS[index - 4].isTapByCross && BUTTONS[index - 8].isTapByCross) {
                    winnerSide = 1;
                    showWindow();
                }
                break;
        }
    }

    private void switchingZero(int index) {
        switch (index) {
            case 0:
                if (BUTTONS[index + 1].isTapByZero && BUTTONS[index + 2].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index + 3].isTapByZero && BUTTONS[index + 6].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index + 4].isTapByZero && BUTTONS[index + 8].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                }
                break;
            case 1:
                if (BUTTONS[index - 1].isTapByZero && BUTTONS[index + 1].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index + 3].isTapByZero && BUTTONS[index + 6].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                }
                break;
            case 2:
                if (BUTTONS[index - 1].isTapByZero && BUTTONS[index - 2].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index + 3].isTapByZero && BUTTONS[index + 6].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index + 2].isTapByZero && BUTTONS[index + 4].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                }
                break;
            case 3:
                if (BUTTONS[index + 1].isTapByZero && BUTTONS[index + 2].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByZero && BUTTONS[index + 3].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                }
                break;
            case 4:
                if (BUTTONS[index - 1].isTapByZero && BUTTONS[index + 1].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByZero && BUTTONS[index + 3].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index - 4].isTapByZero && BUTTONS[index + 4].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index - 2].isTapByZero && BUTTONS[index + 2].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                }
                break;
            case 5:
                if (BUTTONS[index - 1].isTapByZero && BUTTONS[index - 2].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByZero && BUTTONS[index + 3].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                }
                break;
            case 6:
                if (BUTTONS[index + 1].isTapByZero && BUTTONS[index + 2].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByZero && BUTTONS[index - 6].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index - 2].isTapByZero && BUTTONS[index - 4].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                }
                break;
            case 7:
                if (BUTTONS[index - 1].isTapByZero && BUTTONS[index + 1].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByZero && BUTTONS[index - 6].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                }
                break;
            case 8:
                if (BUTTONS[index - 1].isTapByZero && BUTTONS[index - 2].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index - 3].isTapByZero && BUTTONS[index - 6].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                } else if (BUTTONS[index - 4].isTapByZero && BUTTONS[index - 8].isTapByZero) {
                    winnerSide = 2;
                    showWindow();
                }
                break;
        }
    }

    private void clearButtons() {
        for (CustomBtn btn : BUTTONS) {
            btn.isTapByZero = false;
            btn.isTapByCross = false;
            btn.setText("");
            btn.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

class CustomBtn extends JButton {
    boolean isTapByCross = false;
    boolean isTapByZero = false;
}

class DialogWin extends JDialog {
    public DialogWin(JFrame owner, int winnerSide) {
        super(owner, "Result", true);
        if (winnerSide == 1) {
            add(new JLabel(
                            "<html><center><h1><i>Winner: </i>"
                                    + "Crosses"
                                    + "</h1></center></html>"),
                    BorderLayout.CENTER);
        } else if (winnerSide == 2) {
            add(new JLabel(
                            "<html><center><h1><i>Winner: </i>"
                                    + "Zeroes"
                                    + "</h1></center></html>"),
                    BorderLayout.CENTER);
        } else if (winnerSide == 3) {
            add(new JLabel(
                            "<html><center><h1><i>Draw</i>"
                                    + "</h1></center></html>"),
                    BorderLayout.CENTER);
        }

        JButton ok = new JButton("OK");
        ok.addActionListener(event -> setVisible(false));

        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        setSize(230, 100);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2 + 140);
        this.setLocation(x, y);
    }
}