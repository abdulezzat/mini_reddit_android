package com.example.android.minireddit.datastructure;

/**
 * The Message Data.
 * Created By Sherif Essam.
 */
public class Message {
    /**
     * Unique Message ID
     */
    private int MessageID;

    /**
     * The text of the message
     */
    private String MessageContent;

    /**
     * The message receiver name
     */
    private String ReceiverName;

    /**
     * The message sender name
     */
    private String SenderName;

    /**
     * The message receiver photo URL
     */
    private String ReceiverPhotoURL;

    /**
     * The message sender photo URL
     */
    private String SenderPhotoURL;


    /**
     * Constructing a new Message.
     *
     * @param messageID         the id of the message
     * @param messageContent    the text of the message
     * @param receiverName      the message receiver name
     * @param senderName        the message sender name
     * @param receiverPhotoURL  the message receiver photo URL
     * @param senderPhotoURL    he message sender photo URL
     */
    public Message(int messageID, String messageContent, String receiverName, String senderName, String receiverPhotoURL, String senderPhotoURL) {
        MessageID = messageID;
        MessageContent = messageContent;
        ReceiverName = receiverName;
        SenderName = senderName;
        ReceiverPhotoURL = receiverPhotoURL;
        SenderPhotoURL = senderPhotoURL;
    }


    /**
     * Gets message ID.
     *
     * @return the message ID
     */
    public int getMessageID() {
        return MessageID;
    }


    /**
     * Sets message ID.
     *
     * @param messageID the message ID
     */
    public void setMessageID(int messageID) {
        MessageID = messageID;
    }


    /**
     * Gets message content.
     *
     * @return the text of the message
     */
    public String getMessageContent() {
        return MessageContent;
    }


    /**
     * Sets message content.
     *
     * @param messageContent the message text
     */
    public void setMessageContent(String messageContent) {
        MessageContent = messageContent;
    }


    /**
     * Gets message content.
     *
     * @return the text of the message
     */
    public String getReceiverName() {
        return ReceiverName;
    }


    /**
     * Sets receiver name.
     *
     * @param receiverName the message receiver name
     */
    public void setReceiverName(String receiverName) {
        ReceiverName = receiverName;
    }

    public String getSenderName() {
        return SenderName;
    }


    /**
     * Sets sender name.
     *
     * @param senderName the message sender name
     */
    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    public String getReceiverPhotoURL() {
        return ReceiverPhotoURL;
    }


    /**
     * Sets receiver photo.
     *
     * @param receiverPhotoURL the message receiver photo URL
     */
    public void setReceiverPhotoURL(String receiverPhotoURL) {
        ReceiverPhotoURL = receiverPhotoURL;
    }

    public String getSenderPhotoURL() {
        return SenderPhotoURL;
    }


    /**
     * Sets sender photo.
     *
     * @param senderPhotoURL the message sender photo URL
     */
    public void setSenderPhotoURL(String senderPhotoURL) {
        SenderPhotoURL = senderPhotoURL;
    }
}
