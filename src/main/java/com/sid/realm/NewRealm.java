/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sid.realm;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.realm.RealmBase;
import org.apache.catalina.realm.GenericPrincipal;
import java.util.logging.Logger;


/**
 *
 * @author sidde
 */
public class NewRealm extends RealmBase {
    private static final Logger log = Logger.getLogger(NewRealm.class.getName());
    private String username;
    private String password;

    @Override
    public Principal authenticate(String username, String credentials) {

        this.username = username;
        this.password = credentials;
        /* dummy authentication */
        
        if (this.username.equals(this.password)) {
            log.info("Authentication is taking place for the user: "+username);
            return getPrincipal(username);
        }else{
            return null;
        }
    }
    @Override
    protected String getName() {
        return username;
    }

    @Override
    protected String getPassword(String username) {
        return password;
    }

    @Override
    protected Principal getPrincipal(String string) {
        List<String> roles = new ArrayList<String>();
        roles.add("manager");
        log.info("Realm: "+this);
        Principal principal = new GenericPrincipal(username, password,roles);
        log.info("Principal: "+principal);
        return principal;
    }
}
