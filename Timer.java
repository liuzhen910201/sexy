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
 * 计时器  
 */    
public class Timer extends JFrame {    
     
    /** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
  
    private static final String INITIAL_LABEL_TEXT = "00:00:00 000";    
    //private static final String INITIAL_LABEL_TEXT=String.valueOf(x());
    // 计数线程    
    private CountingThread thread = new CountingThread();    
     
    // 记录程序开始时间    
    private long programStart = System.currentTimeMillis();    //起始时间
     
    // 程序一开始就是暂停的    
    private long pauseStart = programStart;    
     
    // 程序暂停的总时间    
    private long pauseCount = 0;    
    
    static int temp=0;//倒计时起始时间
    //文本框
    private JTextField text = new JTextField("请输入时间");
    
    private JButton button = new JButton("确定");
  //文本框
     
    private JLabel label = new JLabel(INITIAL_LABEL_TEXT); 
    private JButton startPauseButton = new JButton("开始");    
     
    private JButton resetButton = new JButton("清零");  
    
   private ActionListener buttonListener=new ActionListener() {
	   public void actionPerformed(ActionEvent e) { 
		   temp=Integer.parseInt(text.getText());
		   
		   //thread.stopped = true;
	   }
   };
   
   
    private ActionListener startPauseButtonListener = new ActionListener() {    
        public void actionPerformed(ActionEvent e) {    
            if (thread.stopped) {    
                pauseCount += (System.currentTimeMillis() - pauseStart); //暂停的时间
                thread.stopped = false;    
                startPauseButton.setText("暂停");    
            } else {    
                pauseStart = System.currentTimeMillis(); 
               
                thread.stopped = true;    
                startPauseButton.setText("继续");    
            }    
        }    
    };    
     
    private ActionListener resetButtonListener = new ActionListener() {    
        public void actionPerformed(ActionEvent e) {    
            pauseStart = programStart;    
            pauseCount = 0;    
            thread.stopped = true;    
            label.setText(INITIAL_LABEL_TEXT); 
            startPauseButton.setText("开始");    
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
        
        //
       
          
        
        /*
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(-1);
            }
        });*/
        //
     
        startPauseButton.addActionListener(startPauseButtonListener);    
        resetButton.addActionListener(resetButtonListener);    
        button.addActionListener(buttonListener);
        thread.start(); // 计数线程一直就运行着    
    }    
     
    // 为窗体面板添加边框    
    private void setupBorder() {    
        JPanel contentPane = new JPanel(new BorderLayout());    
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));    
        this.setContentPane(contentPane);    
    }    
     
    // 配置按钮    
    private void setupButtonsPanel() {    
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(null);
        panel.add(startPauseButton);    
        panel.add(resetButton); 
        panel.add(button);
        text.setBounds(40, 110, 200, 20);
        panel.add(text);
        
        add(panel, BorderLayout.SOUTH);    
    }    
     
    // 配置标签    
    private void setupLabel() {    
        label.setHorizontalAlignment(SwingConstants.CENTER);    
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 40));    
        this.add(label, BorderLayout.CENTER); 
        
    }    
     
    // 程序入口    
    public static void main(String[] args) {    
        try {    
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
     
        Timer frame = new Timer("计时器");  
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
        	//
        	/*
        	System.out.println("请输入需要设定的倒计时长：");
            Scanner input=new Scanner(System.in);
            //i可以设计成任意的大小，不是固定的
            int i=input.nextInt();
            */
            int j=0;
            j=temp;
            final long end=System.currentTimeMillis()+j*1000*60;//倒计时起始时间
            System.out.println(temp);
            //
            
            while (true) {    
                if (!stopped) { 
                	long elapsed=end-System.currentTimeMillis();//剩余时间
                    //long elapsed = System.currentTimeMillis() - programStart - pauseCount; //毫秒级别
                    //目前时间-起始时间-暂停总时间
                    label.setText(format(elapsed)); // 整理函数 将毫秒数格式化显示  
                }    
     
                try {    
                    sleep(1);  // 1毫秒更新一次显示  
                } catch (InterruptedException e) {    
                    e.printStackTrace();    
                    System.exit(1);    
                }    
            }    
        }    
     
        // 将毫秒数格式化    
        private String format(long elapsed) {    
            int hour, minute, second, milli;    
     
            milli = (int) (elapsed % 1000);    
            elapsed = elapsed / 1000;    
     
            second = (int) (elapsed % 60);    
            elapsed = elapsed / 60;    
     
            minute = (int) (elapsed % 60);    
            elapsed = elapsed / 60;    
     
            hour = (int) (elapsed % 60);    
            return String.format("%02d:%02d:%02d %03d", hour, minute, second, milli);//以时间形式返回    
        }    
    }    
}    
