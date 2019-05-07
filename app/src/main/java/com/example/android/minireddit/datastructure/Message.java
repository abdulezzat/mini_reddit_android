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
     * The subject of the message
     */
    private  String MessageSubject;

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
     * The time since the message was sent
     */
    private String Duration;


    /**
     * Constructing a new Message.
     *
     * @param messageID         the id of the message
     * @param messageContent    the text of the message
     * @param messageSubject    the subject of the message
     * @param receiverName      the message receiver name
     * @param senderName        the message sender name
     * @param receiverPhotoURL  the message receiver photo URL
     * @param senderPhotoURL    the message sender photo URL
     * @param duration          the time since the message was sent
     */
    public Message(int messageID, String messageContent, String messageSubject, String receiverName, String senderName, String receiverPhotoURL, String senderPhotoURL, String duration) {
        MessageID = messageID;
        MessageContent = messageContent;
        MessageSubject = messageSubject;
        ReceiverName = receiverName;
        SenderName = senderName;
        ReceiverPhotoURL = receiverPhotoURL;
        SenderPhotoURL = senderPhotoURL;
        Duration = duration;
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
     * Gets message subject.
     *
     * @return the subject of the message
     */
    public String getMessageSubject() {
        return MessageSubject;
    }

    /**
     * Sets message subject.
     *
     * @param messageSubject the message text
     */
    public void setMessageSubject(String messageSubject) { MessageSubject = messageSubject; }


    /**
     * Gets receiver name.
     *
     * @return the message receiver name
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


    /**
     * Gets sender name.
     *
     * @return the message sender name
     */
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


    /**
     * Gets the url of the receiver photo.
     *
     * @return the message receiver photo URL
     */
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


    /**
     * Gets the url of the sender photo.
     *
     * @return the message sender photo URL
     */
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


    /**
     * Gets the time since the message was sent
     *
     * @return the duration from the message sending
     */
    public String getDuration() {
        return Duration;
    }

    /**
     * Sets the time since the message was sent
     *
     * @param duration the duration from the message sending
     */
    public void setDuration(String duration) {
        Duration = duration;
    }
}
