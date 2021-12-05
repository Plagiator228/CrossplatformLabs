package UserInterface;

import LogicStuff.AdvancedFileWalker;
import LogicStuff.FileWalker;
import LogicStuff.IWalkable;
import LogicStuff.ThreadMonitor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JButton button1;
    private JPanel Lab7;
    private JTextArea textArea1;
    private JSpinner spinner1;
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton useExecutionServiceRadioButton;
    private JRadioButton useRegularThreadsRadioButton;
    private JTextField textField3;
    private static IWalkable walker;

    public MainForm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(useExecutionServiceRadioButton.isSelected()){
                    walker = new AdvancedFileWalker((int)spinner1.getValue(), textField1.getText(), Long.parseLong(textField3.getText()));
                }else{
                    walker =  new FileWalker((int)spinner1.getValue(), textField1.getText(), Long.parseLong(textField3.getText()));
                }
                walker.Initialize();
                ThreadMonitor tm = new ThreadMonitor(walker.getThreads());
                tm.BeginThreadMonitoring(textArea1, 10);
                Thread waiter = new Thread(()->walker.Start(textField2, tm));
                waiter.start();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab 7");
        MainForm form = new MainForm();
        frame.setContentPane(form.Lab7);
        ButtonGroup bg = new ButtonGroup();
        bg.add(form.useExecutionServiceRadioButton);
        bg.add(form.useRegularThreadsRadioButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(frame.getWidth(), 300);
    }
}
