/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author mrben
 */
public class Menu_model {

  public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Menu_model(String icon, String name, MenuType type) {
        this.name = name;
        this.icon = icon;
        this.type = type;
    }
    
    String icon;
    String name;
    MenuType type;
    
    public Icon toIcon() {
        return new ImageIcon(getClass().getResource("/GUI/icon/" + icon + ".png"));
    }
    
    public static enum MenuType {
        TITLE, MENU, EMPTY;
    }
}
