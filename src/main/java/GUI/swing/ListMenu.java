/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.swing;

import GUI.event.EventMenuSelected;
import Utility.Menu_model;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

/**
 *
 * @author mrben
 */
public class ListMenu<E extends Object>extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    
    private EventMenuSelected event;
    
    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
    }
    
    public ListMenu() {
       model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    int index = locationToIndex(me.getPoint());
                    Object o = model.getElementAt(index);
                    if (o instanceof Menu_model) {
                        Menu_model menu = (Menu_model) o;
                        if (menu.getType() == Menu_model.MenuType.MENU) {
                            selectedIndex = index;
                            if(event != null) {
                                event.selected(index);
                            }
                        }
                    } else {
                        selectedIndex = index;
                    }
                    repaint();
                }
            }
        }); 
    }

  @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected, boolean focus) {
                Menu_model data;
                if (o instanceof Menu_model) {
                    data = (Menu_model) o;
                } else {
                    data = new Menu_model("", o + "", Menu_model.MenuType.EMPTY);
                }
                MenuItem item = new MenuItem(data);
                item.setSelected(selectedIndex == index);
                return item;
            }

        };
    }

    public void addItem(Menu_model data) {
        model.addElement(data);
    }
    
    
}
