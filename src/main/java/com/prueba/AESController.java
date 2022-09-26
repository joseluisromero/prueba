package com.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String cadena =text.replace("{","").replace("}","").replace("\n","").replace("\r","");
        return AES.decryptAES(cadena.trim());
    }

    @GetMapping("/decryptRSA")
    public String decryptRSA(String text) throws Exception {
        return RSA.decryptRSA(text);
    }
}
