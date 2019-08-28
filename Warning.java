package com.ethanyang.project_1;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Warning {
    private static Frame frame;
    private static Label textLabel;
    private static Panel buttons;
    public Button yes, no;
    public static String text;

    public Warning(String name, String... param) {
        frame = new Frame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1000, 250);
        frame.setTitle(name);
        setText(param[0]);
        textLabel = new Label(text);
        textLabel.setAlignment(Label.CENTER);
        textLabel.setFont(Resources.text_20);
        if (param.length > 2) {
            yes = new Button(param[1]);
            no = new Button(param[2]);
        } else {
            yes = new Button("Yes");
            no = new Button("No");
        }
        yes.setFont(Resources.text_20);
        no.setFont(Resources.text_20);
        buttons = new Panel(new FlowLayout());
        buttons.add(no);
        buttons.add(yes);
        frame.add(textLabel, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.SOUTH);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
        setCenter(frame);
        frame.setVisible(true);
    }

    private static void setText(String str) {
        text = str;
    }

    public void close() {
        frame.dispose();
    }

    public static void setCenter(Frame window) {
        int windowWidth = window.getWidth();
        int windowHeight = window.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        window.setLocation((screenWidth - windowWidth) / 2, (screenHeight - windowHeight) / 2);
    }
}
