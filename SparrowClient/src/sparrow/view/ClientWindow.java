package sparrow.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import sparrow.client.TCPClient;
import sparrow.constants.Constants;

public class ClientWindow extends JFrame {
    private static final long serialVersionUID = 8093757172462558425L;
    private int mWidth = 400;
    private int mHeight = 200;
    TCPClient mTcpClient;

    public ClientWindow() {
        initLayout();
        initModel();
    }

    public void initLayout() {
        JPanel content = new JPanel();
        setContentPane(content);
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        JTextArea jTextArea = new JTextArea();
        content.add(jTextArea);
        MessageBox.getInstance().setMessageBox(jTextArea);
        setPreferredSize(new Dimension(600, 400));
        setCenterDisplay();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Client");
        pack();
        setVisible(true);
    }

    public void initModel() {
        mTcpClient = new TCPClient("Pl1");
        mTcpClient.connect("localhost", Constants.TCP.SERVER_LISTEN_PORT);
    }

    private void setCenterDisplay() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (mHeight > screenSize.height) {
            mHeight = screenSize.height;
        }
        if (mWidth > screenSize.width) {
            mWidth = screenSize.width;
        }
        this.setLocation((screenSize.width - mWidth) / 2, (screenSize.height - mHeight) / 2);
    }

    public static void main(String[] args) {
        new ClientWindow();
    }
}