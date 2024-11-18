package yape;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("unused")
public class Yape extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int maxDigitos = 4;
    private static final String PASSWORD = "1234";
    private JLabel lblPassword;
    private StringBuilder password;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Yape ventana = new Yape();
                    ventana.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Yape() {
        password = new StringBuilder();

        setTitle("Yape");
        ImageIcon icono = new ImageIcon(getClass().getResource("Yape.png"));
        setIconImage(icono.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0, 0, 500, 800);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(128, 0, 128));

        ImageIcon iconoQR = new ImageIcon(getClass().getResource("QR.jpg"));
        Image imagenQR = iconoQR.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon QR = new ImageIcon(imagenQR);

        ImageIcon iconoAyuda = new ImageIcon(getClass().getResource("Ayuda.png"));
        Image imagenAyuda = iconoAyuda.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon ayuda = new ImageIcon(imagenAyuda);

        PanelRedondo pnlPrincipal = new PanelRedondo(30);
        pnlPrincipal.setBounds(0, 350, 485, 415);
        pnlPrincipal.setBackground(new Color(254, 254, 254));
        pnlPrincipal.setLayout(null);
        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(pnlPrincipal);

        JLabel lblContra = new JLabel("Ingresa tu contraseña");
        lblContra.setBounds(30, 10, 425, 30);
        lblContra.setHorizontalAlignment(SwingConstants.CENTER);
        lblContra.setFont(new Font("Arial", Font.BOLD, 18));
        pnlPrincipal.add(lblContra);

        PanelRedondo pnlQR = new PanelRedondo(20);
        pnlQR.setIcon(QR);
        pnlQR.setBounds(160, 80, 160, 160);
        getContentPane().add(pnlQR);

        lblPassword = new JLabel("o o o o");
        lblPassword.setBounds(30, 30, 425, 30);
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 24));
        pnlPrincipal.add(lblPassword);

        JPanel pnlBotones = new JPanel();
        pnlBotones.setBounds(30, 70, 425, 290);
        pnlBotones.setBackground(Color.WHITE);
        pnlBotones.setLayout(new GridLayout(4, 3, 10, 10));
        pnlBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlPrincipal.add(pnlBotones);

        BotonRedondo btnAyuda = new BotonRedondo("Ayuda", 30);
        btnAyuda.setText("Ayuda");
        btnAyuda.setIcon(ayuda);
        btnAyuda.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnAyuda.setIconTextGap(10);
        btnAyuda.setBounds(350, 30, 115, 30);
        btnAyuda.setBackground(new Color(159, 102, 169));
        btnAyuda.setFont(new Font("Arial", Font.BOLD, 13));
        btnAyuda.setForeground(Color.WHITE);
        btnAyuda.setBorder(null);
        getContentPane().add(btnAyuda);

        List<String> numeros = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numeros.add(String.valueOf(i));
        }
        Collections.shuffle(numeros);

        for (String numero : numeros) {
            JButton botonNumero = crearBotonNumerico(numero);
            pnlBotones.add(botonNumero);
        }

        ImageIcon iconoHuella1 = new ImageIcon(getClass().getResource("Huella.png"));
        Image imagenHuella = iconoHuella1.getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon huella = new ImageIcon(imagenHuella);

        ImageIcon iconoDelete = new ImageIcon(getClass().getResource("Delete.png"));
        Image imagenDelete = iconoDelete.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon delete = new ImageIcon(imagenDelete);

        JButton btnHuella = new JButton(huella);
        btnHuella.setContentAreaFilled(false);
        btnHuella.setBorderPainted(false);
        btnHuella.setFocusPainted(false);
        pnlBotones.add(btnHuella);

        JButton btnNumero0 = crearBotonNumerico("0");
        pnlBotones.add(btnNumero0);

        JButton btnDelete = new JButton(delete);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(e -> borrarUltimoNumero());
        pnlBotones.add(btnDelete);

        JButton btnOlvidaste = new JButton("¿OLVIDASTE TU CLAVE?");
        btnOlvidaste.setBounds(140, 360, 200, 50);
        btnOlvidaste.setBackground(Color.WHITE);
        btnOlvidaste.setLayout(null);
        btnOlvidaste.setBorder(null);
        btnOlvidaste.setFont(new Font("Arial", Font.BOLD, 14));
        btnOlvidaste.setForeground(new Color(59, 209, 182));
        pnlPrincipal.add(btnOlvidaste);
    }

    private JButton crearBotonNumerico(String numero) {
        BotonRedondo boton = new BotonRedondo(numero, 30);
        boton.setFont(new Font("Arial", Font.BOLD, 20));
        boton.setForeground(Color.BLACK);
        boton.setBackground(new Color(217, 215, 225));
        boton.setFocusPainted(false);
        boton.setBorder(null);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNumero(numero);
            }
        });
        return boton;
    }

    private void agregarNumero(String numero) {
        if (password.length() < maxDigitos) {
            password.append(numero);
            actualizarPassword();
        }

        if (password.length() == maxDigitos) {
            if (password.toString().equals(PASSWORD)) {
                JOptionPane.showMessageDialog(this, "Contraseña Correcta.");
            } else {
                JOptionPane.showMessageDialog(this, "Contraseña Incorrecta.");
                password.setLength(0); 
            }
        }
    }

    private void borrarUltimoNumero() {
        if (password.length() > 0) {
            password.setLength(password.length() - 1);
            actualizarPassword();
        }
    }

    private void actualizarPassword() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (i < password.length()) {
                display.append("• ");
            } else {
                display.append("o ");
            }
        }
        lblPassword.setText(display.toString().trim());
    }

    public class BotonRedondo extends JButton {
        private static final long serialVersionUID = 1L;
        private int radio;

        public BotonRedondo(String texto, int radio) {
            super(texto);
            this.radio = radio;
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
            super.paintComponent(g2);
            g2.dispose();
        }
    }

    public class PanelRedondo extends JButton {
        private static final long serialVersionUID = 1L;
        private int radio;

        public PanelRedondo(int radio) {
            this.radio = radio;
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
            super.paintComponent(g2);
            g2.dispose();
        }
    }
}