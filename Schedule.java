package com.ethanyang.project_1;
/**
 * 课程表边栏(Chinese Simplified)
 * English Version Coming Soon
 * 作者：杨易凡
 * @author YangYifan
 * Copyright(c) 2019 Ethan Yang
 * 
 * Version 2.0
 * 2019.07.13.
 */

import java.awt.*;
//import java.
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Schedule {
    static Frame frame;
    static Panel mainPanel, titlePanel;
    static Label l1, l2, l3, l4, l5, l6, l7, l8, l9;
    static Label[] labelArray;
    static ArrayList<String> data;
    static ArrayList<Label> labelList;
    // 日程信息：可修改
    static String[] Mon = new String[] { "物理", "化学", "数学", "政治", "体育", "英语", "语文", "班会", "心理" };
    static String[] Tue = new String[] { "地理", "化学", "英语", "英语", "数学", "语文", "物理", "数竞", "数竞" };
    static String[] Wed = new String[] { "数学", "物理", "语文", "英语", "体育", "政治", "历史", "物竞", "物竞" };
    static String[] Thu = new String[] { "地理", "物理", "语文", "语文", "数学", "历史", "化学", "数竞", "数竞" };
    static String[] Fri = new String[] { "地理", "历史", "数学", "英语", "化学", "微机", "阅览", "物竞", "物竞" };
    static String[] Sat = new String[] { "英语", "数学", "语文", "化学", "物理", "放", "学", "回", "家" };
    static String[] Sun = new String[] { "英语", "数学", "语文", "化学", "物理", "OR", "晚", "自", "习" };
    // Constants:
    static final int MONDAY = 1;
    static final int TUESDAY = 2;
    static final int WEDNESDAY = 3;
    static final int THURSDAY = 4;
    static final int FRIDAY = 5;
    static final int SATURDAY = 6;
    static final int SUNDAY = 7;

    // Functional Variables:
    static ArrayList<Integer> index = new ArrayList<Integer>();
    static boolean isChangeable = false;
    static boolean classOverNotify = true;
    static ArrayList<String> notifications = new ArrayList<String>();
    static String inputString = "";
    static String time = "";

    public Schedule() {
        // 录入课表信息，根据当天时间自动选择
        // !!!!!!!!!Attention!!!!!!!!
        // Fatal:
        // Please be careful that the weekdays below are obtained from the system, the
        // language is identical to that of your system
        int Day = 0;
        if (getWeek().equals("Monday")) {
            Day = MONDAY;
        } else if (getWeek().equals("Tuesday")) {
            Day = TUESDAY;
        } else if (getWeek().equals("Wednesday")) {
            Day = WEDNESDAY;
        } else if (getWeek().equals("Thursday")) {
            Day = THURSDAY;
        } else if (getWeek().equals("Friday")) {
            Day = FRIDAY;
        } else if (getWeek().equals("Saturday")) {
            Day = SATURDAY;
        } else if (getWeek().equals("Sunday")) {
            Day = SUNDAY;
        }
        switch (Day) {
        case MONDAY:
            data = fitArray(Mon);
            break;
        case TUESDAY:
            data = fitArray(Tue);
            break;
        case WEDNESDAY:
            data = fitArray(Wed);
            break;
        case THURSDAY:
            data = fitArray(Thu);
            break;
        case FRIDAY:
            data = fitArray(Fri);
            break;
        case SATURDAY:
            data = fitArray(Sat);
            break;
        case SUNDAY:
            data = fitArray(Sun);
            break;
        default:
            break;
        }

        // 初始化图形窗口
        frame = new Frame();
        // frame.setBounds(1500, 200, 500, 800);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setAlwaysOnTop(true);
        // frame.setUndecorated(true);
        // frame.setOpacity(0.5f);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int input = e.getKeyCode();
                if (input == KeyEvent.VK_F6) {
                    System.exit(0);
                }
                if (input == KeyEvent.VK_F9) {
                    isChangeable = !isChangeable;
                    if (isChangeable) {
                        System.out.println("Schedule is now Changeable!");
                    } else {
                        System.out.println("Schedule is now Unchangeable!");
                    }

                }
                if (input == KeyEvent.VK_F2) {

                    Input newNote = new Input("添加下课提醒", "请输入提醒的时间，以空格分隔（HH MM SS)");
                    newNote.frame.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            int key = e.getKeyCode();
                            if (key == KeyEvent.VK_ENTER) {
                                addNotification(newNote);
                            }
                            if (key == KeyEvent.VK_ESCAPE) {
                                newNote.close();
                            }
                        }
                    });

                    newNote.ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            addNotification(newNote);
                        }
                    });
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                // 这是个可以玩死你的彩蛋，我之所以把它注释掉是因为，班主任被耍了
                /*
                 * frame.setVisible(false); String[] datax = new String[]
                 * {"内置AI:","你尽可","以消灭","我","可","你就是","关不掉","我","哈哈哈"}; try {
                 * Thread.sleep(1500); } catch (InterruptedException e1) { e1.printStackTrace();
                 * } l1.setText(datax[0]); l2.setText(datax[1]); l3.setText(datax[2]);
                 * l4.setText(datax[3]); l5.setText(datax[4]); l6.setText(datax[5]);
                 * l7.setText(datax[6]); l8.setText(datax[7]); l9.setText(datax[8]);
                 * frame.pack(); setSide(frame); frame.setVisible(true);
                 */
                /*
                 * try { Thread.sleep(10000); } catch (InterruptedException e1) {
                 * e1.printStackTrace(); }
                 */
                /*
                 * l1.setText(data[0]); l2.setText(data[1]); l3.setText(data[2]);
                 * l4.setText(data[3]); l5.setText(data[4]); l6.setText(data[5]);
                 * l7.setText(data[6]); l8.setText(data[7]); l9.setText(data[8]); frame.pack();
                 */
            }
            /*
             * @Override public void windowIconified(WindowEvent e) {
             * 
             * }
             */
        });

        // 初始化组件
        mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(data.size() + 1, 1, 30, 50));
        titlePanel = new Panel();
        titlePanel.setLayout(new GridLayout(4, 1));

        /*
         * 表头日期显示模块：
         */
        // 三种日期时间格式：
        SimpleDateFormat year = new SimpleDateFormat("YYYY年");
        SimpleDateFormat monDay = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        // 格式化日期时间显示：
        Date today = new Date();
        Label[] dates = new Label[4]; // 年 月日 星期 时间
        dates[0] = new Label(year.format(today));
        dates[1] = new Label(monDay.format(today));
        dates[2] = new Label(getWeek());
        dates[3] = new Label(time.format(today));
        // 将日期时间标签添加到组件中：
        addComponent(dates, titlePanel);
        // 这里的t是程序运行初始化时的时刻，实时更新时间模块在后面
        /*
         * 日期时间显示模块结束
         */

        /*
         * 课表显示模块：
         */
        // 初始化课表标签：
        labelArray = createLabelArray(data);

        // 设置字体：字体，效果，字号
        Font font = new Font("楷体", Font.BOLD, 30);// 方便统一修改
        formatFont(labelArray, font);

        // 时间显示模块的字体也在这里进行初始化设置
        Font titleFont = new Font("黑体", Font.BOLD, 13);// 方便统一修改
        formatFont(dates, titleFont);
        // 将课表显示标签加入面板组件中：
        addComponent(labelArray, mainPanel);
        addListener(labelArray);

        // 设置文字对其方式：水平中心对齐
        setAlignment(labelArray, Label.CENTER);
        // 日期也一起搞掉：
        setAlignment(dates, Label.CENTER);
        /*
         * 课表显示模块结束
         */

        // Status Report:
        System.out.println("data = " + data);
        System.out.println("notifications = " + notifications);
        // System.out.println();
        // System.out.println();

        /*
         * 图形窗口界面设置：
         */
        // 将两个面板添加进窗口：
        // 使用边界布局，课表主面板在CENTER区域；时间日期在NORTH区域；
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(titlePanel, BorderLayout.NORTH);
        // 设置窗口大小：适当大小，包含所有组件：
        frame.pack();

        // 靠右边模块，可选，注释去除
        setRightSide(frame);

        // 使窗口可见：
        frame.setVisible(true);
        /*
         * 图形窗口界面设置结束
         */

        /*
         * 时间实时更新模块：
         */
        // 实时，所以使用无穷循环：
        while (true) {
            try {// try...catch...保护程序健康运行
                Thread.sleep(1000);
                // 线程暂停1000ms
                // 为防止内存占用，每隔一秒更新一次
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            // 将时间标签信息设置为实时获取的时间
            dates[3].setText(time.format(new Date()));
            // 下课播报功能，请谨慎使用看人下菜，见风使舵方能使得万年船
            if (classOverNotify) {
                if (inList(dates[3].getText(), notifications)) {
                    System.out.println("Notifying...");
                    Warning classOver = new Warning("下课小精灵", "老师，您辛苦了！同学们提醒您：下课时间到了！您该去休息啦！谢谢老师您的付出！我们饶过彼此可好？",
                            "好的，我马上下课", "OK, 我现在下课");
                    classOver.yes.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            classOver.close();
                        }
                    });
                }
            }
        }
        /*
         * 时间实时更新模块结束；
         */
    }

    /*
     * 静态方法 调整窗口使其靠在屏幕最右边并填充屏幕高度 为main方法提供复用代码支持 方便修改优化
     */
    public static void setRightSide(Frame window) {
        int windowWidth = window.getWidth();
        int windowHeight = window.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        // 填充高度：可选
        window.setSize(windowWidth, screenHeight);
        window.setLocation(screenWidth - windowWidth, 0);
    }

    public static void setLeftSide(Frame window) {
        int windowWidth = window.getWidth();
        int windowHeight = window.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        // 填充高度：可选
        window.setSize(windowWidth, screenHeight);
        window.setLocation(0, 0);
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

    public static String getWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(new Date());
        return week;
    }

    public static ArrayList<String> fitArray(String[] arr) {
        ArrayList<String> fit = new ArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            fit.add(arr[i]);
        }
        return fit;
    }

    public static Label[] createLabelArray(ArrayList<String> l) {
        Label[] arr = new Label[l.size()];
        for (int i = 0; i < l.size(); i++) {
            arr[i] = new Label(l.get(i));
        }
        return arr;
    }

    public static void formatFont(Label[] arr, Font font) {
        for (int i = 0; i < arr.length; i++) {
            arr[i].setFont(font);
        }
    }

    public static void addComponent(Component ele, Panel tar) {
        tar.add(ele);
    }

    public static void addComponent(Component ele, Frame frame) {
        frame.add(ele);
    }

    public static void addComponent(Component[] elist, Panel tar) {
        for (int i = 0; i < elist.length; i++) {
            addComponent(elist[i], tar);
        }
    }

    public static void addComponent(Component[] elist, Frame tar) {
        for (int i = 0; i < elist.length; i++) {
            addComponent(elist[i], tar);
        }
    }

    public static void setAlignment(Label[] arr, int Alignment) {
        for (int i = 0; i < arr.length; i++) {
            arr[i].setAlignment(Alignment);
        }
    }

    // 换课实现
    public static void exchange(int i, int j) {
        Label temp = labelArray[i];
        labelArray[i] = labelArray[j];
        labelArray[j] = temp;
        addComponent(labelArray, mainPanel);
        frame.pack();
    }

    public static void addListener(Label lab) {
        lab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isChangeable) {
                    if (index.size() < 2) {
                        index.add(indexOf(lab, labelArray));
                        System.out.println(lab.getText());
                    }
                    if (index.size() == 2) {
                        exchange(index.remove(0), index.remove(0));
                        System.out.println("Classes Arrangement has Changed!");
                        System.out.println("");
                    }
                } else {
                    System.out.println("Schedule is Unchangeable, please press F9 to turn on the switch!");
                }

            }
        });
    }

    public static void addListener(Label[] arr) {
        for (int i = 0; i < arr.length; i++) {
            addListener(arr[i]);
        }
    }

    public static int indexOf(Object a, Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == a) {
                return i;
            }
        }
        return -1;
    }

    public static void addNotification(Input newNote) {
        inputString = newNote.getInput();
        String[] split = inputString.split(" ");
        boolean valid = true;
        try {
            if (Integer.parseInt(split[0]) > 24 || Integer.parseInt(split[0]) < 0) {
                valid = false;
            }
            if (Integer.parseInt(split[1]) > 60 || Integer.parseInt(split[1]) < 0) {
                valid = false;
            }
            if (Integer.parseInt(split[2]) > 60 || Integer.parseInt(split[2]) < 0) {
                valid = false;
            }
            if (valid) {
                time = inputString.replaceAll(" ", ":");
                notifications.add(time);
                System.out.println("New Notification Added at " + time);
                newNote.close();
            }
        } catch (NumberFormatException nfe) {
            valid = false;
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            valid = false;
        } finally {
            if (!valid) {
                System.out.println("Input time is not valid. Please try again.");
                newNote.textLabel.setText("Input time is not valid. Please try again.");
            }
        }
    }

    public static void switchNote() {
        classOverNotify = !classOverNotify;
    }

    // Main Method
    public static void main(String[] args) throws Exception {
        Schedule schedule = new Schedule();
    }

    public static boolean inList(String o, ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (o.equals(list.get(i))) {
                return true;
            }
        }
        return false;
    }
}

/*
 * Version 2.0
 * 
 * yangyifan529@gmail.com
 * 
 */
