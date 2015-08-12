package com.cesarynga.blog.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CesarYnga on 8/10/15.
 */
public class Post implements Parcelable {

    public int id;
    public String title;
    public String description;
    public String lastUpdate;

    public Post() {
        // Do nothing
    }

    protected Post(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        lastUpdate = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(lastUpdate);
    }
}
