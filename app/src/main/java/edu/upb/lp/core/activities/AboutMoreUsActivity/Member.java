package edu.upb.lp.core.activities.AboutMoreUsActivity;

public class Member {
    private int imageResId;
    private String name;
    private String role;

    public Member(int imageResId, String name, String role) {
        this.imageResId = imageResId;
        this.name = name;
        this.role = role;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
