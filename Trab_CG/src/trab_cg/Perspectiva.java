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
public class Perspectiva implements Serializable
{
    private double[][] MultMatriz(double[][] M1, double[][] M2,int m,int n){
        double[][] M3 = new double [m][n];
        
        double soma = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    soma = soma + M1[i][k]*M2[k][j];
                }
                M3[i][j] = soma;
                soma = 0;
            }
        }
        
        return M3;
    }
    
    private double[][] MatSRUSRC(Ponto P, Ponto VRP)
    {
        double[][] matrizTrans = new double[4][4];
        double[][] R = new double[4][4];
        double norma,valor;
        double[][] matrizF;
        
        matrizTrans[0][0] = 1;
        matrizTrans[0][1] = 0;
        matrizTrans[0][2] = 0;
        matrizTrans[0][3] = -VRP.getX();
        
        matrizTrans[1][0] = 0;
        matrizTrans[1][1] = 1;
        matrizTrans[1][2] = 0;
        matrizTrans[1][3] = -VRP.getY();
        
        matrizTrans[2][0] = 0;
        matrizTrans[2][1] = 0;
        matrizTrans[2][2] = 1;
        matrizTrans[2][3] = -VRP.getZ();
        
        matrizTrans[3][0] = 0;
        matrizTrans[3][1] = 0;
        matrizTrans[3][2] = 0;
        matrizTrans[3][3] = 1;

        Ponto aux = new Ponto();
        
        Ponto N = new Ponto();
        aux.setX(((VRP.getX())-(P.getX())));
        aux.setY(((VRP.getY())-(P.getY())));
        aux.setZ(((VRP.getZ())-(P.getZ())));
  
        norma = Math.sqrt(((aux.getX()*aux.getX()) + (aux.getY()*aux.getY()) + (aux.getZ()*aux.getZ())));
        
        N.setX(aux.getX()/norma);
        N.setY(aux.getY()/norma);
        N.setZ(aux.getZ()/norma);
        
        Ponto V = new Ponto();
        Ponto Y = new Ponto();
        aux = new Ponto();
        
        Y.setX(0);
        Y.setY(1);
        Y.setZ(0);
        
        norma = (Y.getX() * N.getX()) + (Y.getY() * N.getY()) + (Y.getZ() * N.getZ());
        
        Ponto aux2 = new Ponto();
        aux2.setX(N.getX()*norma);
        aux2.setY(N.getY()*norma);
        aux2.setZ(N.getZ()*norma);
        
        aux.setX(0 - aux2.getX());
        aux.setY(1 - aux2.getY());
        aux.setZ(0 - aux2.getZ());
        
        norma = Math.sqrt((aux.getX()*aux.getX()) + (aux.getY()*aux.getY()) + (aux.getZ()*aux.getZ()));
        
        V.setX(aux.getX() / norma);
        V.setY(aux.getY() / norma);
        V.setZ(aux.getZ() / norma);
        
        Ponto U = new Ponto();
        
        
        valor = (V.getY() * N.getZ()) - (V.getZ() * N.getY());
        
        U.setX(valor);
        
        valor = (V.getZ() * N.getX()) - (N.getZ() * V.getX());
        
        U.setY(valor);  
        valor = (V.getX() * N.getY()) - (V.getY() * N.getX());
        U.setZ(valor);
        
        
        
        R[0][0] = U.getX();
        R[0][1] = U.getY();
        R[0][2] = U.getZ();
        R[0][3] = 0;
        
        R[1][0] = V.getX();
        R[1][1] = V.getY();
        R[1][2] = V.getZ();
        R[1][3] = 0;
        
        R[2][0] = N.getX();
        R[2][1] = N.getY();
        R[2][2] = N.getZ();
        R[2][3] = 0;
        
        R[3][0] = 0;
        R[3][1] = 0;
        R[3][2] = 0;
        R[3][3] = 1;
      
        matrizF = MultMatriz(R, matrizTrans, 4, 4);
        
        return matrizF;
    }
    
    private double[][] MPersp(Ponto P, Ponto VRP)
    {
        double[][] mSruSrc = new double [4][4];
        mSruSrc = MatSRUSRC(P, VRP);
        double Dp;
        double[][] aux = new double[4][4];
        double[][] aux2 = new double[4][1];
        double[][] MatrizPers = new double[4][4];
        
        aux2[0][0] = P.getX();
        aux2[1][0] = P.getY();
        aux2[2][0] = P.getZ();
        aux2[3][0] = 1;
        
        aux = MultMatriz(mSruSrc, aux2, 4, 1);
        
        Dp = -(aux[2][0]);
       
        MatrizPers[0][0] = 1;
        MatrizPers[0][1] = 0;
        MatrizPers[0][2] = 0;
        MatrizPers[0][3] = 0;
        
        MatrizPers[1][0] = 0;
        MatrizPers[1][1] = 1;
        MatrizPers[1][2] = 0;
        MatrizPers[1][3] = 0;
        
        MatrizPers[2][0] = 0;
        MatrizPers[2][1] = 0;
        MatrizPers[2][2] = 1;
        MatrizPers[2][3] = 0;
        
        MatrizPers[3][0] = 0;
        MatrizPers[3][1] = 0;
        MatrizPers[3][2] = -(1/Dp);
        MatrizPers[3][3] = 0;
        
        return MatrizPers;
    }
    
    
    
    
    private List<Aresta> Pontos(List<Aresta> arestas, Ponto P, Ponto VRP)
    {
        double[][] mSruSrc;
        int quantidadePontos;
        mSruSrc = MatSRUSRC(P, VRP);
        
        quantidadePontos = arestas.size()*2;
        
        double[][] matrizPontos = new double[4][quantidadePontos];
        int j= 0;
        for(int i = 0; i < arestas.size(); i++)
        {
            
                matrizPontos[0][j] = arestas.get(i).pontos.get(0).getX();
                matrizPontos[1][j] = arestas.get(i).pontos.get(0).getY();
                matrizPontos[2][j] = arestas.get(i).pontos.get(0).getZ();
                matrizPontos[3][j] = 1;
                j++;
            
            
            
                matrizPontos[0][j] = arestas.get(i).pontos.get(1).getX();
                matrizPontos[1][j] = arestas.get(i).pontos.get(1).getY();
                matrizPontos[2][j] = arestas.get(i).pontos.get(1).getZ();
                matrizPontos[3][j] = 1;
                j++;
            
           
        }
        
        double[][] matrizResultado = MultMatriz(mSruSrc, matrizPontos, 4, quantidadePontos);
        
        double[][] MPers = MPersp(P, VRP);
        
        double[][] matrizResultadoF = MultMatriz(MPers, matrizResultado, 4, quantidadePontos);
        
        for(int i = 0; i < quantidadePontos; i++)
        {
            matrizResultadoF[0][i] = (matrizResultadoF[0][i]) / (matrizResultadoF[3][i]);
            matrizResultadoF[1][i] = (matrizResultadoF[1][i]) / (matrizResultadoF[3][i]);
            matrizResultadoF[2][i] = (matrizResultadoF[2][i]) / (matrizResultadoF[3][i]);
            matrizResultadoF[3][i] = (matrizResultadoF[3][i]) / (matrizResultadoF[3][i]);
        }
        
        ArrayList<Aresta> novo = new ArrayList<>();
        Ponto p1;
        Ponto p2;
        Aresta a;
        for(int i = 0; i < quantidadePontos; i+=2)
        {
            p1 = new Ponto();
            p2 = new Ponto();
            p1.setX(matrizResultadoF[0][i]);
            p1.setY(matrizResultadoF[1][i]);
            p1.setZ(matrizResultadoF[2][i]);
            
            p2.setX(matrizResultadoF[0][i+1]);
            p2.setY(matrizResultadoF[1][i+1]);
            p2.setZ(matrizResultadoF[2][i+1]);
            a = new Aresta(p1, p2);
            novo.add(a);
        }
        return novo;
    }
    

    public List<Aresta> VisualizaPersp(List<Aresta> arestas, Ponto VRP) {
        Ponto P = new Ponto();

        P.setX(0);
        P.setY(0);
        P.setZ(0);

        List<Aresta> Final = new ArrayList<>();
        Final = Pontos(arestas, P, VRP);

        return Final;
    }
}