package event;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *Java事件处理机制:外部类作为事件监听器
 *@author Winty(wintys@gmail.com) 
 *@version 2008-12-3
 */
class OuterClassEvent extends JFrame{ 
    JButton btn;

    public OuterClassEvent(){
        super("Java事件监听机制");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn=new JButton("点击");
        btn.addActionListener(new OuterClass(this));
        getContentPane().add(btn);

        setBounds(200,200,300,160);
        setVisible(true);
    }

    public static void main(String args[]){
        new OuterClassEvent();
    }
} 

/*外部类*********************************/
class OuterClass implements ActionListener{
    OuterClassEvent oce;

    public OuterClass(OuterClassEvent oce){
        this.oce = oce;
    }

    public void actionPerformed(ActionEvent e){
        Container c=oce.getContentPane();
        c.setBackground(Color.red);
    }
}