/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsak4;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;


/**
 *
 * @author Shwartskopff
 */
class SimpleEditorListener extends WindowAdapter implements ActionListener, AutoCloseable{

    private SimpleEditor editor;
    private File file;
    private FileReader reader;
    private FileWriter writer;
    
    public SimpleEditorListener(SimpleEditor editor){
        this.editor = editor;
    }
    
    private void createNewFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select file to save");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = fileChooser.showSaveDialog(editor);
        if (res == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
    }

    private void cancelOperation() {
        if (file != null) saveOperation();
     }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case "Open":{
                openOperation();
                break;
            }
               case "Save":{
                saveOperation(); 
                break;
            }
                   case "Cansel":{
                    canselOperation(); 
                    break;
                   }
                       case "Exit":{
                        exitOperation();
                        break;
                       }
            }
    }
    
    private void openOperation() {
        if (file == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a file");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = fileChooser.showOpenDialog(editor);
            
            if (res == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                
                try (FileReader reader = new FileReader(file)) {
                    char[] buffer = new char[1024];
                    reader.read(buffer);
                    }catch (IOException e) {
                    JOptionPane.showMessageDialog(editor, "File read error");
                }  
            } editor.setJLabelText("INFO: creation new file. New File Name:" + file.getName());
        }
    }

    private void saveOperation() {
       if (file == null) {
            createNewFile();
            editor.setJLabelText("INFO: creation new file. New File Name: " + file.getName());
        }
        try (FileWriter writer = new FileWriter(file, false)) {
            String resultText = editor.getText();
            writer.write(resultText);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(editor, "File write error");
        }
    }

    private void canselOperation() {
        file = null;
        editor.setJLabelText("INFO: creation new file. New File Name: ");
        editor.appendText("", true);
    }

    private void exitOperation() {
        if (file != null) {
            Object[] operation = {"Save", "Cancel"};
            int userOperation = JOptionPane.showOptionDialog(editor, "Save changes or not?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, operation, null);
            if (userOperation == 0) saveOperation();
            if (userOperation == 1) cancelOperation();
        }
        editor.dispose();
        
    }

    @Override
    public void close() {
        if (file != null) saveOperation();
    }    
    
}

    
    

