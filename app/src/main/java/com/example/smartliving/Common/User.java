package com.example.smartliving.Common;

import android.widget.EditText;

class User {

    private EditText UserName, FullName, County;

    public User(){

    }

    public User(EditText userName, EditText fullName, EditText county) {
        UserName = userName;
        FullName = fullName;
        County = county;
    }

    public EditText getUserName() {
        return UserName;
    }

    public void setUserName(EditText userName) {
        UserName = userName;
    }

    public EditText getFullName() {
        return FullName;
    }

    public void setFullName(EditText fullName) {
        FullName = fullName;
    }

    public EditText getCounty() {
        return County;
    }

    public void setCounty(EditText county) {
        County = county;
    }
}