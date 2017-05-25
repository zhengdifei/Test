package event;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *Java事件处理机制:匿名内部类作为事件监听器
 *@author Winty(wintys@gmail.com) 
 *@version 2008-12-3
 */
class AnonymousEvent extends JFrame{ 
    JButton btn;

    public AnonymousEvent(){
        super("Java事件监听机制");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn=new JButton("点击");
        /*匿名内部类******************************/
        btn.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Container c=getContentPane();
                    c.setBackground(Color.red);
                }
            }
        );
        /***************************************/
        getContentPane().add(btn);

        setBounds(200,200,300,160);
        setVisible(true);
    }

    public static void main(String args[]){
        new AnonymousEvent();
    }
}
