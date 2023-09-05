package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MoneyObserver;

public class MoneyView extends JPanel implements MoneyObserver{
    private JLabel moneyLabel;

    public MoneyView(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        moneyLabel = new JLabel("0");
        add(moneyLabel);

        
        BufferedImage moneyImage = null;
        try{
            moneyImage = ImageIO.read(new File("img/other/money.png"));
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        add(new JLabel(new ImageIcon(moneyImage.getScaledInstance(30, 30, Image.SCALE_FAST))));

    }

    @Override
    public void updateMoney(int money) {
        moneyLabel.setText(Integer.toString(money));
    }
    
}
