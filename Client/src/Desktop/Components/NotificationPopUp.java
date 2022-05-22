package Desktop.Components;

import org.codehaus.jackson.map.ObjectMapper;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;


public class NotificationPopUp {
    DataOutputStream toServer;
    DataInputStream fromServer;
    ObjectMapper mapper;

    String request = "notification/getAll/4";

    public static void main(String[] args) throws AWTException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (SystemTray.isSupported()) {
            NotificationPopUp td = new NotificationPopUp();
            td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
    }
    public void displayTray() throws AWTException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        SystemTray tray = SystemTray.getSystemTray();

        URL file = new URL("https://www.wavsource.com/snds_2020-10-01_3728627494378403/sfx/bicycle_bell.wav");
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(ais);

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        ImageIcon icon = new ImageIcon("C:\\Users\\imanz\\Desktop\\waste-management\\Client\\logo.png");
        Image image1 = icon.getImage();

        TrayIcon trayIcon = new TrayIcon(image1, "Tray Demo");

        trayIcon.setImageAutoSize(true);

        trayIcon.setToolTip("Waste Management");
        tray.add(trayIcon);

        trayIcon.displayMessage("Trash Payment Due", "You're trash payment for 12/05/2022 is long over due", MessageType.INFO);
        clip.setFramePosition(0);
        clip.start();
    }
}
