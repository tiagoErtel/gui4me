package gui4me.utils;

public class Message {
    private MessageType messageType;
    private String message;

    public Message() {}

    public Message(MessageType messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    // Getters and Setters
    public boolean isError() {
        return messageType.equals(MessageType.ERROR);
    }

    public boolean isSuccess() {
        return messageType.equals(MessageType.SUCCESS);
    }

    public MessageType getMessageType(){
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
