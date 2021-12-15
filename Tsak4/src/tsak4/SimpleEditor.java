/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsak4;

import java.awt.*;
import javax.swing.*;


/**
 *
 * @author Shwartskopff
 */
public class SimpleEditor extends JFrame{
    
    private Container cp;
    private JLabel fileName;
    private JTextArea text;
    private JMenuBar bar;
    private JMenu[] menu;
    private JMenuItem[] commandMenu;
    private JButton[] commandButton;
    private SimpleEditorListener listener;
    
    protected SimpleEditor(){
        setTitle("Simple text editor");
        init();
        createMenu();
        setVisible(true);
    }
             
    private void init(){
    
    setSize(500,500);
    setLocation(600,200);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   
    cp = getContentPane();   
    listener = new SimpleEditorListener(this);    
        
        cp.setLayout(new BorderLayout());
        JPanel ButtonsPanel = new JPanel();
        ButtonsPanel.setLayout(new FlowLayout());
        cp.add(ButtonsPanel, BorderLayout.NORTH);
        
       
        createButtons();
        ButtonsPanel.add(commandButton[0]);
        ButtonsPanel.add(commandButton[1]);
        ButtonsPanel.add(commandButton[2]);
        ButtonsPanel.add(commandButton[3]);
        
        text = new JTextArea();
        text.setBackground(Color.YELLOW);
        cp.add(text, BorderLayout.CENTER);
        
        fileName = new JLabel();
        fileName.setText("INFO: creation new file. New File Name:");
        fileName.setHorizontalTextPosition(PROPERTIES);
        cp.add(fileName, BorderLayout.SOUTH);
    }
    
    public void setJLabelText (String str){
        fileName.setText(str);
    }
    
    private void createMenu() {
        bar = new JMenuBar();
        menu = new JMenu[] {new JMenu("File"), new JMenu("Edit")};
        commandMenu = new JMenuItem[] {new JMenuItem("Open"), new JMenuItem("Save"),
                new JMenuItem("Cancel"), new JMenuItem("Exit")};

        commandMenu[0].addActionListener(listener);
        commandMenu[0].setActionCommand("Open");
        commandMenu[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandMenu[1].addActionListener(listener);
        commandMenu[1].setActionCommand("Save");
        commandMenu[1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandMenu[2].addActionListener(listener);
        commandMenu[2].setActionCommand("Cancel");
        commandMenu[2].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandMenu[3].addActionListener(listener);
        commandMenu[3].setActionCommand("Exit");
        commandMenu[3].setForeground(Color.red);
        commandMenu[3].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        menu[0].add(commandMenu[0]);
        menu[0].add(commandMenu[1]);
        menu[0].add(commandMenu[3]);
        menu[1].add(commandMenu[2]);

        bar.add(menu[0]);
        bar.add(menu[1]);

        setJMenuBar(bar);
    }
        
        
    void appendText(String str, boolean append){
        if (append) text.setText("");
        text.append(str);
    }
    
    String getText(){
        return text.getText();
    }
    
    public static void main(String[] args) {
        SimpleEditor simpleEditor = new SimpleEditor();
        
        simpleEditor.appendText("I am a future programmer." + "\n"+ "I am learning the java language." + "\n" + "I really want to get a credit for this assignment.", false);
        System.out.println(simpleEditor.getText());
        
    }

    private void createButtons() {
        commandButton = new JButton[] {new JButton("Open"), new JButton("Save"),
                new JButton("Cancel"), new JButton("Exit")};

        commandButton[0].addActionListener(listener);
        commandButton[0].setActionCommand("Open");
        commandButton[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandButton[1].addActionListener(listener);
        commandButton[1].setActionCommand("Save");
        commandButton[1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandButton[2].addActionListener(listener);
        commandButton[2].setActionCommand("Cancel");
        commandButton[2].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandButton[3].addActionListener(listener);
        commandButton[3].setActionCommand("Exit");
        commandButton[3].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commandButton[3].setForeground(Color.red);
    }

    
    
}
