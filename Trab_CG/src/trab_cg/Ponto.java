//@Vitor Lisboa Nogueira @João Luiz Reolon

package trab_cg;

import java.io.Serializable;

public class Ponto implements Serializable{
    public double x,y,z;

    public Ponto() {
        this.x = 100;
        this.y = 100;
        this.z = 100;
    }
    public Ponto(Ponto p) {
        setX(p.getX());
        setY(p.getY());
        setZ(p.getZ());
    }
    

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }
    public void setZ(double z) {
        this.z = z;
    }
}