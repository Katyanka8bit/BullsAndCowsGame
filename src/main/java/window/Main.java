package window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Main {

    JButton newGameButton = new JButton("Заново");
    JButton nextButton = new JButton("Следущий шаг");
    JButton sitoButton = new JButton("Отсеять");
    JButton showVariantsButton = new JButton("Показать");
    JLabel numberLabel = new JLabel("Введите число:");
    JLabel bullsLabel = new JLabel("Быки:");
    JLabel cowsLabel = new JLabel("Коровы:");
    JLabel turLabel = new JLabel("Попытка №:");
    JLabel variantsLabel = new JLabel("Количество оставшихся вариантов:");
    JLabel variantsShowLabel =  new JLabel();
    JLabel recomendationLabel = new JLabel("Рекомендуется ввести число:");
    JLabel showRecomendationLabel = new JLabel("0123");
    JLabel iconLabel = new JLabel();
    JLabel emptyLabel = new JLabel();
    JLabel emptyLabel1 = new JLabel();
    JLabel aboutLabel = new JLabel();
    JTextField numberText = new JTextField(8);
    JTextField bullsText = new JTextField(8);
    JTextField cowsText = new JTextField(8);
    JTextField sitoText = new JTextField(8);
    JLabel infoLabel = new JLabel("<html>________<br>");
    JPanel mainPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel infoPanel = new JPanel();
    

    Main(){
        JFrame jfrm = new JFrame("Bulls & Cows Demo");
        jfrm.setSize(1080, 280);
        jfrm.setLayout(new GridLayout(0,1,10,10));
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildGamePanel();
        buildInfoPanel(jfrm);
        buildMainPanel();
        jfrm.add(mainPanel);
        jfrm.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

        private Image scaleButtonImage(Component component,Dimension dimension, Image ButtonIcon) {
        double Width  = ButtonIcon.getWidth(component);
        double Height = ButtonIcon.getHeight(component);
        double xScale = dimension.getWidth()/Width;//component.getWidth() / Width;
        double yScale = dimension.getHeight()/Height;//component.getHeight() / Height;
        double Scale = Math.min(xScale, yScale);   //ToFit
        //double Scale = Math.max(xScale, yScale); //ToFill
        java.awt.Image scaled = ButtonIcon.getScaledInstance((int)(Scale * Width), (int)(Scale * Height), java.awt.Image.SCALE_SMOOTH);
        return scaled;
    }
    private void buildGamePanel(){
        gamePanel.setLayout(new GridLayout(8,2,5,5));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
        numberLabel.setLabelFor(numberText);
        gamePanel.add(numberLabel);
        gamePanel.add(numberText);
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        bullsLabel.setLabelFor(bullsText);
        bullsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gamePanel.add(bullsLabel);
        gamePanel.add(bullsText);

        cowsLabel.setLabelFor(cowsText);
        cowsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gamePanel.add(cowsLabel);
        gamePanel.add(cowsText);

        gamePanel.add(nextButton);
        gamePanel.add(emptyLabel);
        gamePanel.add(sitoText);
        gamePanel.add(sitoButton);
        gamePanel.add(variantsLabel);
        variantsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gamePanel.add(variantsShowLabel);
        variantsShowLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gamePanel.add(showVariantsButton);
        gamePanel.add(emptyLabel1);
        gamePanel.add(recomendationLabel);
        gamePanel.add(showRecomendationLabel);
        showRecomendationLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void buildInfoPanel(JFrame jfrm){
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEADING,5,5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        newGameButton.setPreferredSize(new Dimension((int)(jfrm.getSize().getWidth()-20)/2-15,25));
        infoLabel.setPreferredSize(new Dimension((int)(jfrm.getSize().getWidth()-20)/2-15,(int)(jfrm.getSize().getHeight()-20)/3-10));
        infoLabel.setVerticalTextPosition(SwingConstants.TOP);
        iconLabel.setSize(new Dimension((int)(jfrm.getSize().getWidth()-20)/2-15,(int)(jfrm.getSize().getHeight()-20)/3-10));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        iconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        iconLabel.setVerticalTextPosition(SwingConstants.CENTER);
        Image img = null;
        try {
            img = ImageIO.read(Main.class.getResource("/icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Icon icon = new ImageIcon(scaleButtonImage(iconLabel,new Dimension((int)(jfrm.getSize().getWidth()-20)/2,(int)((jfrm.getSize().getHeight()-20)/2.5)),img));
        jfrm.setIconImage(img);
        iconLabel.setIcon(icon);
        infoPanel.add(infoLabel);
        infoPanel.add(newGameButton);
        infoPanel.add(iconLabel);
    }

    private void buildMainPanel(){
        mainPanel.setLayout(new GridLayout(1,1,10,5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        mainPanel.setLayout(new GridLayout(1,2));
        mainPanel.add(gamePanel);
        mainPanel.add(infoPanel);
    }
}
