/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Mathew alex
 */
public class Clientchat {
    
 private static JPanel contentPane;
 static JFrame chatWindow = new JFrame("Chat Application");
static JLabel lblNewLabel = new JLabel("CHAT APPLICATION");
static JLabel lblNewLabel_1 = new JLabel("ONLINE USER");
JScrollPane scrollPane = new JScrollPane();
static JList list = new JList();
static JButton btnNewButton = new JButton("SEND MESSAGE");
static JButton btnNewButton_1 = new JButton("DISSCONECT");
 static JTextField textField = new JTextField();
JScrollPane scrollPane_1 = new JScrollPane();
static JTextArea chatarea = new JTextArea();
JLabel lblNewLabel_2 = new JLabel("");
static BufferedReader in;
static PrintWriter out;
static Clientchat ww;
static String nam;
  static Socket soc;
//static ArrayList<String> userNames = new ArrayList<String>();

    //DefaultListModel dm = new DefaultListModel();
    
    Clientchat(){
   //chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   chatWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

WindowListener exitListener = new WindowAdapter() {

    @Override
    public void windowClosing(WindowEvent e) {
        int confirm = JOptionPane.showOptionDialog(
             null, "Are You Sure to Close Application?", 
             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
             JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            out.println("left the chat");
            try {
                soc.close();
            } catch (IOException ex) {
                Logger.getLogger(Clientchat.class.getName()).log(Level.SEVERE, null, ex);
            }
   System.exit(0);
        }
    }
};chatWindow.addWindowListener(exitListener);
   chatWindow.setBounds(100, 100, 618, 469);
   contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

chatWindow.setContentPane(contentPane);
contentPane.setLayout(null);


lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
lblNewLabel.setBackground(Color.BLACK);
lblNewLabel.setForeground(Color.BLACK);
lblNewLabel.setBounds(10, 22, 164, 27);
contentPane.add(lblNewLabel);

lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
lblNewLabel_1.setBounds(449, 36, 145, 34);
contentPane.add(lblNewLabel_1);

scrollPane.setBounds(449, 72, 145, 285);
contentPane.add(scrollPane);

list.setBackground(Color.white);
scrollPane.setViewportView(list);

btnNewButton.setBackground(Color.WHITE);
btnNewButton.setForeground(new Color(0, 0, 0));
btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
btnNewButton.setBounds(384, 381, 171, 33);
sendmessage();
contentPane.add(btnNewButton);

btnNewButton_1.setBackground(Color.WHITE);
btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
btnNewButton_1.setBounds(265, 22, 151, 26);
disconnectmessage();
contentPane.add(btnNewButton_1);


textField.setBounds(10, 378, 330, 44);
contentPane.add(textField);
textField.setColumns(10);


scrollPane_1.setBounds(10, 72, 429, 285);
contentPane.add(scrollPane_1);


chatarea.setEditable(false);
scrollPane_1.setViewportView(chatarea);


lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
lblNewLabel_2.setBounds(20, 49, 396, 21);
contentPane.add(lblNewLabel_2);

chatWindow.setVisible(true);

       

        textField.setEditable(false);

        chatarea.setEditable(false);

       

        //btnNewButton.addActionListener(new Listener());

        //textField.addActionListener(new Listener());

//btnNewButton_1.addActionListener(new Listener());



    }
    
    void startChat() throws Exception

    {

       String ipAddress = JOptionPane.showInputDialog(

                chatWindow,

                "Enter IP Address:",

                "IP Address Required!!",

                JOptionPane.PLAIN_MESSAGE);    

 

 soc = new Socket(ipAddress, 9806);

       in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

       out = new PrintWriter(soc.getOutputStream(), true);

       while (true)

       {

         String str = in.readLine();

           if (str.equals("NAMEREQUIRED"))

           {

           String name = JOptionPane.showInputDialog(

                       chatWindow,

                       "Enter a unique name:",

                       "Name Required!!",

                       JOptionPane.PLAIN_MESSAGE);

          

               out.println(name);

              

           }

           else if(str.equals("NAMEALREADYEXISTS"))

           {

           String name = JOptionPane.showInputDialog(

                       chatWindow,

                       "Enter another name:",

                       "Name Already Exits!!",

                       JOptionPane.WARNING_MESSAGE);

          

               out.println(name);

           }

           else if (str.startsWith("NAMEACCEPTED")) //nameacceptedmathew

           {

               textField.setEditable(true);

               lblNewLabel_2.setText("You are logged in as: "+str.substring(12));
               nam=str.substring(12);
               //userNames.add(str.substring(12));
          
        //Integer[] arr = new Integer[al.size()];
        //arr = al.toArray(arr);
  
        //for (Integer x : arr)
           // System.out.print(x + " ");
        //list.setModel(dm);
        //String[] a = new String[userNames.size()];
        //a = userNames.toArray(a);
    //for(String wa : a){
      //  System.out.println(wa);
    //dm.addElement(wa);
    //}
    
              chatWindow.setTitle(str.substring(12)+" 's chatbox");
              
              //dm.addElement(str.substring(12));
              //list.setModel(dm);
            
            //list.setListData(ans);
           }
           else if(str.startsWith("123456789"))
           
           {
           String s = str.substring(9);
           System.out.println(s);
           s=s.replace('[', ' ').replace(']',' ');
            System.out.println(s);
            String[] fans=s.split(",");
            list.setListData(fans);
           
           }
           else

           {

               chatarea.append(str + "\n");

           }

       }

   }
    
 

    public static void main(String[] args) throws Exception {
       ww = new Clientchat();
        ww.startChat();
    }

    public static void sendmessage() {
        btnNewButton.addActionListener(
        new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                sendmessageenter();
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }
        );
    }
    
    public static void sendmessageenter(){
    
    Clientchat.out.println(Clientchat.textField.getText());

            Clientchat.textField.setText("");
    
    }

    public static void disconnectmessage() {
        btnNewButton_1.addActionListener(
        new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    disconnectenter();
                    //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                } catch (IOException ex) {
                    Logger.getLogger(Clientchat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        
        
    }
    
    public static void disconnectenter() throws IOException{
   Clientchat.out.println("left the chat");
   soc.close();
   System.exit(0);
   
    
    }
}

//class Listener implements ActionListener

//{

      //@Override

      //public void actionPerformed(ActionEvent e) {

            // TODO Auto-generated method stub

          //  Clientchat.out.println(Clientchat.textField.getText());

            //Clientchat.textField.setText("");

      //}//
      
     
      
      //public void actionPerformed(ActionEvent l) {

            // TODO Auto-generated method stub

            //Clientchat.out.println(Clientchat.textField.getText());

            //Clientchat.textField.setText("");

      //}

     

