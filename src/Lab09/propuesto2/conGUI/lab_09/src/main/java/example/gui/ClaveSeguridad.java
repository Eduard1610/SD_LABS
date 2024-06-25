package example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import example.Database;
import example.Transaccion;

public class ClaveSeguridad extends JFrame {
    private JLabel labelClave;
    private JButton[] botonesNumeros;
    private JButton botonEmpezar;
    private String clave = "";

    public ClaveSeguridad() {
        super("Clave de Seguridad");
        setSize(400, 400); // Tamaño de la ventana
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Paneles
        JPanel panelNorte = new JPanel();
        panelNorte.setBackground(new Color(128, 0, 128)); // Morado
        panelNorte.setLayout(new BorderLayout());
        panelNorte.setPreferredSize(new Dimension(400, 100));
        add(panelNorte, BorderLayout.NORTH);

        JLabel labelTitulo = new JLabel("Ingrese su clave");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24)); // Tamaño 24
        labelTitulo.setForeground(Color.decode("#FFFFFF")); // Blanco
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        panelNorte.add(labelTitulo, BorderLayout.NORTH);

        labelClave = new JLabel("");
        labelClave.setFont(new Font("Arial", Font.BOLD, 24)); // Tamaño 24
        labelClave.setForeground(Color.BLACK); // Negro
        labelClave.setBackground(Color.WHITE); // Blanco
        labelClave.setOpaque(true);
        labelClave.setHorizontalAlignment(JLabel.CENTER);
        panelNorte.add(labelClave, BorderLayout.CENTER);

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(4, 3, 10, 10)); // 4 filas, 3 columnas, espacio entre botones
        add(panelCentro, BorderLayout.CENTER);

        // Botones números
        botonesNumeros = new JButton[10];
        for (int i = 0; i < 10; i++) {
            botonesNumeros[i] = new JButton(String.valueOf(i));
            botonesNumeros[i].setFont(new Font("Arial", Font.BOLD, 24)); // Tamaño 24
            botonesNumeros[i].setBackground(new Color(128, 0, 128)); // Morado
            botonesNumeros[i].setForeground(Color.WHITE); // Blanco
            botonesNumeros[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton boton = (JButton) e.getSource();
                    clave += boton.getText();
                    labelClave.setText(clave);
                }
            });
            botonesNumeros[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JButton boton = (JButton) e.getSource();
                    boton.setBackground(Color.WHITE); // Blanco
                    boton.setForeground(new Color(128, 0, 128)); // Morado
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    JButton boton = (JButton) e.getSource();
                    boton.setBackground(new Color(128, 0, 128)); // Morado
                    boton.setForeground(Color.WHITE); // Blanco
                }
            });
            panelCentro.add(botonesNumeros[i]);
        }

        // Botón Empezar
        botonEmpezar = new JButton("Empezar");
        botonEmpezar.setFont(new Font("Arial", Font.BOLD, 18));
        botonEmpezar.setBackground(new Color(128, 0, 128)); // Morado
        botonEmpezar.setForeground(Color.WHITE); // Blanco
        botonEmpezar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clave = labelClave.getText();
                try {
                    Connection conn = Database.getConnection();
                    int acceso = new Transaccion().verificarClave(conn, clave);
                    if (acceso != -1) {
                        Inicio ventanaInicio = new Inicio(acceso);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Clave incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        botonEmpezar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonEmpezar.setBackground(Color.WHITE); // Blanco
                botonEmpezar.setForeground(new Color(128, 0, 128)); // Morado
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonEmpezar.setBackground(new Color(128, 0, 128)); // Morado
                botonEmpezar.setForeground(Color.WHITE); // Blanco
            }
        });
        panelCentro.add(botonEmpezar);

        // Botón Limpiar
        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setFont(new Font("Arial", Font.BOLD, 18));
        botonLimpiar.setBackground(new Color(128, 0, 128)); // Morado
        botonLimpiar.setForeground(Color.WHITE); // Blanco
        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clave = "";
                labelClave.setText(clave);
            }
        });
        botonLimpiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonLimpiar.setBackground(Color.WHITE); // Blanco
                botonLimpiar.setForeground(new Color(128, 0, 128)); // Morado
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonLimpiar.setBackground(new Color(128, 0, 128)); // Morado
                botonLimpiar.setForeground(Color.WHITE); // Blanco
            }
        });
        panelCentro.add(botonLimpiar);

        setVisible(true);
    }
}