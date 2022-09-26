package com.prueba;

public class Enmascarar {
    public static void main(String[] args) {
        String cellPhone = "09877";
        String unMaskaNumber = maskCellNumber(cellPhone);
    }

    private static String maskCellNumber(String msg) {
        if (msg.isEmpty()) {
            return msg;
        }
        msg = msg.trim().replaceAll(" {2,}", " ");
        StringBuilder str = new StringBuilder(msg.length());
        str.append(msg.charAt(0));
        str.append(msg.charAt(1));
        for (int i = 2; i < msg.length(); ++i) {
            str.append((i < msg.length() - 2) ? 'X' : msg.charAt(i));
        }
        return str.toString().toUpperCase();
    }

}

