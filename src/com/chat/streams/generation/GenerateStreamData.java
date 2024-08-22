package com.chat.streams.generation;

import java.util.Random;

public class GenerateStreamData {
    public static void main(String[] args) {
        // Sender Username (Pool size: 15)
        String[] usernames = {"Alice", "Bob", "Charlie", "Dave", "Eve",
                "Frank", "Grace", "Heidi", "Ivan", "Judy",
                "Kathy", "Leo", "Mallory", "Nina", "Oscar"};
        String senderUsername = RandomDataGenUtility.randomElement(usernames);
        System.out.println("Sender Username: " + senderUsername);

        // Status
        String[] statuses = {"online", "away"};
        String status = RandomDataGenUtility.randomElement(statuses);
        System.out.println("Status: " + status);

        // Is Multimedia
        boolean isMultiMedia = new Random().nextBoolean();
        System.out.println("Is Multimedia: " + isMultiMedia);

        // Multimedia Attachments (Pool size: 15)
        String[] mediaPool = {"media1.jpg", "media2.mp4", "media3.gif", "media4.png", "media5.mp3",
                "media6.jpg", "media7.mp4", "media8.gif", "media9.png", "media10.mp3",
                "media11.jpg", "media12.mp4", "media13.gif", "media14.png", "media15.mp3"};
        String[] multimediaAttachments = {};
        if (isMultiMedia) {
            int mediaCount = (int)Math.round(RandomDataGenUtility.randomBetween(1, 10));
            multimediaAttachments = new String[mediaCount];
            for (int i = 0; i < mediaCount; i++) {
                multimediaAttachments[i] = RandomDataGenUtility.randomElement(mediaPool);
            }
        }
        System.out.println("Multimedia Attachments: " + String.join(", ", multimediaAttachments));

        // Message Textual Content (Pool size: 25)
        String[] textPool = {"Hello!", "See you later.", "How are you?", "😂", "Good morning!",
                "Let's catch up.", "What do you think?", "👍", "I'll be there.",
                "Happy Birthday!", "🎉", "Where are you?", "Let's start the meeting.",
                "Congratulations!", "Looking forward to it.", "Well done!", "🤔",
                "Check this out.", "That’s great!", "Can you help me?", "😅",
                "Thank you!", "Sure!", "Good night!"};
        String messageContent = null;
        if (!isMultiMedia || new Random().nextBoolean()) {
            messageContent = RandomDataGenUtility.randomElement(textPool);
        }
        System.out.println("Message Content: " + (messageContent != null ? messageContent : "null"));

        // Receiver Type
        String[] receiverTypes = {"individual", "group"};
        String receiverType = RandomDataGenUtility.randomElement(receiverTypes);
        System.out.println("Receiver Type: " + receiverType);

        // Receiver (Pool size: 15)
        String receiver;
        if (receiverType.equals("individual")) {
            receiver = RandomDataGenUtility.randomElement(usernames);
        } else {
            String[] groupNames = {"Group1", "ProjectTeam", "Friends", "Family", "Work",
                    "Gaming", "BookClub", "TravelBuddies", "StudyGroup", "DevTeam",
                    "MusicLovers", "Foodies", "FitnessCrew", "MovieBuffs", "PetLovers"};
            receiver = RandomDataGenUtility.randomElement(groupNames);
        }
        System.out.println("Receiver: " + receiver);
    }
}

