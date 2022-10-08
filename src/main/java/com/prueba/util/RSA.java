package com.prueba.util;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;

@Component
public class RSA {
    // Definición del tipo de algoritmo a utilizar (AES, DES, RSA)
    private final static String alg = "AES";
    // Definición del modo de cifrado a utilizar
    private final static String cI = "AES/CBC/PKCS5Padding";

    private final static String KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8qELJdC4K42grUbae3up/aaflbqTUSywjTujgjgQ5cgUoX0C9lS4u+bUL+YzUAchOni9kBiQHl1RYK9FEiKCqRMcCkWd/9OW+Wrbj4AkQJ3iI+Q9OmbLpMdcf+XTahUbL9BuygGn6OG5EM4Jemkpa1UBV0/KFA7LEI2xF6W4e2/AzX+QJZ8BbLX/dHh1NC+rLMDjQBW+5MmPDllY6UvRwXR/r+emLBze7Mk2Bm+3SToprL4HjBLcTktgPlI1DqwDwBmNpU28NFLVGvuqLf4Ijvzz+ZPbtjFVySpHa/VrZSMPHX+n9reoc2NyOgmb3AMQphtza3wn3tYqg5uKURWByQIDAQAB";

    /*public static void main(String[] args) throws Exception {
        String key = "92AE31A79FEEB2A3"; //llave
        String iv = "0123456789ABCDEF"; // vector de inicialización
        //String cleartext = "TramAdditional(registerType=2, product=0110, cardType=04, plasticType=R0, cycle=  , identification=1311901001, identificationType=1, petiti plasticType=R0, cycle=  , identification=1311901001 petiti plasticType=R0, cycle=  , identification=1311901001";
        String cleartext = "TramAdditional(registerType=2, product=0110, cardType=04, plasticType=R0, cycle=  , identification=1311901001, identificationType=1, petiti plasticType=R0, cycle=  , identification=1311901001 petiti plasticType=R0, cycle=  , identification=1311901001";
        System.out.println("Texto encriptado: " + encrypt(key, iv, cleartext));
        //System.out.println("Texto desencriptado: " + decrypt(key, iv, encrypt(key, iv, cleartext)));
        System.out.println("Texto desencriptado hash2: " + decrypt(key, iv, "Yom7jJ5KObjMuBE0LsVskA=="));
        System.out.println("Texto desencriptado hash2: " + decrypt(key, iv, "3leHo3D9TuxPfcW52r1Psw=="));
        System.out.println("Texto desencriptado hash 5: " + decrypt(key, iv, "2L3t4OW+8xqkmLtyzmgaog=="));
    }*/


    public static String decryptRSA(String text) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        try {
            String privateKeyPEM = KEY
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replaceAll("\n", "")
                    .replace("-----END PRIVATE KEY-----", "");
            Integer mode = 2;
            Cipher cifradorRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA/ECB/PKCS1Padding");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            cifradorRSA.init(mode, key);
            byte[] bytesClaveBlowfish2 = cifradorRSA.doFinal(Base64.getDecoder().decode(text.getBytes(StandardCharsets.UTF_8)));
            return new String(encodeBase64(bytesClaveBlowfish2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
