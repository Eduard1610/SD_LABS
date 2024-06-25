package example.gui;

import javax.swing.*;

import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import example.Database;
import example.Transaccion;

public class Transferir extends JFrame {

    private JLabel labelTitulo;
    private JLabel labelNumero;
    private JTextField campoNumero;
    private JLabel labelMonto;
    private JTextField campoMonto;
    private JButton botonConfirmar;

    public Transferir(JFrame ventanaPrincipal, int idUsuario) {
        super("Depósito");
        setSize(400, 500); // Tamaño de la ventana
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

        labelTitulo = new JLabel("Depósito");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24)); // Tamaño 24
        labelTitulo.setForeground(Color.decode("#FFFFFF")); // Blanco
        panelNorte.add(labelTitulo);

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes
        add(panelCentro, BorderLayout.CENTER);

        // Componentes
        labelNumero = new JLabel("Introduce el número a depositar");
        labelNumero.setFont(new Font("Arial", Font.PLAIN, 18)); // Tamaño 18
        gbc.anchor = GridBagConstraints.CENTER; // Centrar label
        gbc.gridy = 0; // Fila 0
        panelCentro.add(labelNumero, gbc);

        campoNumero = new JTextField();
        campoNumero.setFont(new Font("Arial", Font.PLAIN, 18)); // Tamaño 18
        campoNumero.setPreferredSize(new Dimension(250, 30));
        gbc.gridy = 1; // Fila 1
        panelCentro.add(campoNumero, gbc);

        labelMonto = new JLabel("Introduce el monto a depositar");
        labelMonto.setFont(new Font("Arial", Font.PLAIN, 18)); // Tamaño 18
        gbc.gridy = 2; // Fila 2
        panelCentro.add(labelMonto, gbc);

        campoMonto = new JTextField();
        campoMonto.setFont(new Font("Arial", Font.PLAIN, 18)); // Tamaño 18
        campoMonto.setPreferredSize(new Dimension(250, 30));
        gbc.gridy = 3; // Fila 3
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
                String numero = campoNumero.getText();
                String monto = campoMonto.getText();

                if (numero.isEmpty() || monto.isEmpty()) {
                    JOptionPane.showMessageDialog(Transferir.this, "Por favor, complete todos los campos");
                } else {
                    // Verificar que el número sea válido (9 digitos y empieza con 9)
                    if (numero.length() != 9 || !numero.startsWith("9")) {
                        JOptionPane.showMessageDialog(Transferir.this, "Número inválido");
                        return;
                    } else {
                        // Verificar que el monto sea un número válido
                        try {
                            double montoDouble = Double.parseDouble(monto);
                            if (montoDouble <= 0) {
                                JOptionPane.showMessageDialog(Transferir.this, "Monto inválido");
                                return;
                            } else {
                                try {
                                    Connection connection = Database.getConnection();
                                    Transaccion transaccion = new Transaccion();
                                    transaccion.transferir(connection, idUsuario, numero, montoDouble);
                                    JOptionPane.showMessageDialog(Transferir.this, "Transferencia realizada con éxito");
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(Transferir.this, "Error al realizar la transferencia");
                                    ex.printStackTrace();
                                }
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(Transferir.this, "Monto inválido");
                            return;
                        }
                    
                    }
                    Transferir.this.dispose();
                }
            }
        });
        gbc.gridy = 4; // Fila 4
        panelCentro.add(botonConfirmar, gbc);

        setVisible(true);
    }
}