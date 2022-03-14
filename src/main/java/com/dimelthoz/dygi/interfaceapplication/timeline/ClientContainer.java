package com.dimelthoz.dygi.interfaceapplication.timeline;

import java.util.List;

public class ClientContainer {
    private int ID;
    private List<Client> clients;

    public ClientContainer(int ID, List<Client> clients){
        this.ID = ID;
        this.clients = clients;
    }

    public int getId(){
        return this.ID;
    }

    public int getLength(){
        return this.clients.size();
    }

    public Client getClient(int index){
        return clients.get(index);
    }

    public List<Client> getListClient(){
        return this.clients;
    }
}
