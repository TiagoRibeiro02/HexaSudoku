/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Encryptor {
    
    public String encryptString(String input) throws NoSuchAlgorithmException {
        
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger bigInt = new BigInteger(1, messageDigest);
        
        return bigInt.toString(16);
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Encryptor encryptor = new Encryptor();
        
        //chave da encryptação md5
        String password = "monkey123";
        String hashedPass = "cc25c0f861a83f5efadc6e1ba9d1269e";
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please input your password: \n");
        
        String userInput = scanner.nextLine();
        
        if(encryptor.encryptString(userInput).equals(hashedPass)){
            System.out.println("correct");
        } else{
            System.out.println("wrong");
        }
    }
}
