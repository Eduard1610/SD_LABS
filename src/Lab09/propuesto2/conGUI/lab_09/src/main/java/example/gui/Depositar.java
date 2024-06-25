package example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import example.Database;
import example.Transaccion;

public class Depositar extends JFrame {

    private JLabel labelTitulo;
    private JLabel labelMonto;
    private JTextField campoMonto;
    private JButton botonConfirmar;

    public Depositar(JFrame ventanaPrincipal, int idUsuario) {
        super("Depositar");
        setSize(400, 350); // Tamaño de la ventana
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ventanaPrincipal.setVisible(true);
            }
        });

        // Paneles
        JPanel panelNorte = new JPanel();
        panelNorte.setBackground(new Color(128, 0, 128)); // Morado
        panelNorte.setPreferredSize(new Dimension(400, 50));
        add(panelNorte, BorderLayout.NORTH);

        labelTitulo = new JLabel("Depositar");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24)); // Tamaño 24
        labelTitulo.setForeground(Color.decode("#FFFFFF")); // Blanco
        panelNorte.add(labelTitulo);

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes
        add(panelCentro, BorderLayout.CENTER);

        // Componentes
        labelMonto = new JLabel("Introduce el monto a depositar");
        labelMonto.setFont(new Font("Arial", Font.PLAIN, 18)); // Tamaño 18
        gbc.anchor = GridBagConstraints.CENTER; // Centrar label
        gbc.gridy = 0; // Fila 0
        panelCentro.add(labelMonto, gbc);

        campoMonto = new JTextField();
        campoMonto.setFont(new Font("Arial", Font.PLAIN, 18)); // Tamaño 18
        campoMonto.setPreferredSize(new Dimension(250, 30));
        gbc.gridy = 1; // Fila 1
        panelCentro.add(campoMonto, gbc);

        // BOTÓN CONFIRMAR
        botonConfirmar = new JButton("Confirmar");
        botonConfirmar.setFont(new Font("Arial", Font.BOLD, 18));
        botonConfirmar.setForeground(Color.decode("#FFFFFF"));
        botonConfirmar.setBackground(new Color(47, 165, 49));
        botonConfirmar.setPreferredSize(new Dimension(150, 30));
        botonConfirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonConfirmar.setForeground(new Color(47, 165, 49));
                botonConfirmar.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonConfirmar.setForeground(Color.WHITE);
                botonConfirmar.setBackground(new Color(47, 165, 49));
            }
        });
        botonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para procesar el depósito
                String monto = campoMonto.getText();

                if (monto.isEmpty()) {
                    JOptionPane.showMessageDialog(Depositar.this, "Por favor, complete todos los campos");
                } else {
                    // Verificar que el monto sea un número válido
                    try {
                        double montoDouble = Double.parseDouble(monto);
                        if (montoDouble <= 0) {
                            JOptionPane.showMessageDialog(Depositar.this, "Monto inválido");
                            return;
                        } else {
                            try {
                                Connection connection = Database.getConnection();
                                Transaccion transaccion = new Transaccion();
                                transaccion.depositar(connection, montoDouble, idUsuario);
                                JOptionPane.showMessageDialog(Depositar.this, "Depósito realizado con éxito");
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(Depositar.this, "Error al realizar el depósito");
                                ex.printStackTrace();
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Depositar.this, "Monto inválido");
                        return;
                    }
                }
                Depositar.this.dispose();
            }
        });
        gbc.gridy = 2; // Fila 2
        panelCentro.add(botonConfirmar, gbc);

        setVisible(true);
    }
}