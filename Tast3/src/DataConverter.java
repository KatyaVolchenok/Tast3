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

import java.util.*;







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
        try (BufferedReader input = new BufferedReader(new FileReader(inputFileName));
             FileWriter output = new FileWriter(outputFileName)){
            
            ArrayList<String> buffer = new ArrayList<>();
            while (input.ready())
                buffer.add(input.readLine());
            
            for (String s : buffer) {
                String[] line = s.split("");
                for (int i = 0; i < line.length; i++) {
                    byte[] bytes = line[i].getBytes(charSet);
                    String words = "";
                        for (int j = 0; j < bytes.length; j++){
                            words += String.format("%8s", Integer.toBinaryString(bytes[j])).replace(' ', '0');
                        output.write(words + (i == line.length - 1 ? "\n": " "));
                        }     
                    }                    
                }
            } catch (FileNotFoundException ex) {
            DataConverter.getLogger().info("File not found");
        } catch (IOException ex) {
            DataConverter.getLogger().info("Error");
        }
        return outputFileName;
    }

    @Override
    public String toText(String inputFileName, String outputFileName, String charSet) {
       try (BufferedReader input = new BufferedReader(new FileReader(inputFileName));
             FileWriter output = new FileWriter(outputFileName)){
           
            ArrayList<String> buffer = new ArrayList<>();
            while (input.ready())
                buffer.add(input.readLine());
            
            for (String s : buffer) {
                String[] line = s.split(String.format("%8s", Integer.toBinaryString(' ')).replace(" ", "0"));
                ArrayList<Byte> lineByte = new ArrayList<>();
                for (int i = 0; i < lineByte.size(); i++) {
                   byte[] textByte = new byte[lineByte.size()];
                   textByte[i] = Byte.parseByte(line[i], 2);
                   output.write(new String(textByte, charSet) + (i == line.length - 1 ? "\n" : " "));
                }
            }      
        } catch (FileNotFoundException ex) {
            DataConverter.getLogger().info("File not found");
        } catch (IOException ex) {
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
    
    

    

