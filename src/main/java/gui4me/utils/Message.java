package gui4me.utils;

import java.util.UUID;

public class Message {
    private String id;
    private MessageType messageType;
    private String message;
    private Link link;

    public Message(MessageType messageType) {
        this.messageType = messageType;
        this.id = UUID.randomUUID().toString();
    }

    public Message(MessageType messageType, String message) {
        this.messageType = messageType;
        this.message = message;
        this.id = UUID.randomUUID().toString();
    }

    public Message(MessageType messageType, String message, Link link) {
        this.messageType = messageType;
        this.message = message;
        this.link = link;
        this.id = UUID.randomUUID().toString();
    }

    public boolean isError() {
        return messageType == MessageType.ERROR;
    }

    public boolean isSuccess() {
        return messageType == MessageType.SUCCESS;
    }

    public MessageType getMessageType() {
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

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getLinkUrl() {
        return link.getUlr();
    }

    public String getLinkText() {
        return link.getText();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
