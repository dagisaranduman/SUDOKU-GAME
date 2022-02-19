import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalDesign extends JFrame{
    boolean basla = true;
    int pb[][] = new int[9][9];
    int difficulty=1;
    private JTextField f[][]= new JTextField[9][9] ;
    private JPanel p[][]= new JPanel [3][3];
    JMenu menu,diff;
    JMenuItem NewGame, exit, submit, solve, Reset;
    JCheckBoxMenuItem zor,normal,kolay;
    int board[][]= SudokuGenerator.generate(1);



    public void submiticin(){

        pb = SudokuSolver.solve(pb);
        boolean isFine = true;
        int solved[][]= SudokuSolver.solve(board);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(f[i][j].getText().equals("")){
                    isFine = false;
                    break;
                }
                else if(Integer.parseInt(f[i][j].getText()) != solved[i][j]){//textfield dan gelen string deÄŸerini parsintager ile intager deÄŸerine Ã§evirerek solved deÄŸeriyle karÅŸÄ±laÅŸtÄ±rma
                    isFine = false;
                    break;
                }
            }
        }
        if(isFine && basla) JOptionPane.showMessageDialog(null, "You Won The Game");
        else JOptionPane.showMessageDialog(null, "You Lost The Game");
        basla = false;


    }

    public GraphicalDesign() {


        Chronometer chronometer = new Chronometer();


        JFrame frame = new JFrame("SUDOKU GAME");
        frame.setSize(500, 500);
        frame.setLocation(600, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);






        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Menu");
        diff = new JMenu("Difficulty");

        solve = new JMenuItem("Solve");
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int solved[][]= SudokuSolver.solve(board);
                for(int i=0;i<9;i++)
                {
                    for(int j = 0; j < 9; j++)
                    {
                        f[i][j].setText(String.valueOf(solved[i][j]));
                    }
                }
            }
        });

        Reset = new JMenuItem("Reset");
        Reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chronometer.dispose();
                frame.dispose();
                new GraphicalDesign ();
            }
        });





        NewGame = new JMenuItem("New Game");
        NewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                chronometer.start();
                chronometer.restart();
                chronometer.setVisible(true);

                int board[][]= SudokuGenerator.generate(difficulty);
                for(int k=0;k<9;k++)
                    for(int l=0;l<9;l++)
                        f[k][l].setEnabled(true);
                for(int i=0;i<9;i++)
                {
                    for(int j = 0; j < 9; j++)
                    {
                        if(board[i][j]!=0)
                        {
                            f[i][j].setText(String.valueOf(board[i][j]));
                            f[i][j].setEnabled(false);//generator tarafÄ±ndan girilen sayilara dokunmamak icin
                            f[i][j].setDisabledTextColor(Color.white);
                        }
                        else
                            f[i][j].setText("");
                    }

                }

            }
        });



        exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        submit = new JMenuItem("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                submiticin();//method
                frame.dispose();
                chronometer.dispose();
                new GraphicalDesign();

            }


        });

        zor = new JCheckBoxMenuItem("Hard");
        zor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty=3;
                kolay.setSelected(false);
                normal.setSelected(false);
            }
        });

        normal = new JCheckBoxMenuItem("Medium");
        normal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty=2;
                kolay.setSelected(false);
                zor.setSelected(false);
            }
        });

        kolay = new JCheckBoxMenuItem("Easy");
        kolay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty=1;
                zor.setSelected(false);
                normal.setSelected(false);
            }
        });





        menu.add(NewGame);
        menu.add(submit);
        menu.add(Reset);
        menu.add(solve);
        menu.add(exit);
        diff.add(kolay);
        diff.add(normal);
        diff.add(zor);



        mb.add(menu);
        mb.add(diff);
        frame.setJMenuBar(mb);




        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) { //griddeki sayÄ± girilecek kareleri tek tek dolasiyor
                f[x][y] = new JTextField(1);
                f[x][y].setBackground(Color.PINK);//kutulari boyadik
                f[x][y].setFont(new Font("Garamond Bold", Font.CENTER_BASELINE, 20));//girilen sayinin boyutu ve ozelligi
                f[x][y].setHorizontalAlignment(SwingConstants.CENTER);//girilen sayilari ortalayarak yazmak icin
            }
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                p[x][y] = new JPanel(new GridLayout(3, 3)); //3x3 gridleri olusturuyor(kucuk)





            }

        }

        frame.setLayout(new GridLayout(3, 3, 6, 6));// gridler icin aradaki bosluklar(buyuk) ve 3x3 boluyor

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {//3x3 leri dolasiyor icerideki
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {//3x3lerin icini dolasiyor
                        p[a][b].add(f[y + a * 3][x + b * 3]);

                    }
                }
                frame.add(p[a][b]);
                p[a][b].setBackground(Color.DARK_GRAY);//cerceve boyadik




            }
        }
        frame.setVisible(true);
        for(int a=0;a<=2;a++) {
            for(int u=0;u<=2;u++) {
                frame.add(p[u][a]);
            }
        }
    }


}






