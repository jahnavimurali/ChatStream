package com.chat.streams.generation;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class GenerateChatInfoStreamToKafka {
    public static void main(String[] args) throws InterruptedException, IOException {
        while (true) {
            int recordsCount = RandomDataGenUtility.randomIntBetween(4, 15);
            for (int i = 0; i <= recordsCount; i++) {
                ChatInfo chatStream = new ChatInfo();

                // Set unique message ID
                chatStream.setMessageId(UUID.randomUUID().toString());

                // Sender Username
                String[] usernames = {"Alice", "Bob", "Charlie", "Dave", "Eve",
                        "Frank", "Grace", "Heidi", "Ivan", "Judy",
                        "Kathy", "Leo", "Mallory", "Nina", "Oscar"};
                chatStream.setSenderUsername(RandomDataGenUtility.randomElement(usernames));

                // Status
                String[] statuses = {"online", "away"};
                chatStream.setStatus(RandomDataGenUtility.randomElement(statuses));

                // Is Multimedia
                boolean isMultiMedia = new Random().nextBoolean();
                chatStream.setIsMultiMedia(isMultiMedia);

                // Multimedia Attachments
                String[] mediaPool = {"media1.jpg", "media2.mp4", "media3.gif", "media4.png", "media5.mp3",
                        "media6.jpg", "media7.mp4", "media8.gif", "media9.png", "media10.mp3",
                        "media11.jpg", "media12.mp4", "media13.gif", "media14.png", "media15.mp3"};
                if (isMultiMedia) {
                    int mediaCount = (int) Math.floor(RandomDataGenUtility.randomBetween(1, 10));
                    String[] multimediaAttachments = new String[mediaCount];
                    for (int j = 0; j < mediaCount; j++) {
                        multimediaAttachments[j] = RandomDataGenUtility.randomElement(mediaPool);
                    }
                    chatStream.setMultiMediaAttachments(multimediaAttachments);
                } else {
                    chatStream.setMultiMediaAttachments(new String[]{});
                }

                // Message Textual Content
                String[] textPool = {"Hello!", "See you later.", "How are you?", "LOL", "Good morning!",
                        "Let's catch up.", "What do you think?", "Okay!", "I'll be there.",
                        "Happy Birthday!", "Yay!", "Where are you?", "Let's start the meeting.",
                        "Congratulations!", "Looking forward to it.", "Well done!", "Hmmm",
                        "Check this out.", "That’s great!", "Can you help me?", "Haha",
                        "Thank you!", "Sure!", "Good night!", "See you soon!", "What’s up?"};
                if (!isMultiMedia || new Random().nextBoolean()) {
                    chatStream.setMessageContent(RandomDataGenUtility.randomElement(textPool));
                } else {
                    chatStream.setMessageContent(null);
                }

                // Receiver Type
                String[] receiverTypes = {"individual", "group"};
                String receiverType = RandomDataGenUtility.randomElement(receiverTypes);
                chatStream.setReceiverType(receiverType);

                // Receiver
                if (receiverType.equals("individual")) {
                    chatStream.setReceiver(RandomDataGenUtility.randomElement(usernames));
                } else {
                    String[] groupNames = {"Group1", "ProjectTeam", "Friends", "Family", "Work",
                            "Gaming", "BookClub", "TravelBuddies", "StudyGroup", "DevTeam",
                            "MusicLovers", "Foodies", "FitnessCrew", "MovieBuffs", "PetLovers"};
                    chatStream.setReceiver(RandomDataGenUtility.randomElement(groupNames));
                }

                TestKafkaProducer.sendDataToKafka("chatstream", chatStream.toString(), chatStream.getMessageId());
            }
            System.out.println(recordsCount + " records Sent to Kafka.");
            Thread.sleep(10000);
        }
    }
}
