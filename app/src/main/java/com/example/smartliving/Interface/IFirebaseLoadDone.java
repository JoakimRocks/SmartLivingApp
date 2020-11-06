package com.example.smartliving.Interface;

import com.example.smartliving.Model.Rooms;

import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<Rooms> roomsList);
    void onFirebaseLoadFailed(String message);
}
