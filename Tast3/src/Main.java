/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Shwartskopff
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, ConverterException {
       
        DataConverter converter= DataConverter.getLogger();
        converter.info("File 24");
        converter.info("23");
        
        System.out.println(converter.toBinary("Input.txt", "Output.txt", "UTF-8"));
       
        System.out.println(converter.toText("Output.txt", "OutputDouble.txt", "UTF-8"));
        
        System.out.println(converter.getSum("Input.txt"));
    }
}

