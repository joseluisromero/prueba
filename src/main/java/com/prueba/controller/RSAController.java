package com.prueba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/RSA")
@CrossOrigin
public class RSAController {

    private static final String RSA = "RSA";

    private static final String ENCRYPTION_OPERATION_MODE_AND_PADDING_SCHEME = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

//clave publica de Genesys
    private String publicEncryptionKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs43vWiBLgo34ww1Iu8CURDhGGo4qfASp5chApxo3AqUQ8ZXoxGD6+aX5346eDf2Fsg449ZrxXYctcRLCSQJPhYGUeXsr5QVR6xn6ZRokhAgtQVv/7ZSzS+QeWIx+s7hLItRYUlj2x1WkhIF8raYebVJ+yQZirldVNNEDDpP8M8jcroLgufRUa4gq2RnafzhcE6UjMHN06bbUo+GwhotMTLNoSHg8A9P/X3GCmAhoVEzG8VX7fi6Vwar9nHobjVe9gF1yC9NRWhmPkVxsWZA09gp1aG+3kI97nQ0ocx2s3RxnedsvALYgJjheKMTx05ZjueLOihZKd+8BEUQBmfM78wIDAQAB";

    private String privateEncryptionKey;

    @PostMapping("/encrypt")
    public Map<String, String> payloadEncript(@RequestBody  Map<String, String> payload) {
        Map<String, String> payloadResponse = new HashMap<>();
        payload.forEach((s, s2) -> {
            payloadResponse.put(s, encrypt(s2));
        });

        return payloadResponse;
    }


    public String encrypt(String text) {
        byte[] messageToBytes = text.getBytes();
        try {
            PublicKey publicKey = getPublicKey();
            Cipher cipher = Cipher.getInstance(ENCRYPTION_OPERATION_MODE_AND_PADDING_SCHEME);
            OAEPParameterSpec oaepParams = new OAEPParameterSpec(
                    "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParams);
            byte[] encryptedBytes = cipher.doFinal(messageToBytes);
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            log.error("Error al encriptar información {}", e);
        }
        return null;
    }


    public String decrypt(String text) {
        byte[] encryptedBytes = Base64.getDecoder().decode(text);
        try {
            PrivateKey privateKey = getPrivateKey();
            Cipher cipher = Cipher.getInstance(ENCRYPTION_OPERATION_MODE_AND_PADDING_SCHEME);
            OAEPParameterSpec oaepParams = new OAEPParameterSpec(
                    "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
            cipher.init(Cipher.DECRYPT_MODE, privateKey, oaepParams);
            byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
            return new String(decryptedMessage, StandardCharsets.UTF_8);
        } catch (Exception e) {

            log.error("Error al desencriptar información {}", e);
        }
        return null;
    }

    private PublicKey getPublicKey() {
        try {
            byte[] derKey = Base64.getDecoder().decode(publicEncryptionKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(derKey);
            return KeyFactory.getInstance(RSA).generatePublic(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            log.error("Llave pública inválida {}", e);
        }
        return null;
    }

    private PrivateKey getPrivateKey() {
        try {
            byte[] derKey = Base64.getDecoder().decode(privateEncryptionKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(derKey);
            return KeyFactory.getInstance(RSA).generatePrivate(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            log.error("Llave privada inválida {}", e);
        }
        return null;
    }

}
