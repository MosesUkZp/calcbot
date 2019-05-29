package org.esurovskiy.tg;

import java.io.*;

public class Main {

    private static final int SKIP = 1000;
    private static final String TEXT = "ABCDEerr3232f32f32f32f32f32f32f32f32F";

    public static void main(String[] args) throws IOException {


        try (FileInputStream in = new FileInputStream(
                "C:\\Users\\user\\Desktop\\3.bmp");
             FileOutputStream on = new FileOutputStream(
                     "C:\\Users\\user\\Desktop\\mutate_photo.bmp");
        ){

            int b = 0;
            int counter = 0;
            int lenght = TEXT.length();
            byte[] bytes = TEXT.getBytes();
            int textCounter = 0;
            while ((b = in.read()) != -1) {
                if (counter < SKIP) {
                    on.write(b);
                }
                else if (counter == SKIP){
                    on.write(lenght);
                }
                else if (counter > SKIP && counter < SKIP + lenght + 1){
                   on.write(bytes[textCounter]);
                   textCounter++;
                } else {
                    on.write(b);
                }
                counter++;
            }
        }
    }
}