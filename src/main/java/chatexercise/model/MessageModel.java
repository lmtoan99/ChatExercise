/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatexercise.model;

/**
 *
 * @author toanm
 */
public class MessageModel {
    private String Send;
    private String Receive;
    private String Message;

    public MessageModel() {
    }

    public MessageModel(String Send, String Receive, String Message) {
        this.Send = Send;
        this.Receive = Receive;
        this.Message = Message;
    }

    public String getSend() {
        return Send;
    }

    public void setSend(String Send) {
        this.Send = Send;
    }

    public String getReceive() {
        return Receive;
    }

    public void setReceive(String Receive) {
        this.Receive = Receive;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
    
}
