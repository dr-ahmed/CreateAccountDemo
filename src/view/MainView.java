package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainView {

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e1) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException e2) {
                JOptionPane.showMessageDialog(null, "Erreur, impossible de charger le Look And Feel du système",
                        "Chargement échoué", JOptionPane.ERROR_MESSAGE, null);
            }
        }
    }

    private static void launchNewClinicFrame() {
        NewClinicFrame newClinicFrame = new NewClinicFrame();
        newClinicFrame.setTitle("Formulaire de création de clinique");
        newClinicFrame.setSize(400, 300);
        newClinicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newClinicFrame.setLocationRelativeTo(null);
        newClinicFrame.setVisible(true);
    }

    public static void main(String[] args) {
        setLookAndFeel();
        launchNewClinicFrame();
    }
}
