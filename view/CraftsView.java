package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.CraftsInfo;
import model.Inventory;
import model.InventoryObserver;
import model.ItemId;

public class CraftsView extends JPanel implements InventoryObserver {
    private List<CraftView> craftViews = new ArrayList<CraftView>();

    public CraftsView(Inventory inventory) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(500, 470));

        add(scrollPane);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        scrollPane.setViewportView(panel);

        List<LinkedHashMap<ItemId, Integer>> craftsInfo = CraftsInfo.getInstance().getAllCrafts();

        for (LinkedHashMap<ItemId, Integer> craftInfo : craftsInfo) {
            CraftView craftView = new CraftView(craftInfo, inventory);
            panel.add(craftView);
            craftViews.add(craftView);
        }
    }

    public void updateInventory(ItemId id, int quantity) {
        for (CraftView craftView : craftViews) {
            craftView.updateQuantity();
        }
    }
}
