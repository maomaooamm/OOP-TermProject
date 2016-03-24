package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainWindow;

public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == MainWindow.add10Button) {
            Main.gameData.add(10);
            int totalBomb = Main.gameData.getTotalBomb();
            int killedBomb = Main.gameData.getKilledBomb();
            MainWindow.messageBomb
                    .setText("Bombs: total = " + totalBomb + 
                            ", killed = " + killedBomb + 
                            ", alive:" + (totalBomb-killedBomb));
        } else if (ae.getSource() == MainWindow.addUFOButton) {
            Main.gameData.addUFO();
            int totalUFO = Main.gameData.getTotalUFO();
            int killedUFO = Main.gameData.getKilledUFO();
            MainWindow.messageUFO
                    .setText("UFO's: total = " + totalUFO + 
                            ", killed = " + killedUFO + 
                            ", alive:" + (totalUFO-killedUFO));
        } else if (ae.getSource() == MainWindow.addBirdButton) {
            Main.gameData.addBird();
            int totalBird = Main.gameData.getTotalBird();
            int killedBird = Main.gameData.getKilledBird();
            MainWindow.messageBird
                    .setText("Bird's: total = " + totalBird + 
                            ", killed = " + killedBird + 
                            ", alive:" + (totalBird-killedBird));
        } else if (ae.getSource() == MainWindow.addFlashButton) {
            Main.gameData.addFlash();
            int totalFlash = Main.gameData.getTotalFlash();
            int killedFlash = Main.gameData.getKilledFlash();
            MainWindow.messageFlash
                    .setText("Flash's: total = " + totalFlash + 
                            ", killed = " + killedFlash + 
                            ", alive:" + (totalFlash-killedFlash));
        } else if (ae.getSource() == MainWindow.quitButton) {
            if (Main.animator.running) {
                Main.animator.running = false;
            } else {
                System.exit(0);
            }
        }
    }

}
