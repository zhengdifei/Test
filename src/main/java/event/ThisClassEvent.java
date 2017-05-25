package event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *Java事件处理机制:自身类作为事件监听器
 *@author Winty(wintys@gmail.com) 
 *@version 2008-12-3
 */
class ThisClassEvent extends JFrame implements ActionListener{ 
    JButton btn;

    public ThisClassEvent(){
        super("Java事件监听机制");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn=new JButton("点击");
        btn.addActionListener(this);
        getContentPane().add(btn);

        setBounds(200,200,300,160);
        setVisible(true);
    }

    /**************************************/
    public void actionPerformed (ActionEvent e){
        Container c=getContentPane();
        c.setBackground(Color.red);
    }
    /**************************************/

    public static void main(String args[]){
        new ThisClassEvent();
    }
}
