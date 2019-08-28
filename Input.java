package com.ethanyang.project_1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Input {
    static int width = 550;
    static int height = 200;
    static Frame frame;
    static Panel buttonP;
    static Label textLabel;
    JTextArea input;
    static Button ok;
    static Font f = new Font("黑体", Font.PLAIN, 25);

    public Input(String name, String... strings) {
        frame = new Frame();
        frame.setTitle(name);
        frame.setSize(width, height);
        frame.setLayout(new BorderLayout());
        textLabel = new Label(strings[0]);
        textLabel.setFont(f);
        frame.add(textLabel, BorderLayout.NORTH);
        input = new JTextArea(1, 5);
        // input.setPreferredSize(new Dimension(0,1));
        // input.setBounds(10, 10, 500, 50);
        input.setFont(f);
        Panel p = new Panel(new FlowLayout());
        p.add(input);
        frame.add(p, BorderLayout.CENTER);
        buttonP = new Panel(new FlowLayout());
        buttonP.setFont(f);
        if (strings.length > 1) {
            ok = new Button(strings[1]);
        } else {
            ok = new Button("OK");
        }
        buttonP.add(ok);
        frame.add(buttonP, BorderLayout.SOUTH);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
        setCenter(frame);
        frame.setVisible(true);
    }

    public String getInput() {
        return this.input.getText();
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
