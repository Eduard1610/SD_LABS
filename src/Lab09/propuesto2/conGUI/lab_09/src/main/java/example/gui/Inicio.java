package example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.SQLException;

import example.Database;
import example.Transaccion;
//import example.gui.Depositar;

public class Inicio {
    private JFrame ventana;
    private JPanel panelNorte;
    private JPanel panelSur;
    private JPanel panelCentro;
    private JButton botonVerSaldo;
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private boolean saldoVisible = false;

    public Inicio(int idUsuario) {
        ventana = new JFrame("Aplicación");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.setSize(400, 500); // ancho, alto
        ventana.setResizable(false); // para que no se pueda redimensionar

        // Panel Norte
        panelNorte = new JPanel();
        panelNorte.setBackground(new Color(128, 0, 128)); // morado
        JLabel titulo = new JLabel("Yape", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20)); // tamaño 20, Arial, negrita
        titulo.setForeground(Color.WHITE); // color blanco
        panelNorte.add(titulo);
        ventana.add(panelNorte, BorderLayout.NORTH);

        // Panel Sur
        panelSur = new JPanel();
        panelSur.setBackground(new Color(128, 0, 128)); // morado
        ventana.add(panelSur, BorderLayout.SOUTH);

        // Panel Centro
        panelCentro = new JPanel();
        panelCentro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // espacio entre componentes

        // BOTÓN VER SALDO
        botonVerSaldo = new JButton("Ver saldo");
        botonVerSaldo.setBackground(Color.WHITE);
        botonVerSaldo.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0; // fila 0
        panelCentro.add(botonVerSaldo, gbc);
        botonVerSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (saldoVisible) {
                    botonVerSaldo.setText("Ver saldo");
                    saldoVisible = false;
                } else {
                    try {
                        Connection conn = Database.getConnection();
                        double saldo = new Transaccion().verSaldo(conn, idUsuario);
                        botonVerSaldo.setText("Total: " + saldo);
                        saldoVisible = true;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // BOTÓN DEPOSITAR
        boton1 = new JButton("Depositar");
        boton1.setBackground(new Color(128, 0, 128));
        boton1.setForeground(Color.WHITE);
        boton1.setFont(new Font("Arial", Font.PLAIN, 16));
        boton1.setPreferredSize(new Dimension(150, 40));
        gbc.gridy = 1; // fila 1
        panelCentro.add(boton1, gbc);
        boton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton1.setForeground(new Color(128, 0, 128));
                boton1.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton1.setForeground(Color.WHITE);
                boton1.setBackground(new Color(128, 0, 128));
            }
        });
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Depositar depositar = new Depositar(ventana, idUsuario);
                botonVerSaldo.setText("Ver saldo");
                saldoVisible = false;
                ventana.setVisible(false);
            }
        });

        // BOTÓN RETIRAR
        boton2 = new JButton("Retirar");
        boton2.setBackground(new Color(128, 0, 128));
        boton2.setForeground(Color.WHITE);
        boton2.setFont(new Font("Arial", Font.PLAIN, 16));
        boton2.setPreferredSize(new Dimension(150, 40));
        gbc.gridy = 2; // fila 2
        panelCentro.add(boton2, gbc);
        boton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton2.setForeground(new Color(128, 0, 128));
                boton2.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton2.setForeground(Color.WHITE);
                boton2.setBackground(new Color(128, 0   , 128));
            }
        });
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Retirar retirar = new Retirar(ventana, idUsuario);
                botonVerSaldo.setText("Ver saldo");
                saldoVisible = false;
                ventana.setVisible(false);
            }
        });

        // BOTÓN TRANSFERIR
        boton3 = new JButton("Transferir");
        boton3.setBackground(new Color(128, 0, 128));
        boton3.setForeground(Color.WHITE);
        boton3.setFont(new Font("Arial", Font.PLAIN, 16));
        boton3.setPreferredSize(new Dimension(150, 40));
        gbc.gridy = 3; // fila 3
        panelCentro.add(boton3, gbc);

        boton3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton3.setForeground(new Color(128, 0, 128));
                boton3.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton3.setForeground(Color.WHITE);
                boton3.setBackground(new Color(128, 0, 128));
            }
        });
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transferir transferir = new Transferir(ventana, idUsuario);
                botonVerSaldo.setText("Ver saldo");
                saldoVisible = false;
                ventana.setVisible(false);
            }
        });

        ventana.add(panelCentro, BorderLayout.CENTER);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}