package tn.esprit.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import tn.esprit.pidev.entities.Pack;
import tn.esprit.pidev.utils.Database;
import tn.esprit.pidev.utils.LoggedUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PackService {
    public ArrayList<Pack> packArrayList;

    public static PackService instance = null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;

    public PackService() {
        connectionRequest = new ConnectionRequest();
    }

    public static PackService getInstance() {
        if (instance == null) {
            instance = new PackService();
        }
        return instance;
    }

    public ArrayList<Pack> parsePack(String jsonText) {
        try {
            packArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> packListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) packListJson.get("root");
            for (Map<String, Object> obj : list) {
                packArrayList.add(new Pack((int) Float.parseFloat(obj.get("id").toString()), obj.get("nom").toString(), obj.get("description").toString() , Double.parseDouble(obj.get("prix").toString()) )); //
            }
        } catch (IOException ex) {
        }
        return packArrayList;
    }

    public ArrayList<Pack> showAll() {
        String url = Database.BASE_URL + "pack/api/index"; // Add Symfony URL Here
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                packArrayList = parsePack(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return packArrayList;
    }
    public ArrayList<Pack> showMyPacks(String type) {
        String url = Database.BASE_URL + "pack/api/"+type+"?idUser="+ LoggedUser.ID_LOGGED_USER; // if all packs then type = "myPacks" else type = "packs/xxx"
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                packArrayList = parsePack(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return packArrayList;
    }
}
