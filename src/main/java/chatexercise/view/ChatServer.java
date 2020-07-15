/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatexercise.view;

import chatexercise.model.Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toanm
 */
public class ChatServer {
    private static HashMap<String, BufferedWriter> connect = new HashMap<>();
    //private static HashMap<String, PairConnection> connect = new HashMap<>();
    private static ArrayList<Client> account = new ArrayList<>();
    public static void main(String[] args) {
        readAccount("account.txt");
        ExecutorService client = Executors.newFixedThreadPool(10);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            try {
                ServerSocket s = new ServerSocket(3200);
                while (true) {
                    System.out.println("Waiting for a Client");

                    Socket ss = s.accept(); //synchronous

                    System.out.println("Talking to client");
                    System.out.println(ss.getPort());

                    client.submit(() -> {
                        InputStream is = null;
                        String username = "";
                        try {
                            is = ss.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String receivedMessage;
                            
                            //OutputStreamWriter bw = new OutputStreamWriter(os);
                            //Signin && Signup
                            while (true){
                                try {
                                    OutputStream os = ss.getOutputStream();
                                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                                    boolean success = false;
                                    receivedMessage = br.readLine();
                                    String[] msg = receivedMessage.split(",");
                                    
                                    //Signin
                                    if (msg[0].equals("signin")){
                                        for (int i = 0; i < account.size(); i++) {
                                            Client get = account.get(i);
                                            if (get.getUsername().equals(msg[1])){
                                                if (get.getPassword().equals(msg[2])){
                                                    bw.write(get.getUsername());
                                                    success = true;
                                                    System.out.println("Login suceessfully");
                                                    username = get.getUsername();
                                                }else{
                                                    bw.write("Signin");
                                                }
                                                bw.newLine();
                                                //bw.write("\n");
                                                bw.flush();
                                                break;
                                            }
                                        }
                                    }
                                    
                                    //Signup
                                    if (msg[0].equals("signup")){
                                        success = true;
                                        for (int i = 0; i < account.size(); i++) {
                                            Client get = account.get(i);
                                            if (get.getUsername().equals(msg[1])){
                                                bw.write("Signup");
                                                success = false;
                                                break;
                                            }
                                        }
                                        if (success){
                                            Client newAccount = new Client(msg[1],msg[2]);
                                            account.add(newAccount);
                                            bw.write(newAccount.getUsername());
                                            System.out.println("sign up successfully");
                                            username = newAccount.getUsername();
                                        }
                                        bw.newLine();
                                        //bw.write("\n");
                                        bw.flush();
                                    }
                                    if (success) {
                                        System.out.println("success");
                                        Set<String> users = connect.keySet();
                                        for (Iterator<String> iterator = users.iterator(); iterator.hasNext();) {
                                            String user = iterator.next();
                                            try {
                                                bw.write(user);
                                                bw.newLine();
                                                //bw.write("\n");
                                            } catch (IOException ex) {
                                                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            BufferedWriter get = connect.get(user);
                                            //OutputStreamWriter get = bw_user.get(i).bw;
                                            get.write("online");
                                            get.newLine();
                                            get.write(username);
                                            get.newLine();
                                            get.flush();
                                        }
                                        bw.newLine();
                                        bw.flush();
                                        System.out.println("success2");
                                        /*if (!connect.isEmpty()){
                                            ArrayList<BufferedWriter> bw_user = new ArrayList<>(connect.values());
                                            for (int i = 0; i < bw_user.size(); i++) {
                                                BufferedWriter get = bw_user.get(i);
                                                //OutputStreamWriter get = bw_user.get(i).bw;
                                                get.write("online");
                                                get.newLine();
                                                //get.write("\n");
                                                get.write(username);
                                                get.newLine();
                                                //get.write("\n");
                                                get.flush();
                                            }
                                        }*/
                                        System.out.println("success3");
                                        connect.put(username, bw);
                                        //connect.put(username,new PairConnection(br,bw));
                                        System.out.println(username);
                                        break;
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    break;
                                }
                            }
                            
                            //comunicate
                            while (true) {
                                try {
                                    receivedMessage = br.readLine();
                                } catch (IOException ex) {
                                    Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                                    break;
                                }
                                System.out.println("Received from " + ss.getPort() + ": " + receivedMessage);
                                
                                if (receivedMessage.equalsIgnoreCase("quit")) {
                                    System.out.println("Client has left !");
                                    connect.get(username).close();
                                    connect.remove(username);
                                    break;
                                }
                                String[] msg = receivedMessage.split(",");
                                System.out.println(msg[0] + " " + msg[1]);

                                //BufferedWriter bw = connect.get(msg[0]).bw;
                                if (connect.containsKey(msg[0])){
                                    System.out.println("van con socket");
                                }
                                BufferedWriter bw = connect.get(msg[0]);
                                bw.write(username + "," + msg[1]);
                                System.out.println(username);
                                bw.newLine();
                                //bw.write("\n");
                                bw.flush();
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                is.close();
                            } catch (IOException ex) {
                                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        executor.submit(() ->
        {
            while (true) {
                try {
                    DataInputStream din = new DataInputStream(System.in);
                    String k = din.readLine();
                    if (k.equals("shutdown")){
                        saveAcount("account.txt");
                        client.shutdown();
                        try{
                            client.awaitTermination(1, TimeUnit.SECONDS);
                            executor.awaitTermination(1, TimeUnit.SECONDS);
                        }catch(Exception ex){
                            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                            executor.shutdownNow();
                            client.shutdownNow();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        });
        executor.shutdown();
    }
    
    private static void readAccount(String filename){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            //BufferedReader bfr = new BufferedReader(new FileReader("account.txt"));
            while(true){
                //Client temp = (Client)ois.readObject();
                Client clt = (Client)ois.readObject();
                if (clt==null){
                    break;
                }
                account.add(clt);
                System.out.println(clt);
            }
        } catch (Exception ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            File file = new File(filename);
            try {
                file.createNewFile();
            } catch (IOException ex1) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    private static void saveAcount(String filename) throws Exception{
        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(filename));
        for (int i = 0; i < account.size(); i++) {
            Client get = account.get(i);
            file.writeObject(get);
        }
        file.close();
    }
    
    private static class PairConnection{
        private BufferedReader br;
        //private BufferedWriter bw;
        private OutputStreamWriter bw;

        public PairConnection() {
        }

        public PairConnection(BufferedReader br, OutputStreamWriter bw) {
            this.br = br;
            this.bw = bw;
        }
        
    }
}
