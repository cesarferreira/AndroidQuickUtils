package com.cesarferreira.quickutils.sample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cesarferreira on 26/12/14.
 */
public class PostEntity {

    @SerializedName("name")
    public String name;

    @SerializedName("url")
    public String url;
}
