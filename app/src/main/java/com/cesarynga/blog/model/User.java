package com.cesarynga.blog.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ANDROID on 12/08/2015.
 */
public class User implements Parcelable{

    public int id;
    public String name;
    public String email;

    public User() {
        // Do nothing
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
    }
}
