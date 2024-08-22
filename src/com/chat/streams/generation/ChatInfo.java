package com.chat.streams.generation;

import com.google.gson.Gson;

public class ChatInfo {
    private String messageId;
    private String senderUsername;
    private String status;
    private boolean isMultiMedia;
    private String[] multiMediaAttachments;
    private String messageContent;
    private String receiverType;
    private String receiver;

    // Getters and Setters
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getIsMultiMedia() {
        return isMultiMedia;
    }

    public void setIsMultiMedia(boolean isMultiMedia) {
        this.isMultiMedia = isMultiMedia;
    }

    public String[] getMultiMediaAttachments() {
        return multiMediaAttachments;
    }

    public void setMultiMediaAttachments(String[] multiMediaAttachments) {
        this.multiMediaAttachments = multiMediaAttachments;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

