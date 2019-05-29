package org.esurovskiy.tg;

import java.io.FileInputStream;
import java.io.IOException;

public class Main1 {
    private static final int SKIP = 1000;

    public static void main(String[] args) throws IOException {


        try (FileInputStream in = new FileInputStream(
                "C:\\Users\\user\\Desktop\\mutate_photo.bmp");
        ) {

            byte[] bytes = new byte[SKIP];
            in.read(bytes);
            int lenght = in.read();
            byte[] textArray = new byte[lenght];
            in.read(textArray);
            System.out.println(new String(textArray));

        }
    }
}