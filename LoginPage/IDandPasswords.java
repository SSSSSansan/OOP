package sis2;

import java.util.HashMap;

public class IDandPasswords {
    
    HashMap<String,String> loginingo = new HashMap<String,String>();

    IDandPasswords(){

        loginingo.put("Bro","pizza");
        loginingo.put("Brometheus","PASSWORD");
        loginingo.put("BroCode","abc123");
    }

    protected HashMap getLoginInfo(){
        return loginingo;
    }
}
