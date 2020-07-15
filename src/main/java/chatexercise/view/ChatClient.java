/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatexercise.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author toanm
 */
public class ChatClient extends javax.swing.JFrame {

    /**
     * Creates new form ChatClient
     */
    public ChatClient() {
        initComponents();
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    bw.write("quit");
                    bw.newLine();
                    bw.flush();
                    bw.close();
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
        //create message area
        chatBox.setEditable(false);
        Chat_mesagePanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
        chatBox.setLineWrap(true);
        
        SignupFrame.setVisible(false);
        ChatPanel.setVisible(false);
        //create message box
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(()->{
            //Signin and Signup
            while(true){
                String reply = br.readLine();
                System.out.println(reply);
                if (reply.equals("Signin")){
                    JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu.");
                }else if (reply.equals("Signup")){
                    JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại.");
                }else{
                    username = reply;
                    SigninFrame.setVisible(false);
                    SignupFrame.setVisible(false);
                    ChatPanel.setVisible(true);
                    break;
                }
            }
            
            //get user online
            DefaultListModel<String> model = new DefaultListModel();
            while(true){
                String reply = br.readLine();
                System.out.println(reply);
                if (reply.length()==0)break;
                model.addElement(reply);
            }
            Chat_listUser.setModel(model);
            
            

            //Message
            while(true){
                String reply = br.readLine();
                System.out.println(reply);
                if (reply.equals("online")){
                    String online = br.readLine();
                    model.addElement(online);
                    Chat_listUser.setModel(model);
                    continue;
                }
                String msg[] = reply.split(",");
                if (list_message.containsKey(msg[0])){
                    list_message.get(msg[0]).add(new Message(msg[1],false));
                }else{
                    ArrayList<Message> temp = new ArrayList<Message>();
                    temp.add(new Message(msg[1],false));
                    list_message.put(msg[0], temp);
                }
                if (Chat_listUser.getSelectedValue().equals(msg[0])){
                    chatBox.append("Receiver from " + msg[0] + ": " + msg[1] + "\n");
                }
            }
        });
        executor.shutdown();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ChatPanel = new javax.swing.JPanel();
        Chat_send = new javax.swing.JButton();
        Chat_Message = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Chat_listUser = new javax.swing.JList<>();
        Chat_mesagePanel = new javax.swing.JPanel();
        SigninFrame = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Login_username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Login_submitBtn = new javax.swing.JButton();
        Login_password = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        signin_signupBtn = new javax.swing.JButton();
        SignupFrame = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        signup_username = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        signup_password = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        signup_displayName = new javax.swing.JTextField();
        signup_submitBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        signup_signinBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Chat_send.setText("send");
        Chat_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chat_sendActionPerformed(evt);
            }
        });

        Chat_listUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Chat_listUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Chat_listUser.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Chat_listUserValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(Chat_listUser);

        Chat_mesagePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Chat_mesagePanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout ChatPanelLayout = new javax.swing.GroupLayout(ChatPanel);
        ChatPanel.setLayout(ChatPanelLayout);
        ChatPanelLayout.setHorizontalGroup(
            ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChatPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Chat_mesagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ChatPanelLayout.createSequentialGroup()
                        .addComponent(Chat_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Chat_send, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ChatPanelLayout.setVerticalGroup(
            ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChatPanelLayout.createSequentialGroup()
                        .addComponent(Chat_mesagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Chat_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Chat_send, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sign in");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Username");

        Login_username.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Password");

        Login_submitBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Login_submitBtn.setText("Sign in");
        Login_submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login_submitBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Don't have account? ");

        signin_signupBtn.setText("Sign up");
        signin_signupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signin_signupBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SigninFrameLayout = new javax.swing.GroupLayout(SigninFrame);
        SigninFrame.setLayout(SigninFrameLayout);
        SigninFrameLayout.setHorizontalGroup(
            SigninFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SigninFrameLayout.createSequentialGroup()
                .addGap(45, 85, Short.MAX_VALUE)
                .addGroup(SigninFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SigninFrameLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Login_username, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SigninFrameLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Login_password, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79))
            .addGroup(SigninFrameLayout.createSequentialGroup()
                .addGroup(SigninFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SigninFrameLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SigninFrameLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(signin_signupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SigninFrameLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(Login_submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SigninFrameLayout.setVerticalGroup(
            SigninFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SigninFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(SigninFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Login_username, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(SigninFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Login_password, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(Login_submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(SigninFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(signin_signupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sign Up");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Username");

        signup_username.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Password");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Display name");

        signup_displayName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        signup_submitBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        signup_submitBtn.setText("Sign up");
        signup_submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signup_submitBtnActionPerformed(evt);
            }
        });

        jLabel9.setText("You have account?");

        signup_signinBtn.setText("Sign in");
        signup_signinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signup_signinBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SignupFrameLayout = new javax.swing.GroupLayout(SignupFrame);
        SignupFrame.setLayout(SignupFrameLayout);
        SignupFrameLayout.setHorizontalGroup(
            SignupFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignupFrameLayout.createSequentialGroup()
                .addGroup(SignupFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SignupFrameLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SignupFrameLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(signup_submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SignupFrameLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(SignupFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SignupFrameLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signup_username))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SignupFrameLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signup_password, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SignupFrameLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signup_displayName))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SignupFrameLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(signup_signinBtn)))))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        SignupFrameLayout.setVerticalGroup(
            SignupFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignupFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(SignupFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signup_username, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(SignupFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(signup_password, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(SignupFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signup_displayName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(signup_submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(SignupFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(signup_signinBtn))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 875, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 186, Short.MAX_VALUE)
                    .addComponent(ChatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 186, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(SigninFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(SignupFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 122, Short.MAX_VALUE)
                    .addComponent(ChatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 123, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(SigninFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(SignupFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Login_submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Login_submitBtnActionPerformed
        String username = Login_username.getText();
        String password = String.valueOf(Login_password.getPassword());
        if (username.equals("") || password.equals("")){
            JOptionPane.showMessageDialog(this, "Các trường không được để trống.");
            return;
        }
        String msg = "signin," + username + "," + password;
        try {
            bw.write(msg);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Login_submitBtnActionPerformed

    private void signup_submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signup_submitBtnActionPerformed
        String username = signup_username.getText();
        String password = String.valueOf(signup_password.getPassword());
        String displayname = signup_displayName.getText();
        if (username.length()==0 || password.length()==0 || displayname.length()==0){
            JOptionPane.showMessageDialog(this, "Các trường không được để trống!");
            return;
        }
        try {
            bw.write("signup," + username + "," + password + "," + displayname);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_signup_submitBtnActionPerformed

    private void signup_signinBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signup_signinBtnActionPerformed
        SignupFrame.setVisible(false);
        SigninFrame.setVisible(true); 
    }//GEN-LAST:event_signup_signinBtnActionPerformed

    private void signin_signupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signin_signupBtnActionPerformed
        SigninFrame.setVisible(false);
        SignupFrame.setVisible(true);
    }//GEN-LAST:event_signin_signupBtnActionPerformed

    private void Chat_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chat_sendActionPerformed
        String msg = Chat_Message.getText();
        if (msg.length()==0 || Chat_listUser.getSelectedIndex() < 0)return;
        String receiver = Chat_listUser.getSelectedValue();
        chatBox.append("Send to " + receiver + ":  " + msg + "\n");
        
        if (list_message.containsKey(receiver)){
            list_message.get(receiver).add(new Message(msg,true));
        }else{
            List<Message> message = new ArrayList<Message>();
            message.add(new Message(msg,true));
            list_message.put(receiver, message);
        }
        try {
            bw.write(receiver + "," + msg);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        Chat_Message.setText("");
    }//GEN-LAST:event_Chat_sendActionPerformed

    private void Chat_listUserValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Chat_listUserValueChanged
        String receiver = Chat_listUser.getSelectedValue();
        chatBox.setText("");
        
        if (list_message.containsKey(receiver)){
            list_message.get(receiver).forEach(s->{
                if (s.isSend()){
                    chatBox.append("Send to " + receiver + ":  " + s.getMsg() + "\n");
                }else{
                    chatBox.append("Receve from " + receiver + ":  " + s.getMsg() + "\n");
                }
                
            });
        }
    }//GEN-LAST:event_Chat_listUserValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ChatClient().setVisible(true);
            }
        });
        
        try
        {
            s = new Socket("localhost",3200);
            System.out.println(s.getPort());

            is=s.getInputStream();
            br=new BufferedReader(new InputStreamReader(is));

            os = s.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(os));
        }catch(Exception ex){
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private static Socket s;
    private static InputStream is;
    private static BufferedReader br;
    private static OutputStream os;
    private static BufferedWriter bw;
    private static String username = "";
    private static Map<String,List<Message>> list_message = new TreeMap<String,List<Message>>();
    private static JTextArea chatBox = new JTextArea();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChatPanel;
    private javax.swing.JTextField Chat_Message;
    private javax.swing.JList<String> Chat_listUser;
    private javax.swing.JPanel Chat_mesagePanel;
    private javax.swing.JButton Chat_send;
    private javax.swing.JPasswordField Login_password;
    private javax.swing.JButton Login_submitBtn;
    private javax.swing.JTextField Login_username;
    private javax.swing.JPanel SigninFrame;
    private javax.swing.JPanel SignupFrame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton signin_signupBtn;
    private javax.swing.JTextField signup_displayName;
    private javax.swing.JPasswordField signup_password;
    private javax.swing.JButton signup_signinBtn;
    private javax.swing.JButton signup_submitBtn;
    private javax.swing.JTextField signup_username;
    // End of variables declaration//GEN-END:variables

    class Message {
        String msg;
        boolean send;

        public Message() {
        }

        public Message(String msg, boolean send) {
            this.msg = msg;
            this.send = send;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isSend() {
            return send;
        }

        public void setSend(boolean send) {
            this.send = send;
        }
        
    }
}