/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Shwartskopff
 */

import java.io.*;
import java.nio.charset.Charset;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;







public class DataConverter implements IFileConverter {

    static DataConverter converter;
    File file;
    
    private DataConverter(){
        file = new File ("Input.txt");
        if(!file.exists()) try {
            file.createNewFile();
        } catch (IOException ex) {}
    }
    
    
    public static DataConverter getLogger(){
        if (converter==null) converter=new DataConverter();
        return converter;
    }
    
    public void info (String message){
        try (FileWriter writer = new FileWriter(file, true);) {
            writer.write("\n"  + message);
        } catch (Exception ex) {}
    }

    @Override
    public String toBinary(String inputFileName, String outputFileName, String charSet) {
           
            String line = "";
            char[] buffer = new char[1024];
        
            try(FileReader reader = new FileReader(inputFileName)){
                 int words;
                   while((words=reader.read(buffer))!=-1){
                     if (words < 1024) {
                      buffer = Arrays.copyOf(buffer, words);
                     }
                        line += new String(buffer);
                }
                 }catch(IOException io){
                    DataConverter.getLogger().info("Error");
                }
        
             String bynares = " ";
             Charset charset = Charset.forName(charSet);
             byte[] bytes = line.getBytes(charset);
             String[] binary = new String[bytes.length];
                for (int i = 0; i < bytes.length; i++) {
                    binary[i]=String.format("%8s", Integer.toBinaryString(bytes[i])).replace(" ", "0");
                    bynares = bynares + " " + binary[i];
                }
        
            file = new File(outputFileName);
                if(!file.exists()) 
                    try {
                     file.createNewFile();
                    } catch (IOException io) {
                        DataConverter.getLogger().info("Error");
                        }
                
                
              try(FileWriter writer = new FileWriter(outputFileName, false)){
                      writer.write(bynares);
                 }catch(IOException io){
                     DataConverter.getLogger().info("Error");
                 
        }
        return outputFileName;
    }

    @Override
    public String toText(String inputFileName, String outputFileName, String charSet) {
            String line = "";
            char[] buffer = new char[1024];
        
                try(FileReader reader = new FileReader(inputFileName)){
                int bynares;
                   while((bynares = reader.read(buffer))!=-1){
                       if (bynares<1024) {
                           buffer = Arrays.copyOf(buffer, bynares);
                     }
                        line += new String(buffer);
                }   
             }catch(IOException io){
                   DataConverter.getLogger().info("Error");
                }
        
            line=line.trim();
            String [] sS = line.split(" ");
            byte [] textBytes = new byte[sS.length];
               for (int i = 0; i < textBytes.length; i++) {
                     textBytes[i]=Byte.parseByte(sS[i], 2);  
                    }
        
            Charset charset = Charset.forName(charSet);
            String result = new String(textBytes, charset);
        
            file = new File(outputFileName);
                 if(!file.exists()) 
                     try {
                      file.createNewFile();
                } catch (IOException io) {
                   DataConverter.getLogger().info("Error");
                    }
            
                try(FileWriter writer = new FileWriter(outputFileName, false)){
                         writer.write(result);
                    }catch(IOException io){
                        DataConverter.getLogger().info("Error");
                    }
                
       return outputFileName;   
    }
    
    
    @Override
    public double getSum(String fileName) throws ConverterException {
       if (fileName == null) throw new ConverterException("File Name is null");
        double sum = 0;

        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            if (!input.ready()) throw new ConverterException("File is empty");

            ArrayList<String> buffer = new ArrayList<>();
            while (input.ready())
                buffer.add(input.readLine());

            for (String s : buffer) {
                String[] line = s.split(" ");

                for (int i = 0; i < line.length; i++) {
                    if (line[i].matches("\\d+")) {
                        sum += Double.parseDouble(line[i]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            DataConverter.getLogger().info("File not found");
        } catch (IOException e) {
            DataConverter.getLogger().info("Error");
        }

        return sum;
    }
}
    
    

    

