/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block_chain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author JOSED
 */
public class Cryptography {
    
    private static Cryptography cryptography;
    
    private Cryptography(){}
    
    public static Cryptography getInstance(){
        if(cryptography == null){
            cryptography = new Cryptography();
        }
        return cryptography;
    }
    
    public String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(UnsupportedEncodingException | NoSuchAlgorithmException ex){
           throw new RuntimeException(ex);
        }
    }
    
    public String toHexadecimal(String txt){
        char ch[] = txt.toCharArray();
        String sb = "";
        
        for (int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            sb += hexString;
        }
        
        return sb;
    }
    
    public byte[] cifrar(String sinCifrar) throws Exception {
	final byte[] bytes = sinCifrar.getBytes("UTF-8");
	final Cipher aes = getChiper(true);
	final byte[] cifrado = aes.doFinal(bytes);
	return cifrado;
    }

    public String descifrar(byte[] cifrado) throws Exception {
            final Cipher aes = getChiper(false);
            final byte[] bytes = aes.doFinal(cifrado);
            final String sinCifrar = new String(bytes, "UTF-8");
            return sinCifrar;
    }

    private Cipher getChiper(boolean paraCifrar) throws Exception {
            final String frase = "YaSalioEstructuraDeDatosCon100!-ListoParaGanar!!!123...";
            final MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(frase.getBytes("UTF-8"));
            final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

            final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            if (paraCifrar) {
                    aes.init(Cipher.ENCRYPT_MODE, key);
            } else {
                    aes.init(Cipher.DECRYPT_MODE, key);
            }

            return aes;
    }
}
