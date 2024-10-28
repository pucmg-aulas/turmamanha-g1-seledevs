/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Leandro Alencar
 */
public class botaoArredondado extends JButton {
    
    public botaoArredondado(){
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5,0,5,0));
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    protected void paintComponent(Graphics g){
        int width = getWidth();
        int heigth = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawRoundRect(0, 0, width -1, heigth -1, heigth, heigth);
        super.paintComponent(g);
    }
    
}
