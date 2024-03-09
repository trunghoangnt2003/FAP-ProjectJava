/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package units;

import java.security.MessageDigest;

/**
 *
 * @author trung
 */
public class SHA1 {
    public static String toSHA1(String str) {
		String salt = "q@;;sc";
		String res = null;
		str += salt;
		try {
			byte[] dataBytes = str.getBytes("UTF-8");
			MessageDigest  md  = MessageDigest.getInstance("SHA-1");
			res = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
    }
    
    public static void main(String[] args) {
        System.out.println(SHA1.toSHA1("123"));
    }
}
