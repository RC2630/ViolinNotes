package io;

public class Main {

    public static void main(String[] args) {
        try {
            new Program().run();
        } catch (Throwable t) {
            System.out.print("\nAn error occurred!\n");
        }
    }

}