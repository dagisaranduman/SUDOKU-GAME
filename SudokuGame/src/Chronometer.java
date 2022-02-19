import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chronometer {

    private JButton resumeB;
    private JButton stopB;
    private JLabel timeLabel;

    private int passingTime = 0;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private Timer timer;
    private JFrame frame;

    public Chronometer() {

        frame = new JFrame("Chronometer");
        frame.setSize(420,290);
        frame.getContentPane().setBackground(Color.PINK);
        frame.setLocation(150, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

        resumeB = new JButton("RESUME");
        resumeB.setBounds(90,150,120,50);
        resumeB.setFont(new Font("Ariel",Font.BOLD,20));
        resumeB.setBackground(Color.BLACK);
        resumeB.setForeground(Color.YELLOW);
        resumeB.setFocusable(false);

        resumeB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                start();

            }
        });

        stopB = new JButton("STOP");
        stopB.setBounds(210,150,120,50);
        stopB.setFont(new Font("Ariel",Font.BOLD,20));
        stopB.setBackground(Color.BLACK);
        stopB.setForeground(Color.YELLOW);
        stopB.setFocusable(false);

        stopB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                stop();

            }
        });


        timeLabel = new JLabel();
        timeLabel.setBounds(90,50,240,100);
        timeLabel.setFont(new Font("Verdana",Font.BOLD,40));
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);


        frame.add(resumeB);
        frame.add(stopB);
        frame.add(timeLabel);

        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                passingTime = passingTime + 1000;
                hours = (passingTime/3600000) % 24;
                minutes = (passingTime/60000) % 60;
                seconds = (passingTime/1000) % 60;
                timeLabel.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));

            }

        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void restart() {
        timer.restart();
        passingTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        timeLabel.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
    }

    public void dispose() {
        frame.dispose();
    }

    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
}

