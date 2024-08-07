package notepad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    
    private JTextArea textarea;
    private JScrollPane p;
    String data = "";

    public Notepad() {
        super("Notepad");
        setSize(1950, 1050);
        setLayout(new BorderLayout());

        ImageIcon ig = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepadicon.png"));
        Image ic = ig.getImage();
        setIconImage(ic);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.lightGray);

        // 1ST MENU ITEM
        JMenu file = new JMenu("File");
        file.setFont(new Font("AERIAL", Font.PLAIN, 15));

        JMenuItem item1 = new JMenuItem("New");
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        item1.addActionListener(this);

        JMenuItem item2 = new JMenuItem("Open");
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        item2.addActionListener(this);

        JMenuItem item3 = new JMenuItem("Save");
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        item3.addActionListener(this);

        JMenuItem item4 = new JMenuItem("Print");
        item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        item4.addActionListener(this);

        JMenuItem item5 = new JMenuItem("Exit");
        item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
        item5.addActionListener(this);

        // 2ND MENU ITEM
        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("AERIAL", Font.PLAIN, 15));

        JMenuItem edit1 = new JMenuItem("Copy");
        edit1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        edit1.addActionListener(this);

        JMenuItem edit2 = new JMenuItem("Paste");
        edit2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        edit2.addActionListener(this);

        JMenuItem edit3 = new JMenuItem("Cut");
        edit3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        edit3.addActionListener(this);

        JMenuItem edit4 = new JMenuItem("Select All");
        edit4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        edit4.addActionListener(this);

        // 3RD MENU ITEM
        JMenu help = new JMenu("Help");
        help.setFont(new Font("AERIAL", Font.PLAIN, 15));

        JMenuItem about = new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        about.addActionListener(this);

        // TEXTAREA
        textarea = new JTextArea();
        textarea.setFont(new Font("Playwrite Italia Moderna", Font.PLAIN, 20));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        p = new JScrollPane(textarea);
        p.setBorder(BorderFactory.createEmptyBorder());

        setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        file.add(item1);
        file.add(item2);
        file.add(item3);
        file.add(item4);
        file.add(item5);

        edit.add(edit1);
        edit.add(edit2);
        edit.add(edit3);
        edit.add(edit4);

        help.add(about);
        add(p, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        switch (command) {
            case "New":
                textarea.setText("");
                break;
            case "Open":
                JFileChooser c = new JFileChooser();
                c.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter only = new FileNameExtensionFilter(".txt files only", "txt");
                c.addChoosableFileFilter(only);
                int action = c.showOpenDialog(this);

                if (action != JFileChooser.APPROVE_OPTION)
                    return;
                else {
                    File file = c.getSelectedFile();
                    try {
                        FileReader reader = new FileReader(file);
                        BufferedReader r = new BufferedReader(reader);
                        textarea.read(r, null);
                        r.close();
                        textarea.requestFocus();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "Save":
                JFileChooser saveas = new JFileChooser();
                saveas.setApproveButtonText("Save");
                action = saveas.showOpenDialog(this);

                if (action != JFileChooser.APPROVE_OPTION)
                    return;

                File filename = new File(saveas.getSelectedFile() + ".txt");
                try (BufferedWriter outFile = new BufferedWriter(new FileWriter(filename))) {
                    textarea.write(outFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Print":
                try {
                    textarea.print();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Copy":
                data = textarea.getSelectedText();
                break;
            case "Paste":
                textarea.insert(data, textarea.getCaretPosition());
                break;
            case "Cut":
                data = textarea.getSelectedText();
                textarea.replaceRange("", textarea.getSelectionStart(), textarea.getSelectionEnd());
                break;
            case "Select All":
                textarea.selectAll();
                break;
            case "About":
                new About().setVisible(true);
                break;
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }
}
