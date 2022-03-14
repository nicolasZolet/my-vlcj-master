package com.dimelthoz.dygi.interfaceapplication.timeline;

import com.dimelthoz.dygi.interfaceapplication.semaphore.Id;
import com.dimelthoz.dygi.interfaceapplication.utils.FileManager;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.dimelthoz.dygi.interfaceapplication.medias.MediasManager.mrlTimeline;

public class Timeline {

    private static final long TIME_READ_FILE = 2000;
    private static long timerInitRead = System.currentTimeMillis();

    public static void start() {
        new Thread(readTimeline).start();
    }

    static Runnable readTimeline = new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (System.currentTimeMillis() - timerInitRead > TIME_READ_FILE) {
                    main();
                    timerInitRead = System.currentTimeMillis();
                }
            }
        }
    };

    public static void main() {
        if (FileManager.doesFileExists(mrlTimeline)) {
            System.out.println("file exists");
            readFile();
        }
    }

    private static final String KEY_SEMAPHORE = "semaphore";
    private static final String KEY_TIMELINE = "timeline";
    private static final String KEY_ID = "id";
    private static final String KEY_ADDS = "adds";
    private static final String KEY_VIDEO = "video";
    private static final String KEY_LENGTH = "length";


    private static void readFile() {
        String fileContent = FileManager.readFile(mrlTimeline);
        JSONObject jsonFile = new JSONObject(fileContent);
        System.out.println("Json file: " + jsonFile);

        int semaphoreId = jsonFile.getInt(KEY_SEMAPHORE);

        if (semaphoreId == Id.getId()) {

            JSONArray timeline = jsonFile.getJSONArray(KEY_TIMELINE);

            List<ClientContainer> clientPackages = new ArrayList<>();
            for (int indexContainer = 0; indexContainer < timeline.length(); indexContainer++) {

                JSONObject clientContainer = timeline.getJSONObject(indexContainer);
                int idClientContainer = clientContainer.getInt(KEY_ID);
                int clientsAddsLength = clientContainer.length();
                JSONArray clientsAdds = clientContainer.getJSONArray(KEY_ADDS);

                List<Client> clients = new ArrayList<>();
                for (int indexClient = 0; indexClient < clientsAdds.length(); indexClient++) {
                    JSONObject client = clientsAdds.getJSONObject(indexClient);

                    String video = client.getString(KEY_VIDEO);
                    int lengthVideo = client.getInt(KEY_LENGTH);

                    clients.add(new Client(video, lengthVideo));
                }

                clientPackages.add(new ClientContainer(idClientContainer, clients));
            }
            contentJson(clientPackages);
        }

    }

    private static ArrayList<ClientContainer> finalClientContainer;

    private static void contentJson(List<ClientContainer> content) {
        System.out.println(content.toString());
        finalClientContainer = (ArrayList<ClientContainer>) content;

        for (ClientContainer clientPackage : finalClientContainer) {
            System.out.println("\n");
            System.out.println("container ID -> " + clientPackage.getId());
            System.out.println("container LENGTH -> " + clientPackage.getLength());

            for (Client client : clientPackage.getListClient()) {
                System.out.println("\tvideo name -> " + client.getName());
                System.out.println("\tvideo url -> " + client.getUrl());
                System.out.println("\tvideo length -> " + client.getLength());
            }
        }
    }

    public static ArrayList<ClientContainer> getClientContainers() {
        return finalClientContainer;
    }


}
