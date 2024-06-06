import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimer extends JFrame implements ActionListener {
    private int minutes = 25;
    private int seconds = 0;
    private boolean running = false;
    private Timer timer;

    private JLabel timeDisplay;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    public PomodoroTimer() {
        setTitle("Pomodoro Timer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        timeDisplay = new JLabel("25:00", SwingConstants.CENTER);
        timeDisplay.setFont(new Font("Helvetica", Font.BOLD, 36));
        add(timeDisplay, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        panel.add(startButton);
        panel.add(stopButton);
        panel.add(resetButton);

        add(panel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> startTimer());
        stopButton.addActionListener(e -> stopTimer());
        resetButton.addActionListener(e -> resetTimer());

        timer = new Timer(1000, this);

        stopButton.setEnabled(false);
        resetButton.setEnabled(false);
    }

    private void startTimer() {
        if (!running) {
            timer.start();
            running = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            resetButton.setEnabled(true);
        }
    }

    private void stopTimer() {
        if (running) {
            timer.stop();
            running = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    private void resetTimer() {
        stopTimer();
        minutes = 25;
        seconds = 0;
        updateTimeDisplay();
        resetButton.setEnabled(false);
    }

    private void updateTimeDisplay() {
        String time = String.format("%02d:%02d", minutes, seconds);
        timeDisplay.setText(time);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (seconds == 0) {
            if (minutes == 0) {
                JOptionPane.showMessageDialog(this, "Time's up! Take a break!");
                resetTimer();
            } else {
                minutes--;
                seconds = 59;
            }
        } else {
            seconds--;
        }
        updateTimeDisplay();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PomodoroTimer().setVisible(true);
        });
    }
}
