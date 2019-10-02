/**public class GUI {
    //currently not "graphic"
    private String menu = "1) Send Message\n2) Receive Message\n3) Close";
    Client client = null;
    public GUI(String address) {
        client = new Client(address,1337 );
    }
    public void DisplayMenu(){
        while(true){
            System.out.println(menu);
            client.running();
        }
    }
}
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUI {
    private Client client = null;
    public GUI(String address) {
        client = new Client(address,1337 );
        makeGUI();
    }

    public void makeGUI(){
        // MAIN WINDOW
        JFrame mainFrame = new JFrame("sendIt");
        mainFrame.setSize(700,400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //PANELS
        JPanel panel = new JPanel();
        panel.setSize(75,75);
        panel.setLayout(new FlowLayout());
        JLabel msgPanel = new JLabel("actions displayed here");

        // MENU BAR
        JMenuBar bar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Help");
        bar.add(menu1);
        bar.add(menu2);
        JMenuItem item1 = new JMenuItem("Open");
        JMenuItem item2 = new JMenuItem("About sendIt");
        menu1.add(item1);
        menu2.add(item2);

        // MENU BAR ACTION LISTENERS
        item1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String itemPicked = e.getActionCommand();
                msgPanel.setText("You have chosen: " + itemPicked);
            }
        });
        item2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String itemPicked = e.getActionCommand();
                msgPanel.setText("You have chosen: " + itemPicked);
            }
        });

        // TEXT BOX
        JLabel msgLabel = new JLabel("Enter your message: ");
        JTextField message = new JTextField(20);

        //BUTTONS
        JButton sendIt = new JButton("Send");
        sendIt.setPreferredSize(new Dimension(100,50));
        JButton closeIt = new JButton("Close");
        closeIt.setPreferredSize(new Dimension(100,50));
        JButton receiveIt = new JButton("Receive");
        receiveIt.setPreferredSize(new Dimension(100,50));

        // BUTTON ACTION LISTENERS
        closeIt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    client.exit();
                }catch(IOException i)
                {
                    System.out.println(i);
                }
            }
        });
        sendIt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    client.sendMessage(message.getText());
                }catch(IOException i)
                {
                    System.out.println(i);
                }
                msgPanel.setText("Your message is: " + message.getText());

            }
        });
        receiveIt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String text = "";
                try{
                    text = client.receiveMessage();
                }catch(IOException i)
                {
                    System.out.println(i);
                }
                msgPanel.setText("Server Message is: " + text);

            }
        });

        panel.add(msgLabel);
        panel.add(message);
        panel.add(sendIt);
        panel.add(receiveIt);
        panel.add(closeIt);
        panel.add(msgPanel);

        mainFrame.add(bar, BorderLayout.NORTH);
        mainFrame.add(panel);
        mainFrame.setVisible(true);

    }

}