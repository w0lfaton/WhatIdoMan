package com.company;


import javax.swing.*;
import java.awt.event.*;

public class Main implements Runnable {
    private static int windowWidth = 1600;
    private static int windowHeight = 1024;
    private static JFrame frame = new JFrame("Test");
    private static GameWorldController gameWorldController = new GameWorldController();
    private static Entity player = new Entity(new EntityTile(new int[]{1,2,33,34}, "BitmapSheet.bmp"), "genericPlaceHolderName", "Player");
    private static Screen worldScreen = new WorldScreen("The hub! Welcome to the madness!", windowWidth, windowHeight, player);
    private static DevScreen devScreen = new DevScreen("Development screen", windowWidth, windowHeight);
    private boolean isRunning;
    private static KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            if(String.valueOf(keyEvent.getKeyChar()).equals("w")) {
                gameWorldController.movePlayerUp(player);
            }
            if(String.valueOf(keyEvent.getKeyChar()).equals("a")) {
                gameWorldController.movePlayerLeft(player);
            }
            if(String.valueOf(keyEvent.getKeyChar()).equals("s")) {
                gameWorldController.movePlayerDown(player);
            }
            if(String.valueOf(keyEvent.getKeyChar()).equals("d")) {
                gameWorldController.movePlayerRight(player);
            }
            if(String.valueOf(keyEvent.getKeyChar()).equals("\u001B")) {
                if (gameWorldController.isUIOpen) {
                    gameWorldController.isUIOpen = false;
                    System.out.println("Settings closed");
                } else {
                    gameWorldController.isUIOpen = true;
                    System.out.println("Settings opened");
                }
            }
            if(String.valueOf(keyEvent.getKeyChar()).equals("u")) {
                gameWorldController.decrementIndicator();
            }
            if(String.valueOf(keyEvent.getKeyChar()).equals("i")) {
                gameWorldController.incrementIndicator();
            }
            if(String.valueOf(keyEvent.getKeyChar()).equals("e")) {
                gameWorldController.showNextTile();
            }
            if(String.valueOf(keyEvent.getKeyChar()).equals("q")) {
                gameWorldController.showPreviousTile();
            }
            if(String.valueOf(keyEvent.getKeyChar()).equals("r")) {
                gameWorldController.rotateTileInDevScreen();
            }
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    };
    private static MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            gameWorldController.mouseClicked(mouseEvent.getX(), mouseEvent.getY());
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    };
    private static MouseMotionListener mouseMotionListener = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            gameWorldController.setInputXLocation(mouseEvent.getX());
            gameWorldController.setInputYLocation(mouseEvent.getY());
        }
    };
    private static MouseWheelListener mouseWheelListener = new MouseWheelListener() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
            if (mouseWheelEvent.getPreciseWheelRotation() < 0) {
                gameWorldController.mouseWheelDown();
            }
            if (mouseWheelEvent.getPreciseWheelRotation() > 0) {
                gameWorldController.mouseWheelUp();
            }
        }
    };

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public static void init() {
        frame.setSize(windowWidth,windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        gameWorldController.addKeyListener(keyListener);
        gameWorldController.addMouseListener(mouseListener);
        gameWorldController.addMouseMotionListener(mouseMotionListener);
        gameWorldController.addMouseWheelListener(mouseWheelListener);
        gameWorldController.addScreen(worldScreen);
        gameWorldController.addScreen(devScreen);
        frame.add(gameWorldController);
        //frame.pack();
    }

    private void render() {
        gameWorldController.paint(gameWorldController.getGraphics());
    }



    @Override
    public void run() {
        this.isRunning = true;
        init();

        long previousTimer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double nanoPerTick = 1000000000D/60D;
        double delta = 0;
        int ticks = 0;
        int frames = 0;

        while (isRunning) {
            long currentTimer = System.currentTimeMillis();
            delta += (System.nanoTime() - lastTime) / nanoPerTick;
            boolean shouldRender = false;
            if (delta >= 1) {
                shouldRender = true;
                ticks++;
            }
            if (shouldRender) {
                render();
                frames++;
                delta = 0;
            }
            if (currentTimer - previousTimer > 1000) {
                System.out.println("First Thread: " + frames + "FPS, " + ticks + " ticks.");
                previousTimer = currentTimer;
                ticks = 0;
                frames = 0;
            }
            lastTime = System.nanoTime();
        }
    }
}
