/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab_cg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author vitor
 */

public class Preenchimento implements Serializable {
    
    private List<Aresta> arestas;
    private double ymax, ymin, zmax, zmin;
    private HashMap<Integer, List<Double>> mapa1;
    private HashMap<Integer, List<Double>> mapa2;
    private HashMap<Integer, List<Double>> mapa3;
    private HashMap<Integer, List<Double>> mapa4;
    String corFundo;
    
    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }
    
    public void setCorFundo(String corFundo) {
        this.corFundo = corFundo;
    }
    
    public void limiteScanlines()
    {
        ymin=1000;
        ymax=-1000;
        zmin=1000;
        zmax=-1000;
        
        for (int i = 0; i < arestas.size(); i++)
        {
            if(arestas.get(i).pontos.get(0).getY()>ymax)
            {
                ymax=arestas.get(i).pontos.get(0).getY();
            }
            if(arestas.get(i).pontos.get(0).getY()<ymin)
            {
                ymin=arestas.get(i).pontos.get(0).getY();
            }
            
            if(arestas.get(i).pontos.get(1).getY()>ymax)
            {
                ymax=arestas.get(i).pontos.get(1).getY();
            }
            if(arestas.get(i).pontos.get(1).getY()<ymin)
            {
                ymin=arestas.get(i).pontos.get(1).getY();
            }
            
            
            if(arestas.get(i).pontos.get(0).getZ()>zmax)
            {
                zmax=arestas.get(i).pontos.get(0).getZ();
            }
            if(arestas.get(i).pontos.get(0).getZ()<zmin)
            {
                zmin=arestas.get(i).pontos.get(0).getZ();
            }
            
            if(arestas.get(i).pontos.get(1).getZ()>zmax)
            {
                zmax=arestas.get(i).pontos.get(1).getZ();
            }
            if(arestas.get(i).pontos.get(1).getZ()<zmin)
            {
                zmin=arestas.get(i).pontos.get(1).getZ();
            }
        }
        
    }
    
    public void calcMapa1()
    {
        for (int j = 0; j < arestas.size(); j++)
        {
            arestas.get(j).limpar();
        }
        mapa1 = new HashMap<Integer, List<Double>>();
        for (int i = (int) ymin; i < (int) ymax; i++)
        {
            List<Double> listaX = new ArrayList<>();
                  
            for (int j = 0; j < arestas.size(); j++)
            {
                if(arestas.get(j).pontos.get(0).getY() < arestas.get(j).pontos.get(1).getY())
                {
                    if ((i >= (int) arestas.get(j).pontos.get(0).getY()) && (i <= (int) arestas.get(j).pontos.get(1).getY()-1))
                            {
                        if((int) arestas.get(j).pontos.get(0).getY() == i)
                        {
                            listaX.add(arestas.get(j).getxAux1());
                        }
                        else
                        {
                            listaX.add(arestas.get(j).calculoX1(arestas.get(j).getxAux1()));
                        }
                    }            
                }
                else if(arestas.get(j).pontos.get(0).getY() > arestas.get(j).pontos.get(1).getY())
                {
                    if ((i <= (int) arestas.get(j).pontos.get(0).getY()-1) && (i >= (int) arestas.get(j).pontos.get(1).getY()))
                    {
                        if((int) arestas.get(j).pontos.get(1).getY() == i)
                        {
                            listaX.add(arestas.get(j).getxAux1());
                        }
                        else
                        {
                            listaX.add(arestas.get(j).calculoX1(arestas.get(j).getxAux1()));
                        }
                    }
                }
            }
            
            Collections.sort(listaX);
            mapa1.put(i, listaX);
        }
        for (int j = 0; j < arestas.size(); j++)
        {
            arestas.get(j).limpar();
        }
    }
    public void pintarMapa1(GraphicsContext c)
    {
        int flip;
        Set<Integer> chaves = mapa1.keySet();
        for (Integer key : chaves) 
        {
            if (key != null)
            {
                flip=1;
                for (int j = 0; j < (mapa1.get(key).size()-1); j++) 
                {
                    if(flip==1)
                    {
                        c.setLineWidth(2);
                        c.setStroke(Color.web(corFundo));
                        c.strokeLine(mapa1.get(key).get(j), key, mapa1.get(key).get(j+1), key);
                    }
                    flip*=-1;
                }     
            } 
        }
    }
    public void calcMapa2()
    {
        for (int j = 0; j < arestas.size(); j++)
        {
            arestas.get(j).limpar();
        }
        mapa2 = new HashMap<Integer, List<Double>>();
        for (int i = (int) zmin; i < (int) zmax; i++)
        {
            List<Double> listaX = new ArrayList<>();
                  
            for (int j = 0; j < arestas.size(); j++)
            {
                if(arestas.get(j).pontos.get(0).getZ() < arestas.get(j).pontos.get(1).getZ())
                {
                    if ((i >= (int) arestas.get(j).pontos.get(0).getZ()) && (i <= (int) arestas.get(j).pontos.get(1).getZ()-1))
                            {
                        if((int) arestas.get(j).pontos.get(0).getZ() == i)
                        {
                            listaX.add(arestas.get(j).getxAux2());
                        }
                        else
                        {
                            listaX.add(arestas.get(j).calculoX2(arestas.get(j).getxAux2()));
                        }
                    }            
                }
                else if(arestas.get(j).pontos.get(0).getZ() > arestas.get(j).pontos.get(1).getZ())
                {
                    if ((i <= (int) arestas.get(j).pontos.get(0).getZ()-1) && (i >= (int) arestas.get(j).pontos.get(1).getZ()))
                    {
                        if((int) arestas.get(j).pontos.get(1).getZ() == i)
                        {
                            listaX.add(arestas.get(j).getxAux2());
                        }
                        else
                        {
                            listaX.add(arestas.get(j).calculoX2(arestas.get(j).getxAux2()));
                        }
                    }
                }
            }
            
            Collections.sort(listaX);
            mapa2.put(i, listaX);
        }
        for (int j = 0; j < arestas.size(); j++)
        {
            arestas.get(j).limpar();
        }
    }
    public void pintarMapa2(GraphicsContext c)
    {
        int flip;
        Set<Integer> chaves = mapa2.keySet();
        for (Integer key : chaves) 
        {
            if (key != null)
            {
                flip=1;
                for (int j = 0; j < (mapa2.get(key).size()-1); j++) 
                {
                    if(flip==1)
                    {
                        c.setLineWidth(2);
                        c.setStroke(Color.web(corFundo));
                        c.strokeLine(mapa2.get(key).get(j), key, mapa2.get(key).get(j+1), key);
                    }
                    flip*=-1;
                }     
            } 
        }
    }
    public void calcMapa3()
    {
        for (int j = 0; j < arestas.size(); j++)
        {
            arestas.get(j).limpar();
        }
        mapa3 = new HashMap<Integer, List<Double>>();
        for (int i = (int) ymin; i < (int) ymax; i++)
        {
            List<Double> listaX = new ArrayList<>();
                  
            for (int j = 0; j < arestas.size(); j++)
            {
                if(arestas.get(j).pontos.get(0).getY() < arestas.get(j).pontos.get(1).getY())
                {
                    if ((i >= (int) arestas.get(j).pontos.get(0).getY()) && (i <= (int) arestas.get(j).pontos.get(1).getY()-1))
                            {
                        if((int) arestas.get(j).pontos.get(0).getY() == i)
                        {
                            listaX.add(arestas.get(j).getxAux3());
                        }
                        else
                        {
                            listaX.add(arestas.get(j).calculoX3(arestas.get(j).getxAux3()));
                        }
                    }            
                }
                else if(arestas.get(j).pontos.get(0).getY() > arestas.get(j).pontos.get(1).getY())
                {
                    if ((i <= (int) arestas.get(j).pontos.get(0).getY()-1) && (i >= (int) arestas.get(j).pontos.get(1).getY()))
                    {
                        if((int) arestas.get(j).pontos.get(1).getY() == i)
                        {
                            listaX.add(arestas.get(j).getxAux3());
                        }
                        else
                        {
                            listaX.add(arestas.get(j).calculoX3(arestas.get(j).getxAux3()));
                        }
                    }
                }
            }
            
            Collections.sort(listaX);
            mapa3.put(i, listaX);
        }
        for (int j = 0; j < arestas.size(); j++)
        {
            arestas.get(j).limpar();
        }
    }
    public void pintarMapa3(GraphicsContext c)
    {
        int flip;
        Set<Integer> chaves = mapa3.keySet();
        for (Integer key : chaves) 
        {
            if (key != null)
            {
                flip=1;
                for (int j = 0; j < (mapa3.get(key).size()-1); j++) 
                {
                    if(flip==1)
                    {
                        c.setLineWidth(2);
                        c.setStroke(Color.web(corFundo));
                        c.strokeLine(mapa3.get(key).get(j), key, mapa3.get(key).get(j+1), key);
                    }
                    flip*=-1;
                }     
            } 
        }
    }
}