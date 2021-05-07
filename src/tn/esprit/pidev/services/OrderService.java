package tn.esprit.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import tn.esprit.pidev.entities.Order;
import tn.esprit.pidev.entities.Pack;
import tn.esprit.pidev.utils.Database;
import tn.esprit.pidev.utils.LoggedUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {
    public ArrayList<Order> orderArrayList;

    public static OrderService instance = null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;

    public OrderService() {
        connectionRequest = new ConnectionRequest();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public boolean buyPack(Order order) {
        String url = Database.BASE_URL + "orders/api/buy?idPack=" + order.getIdPack() + "&idUser=" + order.getIdUser(); // Add Symfony URL here
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return resultOK;
    }
    public ArrayList<Order> showAll() {
        String url = Database.BASE_URL + "orders/api/myOrders?&idUser=" + LoggedUser.ID_LOGGED_USER; // Add Symfony URL Here
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                orderArrayList = parseOrders(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return orderArrayList;
    }
    public ArrayList<Order> parseOrders(String jsonText) {
        try {
            orderArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> orderListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) orderListJson.get("root");
            for (Map<String, Object> obj : list) {
                orderArrayList.add(new Order((int) Float.parseFloat(obj.get("id").toString()),
                        (int) Float.parseFloat(obj.get("iduser").toString()),
                        (int) Float.parseFloat(obj.get("idpack").toString()),
                        obj.get("status").toString() )); //
            }
        } catch (IOException ex) {
        }
        return orderArrayList;
    }
}
