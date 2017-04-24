package lipanampesa.juliuskim.githublagosusers.models;

import java.io.Serializable;


public class User implements Serializable {
    String userid, username, userphoto,userprofileurl;

    public User() {
    }

    public User(String id, String name, String photo, String url) {
        this.userid = id;
        this.username = name;
        this.userphoto = photo;
        this.userprofileurl=url;

    }

    public String getId() {
        return userid;
    }

    public void setId(String id) {
        this.userid = id;
    }
    public String getImage() {
        return userphoto;
    }

    public void setImage(String image) {
        this.userphoto = image;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String geturl() {
        return userprofileurl;
    }

    public void seturl(String url) {
        this.userprofileurl = url;
    }
}
