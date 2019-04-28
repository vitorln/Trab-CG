/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab_cg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vitor
 */
public class Aresta implements Serializable{
    public List<Ponto> pontos = new ArrayList<>(2);
    double u1, u2, u3;
    double xAux1, xAux2, xAux3;
    
    
    public Aresta(Ponto p1, Ponto p2)
    {
        this.pontos.add(0, p1);
        this.pontos.add(1, p2);
        limpar();
    }
    
    public double getU1() {
        return u1;
    }

    public double getU2() {
        return u2;
    }

    public double getU3() {
        return u3;
    }
    
    public double getxAux1() {
        return xAux1;
    }
  
    public double getxAux2() {
        return xAux2;
    }

    public double getxAux3() {
        return xAux3;
    }

    public void limpar()
    {
        if(pontos.get(0).getY() < pontos.get(1).getY())
        {
            xAux1 = pontos.get(0).getX();
        }
        else if(pontos.get(0).getY() > pontos.get(1).getY())
        {
            xAux1 = pontos.get(1).getX();
        }
        this.u1=(pontos.get(1).getX()-pontos.get(0).getX())/(pontos.get(1).getY()-pontos.get(0).getY());
        
        if(pontos.get(0).getZ() < pontos.get(1).getZ())
        {
            xAux2 = pontos.get(0).getX();
        }
        else if(pontos.get(0).getZ() > pontos.get(1).getZ())
        {
            xAux2 = pontos.get(1).getX();
        }
        this.u2=(pontos.get(1).getX()-pontos.get(0).getX())/(pontos.get(1).getZ()-pontos.get(0).getZ());
        
        if(pontos.get(0).getY() < pontos.get(1).getY())
        {
            xAux3 = pontos.get(0).getZ();
        }
        else if(pontos.get(0).getY() > pontos.get(1).getY())
        {
            xAux3 = pontos.get(1).getZ();
        }
        this.u3=(pontos.get(1).getZ()-pontos.get(0).getZ())/(pontos.get(1).getY()-pontos.get(0).getY());
    }
    
    public double calculoX1(double xi){
        if(pontos.get(1).getY()==pontos.get(0).getY())
        {
            return pontos.get(0).getX();
        }
        xAux1 = (xi+u1);
        return xAux1;
    }
    
    public double calculoX2(double xi){
        if(pontos.get(1).getZ()==pontos.get(0).getZ())
        {
            return pontos.get(0).getX();
        }
        xAux2 = (xi+u2);
        return xAux2;
    }
    
    public double calculoX3(double xi){
        if(pontos.get(1).getY()==pontos.get(0).getY())
        {
            return pontos.get(0).getZ();
        }
        xAux3 = (xi+u3);
        return xAux3;
    }
    
}