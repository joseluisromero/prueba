package com.prueba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.service.dto.DataEncryptApigeeRequest;
import com.prueba.service.dto.DataEncryptApigeeResponse;
import com.prueba.service.dto.DataRequest;
import com.prueba.service.dto.EncryptApigeeRequest;
import com.prueba.util.AES;
import com.prueba.util.RSA;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.util.Base64.getEncoder;
import static org.apache.commons.codec.binary.Base64.*;

@RestController
@CrossOrigin
public class AESController {


    @GetMapping("/encrypt")
    public String encryptAES(String text) throws Exception {
        return AES.encryptAES(text);
    }


    @GetMapping("/decrypt")
    public String decryptAES(String text) throws Exception {
        return AES.decryptAES(text);
    }

    @PostMapping("/decryptBody")
    public String decryptBodyAES(@RequestBody String text) throws Exception {
        String cadena = text.replace("{", "").replace("}", "").replace("\n", "").replace("\r", "");
        return AES.decryptAES(cadena.trim());
    }

    @GetMapping("/decryptRSA")
    public String decryptRSA(String text) throws Exception {
        return RSA.decryptRSA(text);
    }

    @PostMapping("/encrypt-aes-cmb-callback")
    public ResponseEntity<DataEncryptApigeeResponse> encryptAESCMBApigee(@RequestBody DataEncryptApigeeRequest dataEncryptApigeeRequest) throws Exception {
        String key = "oPDomhSy6nycDWS1AO5zhrcRSlf2WSEV";
        String iv = "cGmtg3KWsIgeldgz";
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = "";
        String jsonFirma = "";
        try {
            jsonStr = Obj.writeValueAsString(dataEncryptApigeeRequest);
            jsonFirma = Obj.writeValueAsString(dataEncryptApigeeRequest.getData());
            System.out.println(jsonStr);
            System.out.println(jsonFirma);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataEncryptApigeeResponse dataEncryptApigeeResponse = DataEncryptApigeeResponse.builder()
                .bodyEncrypt(aesEncrypt(jsonStr, key, iv, "otro"))
                .firme(firmeDigital(jsonFirma, true, null))
                .request(jsonStr).build();
        return new ResponseEntity<>(dataEncryptApigeeResponse, HttpStatus.OK);
    }


    public static String aesEncrypt(String data, String key, String iv, String format)
            throws InvalidParameterException, Exception {
        try {
            byte[] bytesOutput = null;
            Cipher cases = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec myKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8),
                    "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            if (format.equals("hex")) {
                bytesOutput = byteAESEncrypt(data, cases, myKey, ivspec);
                return DatatypeConverter.printHexBinary(bytesOutput);
            } else {
                bytesOutput = byteAESEncrypt(data, cases, myKey, ivspec);
                return encodeBase64String(bytesOutput);
            }
        } catch (Exception e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    private static byte[] byteAESEncrypt(String info, Cipher cases, SecretKeySpec myKey,
                                         IvParameterSpec ivspec) throws Exception {
        cases.init(1, myKey, ivspec);
        return cases.doFinal(info.getBytes());
    }

    private static String firmeDigital(String textToHash, Boolean isPost, String URL) throws NoSuchAlgorithmException {
        //String textToHash = "Texto para generar Hash";
        String firme = "";

        if (isPost) {
            //{APP_ID} + "|" + {GUID} + "|" + {REQUEST_PAYLOAD} + "|" + {API_SECRET}
            firme = "e2446259-4dfe-47f6-ba7b-38460ff8f243" + "|" + "491c9ffaf47743519ee33dfb975a7676" + "|" + textToHash + "|" + "cGmtg3KWsIgeldgz";
        } else {
            //{APP_ID} + "|" + {GUID} + "|" + {URI} + "|" + {API_SECRET}

            firme = "e2446259-4dfe-47f6-ba7b-38460ff8f243" + "|" + "491c9ffaf47743519ee33dfb975a7676" + "|" + URL + "|" + "cGmtg3KWsIgeldgz";
        }

        byte[] data = firme.getBytes(Charset.forName("UTF-8"));
        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        digester.update(data);
        String base64Encoded = getEncoder().encodeToString(digester.digest());
        return base64Encoded;
    }

    @PostMapping("/encrypt-aes-cmb-call-information")
    public ResponseEntity<DataEncryptApigeeResponse> getEncryptAESCMBApigee(@RequestBody EncryptApigeeRequest encryptApigeeRequest) throws Exception {
        String key = "oPDomhSy6nycDWS1AO5zhrcRSlf2WSEV";
        String iv = "cGmtg3KWsIgeldgz";
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = "";
        String jsonFirma = "";
        String oppEncrypt = aesEncrypt(encryptApigeeRequest.getOpportunityId(), key, iv, "otro");
        String ascii = encode(oppEncrypt);
        DataEncryptApigeeResponse dataEncryptApigeeResponse = DataEncryptApigeeResponse.builder()
                .bodyEncrypt(ascii)
                .firme(firmeDigital(jsonFirma, false, (encryptApigeeRequest.getRuta() + ascii)))
                .request(encryptApigeeRequest.getOpportunityId()).build();
        return new ResponseEntity<>(dataEncryptApigeeResponse, HttpStatus.OK);
    }

    public static String encode(String url) {
        try {
            String encodeURL = URLEncoder.encode(url, "UTF-8");
            return encodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Error de " + e.getMessage();
        }
    }

    @PostMapping("/encrypt-aes-cmb-updateContacto")
    public ResponseEntity<DataEncryptApigeeResponse> encryptAESCMBUpdateContactApigee(@RequestBody EncryptApigeeRequest encryptApigeeRequest) throws Exception {
        String key = "oPDomhSy6nycDWS1AO5zhrcRSlf2WSEV";
        String iv = "cGmtg3KWsIgeldgz";
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = "";
        String jsonFirma = "";
        try {
            DataEncryptApigeeRequest dataEncryptApigeeRequest = DataEncryptApigeeRequest.builder()
                    .data(DataRequest.builder().cellPhone(encryptApigeeRequest.getCellPhone()).build()).build();
            jsonStr = Obj.writeValueAsString(dataEncryptApigeeRequest);

            jsonFirma = Obj.writeValueAsString(dataEncryptApigeeRequest.getData());
            System.out.println(jsonStr);
            System.out.println(jsonFirma);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String oppEncrypt = aesEncrypt(encryptApigeeRequest.getOpportunityId(), key, iv, "otro");
        String ascii = encode(oppEncrypt);
        DataEncryptApigeeResponse dataEncryptApigeeResponse = DataEncryptApigeeResponse.builder()
                .bodyEncrypt(aesEncrypt(jsonStr, key, iv, "otro"))
                .firme(firmeDigital(jsonFirma, true, null))
                .request(jsonStr)
                .ascii(ascii).build();
        return new ResponseEntity<>(dataEncryptApigeeResponse, HttpStatus.OK);
    }

}
