package com.example.amazonprimeclone.modal.Trailers;

import android.os.Parcel;
import android.os.Parcelable;

public class TrailerResponse implements Parcelable {

    public String iso_639_1;
    public String iso_3166_1;
    public String name;
    public String key;
    public String published_at;
    public String site;
    public int size;
    public String type;
    public boolean official;
    public String id;

    public TrailerResponse(String iso_639_1, String iso_3166_1, String name, String key, String published_at, String site, int size, String type, boolean official, String id) {
        this.iso_639_1 = iso_639_1;
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
        this.key = key;
        this.published_at = published_at;
        this.site = site;
        this.size = size;
        this.type = type;
        this.official = official;
        this.id = id;
    }

    protected TrailerResponse(Parcel in) {
        iso_639_1 = in.readString();
        iso_3166_1 = in.readString();
        name = in.readString();
        key = in.readString();
        published_at = in.readString();
        site = in.readString();
        size = in.readInt();
        type = in.readString();
        official = in.readByte() != 0;
        id = in.readString();
    }

    public static final Creator<TrailerResponse> CREATOR = new Creator<TrailerResponse>() {
        @Override
        public TrailerResponse createFromParcel(Parcel in) {
            return new TrailerResponse(in);
        }

        @Override
        public TrailerResponse[] newArray(int size) {
            return new TrailerResponse[size];
        }
    };

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iso_639_1);
        dest.writeString(iso_3166_1);
        dest.writeString(name);
        dest.writeString(key);
        dest.writeString(published_at);
        dest.writeString(site);
        dest.writeInt(size);
        dest.writeString(type);
        dest.writeByte((byte) (official ? 1 : 0));
        dest.writeString(id);
    }
}
