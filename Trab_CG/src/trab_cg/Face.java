/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab_cg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author vitor
 */
public class Face implements Serializable{
    public List<Aresta> arestas = new ArrayList<>();
    public List<Aresta> arestasPerspectiva = new ArrayList<>();
    boolean c1v, c2v, c3v, c4v;
    
    public void calcPespectiva(boolean c1v, boolean c2v, boolean c3v, boolean c4v, Ponto VRP)
    {
        this.c1v = c1v;
        this.c2v = c2v;
        this.c3v = c3v;
        this.c4v = c4v;
        if(c4v)
        {
            Perspectiva p = new Perspectiva();
            arestasPerspectiva = p.VisualizaPersp(arestas, VRP);
        }
    }
    
    public void draw(GraphicsContext c1, GraphicsContext c2, GraphicsContext c3, GraphicsContext c4, String corBorda, boolean sel)
    { 
        for(Aresta aresta : arestas)
        {
            if(sel==true)
            {
                c1.setLineWidth(4);
                c2.setLineWidth(4);
                c3.setLineWidth(4);
                c4.setLineWidth(4);
            }
            else
            {
                c1.setLineWidth(2);
                c2.setLineWidth(2);
                c3.setLineWidth(2);
                c4.setLineWidth(2);
            }
            
            c1.setStroke(Color.web(corBorda));
            c2.setStroke(Color.web(corBorda));
            c3.setStroke(Color.web(corBorda));
            c4.setStroke(Color.web(corBorda));
            
            if(c1v)
            {
                c1.strokeLine(aresta.pontos.get(0).getX(), aresta.pontos.get(0).getY(), aresta.pontos.get(1).getX(), aresta.pontos.get(1).getY());
            }
            if(c2v)
            {
                c2.strokeLine(aresta.pontos.get(0).getX(), aresta.pontos.get(0).getZ(), aresta.pontos.get(1).getX(), aresta.pontos.get(1).getZ());
            }
            if(c3v)
            {
                c3.strokeLine(aresta.pontos.get(0).getZ(), aresta.pontos.get(0).getY(), aresta.pontos.get(1).getZ(), aresta.pontos.get(1).getY()); 
            }
        }
        if(c4v)
        {
            for(Aresta aresta : arestasPerspectiva)
            {
                c4.strokeLine(aresta.pontos.get(0).getX(), aresta.pontos.get(0).getY(), aresta.pontos.get(1).getX(), aresta.pontos.get(1).getY());
            }
        }
        
    }
    
    public void pintarFace(String corFundo, GraphicsContext c1, GraphicsContext c2, GraphicsContext c3, GraphicsContext c4)
    {
        Preenchimento p = new Preenchimento();
        p.setArestas(arestas);
        p.setCorFundo(corFundo);
        p.limiteScanlines();
        if(c1v)
        {
            p.calcMapa1();
            p.pintarMapa1(c1);
        }
        if(c2v)
        {
            p.calcMapa2();
            p.pintarMapa2(c2);
        }
        if(c3v)    
        {
            p.calcMapa3();
            p.pintarMapa3(c3);
        }
        if(c4v)
        {
            Preenchimento p2 = new Preenchimento();
            p2.setArestas(arestasPerspectiva);
            p2.setCorFundo(corFundo);
            p2.limiteScanlines();
            p2.calcMapa1();
            p2.pintarMapa1(c4);
        }
    }
    
    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }
}
