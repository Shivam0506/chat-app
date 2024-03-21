/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Mathew alex
 */
public class Serverchat {
    
     

      static ArrayList<String> userNames = new ArrayList<String>();

      static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

     

      public static void main(String[] args) throws Exception{

            // TODO Auto-generated method stub

           System.out.println("Waiting for clients..."); 

           ServerSocket ss = new ServerSocket(9806);

           while (true)

           {

             Socket soc = ss.accept();

               System.out.println("Connection established");

             ConversationHandler handler = new ConversationHandler(soc);

             handler.start();

           }

            

      }
    
}

class ConversationHandler extends Thread

{

    Socket socket;

    BufferedReader in;

    PrintWriter out;

    String name;

    //PrintWriter pw;

    //static FileWriter fw;

    //static BufferedWriter bw;

  

   public ConversationHandler(Socket socket) throws IOException {

        this.socket = socket;

        //fw = new FileWriter("C:\\Users\\Abhay\\Desktop\\ChatServer-Logs.txt",true);

        //bw = new BufferedWriter(fw);

        //pw = new PrintWriter(bw,true);

    }

    @Override
   public void run()

   {

        try

        {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);
            
           

            int count = 0;

            while (true)

            {

               if(count > 0)

               {

                    out.println("NAMEALREADYEXISTS");

               }

               else

               {

                    out.println("NAMEREQUIRED");

               }

            

               name = in.readLine();

            

               if (name == null)

               {

                   return;

               }

        

               if (!Serverchat.userNames.contains(name))

               {

                  Serverchat.userNames.add(name);
                  //Clientchat.update();
                  //System.out.println(Serverchat.userNames.toString());
//Clientchat.list.setModel(dm);
       //String[] a = new String[Serverchat.userNames.size()];
       //a = Serverchat.userNames.toArray(a);
    //for(String wa : a){
       // System.out.println(wa);
    //w.addElement(wa);
    //}
    //Clientchat.list.setModel(w);
                  break;

               }

             count++;

           }

           

            out.println("NAMEACCEPTED"+name);  //nameacceptmathew

            Serverchat.printWriters.add(out);
            
            for (PrintWriter writer : Serverchat.printWriters) {

                    writer.println("123456789"+ Serverchat.userNames);

                }

           

            while (true)

            {

                String message = in.readLine();

               

                if (message == null)

                {

                    return;

                }

               

                //pw.println(name + ": " + message);
                
                if(message.equals("left the chat")){
                    Serverchat.printWriters.remove(out);
                    Serverchat.userNames.remove(name);
                for (PrintWriter writer : Serverchat.printWriters) {

                    writer.println("123456789"+ Serverchat.userNames);

                }
                
                }

                for (PrintWriter writer : Serverchat.printWriters) {

                    writer.println(name + ": " + message);

                }

            }

           

        }

        catch (IOException e)

        {

            System.out.println(e);

        }

   }

}
