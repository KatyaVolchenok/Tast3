/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Shwartskopff
 */
public interface IFileConverter {
    String toBinary (String inputFileName, String outputFileName, String charSet);
    String toText (String inputFileName, String outputFileName, String charSet);
    double getSum (String fileName) throws ConverterException;
}
