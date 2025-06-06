package sistemapoo;

import sistemapoo.view.LoginFrame;
import java.awt.EventQueue;
import javax.swing.UIManager;

public class SistemaPOO {

    public static void main(String[] args) {
        // Configurar look and feel do sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Iniciar aplicação
        EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}