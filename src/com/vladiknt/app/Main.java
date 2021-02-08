package com.vladiknt.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
        Color curColor;

        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.setRGB(i, j, new Color(0xFFFFFF).getRGB());
            }
        }

        int ch;
        while (true) {
            try {
                System.out.println("1 - Get black-white picture");
                System.out.println("2 - Get colored picture");
                System.out.print("\nEnter your choice: ");
                ch = Integer.parseInt(sc.nextLine());
                if (ch == 1 || ch == 2)
                    break;
                else
                    System.out.println("Enter only 1 or 2!\n");
            } catch (Exception e) {
                System.out.println("Enter only 1 or 2!\n");
            }
        }

        if (ch == 1) {
            // Закрашиваем половину верхнего левого квадрата (черно-белый режим)
            Color black = new Color(0);
            for (int i = 0; i <= image.getWidth() / 2; i++) {
                for (int j = 0; j <= i; j++) {
                    if (Math.random() < Math.random()) {
                        image.setRGB(i, j, black.getRGB());
                    }
                }
            }
        } else {
            // Закрашиваем половину верхнего левого квадрата (цветной режим)
            for (int i = 0; i <= image.getWidth() / 2; i++) {
                for (int j = 0; j <= i; j++) {
                    image.setRGB(i, j, new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)).getRGB());
                }
            }
        }

        // Отображаем относительно главной диагонали, чтобы полностью закрыть первый квадрат
        for (int i = 0; i < image.getWidth()/2; i++) {
            for (int j = 0; j < i; j++) {
                curColor = new Color(image.getRGB(i, j));
                image.setRGB(j, i, curColor.getRGB());
            }
        }
        // Отображаем рисунок симетрично относительно оси ОУ
        for (int i = 0; i < image.getWidth()/2; i++) {
            for (int j = 0; j < image.getHeight() / 2; j++) {
                curColor = new Color(image.getRGB(i, j));
                image.setRGB(image.getWidth() - i - 1, j, curColor.getRGB());
            }
        }
        // Отображаем рисунок симетрично относительно оси ОХ
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight() / 2; j++) {
                curColor = new Color(image.getRGB(i, j));
                image.setRGB(i, image.getHeight() - j - 1, curColor.getRGB());
            }
        }

        // Увеличиваем рисунок с 100х100 до 1000х1000
        BufferedImage current = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                curColor = new Color(image.getRGB(i, j));
                for (int i1 = i * 10; i1 < (i + 1) * 10; i1++) {
                    for (int j1 = j * 10; j1 < (j + 1) * 10; j1++) {
                        current.setRGB(i1, j1, curColor.getRGB());
                    }
                }
            }
        }

        // Блюрим фотку
        System.out.println("Wait please.");
        BufferedImage result = current.getSubimage(0, 0, 1000, 1000);
        Color currentColor;
        Color col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12;
        // Прогоняем 50 раз операцию, чтобы всё сгладилось
        for (int loops = 0; loops < 50; loops++) {
            // Blur
            for (int i = 2; i < current.getWidth() - 2; i++) {
                for (int j = 2; j < current.getHeight() - 2; j++) {
                    col1 = new Color(current.getRGB(i - 1, j));
                    col2 = new Color(current.getRGB(i, j - 1));
                    col3 = new Color(current.getRGB(i + 1, j));
                    col4 = new Color(current.getRGB(i, j + 1));

                    col5 = new Color(current.getRGB(i + 1, j + 1));
                    col6 = new Color(current.getRGB(i - 1, j + 1));
                    col7 = new Color(current.getRGB(i + 1, j - 1));
                    col8 = new Color(current.getRGB(i - 1, j - 1));

                    col9 = new Color(current.getRGB(i, j + 2));
                    col10 = new Color(current.getRGB(i + 2, j));
                    col11 = new Color(current.getRGB(i, j - 2));
                    col12 = new Color(current.getRGB(i - 2, j));
                    currentColor = new Color(
                            (col1.getRed() + col2.getRed() + col3.getRed() + col4.getRed() + col5.getRed() + col6.getRed() + col7.getRed() + col8.getRed() + col9.getRed() + col10.getRed() + col11.getRed() + col12.getRed())/12,
                            (col1.getGreen() + col2.getGreen() + col3.getGreen() + col4.getGreen() + col5.getGreen() + col6.getGreen() + col7.getGreen() + col8.getGreen() + col9.getGreen() + col10.getGreen() + col11.getGreen() + col12.getGreen())/12,
                            (col1.getBlue() + col2.getBlue() + col3.getBlue() + col4.getBlue() + col5.getBlue() + col6.getBlue() + col7.getBlue() + col8.getBlue() + col9.getBlue() + col10.getBlue() + col11.getBlue() + col12.getBlue())/12
                    );
                    result.setRGB(i, j, currentColor.getRGB());
                }
            }
            current = result.getSubimage(0, 0, 1000, 1000);
        }

        // Рисуем новую картинку по очертаниям
        if (ch == 1) {
            for (int i = 2; i < current.getWidth(); i++) {
                for (int j = 2; j < current.getHeight(); j++) {
                    currentColor = new Color(result.getRGB(i, j));
                    if (currentColor.getRed() > 96)
                        currentColor = new Color(0xFFFFFF);
                    else
                        currentColor = new Color(0);
                    result.setRGB(i, j, currentColor.getRGB());
                }
            }
        } else {
            int currentRed, currentGreen, currentBlue;
            for (int i = 2; i < current.getWidth(); i++) {
                for (int j = 2; j < current.getHeight(); j++) {
                    currentColor = new Color(result.getRGB(i, j));
                    currentRed = currentColor.getRed();
                    currentGreen = currentColor.getGreen();
                    currentBlue = currentColor.getBlue();
                    if (currentRed >= currentGreen && currentRed >= currentBlue)
                        currentColor = new Color(0xFF0000);
                    else if (currentGreen >= currentRed && currentGreen >= currentBlue)
                        currentColor = new Color(0x00FF00);
                    else
                        currentColor = new Color(0x0000FF);
                    result.setRGB(i, j, currentColor.getRGB());
                }
            }
        }

        String str;
        System.out.print("Enter path for saving picture: ");
        while (true) {
            try {
                str = sc.nextLine();
                str += "\\out.png";
                File output = new File(str);
                ImageIO.write(result, "png", output);
                break;
            } catch (Exception e) {
                System.out.println("Oops! This is error while saving.");
                System.out.println("Maybe your path isn`t correct.");
                System.out.print("Enter again: ");
            }
        }
    }

    private static void greeting() {
        try {
            System.out.println(" _____    _           ______                   ");
            Thread.sleep(500);
            System.out.println("|  __ \\  |_|         /  __  \\                  ");
            Thread.sleep(500);
            System.out.println("| |__| |  _    ____ |  /  \\__|   ____   _  ___ ");
            Thread.sleep(500);
            System.out.println("|  ___/  | |  / __/ | |  ____   / __ \\ | |/_  \\");
            Thread.sleep(500);
            System.out.println("| |      | | | |__  |  \\_\\   | |  ___/ |  / \\ |");
            Thread.sleep(500);
            System.out.println("|_|      |_|  \\___\\  \\______/   \\____  |__| |_|\n");
            Thread.sleep(500);
            System.out.println("     https://github.com/DevVladikNT/PicGen\n");
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Error while greeting.");
        }
    }

}
