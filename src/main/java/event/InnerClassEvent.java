package event;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *Java事件处理机制:内部类作为事件监听器
 *@author Winty(wintys@gmail.com) 
 *@version 2008-12-3
 */
class InnerClassEvent extends JFrame{ 
    JButton btn;

    public InnerClassEvent(){
        super("Java事件监听机制");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn=new JButton("点击");
        btn.addActionListener(new InnerClass());
        getContentPane().add(btn);

        setBounds(200,200,300,160);
        setVisible(true);
    }

    /*内部类*********************************/
    class InnerClass implements ActionListener{
        public void actionPerformed (ActionEvent e){
            Container c=getContentPane();
            c.setBackground(Color.red);
        }
    }
    /**************************************/

    public static void main(String args[]){
        new InnerClassEvent();
    }
}
