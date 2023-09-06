package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import model.Market;

public class GraphPanel extends JPanel {
    private List<Integer> data;
    private final String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec" };
    private int monthsToDisplay = 24;

    public GraphPanel(List<Integer> data) {
        setSize(WIDTH, 400);
        this.data = data;
        if(data.size() <= 24){
            monthsToDisplay = data.size();
        }
        else{
           //delete first element to keep only the 24 last
            while(data.size() > 24){
                data.remove(0);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int spaceFromLeft = 30;
        int heightBottom = 250;
        int spacing = (getWidth() - spaceFromLeft) / data.size();
        int yMax = getMax();
        int yMin = getMin();
        int height = getHeight() - 150;
        

        paintOutlineGraph(g, spacing, heightBottom, height, yMax, yMin, spaceFromLeft);

        int y1 = 0;
        int y2 = 0;
        int i = 0;

        for (i = 1; i < monthsToDisplay; i++) {

            y1 = -(int) Math.round((((data.get(i - 1) - yMin) * 1.0) / (yMax - yMin)) * height) + heightBottom;
            y2 = -(int) Math.round((((data.get(i) - yMin) * 1.0) / (yMax - yMin)) * height) + heightBottom;

            // draw point
            g.setColor(Color.BLACK);
            g.fillOval(spaceFromLeft, y1 - 2, 5, 5);

            // draw text on point
            byte[] bytes = intToBytes(data.get(i - 1));
            g.drawBytes(bytes, 0, bytes.length, spaceFromLeft, y1 - 2);

            // draw line between points
            g.setColor(Color.RED);
            g.drawLine(spaceFromLeft, y1, spaceFromLeft + spacing, y2);
            spaceFromLeft += spacing;
        }
        // draw last point and its number
        y1 = -(int) Math.round((((data.get(i - 1) - yMin) * 1.0) / (yMax - yMin)) * height) + heightBottom;
        g.setColor(Color.BLACK);
        g.fillOval(spaceFromLeft, y1 - 2, 5, 5);
        byte[] bytes = intToBytes(data.get(i - 1));
        g.drawBytes(bytes, 0, bytes.length, spaceFromLeft, y1 - 2);
    }

    private void paintOutlineGraph(Graphics g, int spacing, int heightBottom, int height, int yMax, int yMin,
            int spaceFromLeft) {
        // draw x axis
        g.setColor(Color.BLACK);
        g.drawLine(0, heightBottom, getWidth(), heightBottom);

        // draw y axis
        g.drawLine(spaceFromLeft - 5, height - heightBottom, spaceFromLeft - 5, heightBottom + spaceFromLeft - 5);

        // draw y axis numbers
        if (yMax - yMin <= 4) {

            for (int i = 1; i <= yMax - yMin; i++) {
                int heightTemp = -(int) Math.round(((i * 1.0) / (yMax - yMin)) * height) + heightBottom;

                // draw light gray line
                g.setColor(Color.LIGHT_GRAY);
                g.drawLine(spaceFromLeft - 5, heightTemp, getWidth(), heightTemp);

                // draw number and little black line
                g.setColor(Color.BLACK);
                byte[] bytes = intToBytes(yMin + i);
                g.drawBytes(bytes, 0, bytes.length, 5, heightTemp - 2);
                g.drawLine(spaceFromLeft - 5, heightTemp, spaceFromLeft - 10, heightTemp);
            }
        } else {
            int ySpacing = (yMax - yMin) / 4;
            for (int i = 1; i <= 4; i++) {
                int heightTemp = -(int) Math.round(( (ySpacing*i*1.0) / (yMax - yMin)) * height) + heightBottom;

                // draw light gray line
                g.setColor(Color.LIGHT_GRAY);
                g.drawLine(spaceFromLeft - 5, heightTemp, getWidth(), heightTemp);

                // draw number and little black line
                g.setColor(Color.BLACK);
                byte[] bytes = intToBytes(yMin + i * ySpacing);
                g.drawBytes(bytes, 0, bytes.length, 5, heightTemp - 2);
                g.drawLine(spaceFromLeft - 5, heightTemp, spaceFromLeft - 10, heightTemp);
            }
        }

        // draw x axis numbers
        int x = 0;
        g.setColor(Color.BLACK);
        int actualMonth = Market.getInstance().getActualMonth();
        for (int i = 0; i < monthsToDisplay; i++) {
            //if beginning of year, or Jan draw year
            if(i==0 || (actualMonth - monthsToDisplay + i) %12 == 1){
                byte[] bytes = intToBytes( (2000 + (actualMonth - (monthsToDisplay-i)) / 12));
                g.drawBytes(bytes, 0, bytes.length, x + spaceFromLeft, heightBottom + 40);
            }
            x = i * spacing;
            g.drawLine(x + spaceFromLeft + 2, heightBottom, x + spaceFromLeft + 2, heightBottom + 5);
            byte[] bytes = strToBytes(months[ (actualMonth - monthsToDisplay + i) %12]);
            g.drawBytes(bytes, 0, bytes.length, x + spaceFromLeft, heightBottom + 20+ (i%2)*10);
        }
    }

    private int getMax() {
        int max = Integer.MIN_VALUE;
        for (int i : data) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    private int getMin() {
        int min = Integer.MAX_VALUE;
        for (int i : data) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    private byte[] intToBytes(int nbr) {
        String str = Integer.toString(nbr);
        byte[] bytes = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            bytes[i] = (byte) str.charAt(i);
        }
        return bytes;
    }

    private byte[] strToBytes(String str){
        byte[] bytes = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            bytes[i] = (byte) str.charAt(i);
        }
        return bytes;
    }
}