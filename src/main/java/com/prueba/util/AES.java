package com.prueba.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;
import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;

@Component
public class AES {
    // Definición del tipo de algoritmo a utilizar (AES, DES, RSA)
    private final static String alg = "AES";
    // Definición del modo de cifrado a utilizar
    private final static String cI = "AES/CBC/PKCS5Padding";

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


    public static String encryptAES(String text) throws Exception {
        //String key = "92AE31A79FEEB2A3"; //llave
        //String iv = "0123456789ABCDEF"; // vector de inicialización*/
        //PARA APIGEE
        String key = "oPDomhSy6nycDWS1AO5zhrcRSlf2WSEV"; //llave
        String iv = "cGmtg3KWsIgeldgz"; // vector de inicialización*/
        return encrypt(key, iv, text);
    }

    public static String decryptAES(String text) throws Exception {
        String key = "92AE31A79FEEB2A3"; //llave
        String iv = "0123456789ABCDEF"; // vector de inicialización
        String message=text;
        //String message="qVfJJNdxKA5GGxSzBFuUhA0FMlUlKKXJu2NAHmNqQ1CbEIhTIT5rrbg8zn5mdrkvrfGDC8Nj/420J5GN17UHUQy0b2luTtrK4m3ZZ+RjxGxMRKXD5CxYGq3EIQkoNWt8PPJyzXJ0GJjV6prezTsKLZwg2atToxSL4WYxCXB32lhlo19jFuQHYq7D7z3PXh/DAg8n/xAxVaHxSrv0XirPiinPwsP7uI8E4w73mq118ZgkTCpe5pUzMyDpnIp1z3V1mo6XlYUyERwKENS1brXdmj+gYkZJOC2HvsTzIvL5d/mZtL4bvP/7HpP+40i41hrc1APceIGU+i2bpJo7MtzqA==";
        System.out.println("cadena  ->:"+message);
        return decrypt(key, iv, message);
        //return decrypt(key, iv, messsage);
    }

    private static String encrypt(String key, String iv, String cleartext) throws Exception {
        Cipher cipher = Cipher.getInstance(cI);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(cleartext.getBytes());
        return new String(encodeBase64(encrypted));
    }

    /**
     * Función de tipo String que recibe una llave (key), un vector de inicialización (iv)
     * y el texto que se desea descifrar
     *
     * @param key       la llave en tipo String a utilizar
     * @param iv        el vector de inicialización a utilizar
     * @param encrypted el texto cifrado en modo String
     * @return el texto desencriptado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException
     */
    private static String decrypt(String key, String iv, String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(cI);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        byte[] enc = decodeBase64(encrypted);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(enc);
        return new String(decrypted);
    }
}
