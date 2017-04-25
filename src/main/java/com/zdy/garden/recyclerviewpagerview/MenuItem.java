package com.zdy.garden.recyclerviewpagerview;

/**
 * Created with Android Studio.
 * Time: 9:40  2017/4/25
 * Author:ZhuangYuan
 */
public class MenuItem {
    private int Id;
    private String menuName;
    private String menuIcon;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
}
