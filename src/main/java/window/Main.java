package window;

import tools.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;


public class Main {
    public static final String ERROR_TITLE = "Ошибка!!!";
    JButton newGameButton = new JButton("Заново");
    JButton nextButton = new JButton("Следущий шаг");
    JButton sitoButton = new JButton("Отсеять");
    JButton showVariantsButton = new JButton("Показать");
    JLabel numberLabel = new JLabel("Введите число:");
    JLabel bullsLabel = new JLabel("Быки:");
    JLabel cowsLabel = new JLabel("Коровы:");
    JLabel variantsLabel = new JLabel("Количество оставшихся вариантов:");
    JLabel variantsShowLabel = new JLabel();
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
    JLabel infoLabel = new JLabel("<html><hr width=\"200%\" size=\"1\" />ТУР Число Быков Коров<br>" +
            "<hr width=\"200%\" size=\"1\"/>");
    JPanel mainPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel infoPanel = new JPanel();
    Integer tur = 0;
    Map<String, Boolean> mainMap;


    Main() {
        JFrame jfrm = new JFrame("Bulls & Cows Demo");
        jfrm.setSize(1080, 300);
        jfrm.setLayout(new GridLayout(0, 1, 10, 10));
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setResizable(false);
        buildGamePanel();
        buildInfoPanel(jfrm);
        buildMainPanel();
        jfrm.add(mainPanel);
        jfrm.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    private Image scaleButtonImage(Component component, Dimension dimension, Image image) {
        double width = image.getWidth(component);
        double height = image.getHeight(component);
        double xScale = dimension.getWidth() / width;
        double yScale = dimension.getHeight() / height;
        double scale = Math.min(xScale, yScale);   
        //double Scale = Math.max(xScale, yScale); //ToFill
        return image.getScaledInstance((int) (scale * width), (int) (scale * height), java.awt.Image.SCALE_SMOOTH);
    }

    private void buildGamePanel() {
        gamePanel.setLayout(new GridLayout(8, 2, 5, 5));
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
        mainMap = Game.getMainMap();
        showRecomendationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nextButton.addActionListener(actionEvent -> nextButtonClick());
        sitoButton.addActionListener(actionEvent -> sitoButtonClick());
        variantsShowLabel.setText(Game.getTrueInMap(mainMap).toString());
    }

    private void nextButtonClick() {
        String number = numberText.getText();
        String bulls = bullsText.getText();
        String cows = cowsText.getText();
        if (!number.isEmpty() && !bulls.isEmpty() && !cows.isEmpty()) {
            if (number.length() == 4) {
                if(checkNumber(number)) {
                    if ((Integer.parseInt(bulls) + Integer.parseInt(cows) < 5)) {
                        tur++;
                        infoLabel.setText(infoLabel.getText() + " " + tur.toString() + ":&nbsp&nbsp&nbsp " + number + "&nbsp&nbsp&nbsp&nbsp " + bulls + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp " + cows + "<br>");
                        Game.processNumber(mainMap,number,new Integer[]{Integer.parseInt(bulls),Integer.parseInt(cows)});
                        clearComponents();
                        variantsShowLabel.setText(Game.getTrueInMap(mainMap).toString());

                    } else {
                        JOptionPane.showMessageDialog(null, "Сумма быков и коров не должна превышать 4", ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                        bullsText.setText("");
                        cowsText.setText("");
                        bullsText.grabFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Число должно состоять из неповторяющихся цифр", ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                    numberText.setText("");
                    numberText.grabFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Число должно быть 4-х значным", ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                numberText.setText("");
                numberText.grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Не задан один из параметров", ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            numberText.grabFocus();
        }
        clearComponents();
        showRecomendationLabel.setText(Game.getRecommendation(mainMap));

    }

    private void buildInfoPanel(JFrame jfrm) {
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        newGameButton.setPreferredSize(new Dimension((int) (jfrm.getSize().getWidth() - 20) / 8 - 15, 25));
        infoLabel.setPreferredSize(new Dimension(200, 210));
        iconLabel.setSize(new Dimension((int) (jfrm.getSize().getWidth() - 20) / 8 - 15, (int) (jfrm.getSize().getHeight() - 20) / 3 - 10));
        Image img = null;
        try {
//            img = ImageIO.read(new File("C:\\Users\\user\\IdeaProjects\\BullsAndCowsGame\\src\\main\\resource\\icon.png"));
            img = ImageIO.read(new File(Main.class.getResource("/icon.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Icon icon = new ImageIcon(scaleButtonImage(iconLabel, new Dimension((int) (jfrm.getSize().getWidth() - 20) / 8, (int) ((jfrm.getSize().getHeight() - 20) / 2.5)), img));
        jfrm.setIconImage(img);
        iconLabel.setIcon(icon);
        Container container = new Container();
        container.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.insets.left = 10;
        c.insets.top = 10;
        c.insets.bottom = 10;
        infoLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 10));
        infoLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        infoLabel.setVerticalTextPosition(SwingConstants.TOP);
        infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        infoLabel.setVerticalAlignment(SwingConstants.TOP);
        c.gridheight = 3;
        container.add(infoLabel, c);
        infoPanel.add(container);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10, 10, 10, 10);
        iconLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        iconLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
        iconLabel.setText("<html>Copyright Dart Inc. 2021.<br>"
                + "Developers:<br> &nbsp&nbsp&nbsp Lex Tutor<br>" +
                "&nbsp&nbsp&nbsp Katyanka8bit");
        container.add(iconLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.insets.left = 10;
        c.insets.top = 10;
        c.insets.bottom = 10;
        c.gridheight = 2;

        c.anchor = GridBagConstraints.LAST_LINE_END;
        newGameButton.addActionListener(actionEvent -> newGameButtonClick());
        container.add(newGameButton, c);

    }

    private void buildMainPanel() {
        mainPanel.setLayout(new GridLayout(1, 1, 10, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(gamePanel);
        mainPanel.add(infoPanel);
    }

    private boolean checkNumber(String number){
        char i = number.charAt(0);
        char j = number.charAt(1);
        char k = number.charAt(2);
        char l = number.charAt(3);
        return ((i != j) && (i != k)) && ((i != l) && (j != k)) && ((j != l) && (k != l));
    }

    private void newGameButtonClick(){
        tur = 0;
        Game.getDefaultMap(mainMap);
        infoLabel.setText("<html><hr width=\"200%\" size=\"1\" />ТУР Число Быков Коров<br>" +
                "<hr width=\"200%\" size=\"1\"/>");
        clearComponents();
        variantsShowLabel.setText(Game.getTrueInMap(mainMap).toString());
    }
    private void clearComponents(){
        numberText.setText("");
        bullsText.setText("");
        cowsText.setText("");
        sitoText.setText("");
        numberText.grabFocus();
    }

    private void sitoButtonClick(){
        if(!sitoText.getText().isEmpty()) {
            Game.getSitoMap(mainMap, sitoText.getText());
            variantsShowLabel.setText(Game.getTrueInMap(mainMap).toString());
            sitoText.setText("");
        }
    }
}
