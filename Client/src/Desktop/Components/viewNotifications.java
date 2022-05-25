package Desktop.Components;

import DataHandlers.NotificationHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class viewNotifications extends JPanel{


    DataOutputStream toServer;
    static DataInputStream fromServer;
    static ObjectMapper mapper;

    public viewNotifications(DataOutputStream toServer, DataInputStream fromServer) throws IOException {
        this.toServer = toServer;
        this.fromServer = fromServer;
        mapper=new ObjectMapper();
        setVisible(false);
        setBounds(200,0,1166,768);
        setBackground(Color.white);
        setBorder(new EmptyBorder(new Insets(30,30,30,30)));

        // create a main panel
        JPanel mainPanel = new JPanel();
        displayNotifications();
        createNotification(mainPanel);

        Color main_bgc = new Color(247, 252, 255);
        mainPanel.setBackground(main_bgc);
        mainPanel.setLayout(new GridLayout(6,1,25, 15));
        mainPanel.setBorder(new EmptyBorder(new Insets(30,30,30,30)));

        add(mainPanel);


    }
//    private static void createPaymentNotification(JPanel panel) {
//        Font  f3  = new Font(Font.DIALOG,  Font.BOLD, 18);
//        Font  f1  = new Font(Font.DIALOG, Font.PLAIN,  15);
//        Color bgc = new Color(255,248,203);
//
//        JLabel notificationHeading = new JLabel("Notification title");
//        notificationHeading.setFont(f3);
//        JLabel notificationContent = new JLabel("Hello! You haven't paid for the waste collection for three months. Any delay can lead to charges.");
//        notificationContent.setFont(f1);
//        Box msg = Box.createVerticalBox();
//        msg.add(notificationHeading);
//        msg.add(notificationContent);
//
//        JLabel iconImage = new JLabel();
//        iconImage.setIcon(new ImageIcon("assets\\warning.png"));// your image here
//        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
//        iconPanel.add(iconImage);
//        iconPanel.setBackground(bgc);
//        iconPanel.setOpaque(true);
//
//        JPanel contentPanel = new JPanel();
//        contentPanel.add(msg);
//        contentPanel.setBackground(bgc);
//        contentPanel.setOpaque(true);
//
//
//        JButton checkBtn = new JButton("check");
//        checkBtn.setBackground(new Color(41, 88, 153));
//        checkBtn.setIcon(new ImageIcon("assets\\eye1.png"));// your image here
//        checkBtn.setForeground(Color.white);
//        JButton deleteBtn = new JButton("delete");
//        deleteBtn.setIcon(new ImageIcon("assets\\trash.png"));
//        deleteBtn.setBackground(new Color(214, 35, 78));
//        deleteBtn.setForeground(Color.white);
//        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
//        btns.setSize(300, 150);
//        btns.add(checkBtn);
//        btns.add(deleteBtn);
//        JLabel date = new JLabel("25 May 2022");
//        Box actionsPanel = Box.createVerticalBox();
//        actionsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
//        actionsPanel.add(btns);
//        actionsPanel.add(date);
//        btns.setBackground(bgc);
//        btns.setOpaque(true);
//
//
//        //Create a border
////        Border blackline = BorderFactory.createLineBorder(Color.black);
//        JPanel notificationsPanel = new JPanel();
////        notificationsPanel.setBorder(blackline);
//        notificationsPanel.setBackground(bgc);
//        notificationsPanel.setForeground(Color.white);
//        notificationsPanel.setLayout(new BorderLayout());
//        notificationsPanel.setBorder(new EmptyBorder(new Insets(10,20,10,20)));
//        notificationsPanel.add(iconPanel, BorderLayout.WEST);
//        notificationsPanel.add(contentPanel, BorderLayout.CENTER);
//        notificationsPanel.add(actionsPanel, BorderLayout.EAST);
//
//        panel.add(notificationsPanel);
//    }



    public void displayNotifications() {
            try {
                sendRequest("notification/getAll/" + 1);
                String response = fromServer.readUTF();
                System.out.println(response);

            }catch (Exception exception) {
                exception.printStackTrace();
            }
    }

    public void sendRequest( String request ){
        try{
            toServer.writeUTF( request );
        }catch ( IOException exception ){
            exception.printStackTrace();
        }
    }

    private static void createNotification(JPanel panel) throws IOException {
        String response = fromServer.readUTF();
        ArrayList<NotificationHandler> notifications=mapper.readValue(response,new TypeReference<ArrayList<NotificationHandler>>(){});
        Iterator<NotificationHandler> notificationIterator= notifications.iterator();

        while (notificationIterator.hasNext()){

            NotificationHandler handler=notificationIterator.next();
            System.out.println(handler.getNotificationId()+ "~" + handler.getContent());
            String notificationTitle = handler.getTitle();
            String Content = handler.getContent();
            String notificationType = handler.getType();
            String notificationDate = handler.getSentDate().toString();

            Font  f3  = new Font(Font.DIALOG,  Font.BOLD, 18);
            Font  f1  = new Font(Font.DIALOG, Font.PLAIN,  15);
            Color yellow = new Color(255,248,203);
            Color green = new Color(205,248,203);
            Color red = new Color(224, 121, 146);

            JButton deleteBtn = new JButton("delete");
            deleteBtn.setBackground(new Color(214, 35, 78));
            deleteBtn.setIcon(new ImageIcon("src/Desktop/Images/trash.png"));
            deleteBtn.setForeground(Color.white);

            if(notificationType == "ServiceNotification"){
                JLabel notificationHeading = new JLabel(notificationTitle);
                notificationHeading.setFont(f3);
                JLabel notificationContent = new JLabel(Content);
                notificationContent.setFont(f1);
                Box msg = Box.createVerticalBox();
                msg.add(notificationHeading);
                msg.add(notificationContent);

                JLabel iconImage = new JLabel();
                iconImage.setIcon(new ImageIcon("src/Desktop/Images/danger.png"));// your image here
                JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
                iconPanel.add(iconImage);
                iconPanel.setBackground(yellow);
                iconPanel.setOpaque(true);

                JPanel contentPanel = new JPanel();
                contentPanel.add(msg);
                contentPanel.setBackground(yellow);
                contentPanel.setOpaque(true);


                JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                btns.setSize(300, 150);
                btns.add(deleteBtn);

                JLabel date = new JLabel(notificationDate);
                Box actionsPanel = Box.createVerticalBox();
                actionsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                actionsPanel.add(btns);
                actionsPanel.add(date);
                btns.setBackground(yellow);
                btns.setOpaque(true);



                JPanel notificationsPanel = new JPanel();
                notificationsPanel.setBackground(yellow);
                notificationsPanel.setForeground(Color.white);
                notificationsPanel.setLayout(new BorderLayout());
                notificationsPanel.setBorder(new EmptyBorder(new Insets(10,20,10,20)));
                notificationsPanel.add(iconPanel, BorderLayout.WEST);
                notificationsPanel.add(contentPanel, BorderLayout.CENTER);
                notificationsPanel.add(actionsPanel, BorderLayout.EAST);
                panel.add(notificationsPanel);


                deleteBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (deleteBtn.getText().equals("delete")){
                            notificationsPanel.setVisible(false);
                            panel.remove(notificationsPanel);

                        }
                    }
                });

            }else if(notificationType == "Provided Service Notification"){
                JLabel notificationHeading = new JLabel(notificationTitle);
                notificationHeading.setFont(f3);
                JLabel notificationContent = new JLabel(Content);
                notificationContent.setFont(f1);
                Box msg = Box.createVerticalBox();
                msg.add(notificationHeading);
                msg.add(notificationContent);

                JLabel iconImage = new JLabel();
                iconImage.setIcon(new ImageIcon("src/Desktop/Images/danger.png"));// your image here
                JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
                iconPanel.add(iconImage);
                iconPanel.setBackground(green);
                iconPanel.setOpaque(true);

                JPanel contentPanel = new JPanel();
                contentPanel.add(msg);
                contentPanel.setBackground(green);
                contentPanel.setOpaque(true);

                JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                btns.setSize(300, 150);
                btns.add(deleteBtn);

                JLabel date = new JLabel(notificationDate);
                Box actionsPanel = Box.createVerticalBox();
                actionsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                actionsPanel.add(btns);
                actionsPanel.add(date);
                btns.setBackground(green);
                btns.setOpaque(true);



                JPanel notificationsPanel = new JPanel();
                notificationsPanel.setBackground(green);
                notificationsPanel.setForeground(Color.white);
                notificationsPanel.setLayout(new BorderLayout());
                notificationsPanel.setBorder(new EmptyBorder(new Insets(10,20,10,20)));
                notificationsPanel.add(iconPanel, BorderLayout.WEST);
                notificationsPanel.add(contentPanel, BorderLayout.CENTER);
                notificationsPanel.add(actionsPanel, BorderLayout.EAST);
                panel.add(notificationsPanel);


                deleteBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (deleteBtn.getText().equals("delete")){
                            notificationsPanel.setVisible(false);
                            panel.remove(notificationsPanel);

                        }
                    }
                });

            }else if(notificationType == "Payment Warning Notification"){

                JLabel notificationHeading = new JLabel(notificationTitle);
                notificationHeading.setFont(f3);
                JLabel notificationContent = new JLabel(Content);
                notificationContent.setFont(f1);
                Box msg = Box.createVerticalBox();
                msg.add(notificationHeading);
                msg.add(notificationContent);

                JLabel iconImage = new JLabel();
                iconImage.setIcon(new ImageIcon("src/Desktop/Images/danger.png"));// your image here
                JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
                iconPanel.add(iconImage);
                iconPanel.setBackground(red);
                iconPanel.setOpaque(true);

                JPanel contentPanel = new JPanel();
                contentPanel.add(msg);
                contentPanel.setBackground(red);
                contentPanel.setOpaque(true);

                JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                btns.setSize(300, 150);
                btns.add(deleteBtn);

                JLabel date = new JLabel(notificationDate);
                Box actionsPanel = Box.createVerticalBox();
                actionsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                actionsPanel.add(btns);
                actionsPanel.add(date);
                btns.setBackground(red);
                btns.setOpaque(true);



                JPanel notificationsPanel = new JPanel();
                notificationsPanel.setBackground(red);
                notificationsPanel.setForeground(Color.white);
                notificationsPanel.setLayout(new BorderLayout());
                notificationsPanel.setBorder(new EmptyBorder(new Insets(10,20,10,20)));
                notificationsPanel.add(iconPanel, BorderLayout.WEST);
                notificationsPanel.add(contentPanel, BorderLayout.CENTER);
                notificationsPanel.add(actionsPanel, BorderLayout.EAST);
                panel.add(notificationsPanel);


                deleteBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (deleteBtn.getText().equals("delete")){
                            notificationsPanel.setVisible(false);
                            panel.remove(notificationsPanel);

                        }
                    }
                });

            }


        }


    }

//    private static void createPaymentNotification(JPanel panel) {
//
//        Font  f3  = new Font(Font.DIALOG,  Font.BOLD, 18);
//        Font  f1  = new Font(Font.DIALOG, Font.PLAIN,  15);
//        Color bgc = new Color(255,248,203);
//
//
//        JLabel notificationHeading = new JLabel("Notification title");
//        notificationHeading.setFont(f3);
//        JLabel notificationContent = new JLabel("Hello! You haven't paid for the waste collection for three months. Any delay can lead to charges.");
//        notificationContent.setFont(f1);
//        Box msg = Box.createVerticalBox();
//        msg.add(notificationHeading);
//        msg.add(notificationContent);
//
//        JLabel iconImage = new JLabel();
//        iconImage.setIcon(new ImageIcon("src/Desktop/Images/warning.png"));// your image here
//        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
//        iconPanel.add(iconImage);
//        iconPanel.setBackground(bgc);
//        iconPanel.setOpaque(true);
//
//        JPanel contentPanel = new JPanel();
//        contentPanel.add(msg);
//        contentPanel.setBackground(bgc);
//        contentPanel.setOpaque(true);
//
//
////        JButton checkBtn = new JButton("check");
////        checkBtn.setBackground(new Color(41, 88, 153));
////        checkBtn.setIcon(new ImageIcon("assets\\eye1.png"));// your image here
////        checkBtn.setForeground(Color.white);
//
//        JButton deleteBtn = new JButton("delete");
//        deleteBtn.setBackground(new Color(214, 35, 78));
//        deleteBtn.setIcon(new ImageIcon("src/Desktop/Images/trash.png"));
//        deleteBtn.setForeground(Color.white);
//
//        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
//        btns.setSize(300, 150);
////        btns.add(checkBtn);
//        btns.add(deleteBtn);
//
//        JLabel date = new JLabel("25 May 2022");
//        Box actionsPanel = Box.createVerticalBox();
//        actionsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
//        actionsPanel.add(btns);
//        actionsPanel.add(date);
//        btns.setBackground(bgc);
//        btns.setOpaque(true);
//
////        JLayeredPane fullNotification = new JLayeredPane();
//        JPanel fullNotification = new JPanel();
//        fullNotification.add(new JLabel("Hello this is a full notification. Hello this is a full notification Hello this is a full notification. Hello this is a full notification. Hello this is a full notification"));
//        fullNotification.setBounds(100, 100, 400, 400);
//        fullNotification.setBackground(new Color(230, 232, 232));
//        fullNotification.setVisible(false);
//        JButton backbtn = new JButton("Back");
//        backbtn.setBackground(new Color(41, 88, 153));
//        backbtn.setIcon(new ImageIcon("src/Desktop/Images/back.png"));
//        backbtn.setForeground(Color.white);
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        buttonPanel.add(backbtn);
//        fullNotification.add(buttonPanel, BorderLayout.SOUTH);
//
//
//        //Create a border
////        Border blackline = BorderFactory.createLineBorder(Color.black);
//        JPanel notificationsPanel = new JPanel();
////        notificationsPanel.setBorder(blackline);
//        notificationsPanel.setBackground(bgc);
//        notificationsPanel.setForeground(Color.white);
//        notificationsPanel.setLayout(new BorderLayout());
//        notificationsPanel.setBorder(new EmptyBorder(new Insets(10,20,10,20)));
//        notificationsPanel.add(iconPanel, BorderLayout.WEST);
//        notificationsPanel.add(contentPanel, BorderLayout.CENTER);
//        notificationsPanel.add(actionsPanel, BorderLayout.EAST);
//        panel.add(notificationsPanel);
//
//
//        deleteBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (deleteBtn.getText().equals("delete")){
//                    notificationsPanel.setVisible(false);
//                    panel.remove(notificationsPanel);
//
//                }
//            }
//        });
//
//
//        //++++++++++++++++++ CHECK BUTTON +++++++++++++
//
////        checkBtn.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                if (checkBtn.getText().equals("check"))
////                    panel.add(fullNotification);
////                    fullNotification1.setVisible(true);
////
////
////                //logic for the back btn
////                    backbtn.addActionListener(new ActionListener() {
////                        @Override
////                        public void actionPerformed(ActionEvent e) {
////                            if (backbtn.getText().equals("Back"))
////                                //logic to go back to the original page
////                                fullNotification.setVisible(false);
////                        }
////                    });
////            }
////        });
//    }

//    private static void wasteCollectionNotification(JPanel panel) {
//
//        Font  f3  = new Font(Font.DIALOG,  Font.BOLD, 18);
//        Font  f1  = new Font(Font.DIALOG, Font.PLAIN,  15);
//        Color bgc = new Color(125, 181, 144);
//
//
//        JLabel notificationHeading = new JLabel("Notification title");
//        notificationHeading.setFont(f3);
//        JLabel notificationContent = new JLabel("Hello! You haven't paid for the waste collection for three months. Any delay can lead to charges.");
//        notificationContent.setFont(f1);
//        Box msg = Box.createVerticalBox();
//        msg.add(notificationHeading);
//        msg.add(notificationContent);
//
//        JLabel iconImage = new JLabel();
//        iconImage.setIcon(new ImageIcon("src/Desktop/Images/dollar.png"));// your image here
//        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
//        iconPanel.add(iconImage);
//        iconPanel.setBackground(bgc);
//        iconPanel.setOpaque(true);
//
//        JPanel contentPanel = new JPanel();
//        contentPanel.add(msg);
//        contentPanel.setBackground(bgc);
//        contentPanel.setOpaque(true);
//
//
////        JButton checkBtn = new JButton("check");
////        checkBtn.setBackground(new Color(41, 88, 153));
////        checkBtn.setIcon(new ImageIcon("assets\\eye1.png"));// your image here
////        checkBtn.setForeground(Color.white);
//
//        JButton deleteBtn = new JButton("delete");
//        deleteBtn.setBackground(new Color(214, 35, 78));
//        deleteBtn.setIcon(new ImageIcon("src/Desktop/Images/trash.png"));
//        deleteBtn.setForeground(Color.white);
//
//        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
//        btns.setSize(300, 150);
////        btns.add(checkBtn);
//        btns.add(deleteBtn);
//
//        JLabel date = new JLabel("25 May 2022");
//        Box actionsPanel = Box.createVerticalBox();
//        actionsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
//        actionsPanel.add(btns);
//        actionsPanel.add(date);
//        btns.setBackground(bgc);
//        btns.setOpaque(true);
//
////        JLayeredPane fullNotification = new JLayeredPane();
//        JPanel fullNotification = new JPanel();
//        fullNotification.add(new JLabel("Hello this is a full notification. Hello this is a full notification Hello this is a full notification. Hello this is a full notification. Hello this is a full notification"));
//        fullNotification.setBounds(100, 100, 400, 400);
//        fullNotification.setBackground(new Color(230, 232, 232));
//        fullNotification.setVisible(false);
//        JButton backbtn = new JButton("Back");
//        backbtn.setBackground(new Color(41, 88, 153));
//        backbtn.setIcon(new ImageIcon("src/Desktop/Images/back.png"));
//        backbtn.setForeground(Color.white);
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        buttonPanel.add(backbtn);
//        fullNotification.add(buttonPanel, BorderLayout.SOUTH);
//
//
//        //Create a border
////        Border blackline = BorderFactory.createLineBorder(Color.black);
//        JPanel notificationsPanel = new JPanel();
////        notificationsPanel.setBorder(blackline);
//        notificationsPanel.setBackground(bgc);
//        notificationsPanel.setForeground(Color.white);
//        notificationsPanel.setLayout(new BorderLayout());
//        notificationsPanel.setBorder(new EmptyBorder(new Insets(10,20,10,20)));
//        notificationsPanel.add(iconPanel, BorderLayout.WEST);
//        notificationsPanel.add(contentPanel, BorderLayout.CENTER);
//        notificationsPanel.add(actionsPanel, BorderLayout.EAST);
//        panel.add(notificationsPanel);
//
//
//        deleteBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (deleteBtn.getText().equals("delete")){
//                    notificationsPanel.setVisible(false);
//                    panel.remove(notificationsPanel);
//                }
//            }
//        });
//
//
//        //++++++++++++++++++ CHECK BUTTON +++++++++++++
//
////        checkBtn.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                if (checkBtn.getText().equals("check"))
////                    panel.add(fullNotification);
////                    fullNotification1.setVisible(true);
////
////
////                //logic for the back btn
////                    backbtn.addActionListener(new ActionListener() {
////                        @Override
////                        public void actionPerformed(ActionEvent e) {
////                            if (backbtn.getText().equals("Back"))
////                                //logic to go back to the original page
////                                fullNotification.setVisible(false);
////                        }
////                    });
////            }
////        });
//    }

//    private static void wasteCollectionNotification(JPanel panel) {
//        Font  f3  = new Font(Font.DIALOG,  Font.BOLD, 18);
//        Font  f1  = new Font(Font.DIALOG, Font.PLAIN,  15);
//        Color bgc = new Color(125, 181, 144);
//
//        JLabel notificationHeading = new JLabel("Notification title");
//        notificationHeading.setFont(f3);
//        JLabel notificationContent = new JLabel("Hello! You haven't paid for the waste collection for three months. Any delay can lead to charges.");
//        notificationContent.setFont(f1);
//        Box msg = Box.createVerticalBox();
//        msg.add(notificationHeading);
//        msg.add(notificationContent);
//
//
//        JLabel iconImage = new JLabel();
//        iconImage.setIcon(new ImageIcon("assets\\dollar.png"));// your image here
//        iconImage.setSize(100, 100);
//        JPanel iconPanel = new JPanel();
//        iconPanel.add(iconImage);
//        iconPanel.setBackground(bgc);
//        iconPanel.setOpaque(true);
//
//        JPanel contentPanel = new JPanel();
//        contentPanel.add(msg);
//        contentPanel.setBackground(bgc);
//        contentPanel.setOpaque(true);
//
//
//        JButton checkBtn = new JButton("check");
//        checkBtn.setBackground(new Color(41, 88, 153));
//        checkBtn.setIcon(new ImageIcon("assets\\eye1.png"));// your image here
//        checkBtn.setForeground(Color.white);
//        JButton deleteBtn = new JButton("delete");
//        deleteBtn.setBackground(new Color(214, 35, 78));
//        deleteBtn.setIcon(new ImageIcon("assets\\trash.png"));//
//        deleteBtn.setForeground(Color.white);
//        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
//        btns.setSize(300, 150);
//        btns.add(checkBtn);
//        btns.add(deleteBtn);
//        JLabel date = new JLabel("25 May 2022");
//        Box actionsPanel = Box.createVerticalBox();
//        actionsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
//        actionsPanel.add(btns);
//        actionsPanel.add(date);
//        btns.setBackground(bgc);
//        btns.setOpaque(true);
//
//
//        //Create a border
////        Border blackline = BorderFactory.createLineBorder(Color.black);
//        JPanel notificationsPanel = new JPanel();
////        notificationsPanel.setBorder(blackline);
//        notificationsPanel.setBackground(bgc);
//        notificationsPanel.setForeground(Color.white);
//        notificationsPanel.setLayout(new BorderLayout());
//        notificationsPanel.setBorder(new EmptyBorder(new Insets(10,20,10,20)));
//        notificationsPanel.add(iconPanel, BorderLayout.WEST);
//        notificationsPanel.add(contentPanel, BorderLayout.CENTER);
//        notificationsPanel.add(actionsPanel, BorderLayout.EAST);
//
//        panel.add(notificationsPanel);
//    }

}

