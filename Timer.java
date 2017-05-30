package timme;

import javax.swing.*;
import java.awt.HeadlessException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;
import java.awt.event.ActionEvent;

/**
 * count down
 */
public class Timer extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String INITIAL_LABEL_TEXT = "00:00:00 000";

    //thread
    private CountingThread thread = new CountingThread();

    //開始時間
    private long programStart = System.currentTimeMillis();


    private long end;
    private static int hour;//count downの開始時間 時
    private static int minute;//count downの開始時間 分
    private static int second;//count downの開始時間 秒

    //テキスト
    private static JTextField text_hour = new JTextField("hour");
    private static JTextField text_minute = new JTextField("minute");
    private static JTextField text_second = new JTextField("second");
   //確認ボタン
    private JButton button = new JButton("OK");

    private JLabel label = new JLabel(INITIAL_LABEL_TEXT);
    //開始ボタン
    private JButton startPauseButton = new JButton("start");
    //リセットボタン
    private JButton resetButton = new JButton("reset");

    //OKポタンのListener
    private ActionListener buttonListener=new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try{
    			minute=Integer.parseInt(text_minute.getText());//minute
    			hour=Integer.parseInt(text_hour.getText());//hour
    			second=Integer.parseInt(text_second.getText());//second
    		}catch(NumberFormatException w){
    			Object[] options = { "OK", "CANCEL" };
    			JOptionPane.showOptionDialog(null, "Please make sure your input", "Warning",
    					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
    					null, options, options[0]);
    		}
    	}
   };


  //suspendとcontinueポタンのListener
    private ActionListener startPauseButtonListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (thread.stopped) {
                thread.stopped = false;
                startPauseButton.setText("suspend");
                end=System.currentTimeMillis()+minute*1000*60+hour*1000*60*60+second*1000;//count downの開始時間
            } else {
                thread.stopped = true;
                startPauseButton.setText("continue");
            }
        }
    };

  //startポタンのListener
    private ActionListener resetButtonListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            thread.stopped = true;
            label.setText(INITIAL_LABEL_TEXT);
            startPauseButton.setText("start");
        }
    };

    public Timer(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300, 300);
        setResizable(false);

        setupBorder();
        setupLabel();
        setupButtonsPanel();

        startPauseButton.addActionListener(startPauseButtonListener);
        resetButton.addActionListener(resetButtonListener);
        button.addActionListener(buttonListener);
        thread.start(); // threadは実行
    }

    // フレームの設置
    private void setupBorder() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
    }

    // ボタンの設置
    private void setupButtonsPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(null);
        panel.add(startPauseButton);
        panel.add(resetButton);
        panel.add(button);
        text_minute.setBounds(40, 110, 200, 20);

        panel.add(text_hour);//hour
        panel.add(text_minute);//minute
        panel.add(text_second);//second

        add(panel, BorderLayout.SOUTH);
    }

    // ラベル設置
    private void setupLabel() {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 40));
        this.add(label, BorderLayout.CENTER);

    }

    // 入口
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Timer frame = new Timer("count down");
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);

    }

    public class CountingThread extends Thread {

        public boolean stopped = true;

        private CountingThread() {
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                if (!stopped) {
                 long elapsed=end-System.currentTimeMillis();//残り時間
                    label.setText(format(elapsed)); //整理メソット
                }

                try {
                    sleep(1);  //1ミリ秒で一回に更新
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        //整理メソット
        private String format(long elapsed) {
            int hour, minute, second, milli;

            milli = (int) (elapsed % 1000);
            elapsed = elapsed / 1000;

            second = (int) (elapsed % 60);
            elapsed = elapsed / 60;

            minute = (int) (elapsed % 60);
            elapsed = elapsed / 60;

            hour = (int) (elapsed % 60);
            return String.format("%02d:%02d:%02d %03d", hour, minute, second, milli);//タイムの形式でリターン
        }
    }
}