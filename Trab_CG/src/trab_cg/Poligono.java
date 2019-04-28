//@Vitor Lisboa Nogueira @João Luiz Reolon

package trab_cg;

import java.io.Serializable;
import static java.lang.Math.*;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Poligono implements Serializable {
    public List<Ponto> pontos = new ArrayList<>();
    public List<Aresta> arestas = new ArrayList<>();
    public List<Face> faces = new ArrayList<>();
    public boolean sel= false;
    Ponto centro;
    double raio;
    String corBorda;
    String corFundo;
    boolean preenchido=false;
    double ymin, ymax;
    
    public void draw(GraphicsContext c1, GraphicsContext c2, GraphicsContext c3, GraphicsContext c4){
      
        for(Face face : faces)
        {
            face.draw(c1, c2, c3, c4, corBorda, sel);
        }
        
    }
    public void calcPespectiva(boolean c1v, boolean c2v, boolean c3v, boolean c4v, Ponto VRP)
    {
        for(Face face : faces)
        {
            face.calcPespectiva(c1v, c2v, c3v, c4v, VRP);
        }
    }
    public void setArestas(){
        arestas = new ArrayList<>();
        if(pontos.size()>=2)
        {
            for(int i = 0; i<pontos.size()-1;i++)
            {
                Ponto p1= new Ponto(pontos.get(i));
                Ponto p2= new Ponto(pontos.get(i+1));
                Aresta aux = new Aresta(p1,p2);
                this.arestas.add(aux);  
            }
        }
        Face aux1 = new Face();
        aux1.setArestas(arestas);
        faces.add(aux1);
    }
    public Ponto getCentro() {
        return centro;
    }

    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    public double getRaio() {
        return raio;
    }
    
    public void setRaio(double x, double y, double z) {
        this.raio = sqrt(pow(abs(centro.x - x),2) + pow(abs(centro.y - y),2) + pow(abs(centro.z - z),2));
    }
    
    public void pintar(GraphicsContext c1, GraphicsContext c2, GraphicsContext c3, GraphicsContext c4)
    {
        if(preenchido)
        {
            for(Face face : faces)
            {
                face.pintarFace(corFundo, c1, c2, c3, c4);
            }
        }
    }   
    
    public String getCorBorda() {
        return corBorda;
    }

    public void setCorBorda(String corBorda) {
        this.corBorda = corBorda;
    }

    public String getCorFundo() {
        return corFundo;
    }

    public void setCorFundo(String corFundo) {
        this.corFundo = corFundo;
    }
    
    public void revolucionario1(Ponto c, int lados, double graus)
    {
        double angulo = toRadians(graus/lados);
        List<Aresta> a1 = new ArrayList<>();
        List<Aresta> a2 = new ArrayList<>();
        List<Aresta> b;
        
        centro.setX(c.getX());
        centro.setY(c.getY());
        int tam = arestas.size();
        for(int i=0; i < tam;i++)
        {
            Ponto p1 = new Ponto(arestas.get(i).pontos.get(0));
            Ponto p2 = new Ponto(arestas.get(i).pontos.get(1));
            Aresta aux = new Aresta(p1,p2);
            a1.add(aux);
            
            p1 = new Ponto(arestas.get(i).pontos.get(0));
            p2 = new Ponto(arestas.get(i).pontos.get(1));
            aux = new Aresta(p1,p2);
            a2.add(aux);
        }
        if(graus==360)
        {
            faces.remove(0);
        }
        for(int k=0; k < lados; k++) 
        {
            for(int i=0; i < tam;i++)
            {   
                for(int j=0; j < 2;j++)
                {
                    Ponto p=new Ponto();
                    a2.get(i).pontos.get(j).setX(a2.get(i).pontos.get(j).getX() - c.getX());
                    a2.get(i).pontos.get(j).setY(a2.get(i).pontos.get(j).getY() - c.getY());

                    p.setX(a2.get(i).pontos.get(j).getX());
                    p.setY(a2.get(i).pontos.get(j).getY());

                    a2.get(i).pontos.get(j).setX( +cos(angulo) * p.getX() + sin(angulo) * p.getY());
                    a2.get(i).pontos.get(j).setY( -sin(angulo) * p.getX() + cos(angulo) * p.getY());

                    a2.get(i).pontos.get(j).setX(a2.get(i).pontos.get(j).getX() + c.getX());
                    a2.get(i).pontos.get(j).setY(a2.get(i).pontos.get(j).getY() + c.getY()); 
                }
            }
            for(int i=0; i < tam;i++)
            {
                b = new ArrayList<>();
                if(i==tam-1)
                {
                    for(int j=0; j < 2;j++)
                    {
                        Ponto p1 = new Ponto(a1.get(i).pontos.get(j));
                        Ponto p2 = new Ponto(a2.get(i).pontos.get(j));
                        Aresta aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(i).pontos.get(j));
                        p2 = new Ponto(a2.get(0).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(0).pontos.get(j));
                        p2 = new Ponto(a1.get(0).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a1.get(0).pontos.get(j));
                        p2 = new Ponto(a1.get(i).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);
                    }
                }
                else
                {
                    for(int j=0; j < 2;j++)
                    {
                        Ponto p1 = new Ponto(a1.get(i).pontos.get(j));
                        Ponto p2 = new Ponto(a2.get(i).pontos.get(j));
                        Aresta aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(i).pontos.get(j));
                        p2 = new Ponto(a2.get(i+1).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(i+1).pontos.get(j));
                        p2 = new Ponto(a1.get(i+1).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a1.get(i+1).pontos.get(j));
                        p2 = new Ponto(a1.get(i).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);
                    }
                }
                Face f = new Face();
                f.setArestas(b);
                faces.add(f);
            }
            a1 = new ArrayList<>();
            for(int i=0; i < tam;i++)
            {
                Ponto p1 = new Ponto(a2.get(i).pontos.get(0));
                Ponto p2 = new Ponto(a2.get(i).pontos.get(1));
                Aresta aux = new Aresta(p1,p2);
                a1.add(aux);
            }
        }
        if(graus!=360)
        {
            Face f = new Face();
            f.setArestas(a2);
            faces.add(f);
        }
    }
    
    public void revolucionario2(Ponto c, int lados, double graus)
    {
        double angulo = toRadians(graus/lados);
        List<Aresta> a1 = new ArrayList<>();
        List<Aresta> a2 = new ArrayList<>();
        List<Aresta> b;
        
        centro.setX(c.getX());
        centro.setZ(c.getZ());
        int tam = arestas.size();
        for(int i=0; i < tam;i++)
        {
            Ponto p1 = new Ponto(arestas.get(i).pontos.get(0));
            Ponto p2 = new Ponto(arestas.get(i).pontos.get(1));
            Aresta aux = new Aresta(p1,p2);
            a1.add(aux);
            
            p1 = new Ponto(arestas.get(i).pontos.get(0));
            p2 = new Ponto(arestas.get(i).pontos.get(1));
            aux = new Aresta(p1,p2);
            a2.add(aux);
        }
        if(graus==360)
        {
            faces.remove(0);
        }
        for(int k=0; k < lados; k++) 
        {
            for(int i=0; i < tam;i++)
            {   
                for(int j=0; j < 2;j++)
                {
                    Ponto p=new Ponto();
                    a2.get(i).pontos.get(j).setX(a2.get(i).pontos.get(j).getX() - c.getX());
                    a2.get(i).pontos.get(j).setZ(a2.get(i).pontos.get(j).getZ() - c.getZ());

                    p.setX(a2.get(i).pontos.get(j).getX());
                    p.setZ(a2.get(i).pontos.get(j).getZ());

                    a2.get(i).pontos.get(j).setX( +cos(angulo) * p.getX() + sin(angulo) * p.getZ());
                    a2.get(i).pontos.get(j).setZ( -sin(angulo) * p.getX() + cos(angulo) * p.getZ());

                    a2.get(i).pontos.get(j).setX(a2.get(i).pontos.get(j).getX() + c.getX());
                    a2.get(i).pontos.get(j).setZ(a2.get(i).pontos.get(j).getZ() + c.getZ()); 
                }
            }
            for(int i=0; i < tam;i++)
            {
                b = new ArrayList<>();
                if(i==tam-1)
                {
                    for(int j=0; j < 2;j++)
                    {
                        Ponto p1 = new Ponto(a1.get(i).pontos.get(j));
                        Ponto p2 = new Ponto(a2.get(i).pontos.get(j));
                        Aresta aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(i).pontos.get(j));
                        p2 = new Ponto(a2.get(0).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(0).pontos.get(j));
                        p2 = new Ponto(a1.get(0).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a1.get(0).pontos.get(j));
                        p2 = new Ponto(a1.get(i).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);
                    }
                }
                else
                {
                    for(int j=0; j < 2;j++)
                    {
                        Ponto p1 = new Ponto(a1.get(i).pontos.get(j));
                        Ponto p2 = new Ponto(a2.get(i).pontos.get(j));
                        Aresta aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(i).pontos.get(j));
                        p2 = new Ponto(a2.get(i+1).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(i+1).pontos.get(j));
                        p2 = new Ponto(a1.get(i+1).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a1.get(i+1).pontos.get(j));
                        p2 = new Ponto(a1.get(i).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);
                    }
                }
                Face f = new Face();
                f.setArestas(b);
                faces.add(f);
            }
            a1 = new ArrayList<>();
            for(int i=0; i < tam;i++)
            {
                Ponto p1 = new Ponto(a2.get(i).pontos.get(0));
                Ponto p2 = new Ponto(a2.get(i).pontos.get(1));
                Aresta aux = new Aresta(p1,p2);
                a1.add(aux);
            }
        }
        if(graus!=360)
        {
            Face f = new Face();
            f.setArestas(a2);
            faces.add(f);
        }
    }
    
    public void revolucionario3(Ponto c, int lados, double graus)
    {
        double angulo = toRadians(graus/lados);
        List<Aresta> a1 = new ArrayList<>();
        List<Aresta> a2 = new ArrayList<>();
        List<Aresta> b;
        
        centro.setZ(c.getZ());
        centro.setY(c.getY());
        int tam = arestas.size();
        for(int i=0; i < tam;i++)
        {
            Ponto p1 = new Ponto(arestas.get(i).pontos.get(0));
            Ponto p2 = new Ponto(arestas.get(i).pontos.get(1));
            Aresta aux = new Aresta(p1,p2);
            a1.add(aux);
            
            p1 = new Ponto(arestas.get(i).pontos.get(0));
            p2 = new Ponto(arestas.get(i).pontos.get(1));
            aux = new Aresta(p1,p2);
            a2.add(aux);
        }
        if(graus==360)
        {
            faces.remove(0);
        }
        for(int k=0; k < lados; k++) 
        {
            for(int i=0; i < tam;i++)
            {   
                for(int j=0; j < 2;j++)
                {
                    Ponto p=new Ponto();
                    a2.get(i).pontos.get(j).setZ(a2.get(i).pontos.get(j).getZ() - c.getZ());
                    a2.get(i).pontos.get(j).setY(a2.get(i).pontos.get(j).getY() - c.getY());

                    p.setZ(a2.get(i).pontos.get(j).getZ());
                    p.setY(a2.get(i).pontos.get(j).getY());

                    a2.get(i).pontos.get(j).setZ( +cos(angulo) * p.getZ() + sin(angulo) * p.getY());
                    a2.get(i).pontos.get(j).setY( -sin(angulo) * p.getZ() + cos(angulo) * p.getY());

                    a2.get(i).pontos.get(j).setZ(a2.get(i).pontos.get(j).getZ() + c.getZ());
                    a2.get(i).pontos.get(j).setY(a2.get(i).pontos.get(j).getY() + c.getY()); 
                }
            }
            for(int i=0; i < tam;i++)
            {
                b = new ArrayList<>();
                if(i==tam-1)
                {
                    for(int j=0; j < 2;j++)
                    {
                        Ponto p1 = new Ponto(a1.get(i).pontos.get(j));
                        Ponto p2 = new Ponto(a2.get(i).pontos.get(j));
                        Aresta aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(i).pontos.get(j));
                        p2 = new Ponto(a2.get(0).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(0).pontos.get(j));
                        p2 = new Ponto(a1.get(0).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a1.get(0).pontos.get(j));
                        p2 = new Ponto(a1.get(i).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);
                    }
                }
                else
                {
                    for(int j=0; j < 2;j++)
                    {
                        Ponto p1 = new Ponto(a1.get(i).pontos.get(j));
                        Ponto p2 = new Ponto(a2.get(i).pontos.get(j));
                        Aresta aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(i).pontos.get(j));
                        p2 = new Ponto(a2.get(i+1).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a2.get(i+1).pontos.get(j));
                        p2 = new Ponto(a1.get(i+1).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);

                        p1 = new Ponto(a1.get(i+1).pontos.get(j));
                        p2 = new Ponto(a1.get(i).pontos.get(j));
                        aux = new Aresta(p1,p2);
                        b.add(aux);
                    }
                }
                Face f = new Face();
                f.setArestas(b);
                faces.add(f);
            }
            a1 = new ArrayList<>();
            for(int i=0; i < tam;i++)
            {
                Ponto p1 = new Ponto(a2.get(i).pontos.get(0));
                Ponto p2 = new Ponto(a2.get(i).pontos.get(1));
                Aresta aux = new Aresta(p1,p2);
                a1.add(aux);
            }
        }
        if(graus!=360)
        {
            Face f = new Face();
            f.setArestas(a2);
            faces.add(f);
        }
    }

}