package com.umons.fpms.model;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDB {
    private int id;
    private String username, email, password;

    public UserDB(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void clear() {
        id = -1;
        username = null;
        email = null;
        password = null;
    }

    public boolean isClear() {
        return id == -1;
    }

    public String userJsonCredential() {
        StringBuilder str = new StringBuilder();
        str.append("login");
        return str.toString();
    }

    @Override
    public String toString() {
        JSONObject user = new JSONObject();
        try {
            user.put("id", id)
                    .put("username", username)
                    .put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
