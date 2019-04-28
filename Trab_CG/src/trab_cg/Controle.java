//@Vitor Lisboa Nogueira @João Luiz Reolon

package trab_cg;
//Canvas1.setOnMousePressed(null);
//Canvas2.setOnMousePressed(null);
//Canvas3.setOnMousePressed(null);
//Canvas4.setOnMousePressed(null);
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Math.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;


public class Controle implements Initializable{
    
    @FXML 
    private ComboBox nLados;
    @FXML
    private Canvas canvas1; 
    @FXML
    private Canvas canvas2;  
    @FXML
    private Canvas canvas3;  
    @FXML
    private Canvas canvas4;  
    @FXML
    private GridPane gridCanvas;
    @FXML
    private ColumnConstraints canvasWidth1;

    @FXML
    private RowConstraints canvasHeight1;
     @FXML
    private ColumnConstraints canvasWidth2;

    @FXML
    private RowConstraints canvasHeight2;
    @FXML
    private ComboBox fullscrean;
    @FXML
    private  ColorPicker corb;
    @FXML
    private  ColorPicker corf;
    @FXML
    private RadioButton selFundo;
    public List<Poligono> figuras = new ArrayList<>();
    Poligono pol;
    //Regular polR;
    int nLados1;
    private double X = 0, Y = 0, XO = 0, YO = 0;
    private Ponto VRP = new Ponto();
    Ponto c=new Ponto();
    Ponto p=new Ponto();
    int indiceSel=-1;
    String canvasAtivo;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    
    boolean flagDime = true;
    @FXML
    private Button selectB;
    @FXML
    private Button delB;
    @FXML
    private Button transladeB;
    @FXML
    private Button shearB;
    @FXML
    private Button fundoB;
    @FXML
    private Button rotationB;
    @FXML
    private Button scaleB;
    @FXML
    private Button vivaLaRevolutionB;
    @FXML
    private Button bordaB;
    @FXML
    private Button freeB;
    @FXML
    private Button circleB;
    @FXML
    private Button polygonB;
    @FXML
    private Button freeFundoB;

    //double x1, y1, x2, y2;
    double graus;
    int segmentos;
    int cliques;
    GraphicsContext gc1;
    GraphicsContext gc2;
    GraphicsContext gc3;
    GraphicsContext gc4;
    
    boolean canvas1V=true, canvas2V=true, canvas3V=true, canvas4V=true;
    
    
    @FXML
    public void novo()
    {
        
        for(int i=figuras.size()-1;i>=0 ;i--)
        {
            figuras.remove(i);
        }
        tela();
            
    }
    @FXML
    public void helppoligonoregular()
    {
        Alert alert2 = new Alert(AlertType.CONFIRMATION);
        alert2.setTitle("Sucesso");
        alert2.setHeaderText(null);
        alert2.setContentText("Poligono Regular:\n1)Seleciona na caixa um numero de lados de 3 ate 20 lados\n2)Clique em algum ponto, e depois em outro.");
        alert2.showAndWait();
    }
    @FXML
    public void helppoligonoirregular()
    {
        Alert alert2 = new Alert(AlertType.CONFIRMATION);
        alert2.setTitle("Sucesso");
        alert2.setHeaderText(null);
        alert2.setContentText("Poligono Iregular:\n1)Selecione o botão de poligonos regulares\n2)Clique em algum ponto, e depois em outro, formando uma reta.\n3)Sinta-se a vontade para criar quantas retas quiser.\n4)Para fechar o poligono, um clique com o botão direito basta.");
        alert2.showAndWait();
    }
    @FXML
    public void helppoligonotranslacao()
    {
        Alert alert2 = new Alert(AlertType.CONFIRMATION);
        alert2.setTitle("Sucesso");
        alert2.setHeaderText(null);
        alert2.setContentText("Translação:\n1)Selecione o poligono\n2)Clique no botão de translação.\n3)Selecione outro ponto na tela.");
        alert2.showAndWait();
    }
    @FXML
    public void helppoligonorotacao()
    {
        Alert alert2 = new Alert(AlertType.CONFIRMATION);
        alert2.setTitle("Sucesso");
        alert2.setHeaderText(null);
        alert2.setContentText("Rotação:\n1)Selecione o poligono\n2)Clique no botão de rotação.\n3)De um clique na area de desenho\n4)Usa o scroll para rotacionar o objeto.");
        alert2.showAndWait();
    }
    @FXML
    public void helppoligonoescala()
    {
        Alert alert2 = new Alert(AlertType.CONFIRMATION);
        alert2.setTitle("Sucesso");
        alert2.setHeaderText(null);
        alert2.setContentText("Escala:\n1)Selecione o poligono\n2)Clique no botão de escala.\n3)De um clique na area de desenho\n4)Selecione a na caixa de seleção se voce deseja que seja proporcional, somente em x, ou somente em Y\n5)Usa o scroll para rotacionar o objeto.");
        alert2.showAndWait();
    }
    @FXML
    public void helppoligonocisalhamento()
    {
        Alert alert2 = new Alert(AlertType.CONFIRMATION);
        alert2.setTitle("Sucesso");
        alert2.setHeaderText(null);
        alert2.setContentText("Escala:\n1)Selecione o poligono\n2)Clique no botão de cisalhar.\n3)De um clique na area de desenho\n4)Selecione a na caixa de seleção se voce deseja que seja proporcional, somente em x, ou somente em Y\n5)Usa o scroll do mouse para cisalhar o objeto.");
        alert2.showAndWait();
    }
    public void helppoligonopreenchimento()
    {
        Alert alert2 = new Alert(AlertType.CONFIRMATION);
        alert2.setTitle("Sucesso");
        alert2.setHeaderText(null);
        alert2.setContentText("Escala:\n1)Selecione o poligono\n2)Escolha a cor desejada.\n3)Selecione o botão de preenchimento.");
        alert2.showAndWait();
    }
    @FXML
    private void save()
    {
        FileChooser salvar = new FileChooser();
        salvar.setTitle("Salve o Arquivo");
        salvar.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".out", "*.out"));
        File end = salvar.showSaveDialog(null);
        end.getPath();
        String endereco;
        endereco = end.getPath();
        System.out.println("end:" + endereco);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(endereco);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(figuras);
            Alert alert2 = new Alert(AlertType.CONFIRMATION);
            alert2.setTitle("Sucesso");
            alert2.setHeaderText(null);
            alert2.setContentText("Arquivo salvo :)");
            alert2.showAndWait();
            oos.flush();
        } 
        catch (IOException e) 
        {
        Alert alert6 = new Alert(AlertType.ERROR);
        alert6.setTitle("Error");
        alert6.setHeaderText(null);
        alert6.setContentText("Impossivel Salvar o arquivo");
        alert6.showAndWait();
        }
        refresh();
    }
    @FXML
    private void select()
    {
        indiceSel=-1;
        refresh();
        if (canvasAtivo == "Canvas3")
        {
            
            canvas3.setOnMouseClicked(this::sel3);
        }
        if (canvasAtivo == "Canvas2")
        {
            
            canvas2.setOnMouseClicked(this::sel2);
        }
        if (canvasAtivo == "Canvas1")
        {
            
            canvas1.setOnMouseClicked(this::sel1);
        }
   
    }
    @FXML
    private void circle()
    {
        pol = new Poligono();
       
        nLados1 = 60;
        cliques= 0;
        gc1 = canvas1.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc3 = canvas3.getGraphicsContext2D();
        gc4 = canvas4.getGraphicsContext2D();
        
        refresh();
        if (canvasAtivo == "Canvas3")
        {
            ;
            canvas3.setOnMouseClicked(this::poli3);
        }
        if (canvasAtivo == "Canvas2")
        {
            
            canvas2.setOnMouseClicked(this::poli2);
        }
        if (canvasAtivo == "Canvas1")
        {
            
            canvas1.setOnMouseClicked(this::poli1);
        }

        
    }
    @FXML
    private void load()
    {
        
        FileChooser open = new FileChooser();
        open.setTitle("Selecione o Arquivo");
        open.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".out", "*.out"));
        File end = open.showOpenDialog(null);
        end.getPath();
        String endereco;
        endereco = end.getPath();
        //System.out.println("endereco: " + endereco);
      
        System.out.println(figuras);
        figuras = null;
        System.out.println(figuras);
        try {
            FileInputStream in = new FileInputStream(endereco);
            ObjectInputStream ois = new ObjectInputStream(in);
            figuras = (List<Poligono>) (ois.readObject());
            Alert alert9 = new Alert(AlertType.INFORMATION);
            alert9.setTitle("Sobre Arquivo");
            alert9.setHeaderText(null);
            alert9.setContentText("Arquivo Aberto");
            alert9.showAndWait();
               
            tela();
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problem serializing: " + e);
             Alert alert3 = new Alert(AlertType.ERROR);
             alert3.setTitle("Isso é bem triste");
             alert3.setHeaderText(null);
             alert3.setContentText("Foi impossivel abrir o arquivo");
             alert3.showAndWait();
             

        }
        refresh();
    }
    @FXML
    private void del()
    {
            refresh();
            figuras.remove(indiceSel);
            indiceSel=-1;
            tela();
    }
    @FXML
    private void free()
    {
        refresh();
        
        pol = new Poligono();
        
        cliques = 0;
        gc1 = canvas1.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc3 = canvas3.getGraphicsContext2D();
        gc4 = canvas4.getGraphicsContext2D();
        pol.setCorFundo(corf.getValue().toString());
        pol.setCorBorda(corb.getValue().toString());
        if (canvasAtivo == "Canvas3")
        {
            
            canvas3.setOnMouseClicked(this::clicar3);
        }
        if (canvasAtivo == "Canvas2")
        {
            
            canvas2.setOnMouseClicked(this::clicar2);
        }
        if (canvasAtivo == "Canvas1")
        {
            
            canvas1.setOnMouseClicked(this::clicar1);
        }
    }
     private void clicar1(MouseEvent e)
     {
        detectaCanvas1();
        if(e.getButton()==MouseButton.SECONDARY){
       
            double xmax,xmin,ymax,ymin;
            xmax=pol.pontos.get(0).getX();
            xmin=pol.pontos.get(0).getX();
            ymax=pol.pontos.get(0).getY();
            ymin=pol.pontos.get(0).getY();
            for(int i = 1; i < pol.pontos.size(); i++)
            {
                if(pol.pontos.get(i).getX()>xmax)
                {
                    xmax=pol.pontos.get(i).getX();
                }
                if(pol.pontos.get(i).getX()<xmin)
                {
                    xmin=pol.pontos.get(i).getX();
                }
                if(pol.pontos.get(i).getY()>ymax)
                {
                    ymax=pol.pontos.get(i).getY();
                }
                if(pol.pontos.get(i).getY()<ymin)
                {
                    ymin=pol.pontos.get(i).getY();
                }
            }
            p = new Ponto();
            p.setX((xmax+xmin)/2);
            p.setY((ymax+ymin)/2);
            pol.setCentro(p);
            pol.pontos.add(pol.pontos.get(0));
            pol.setCorFundo(corf.getValue().toString());
            pol.setCorBorda(corb.getValue().toString());
            pol.setArestas();
            if(selFundo.isSelected())
            {
                pol.preenchido= true;
            }
            figuras.add(pol);
            tela();
            pol = new Poligono();
            refresh();
        }
        if(e.getButton()==MouseButton.PRIMARY){
            
            p = new Ponto();

            p.x=e.getX();
            p.y=e.getY();

            pol.pontos.add(p);
            pol.setArestas();

            tela();
            pol.draw(gc1, gc2, gc3, gc4);
            
        }     
    }
     
    private void clicar2(MouseEvent e)
    {
        detectaCanvas2();
        if(e.getButton()==MouseButton.SECONDARY){
       
            double xmax,xmin,zmax,zmin;
            xmax=pol.pontos.get(0).getX();
            xmin=pol.pontos.get(0).getX();
            zmax=pol.pontos.get(0).getZ();
            zmin=pol.pontos.get(0).getZ();
            for(int i = 1; i < pol.pontos.size(); i++)
            {
                if(pol.pontos.get(i).getX()>xmax)
                {
                    xmax=pol.pontos.get(i).getX();
                }
                if(pol.pontos.get(i).getX()<xmin)
                {
                    xmin=pol.pontos.get(i).getX();
                }
                if(pol.pontos.get(i).getZ()>zmax)
                {
                    zmax=pol.pontos.get(i).getZ();
                }
                if(pol.pontos.get(i).getZ()<zmin)
                {
                    zmin=pol.pontos.get(i).getZ();
                }
            }
            p = new Ponto();
            p.setX((xmax+xmin)/2);
            p.setZ((zmax+zmin)/2);
            pol.setCentro(p);
            pol.pontos.add(pol.pontos.get(0));
            pol.setCorFundo(corf.getValue().toString());
            pol.setCorBorda(corb.getValue().toString());
            pol.setArestas();
            if(selFundo.isSelected())
            {
                pol.preenchido= true;
            }
            figuras.add(pol);
            tela();
            pol = new Poligono();
            refresh();
            canvas2.setOnMouseClicked(null);
            canvas2.setOnMouseDragged(null);
            return;
        }
        if(e.getButton()==MouseButton.PRIMARY){
            p = new Ponto();

            p.x=e.getX();
            p.z=e.getY();

            pol.pontos.add(p);
            pol.setArestas();

            tela();
            pol.draw(gc1, gc2, gc3, gc4);
            
        }     
    }
     
    private void clicar3(MouseEvent e)
    {
        detectaCanvas3();
        if(e.getButton()==MouseButton.SECONDARY){
       
            double zmax,zmin,ymax,ymin;
            zmax=pol.pontos.get(0).getZ();
            zmin=pol.pontos.get(0).getZ();
            ymax=pol.pontos.get(0).getY();
            ymin=pol.pontos.get(0).getY();
            for(int i = 1; i < pol.pontos.size(); i++)
            {
                if(pol.pontos.get(i).getZ()>zmax)
                {
                    zmax=pol.pontos.get(i).getZ();
                }
                if(pol.pontos.get(i).getZ()<zmin)
                {
                    zmin=pol.pontos.get(i).getZ();
                }
                if(pol.pontos.get(i).getY()>ymax)
                {
                    ymax=pol.pontos.get(i).getY();
                }
                if(pol.pontos.get(i).getY()<ymin)
                {
                    ymin=pol.pontos.get(i).getY();
                }
            }
            p = new Ponto();
            p.setZ((zmax+zmin)/2);
            p.setY((ymax+ymin)/2);
            pol.setCentro(p);
            pol.pontos.add(pol.pontos.get(0));
            pol.setCorFundo(corf.getValue().toString());
            pol.setCorBorda(corb.getValue().toString());
            pol.setArestas();
            if(selFundo.isSelected())
            {
                pol.preenchido= true;
            }
            figuras.add(pol);
            tela();
            pol = new Poligono();
            refresh();
            return;
        }
        if(e.getButton()==MouseButton.PRIMARY){
            p = new Ponto();

            p.z=e.getX();
            p.y=e.getY();

            pol.pontos.add(p);
            pol.setArestas();

            tela();
            pol.draw(gc1, gc2, gc3, gc4);
            
        }     
    }
    
    @FXML
    private void polygon()
    {
        try
        {
            nLados1 = (int) nLados.getValue();
            
        }
        catch(Exception erro) 
        {
                nLados1=0;
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Numero de lados nao selecionado");
                alert.showAndWait();
        }
        
        pol = new Poligono();
        
        cliques= 0;
        gc1 = canvas1.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc3 = canvas3.getGraphicsContext2D();
        gc4 = canvas4.getGraphicsContext2D();
        pol.setCorFundo(corf.getValue().toString());
        pol.setCorBorda(corb.getValue().toString());
      
        refresh();
        
        if (canvasAtivo == "Canvas3")
        {
            
            canvas3.setOnMouseClicked(this::poli3);
        }
        if (canvasAtivo == "Canvas2")
        {
            
            canvas2.setOnMouseClicked(this::poli2);
        }
        if (canvasAtivo == "Canvas1")
        {
            
            canvas1.setOnMouseClicked(this::poli1);
        }
    }

    private void poli1(MouseEvent e)
    {
        detectaCanvas1();
        if(cliques==0)
        {
            c.setX(e.getX());
            c.setY(e.getY());
            pol.setCentro(c);
            c=new Ponto();
            cliques++;
        }
        else{
            double angulo;
            angulo = 360/nLados1;
            p = new Ponto();
            angulo = toRadians(angulo);
            pol.setRaio(e.getX(),e.getY(),pol.getCentro().getZ());
            p.x=pol.getCentro().getX();
            p.y=pol.getCentro().getY()-pol.getRaio();
            pol.pontos.add(p);
            
            for(int i=0;i<nLados1;i++)
            {
               
                Ponto p2= new Ponto();
                p.x-=pol.getCentro().getX();
                p.y-=pol.getCentro().getY();

                p2.setX(p.getX());
                p2.setY(p.getY());

                p2.setX(+cos(angulo) * p.getX() + sin(angulo) * p.getY());
                p2.setY(-sin(angulo) * p.getX() + cos(angulo) * p.getY());

                p.setX(p2.getX());
                p.setY(p2.getY());

                p.x+=pol.getCentro().getX();
                p.y+=pol.getCentro().getY();
                p2.x+=pol.getCentro().getX();
                p2.y+=pol.getCentro().getY();
                pol.pontos.add(p2);
            }
            pol.setCorFundo(corf.getValue().toString());
            pol.setCorBorda(corb.getValue().toString());
            pol.setArestas();
            figuras.add(pol);
            if(selFundo.isSelected())
            {
                pol.preenchido= true;
            }
            pol = new Poligono();
            cliques=0;
            indiceSel=-1;
            tela();
            refresh();
            return;
        }
   
    }
    private void poli2(MouseEvent e)
    {
        detectaCanvas2();
        if(cliques==0)
        {
            c.setX(e.getX());
            c.setZ(e.getY());
            pol.setCentro(c);
            c=new Ponto();
            cliques++;
        }
        else{
            double angulo;
            angulo = 360/nLados1;
            p = new Ponto();
            angulo = toRadians(angulo);
            pol.setRaio(e.getX(),pol.getCentro().getY(),e.getY());
            p.x=pol.getCentro().getX();
            p.z=pol.getCentro().getZ()-pol.getRaio();
            pol.pontos.add(p);
            
            for(int i=0;i<nLados1;i++)
            {
               
               Ponto p2= new Ponto();
                p.x-=pol.getCentro().getX();
                p.z-=pol.getCentro().getZ();

                p2.setX(p.getX());
                p2.setZ(p.getZ());

                p2.setX(+cos(angulo) * p.getX() + sin(angulo) * p.getZ());
                p2.setZ(-sin(angulo) * p.getX() + cos(angulo) * p.getZ());

                p.setX(p2.getX());
                p.setZ(p2.getZ());

                p.x+=pol.getCentro().getX();
                p.z+=pol.getCentro().getZ();
                p2.x+=pol.getCentro().getX();
                p2.z+=pol.getCentro().getZ();
                pol.pontos.add(p2);
                
                //Ponto p = new Ponto();
            }
            pol.setCorFundo(corf.getValue().toString());
            pol.setCorBorda(corb.getValue().toString());
            pol.setArestas();
            figuras.add(pol);
            if(selFundo.isSelected())
            {
                pol.preenchido= true;
            }
            pol = new Poligono();
            cliques=0;
            indiceSel=-1;
            tela();
            refresh();
            return;
        }
   
    }
    private void poli3(MouseEvent e)
    {
        detectaCanvas3();
        if(cliques==0)
        {
            c.setZ(e.getX());
            c.setY(e.getY());
            pol.setCentro(c);
            c=new Ponto();
            cliques++;
        }
        else{
            double angulo;
            angulo = 360/nLados1;
            p = new Ponto();
            angulo = toRadians(angulo);
            pol.setRaio(pol.getCentro().getX(),e.getY(),e.getX());
            p.z=pol.getCentro().getZ();
            p.y=pol.getCentro().getY()-pol.getRaio();
            pol.pontos.add(p);
            
            for(int i=0;i<nLados1;i++)
            {
               
               Ponto p2= new Ponto();
                p.z-=pol.getCentro().getZ();
                p.y-=pol.getCentro().getY();

                p2.setY(p.getY());
                p2.setZ(p.getZ());

                p2.setZ(+cos(angulo) * p.getZ() + sin(angulo) * p.getY());
                p2.setY(-sin(angulo) * p.getZ() + cos(angulo) * p.getY());

                p.setY(p2.getY());
                p.setZ(p2.getZ());

                p.y+=pol.getCentro().getY();
                p.z+=pol.getCentro().getZ();
                p2.y+=pol.getCentro().getY();
                p2.z+=pol.getCentro().getZ();
                pol.pontos.add(p2);
                
                //Ponto p = new Ponto();
            }
            pol.setCorFundo(corf.getValue().toString());
            pol.setCorBorda(corb.getValue().toString());
            pol.setArestas();
            figuras.add(pol);
            if(selFundo.isSelected())
            {
                pol.preenchido= true;
            }
            pol = new Poligono();
            cliques=0;
            indiceSel=-1;
            tela();
            refresh();
            return;
        }
    }
    private void sel1(MouseEvent e)
    {
        detectaCanvas1();
        boolean flag;
        p = new Ponto();
        p.setX(e.getX());
        p.setY(e.getY());
        flag =false;
        for (int i = figuras.size()-1; i >= 0; i--){
            
            figuras.get(i).sel=false;
            for(Face face : figuras.get(i).faces)
            {
                if(flag==true)
                    {
                        break;
                    }
                for (int j = 0; j <= face.arestas.size()-1; j++)
                {
                    double r,s,t,l,m1,m2,x,y;

                    r=(face.arestas.get(j).pontos.get(0).getY()-face.arestas.get(j).pontos.get(1).getY());
                    s=-(face.arestas.get(j).pontos.get(0).getX()-face.arestas.get(j).pontos.get(1).getX());
                    t=(face.arestas.get(j).pontos.get(0).getX()*face.arestas.get(j).pontos.get(1).getY())-(face.arestas.get(j).pontos.get(0).getY()*face.arestas.get(j).pontos.get(1).getX());
                    l=Math.abs((r*p.getX())+ s*p.getY()+t)/sqrt(pow(r, 2)+pow(s, 2));

                    m1=-r/s;
                    m2=-1/m1;
                    if(s==0)
                    {
                        if(r==0)
                        {
                            x=face.arestas.get(j).pontos.get(1).getX();
                            y=face.arestas.get(j).pontos.get(1).getY();
                        }
                        else
                        {
                            x=face.arestas.get(j).pontos.get(1).getX();
                            y=p.getY();
                        }
                    }
                    else
                    {
                        if(r==0)
                        {
                            y=face.arestas.get(j).pontos.get(1).getY();
                            x=p.getX();
                        }
                        else
                        {
                            x=((m1*face.arestas.get(j).pontos.get(1).getX() - face.arestas.get(j).pontos.get(1).getY()-m2*p.getX() + p.getY())/(m1-m2));
                            y=m2*x-m2*p.getX()+p.getY();
                        }
                    }
                    if(l<=5)
                    {
                        if (face.arestas.get(j).pontos.get(1).getX() >= x){
                            if (face.arestas.get(j).pontos.get(0).getX() <= x){
                                if(face.arestas.get(j).pontos.get(1).getY() >= y){
                                    if(face.arestas.get(j).pontos.get(0).getY() <= y){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;
                                    }
                                }
                                if(face.arestas.get(j).pontos.get(1).getY() <= y){
                                    if(face.arestas.get(j).pontos.get(0).getY() >= y){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;
                                    }
                                }
                            }    
                        }
                        if (face.arestas.get(j).pontos.get(1).getX() <= x){
                            if (face.arestas.get(j).pontos.get(0).getX() >= x){
                                if(face.arestas.get(j).pontos.get(1).getY()>= y){
                                    if(face.arestas.get(j).pontos.get(0).getY() <= y){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;  
                                    }
                                }
                                if(face.arestas.get(j).pontos.get(1).getY() <= y){
                                    if(face.arestas.get(j).pontos.get(0).getY() >= y){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;
                                    }
                                }
                            }    
                        }
                    }
                }
            }
        }
        tela();
        refresh();
    }
    
    private void sel2(MouseEvent e)
    {
        detectaCanvas2();
        boolean flag;
        p = new Ponto();
        p.setX(e.getX());
        p.setZ(e.getY());
        flag =false;
        for (int i = figuras.size()-1; i >= 0; i--){
            
            figuras.get(i).sel=false;
            
            for(Face face : figuras.get(i).faces)
            {
                if(flag==true)
                {
                    break;
                }
                for (int j = 0; j <= face.arestas.size()-1; j++)
                {
                    
                    double r,s,t,l,m1,m2,x,z;

                    r=(face.arestas.get(j).pontos.get(0).getZ()-face.arestas.get(j).pontos.get(1).getZ());
                    s=-(face.arestas.get(j).pontos.get(0).getX()-face.arestas.get(j).pontos.get(1).getX());
                    t=(face.arestas.get(j).pontos.get(0).getX()*face.arestas.get(j).pontos.get(1).getZ())-(face.arestas.get(j).pontos.get(0).getZ()*face.arestas.get(j).pontos.get(1).getX());
                    l=Math.abs((r*p.getX())+ s*p.getZ()+t)/sqrt(pow(r, 2)+pow(s, 2));

                    m1=-r/s;
                    m2=-1/m1;
                    if(s==0)
                    {
                        if(r==0)
                        {
                            x=face.arestas.get(j).pontos.get(1).getX();
                            z=face.arestas.get(j).pontos.get(1).getZ();
                        }
                        else
                        {
                            x=face.arestas.get(j).pontos.get(1).getX();
                            z=p.getZ();
                        }
                    }
                    else
                    {
                        if(r==0)
                        {
                            z=face.arestas.get(j).pontos.get(1).getZ();
                            x=p.getX();
                        }
                        else
                        {
                            x=((m1*face.arestas.get(j).pontos.get(1).getX() - face.arestas.get(j).pontos.get(1).getZ()-m2*p.getX() + p.getZ())/(m1-m2));
                            z=m2*x-m2*p.getX()+p.getZ();
                        }
                    }
                    if(l<=5)
                    {
                        if (face.arestas.get(j).pontos.get(1).getX() >= x){
                            if (face.arestas.get(j).pontos.get(0).getX() <= x){
                                if(face.arestas.get(j).pontos.get(1).getZ() >= z){
                                    if(face.arestas.get(j).pontos.get(0).getZ() <= z){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;
                                    }
                                }
                                if(face.arestas.get(j).pontos.get(1).getZ() <= z){
                                    if(face.arestas.get(j).pontos.get(0).getZ() >= z){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;
                                    }
                                }
                            }    
                        }
                        if (face.arestas.get(j).pontos.get(1).getX() <= x){
                            if (face.arestas.get(j).pontos.get(0).getX() >= x){
                                if(face.arestas.get(j).pontos.get(1).getZ()>= z){
                                    if(face.arestas.get(j).pontos.get(0).getZ() <= z){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;  
                                    }
                                }
                                if(face.arestas.get(j).pontos.get(1).getZ() <= z){
                                    if(face.arestas.get(j).pontos.get(0).getZ() >= z){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;
                                    }
                                }
                            }    
                        }
                    }
                }
            }
        }
        tela();
        refresh();
        return;
    }
    
    private void sel3(MouseEvent e)
    {
        detectaCanvas3();
        boolean flag;
        p = new Ponto();
        p.setZ(e.getX());
        p.setY(e.getY());
        flag =false;
        for (int i = figuras.size()-1; i >= 0; i--){
            
            figuras.get(i).sel=false;
            for(Face face : figuras.get(i).faces)
            {
                if(flag==true)
                {
                    break;
                }

                for (int j = 0; j <= face.arestas.size()-1; j++)
                {
                    double r,s,t,l,m1,m2,z,y;

                    r=(face.arestas.get(j).pontos.get(0).getY()-face.arestas.get(j).pontos.get(1).getY());
                    s=-(face.arestas.get(j).pontos.get(0).getZ()-face.arestas.get(j).pontos.get(1).getZ());
                    t=(face.arestas.get(j).pontos.get(0).getZ()*face.arestas.get(j).pontos.get(1).getY())-(face.arestas.get(j).pontos.get(0).getY()*face.arestas.get(j).pontos.get(1).getZ());
                    l=Math.abs((r*p.getZ())+ s*p.getY()+t)/sqrt(pow(r, 2)+pow(s, 2));

                    m1=-r/s;
                    m2=-1/m1;
                    if(s==0)
                    {
                        if(r==0)
                        {
                            z=face.arestas.get(j).pontos.get(1).getZ();
                            y=face.arestas.get(j).pontos.get(1).getY();
                        }
                        else
                        {
                            z=face.arestas.get(j).pontos.get(1).getZ();
                            y=p.getY();
                        }
                    }
                    else
                    {
                        if(r==0)
                        {
                            y=face.arestas.get(j).pontos.get(1).getZ();
                            z=p.getX();
                        }
                        else
                        {
                            z=((m1*face.arestas.get(j).pontos.get(1).getZ() - face.arestas.get(j).pontos.get(1).getY()-m2*p.getZ() + p.getY())/(m1-m2));
                            y=m2*z-m2*p.getZ()+p.getY();
                        }
                    }
                    if(l<=5)
                    {
                        if (face.arestas.get(j).pontos.get(1).getZ() >= z){
                            if (face.arestas.get(j).pontos.get(0).getZ() <= z){
                                if(face.arestas.get(j).pontos.get(1).getY() >= y){
                                    if(face.arestas.get(j).pontos.get(0).getY() <= y){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;
                                    }
                                }
                                if(face.arestas.get(j).pontos.get(1).getY() <= y){
                                    if(face.arestas.get(j).pontos.get(0).getY() >= y){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;
                                    }
                                }
                            }    
                        }
                        if (face.arestas.get(j).pontos.get(1).getZ() <= z){
                            if (face.arestas.get(j).pontos.get(0).getZ() >= z){
                                if(face.arestas.get(j).pontos.get(1).getY()>= y){
                                    if(face.arestas.get(j).pontos.get(0).getY() <= y){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;  
                                    }
                                }
                                if(face.arestas.get(j).pontos.get(1).getY() <= y){
                                    if(face.arestas.get(j).pontos.get(0).getY() >= y){

                                        flag=true;
                                        figuras.get(i).sel=true;
                                        indiceSel=i;
                                        break;
                                    }
                                }
                            }    
                        }
                    }
                }
            }
        }
        tela();
        refresh();
        return;
    }
    
    public void tela()
    {
        gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
        gc2.clearRect(0, 0, canvas2.getWidth(), canvas2.getHeight());
        gc3.clearRect(0, 0, canvas3.getWidth(), canvas3.getHeight());
        gc4.clearRect(0, 0, canvas4.getWidth(), canvas4.getHeight());
        
        for(Poligono pol : figuras)
        {
            pol.calcPespectiva(canvas1V, canvas2V, canvas3V, canvas4V, VRP);
            pol.pintar(gc1, gc2, gc3, gc4);
            pol.draw(gc1, gc2, gc3, gc4);
        }
    }
    @FXML
    public void translade()
    {
        gc1 = canvas1.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc3 = canvas3.getGraphicsContext2D();
        gc4 = canvas4.getGraphicsContext2D();
        refresh();
        if (canvasAtivo == "Canvas3")
        {
            canvas3.setOnMouseClicked(this::move3);
        }
        else if (canvasAtivo == "Canvas2")
        {
            canvas2.setOnMouseClicked(this::move2);
        }
        else if (canvasAtivo == "Canvas1")
        {
            canvas1.setOnMouseClicked(this::move1);
        }
        refresh();
    }
    
    void move1(MouseEvent e)
    {
        detectaCanvas1();
        c.setX(e.getX());
        c.setY(e.getY());
        for(int i=0; i <figuras.get(indiceSel).faces.size();i++){
            for(int j=0; j <figuras.get(indiceSel).faces.get(i).arestas.size();j++){
                for(int k=0; k < 2; k++)
                {
                    figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).x=figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX()-figuras.get(indiceSel).getCentro().getX()+c.getX();
                    figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).y=figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY()-figuras.get(indiceSel).getCentro().getY()+c.getY();

                }
            }
        }
        figuras.get(indiceSel).centro.setX(c.getX());
        figuras.get(indiceSel).centro.setY(c.getY());
        c=new Ponto();
        figuras.get(indiceSel).sel=false;
        indiceSel=-1;
        tela();
        refresh();
    }
    void move2(MouseEvent e)
    {
        detectaCanvas2();
        c.setX(e.getX());
        c.setZ(e.getY());
        for(int i=0; i <figuras.get(indiceSel).faces.size();i++){
            for(int j=0; j <figuras.get(indiceSel).faces.get(i).arestas.size();j++){
                for(int k=0; k < 2; k++)
                {
                    figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).x=figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX()-figuras.get(indiceSel).getCentro().getX()+c.getX();
                    figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).z=figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ()-figuras.get(indiceSel).getCentro().getZ()+c.getZ();

                }
            }
        }
        figuras.get(indiceSel).centro.setX(c.getX());
        figuras.get(indiceSel).centro.setZ(c.getZ());
        c=new Ponto();
        figuras.get(indiceSel).sel=false;
        indiceSel=-1;
        tela();
        refresh();
    }
      
    void move3(MouseEvent e)
    {
        detectaCanvas3();
        c.setZ(e.getX());
        c.setY(e.getY());
        for(int i=0; i <figuras.get(indiceSel).faces.size();i++){
            for(int j=0; j <figuras.get(indiceSel).faces.get(i).arestas.size();j++){
                for(int k=0; k < 2; k++)
                {
                    figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).z=figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ()-figuras.get(indiceSel).getCentro().getZ()+c.getZ();
                    figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).y=figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY()-figuras.get(indiceSel).getCentro().getY()+c.getY();

                }
            }
        }
        figuras.get(indiceSel).centro.setZ(c.getZ());
        figuras.get(indiceSel).centro.setY(c.getY());
        c=new Ponto();
        figuras.get(indiceSel).sel=false;
        indiceSel=-1;
        tela();
        refresh();
    }
        
    @FXML
    public void rotation ()
    {

        gc1 = canvas1.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc3 = canvas3.getGraphicsContext2D();
        gc4 = canvas4.getGraphicsContext2D();
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Rotação");
        alert.setHeaderText(null);
        alert.setContentText("1)Clique na area de desenho\n2)Utilize o scroll do mouse para rotacionar o objeto");
        alert.showAndWait();
        refresh();
        if (canvasAtivo == "Canvas3")
        {
            canvas3.setOnMouseClicked(this::girar3);
        }
        else if (canvasAtivo == "Canvas2")
        {
            canvas2.setOnMouseClicked(this::girar2);
        }
        else if (canvasAtivo == "Canvas1")
        {
            canvas1.setOnMouseClicked(this::girar1);
        }
        refresh();
    }
    
    public void girar1(MouseEvent e)
    {
       detectaCanvas1();
       refresh();
       canvas1.setOnScroll(ev -> 
       {
           double angulo=0, rad = 0;
           p= new Ponto();
        
            if (ev.getDeltaY() > 0) 
            {
                angulo+=6; 
            } 
            else 
            {
                angulo-=6;
            }
            rad = toRadians(angulo);
            for(int i=0; i < figuras.get(indiceSel).faces.size();i++)
            {
                for(int j=0; j < figuras.get(indiceSel).faces.get(i).arestas.size();j++)
                {
                    for(int k=0; k < 2; k++)
                    {
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() - figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() - figuras.get(indiceSel).getCentro().getY());

                        p.setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX());
                        p.setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY());

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX( +cos(rad) * p.getX() + sin(rad) * p.getY());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY( -sin(rad) * p.getX() + cos(rad) * p.getY());

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() + figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() + figuras.get(indiceSel).getCentro().getY());
                    }
                } 
            }
            tela();
        });
    }
    
    public void girar2(MouseEvent e)
    {
       detectaCanvas2();
       refresh();
       canvas2.setOnScroll(ev -> 
       {
           double angulo=0, rad = 0;
           p= new Ponto();
        
            if (ev.getDeltaY() > 0) 
            {
                angulo+=6; 
            } 
            else 
            {
                angulo-=6;
            }
            rad = toRadians(angulo);
            for(int i=0; i < figuras.get(indiceSel).faces.size();i++)
            {
                for(int j=0; j < figuras.get(indiceSel).faces.get(i).arestas.size();j++)
                {
                    for(int k=0; k < 2; k++)
                    {
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() - figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() - figuras.get(indiceSel).getCentro().getZ());

                        p.setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX());
                        p.setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ());

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX( +cos(rad) * p.getX() + sin(rad) * p.getZ());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ( -sin(rad) * p.getX() + cos(rad) * p.getZ());

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() + figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() + figuras.get(indiceSel).getCentro().getZ());
                    }
                } 
            }
            tela();
        }); 
    }
    
    public void girar3(MouseEvent e)
    {
       detectaCanvas3();
       refresh();
       canvas3.setOnScroll(ev -> 
       {
           double angulo=0, rad = 0;
           p= new Ponto();
        
            if (ev.getDeltaY() > 0) 
            {
                angulo+=6; 
            } 
            else 
            {
                angulo-=6;
            }
            rad = toRadians(angulo);
            for(int i=0; i < figuras.get(indiceSel).faces.size();i++)
            {
                for(int j=0; j < figuras.get(indiceSel).faces.get(i).arestas.size();j++)
                {
                    for(int k=0; k < 2; k++)
                    {
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() - figuras.get(indiceSel).getCentro().getZ());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() - figuras.get(indiceSel).getCentro().getY());

                        p.setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ());
                        p.setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY());

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ( +cos(rad) * p.getZ() + sin(rad) * p.getY());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY( -sin(rad) * p.getZ() + cos(rad) * p.getY());

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() + figuras.get(indiceSel).getCentro().getZ());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() + figuras.get(indiceSel).getCentro().getY());
                    }
                } 
            }
            tela();
        });  
    }
    @FXML
    public void scale()
    {
        gc1 = canvas1.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc3 = canvas3.getGraphicsContext2D();
        gc4 = canvas4.getGraphicsContext2D();
        refresh();
        if (canvasAtivo == "Canvas3")
        {           
            desenhaContorno3();
            canvas3.setOnMouseDragged(this::escala3);
        }
        else if (canvasAtivo == "Canvas2")
        {
            desenhaContorno2();
            canvas2.setOnMouseDragged(this::escala2);
        }
        else if (canvasAtivo == "Canvas1")
        {
            desenhaContorno1();
            canvas1.setOnMouseDragged(this::escala1);
        }
        refresh();
    }
  
    public void escala1(MouseEvent e)
    {
        detectaCanvas1();
        desenhaContorno1();
        if(cliques==0)
        {
            c=new Ponto();
            c.setX(e.getX());
            c.setY(e.getY());
            
            cliques++;
        }
        else 
        {
            p=new Ponto();
            p.setX(e.getX());
            p.setY(e.getY());
            double tamanhox=1;
            double tamanhoy=1;
            int pontoCont= pontoControle1(p);
            
            if(pontoCont==1)
            {
                if(p.getY()<c.getY())
                {
                    tamanhox= 1;
                    tamanhoy= 1.05;
                }
                else
                {
                    tamanhox= 1;
                    tamanhoy= 0.95;
                }
                
            }
            else if(pontoCont==2)
            {
                if(p.getX()<c.getX())
                {
                    tamanhox= 0.95;
                    tamanhoy= 1;
                }
                else
                {
                    tamanhox= 1.05;
                    tamanhoy= 1;
                }
               
            }
            else if(pontoCont==3)
            {
                if(p.getY()>c.getY())
                {
                    tamanhox= 1;
                    tamanhoy= 1.05;
                }
                else
                {
                    tamanhox= 1;
                    tamanhoy= 0.95;
                }
            }
            else if(pontoCont==4)
            {
                if(p.getX()>c.getX())
                {
                    tamanhox= 0.95;
                    tamanhoy= 1;
                }
                else
                {
                    tamanhox= 1.05;
                    tamanhoy= 1;
                }
            }
            else if(pontoCont==5)
            {
                
                    tamanhox= 1.05;
                    tamanhoy= 1.05;
                    
            }
            else if(pontoCont==6)
            {
                
                    tamanhox= 0.95;
                    tamanhoy= 0.95;

            }
            else if(pontoCont==7)
            {
                tamanhox= 0.95;
                tamanhoy= 0.95;
            }
            else if(pontoCont==8)
            {
                tamanhox= 1.05;
                tamanhoy= 1.05;
            }
            for(int i=0; i < figuras.get(indiceSel).faces.size();i++)
            {
                for(int j=0; j < figuras.get(indiceSel).faces.get(i).arestas.size();j++)
                {
                    for(int k = 0; k < 2; k++)
                    {

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() - figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() - figuras.get(indiceSel).getCentro().getY());
                        
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() * tamanhox);
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() * tamanhoy);

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() + figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() + figuras.get(indiceSel).getCentro().getY());

                    }
                }
            }
            cliques++;
            c=p;
        }
        tela();
        desenhaContorno1();
    }
    
    public void escala2(MouseEvent e)
    {
        detectaCanvas2();
        desenhaContorno2();
        if(cliques==0)
        {
            c=new Ponto();
            c.setX(e.getX());
            c.setZ(e.getY());
            
            cliques++;
        }
        else 
        {
            p=new Ponto();
            p.setX(e.getX());
            p.setZ(e.getY());
            double tamanhox=1;
            double tamanhoy=1;
            int pontoCont= pontoControle2(p);
            
            if(pontoCont==1)
            {
                if(p.getZ()<c.getZ())
                {
                    tamanhox= 1;
                    tamanhoy= 1.05;
                }
                else
                {
                    tamanhox= 1;
                    tamanhoy= 0.95;
                }
                
            }
            else if(pontoCont==2)
            {
                if(p.getX()<c.getX())
                {
                    tamanhox= 0.95;
                    tamanhoy= 1;
                }
                else
                {
                    tamanhox= 1.05;
                    tamanhoy= 1;
                }
               
            }
            else if(pontoCont==3)
            {
                if(p.getZ()>c.getZ())
                {
                    tamanhox= 1;
                    tamanhoy= 1.05;
                }
                else
                {
                    tamanhox= 1;
                    tamanhoy= 0.95;
                }
            }
            else if(pontoCont==4)
            {
                if(p.getX()>c.getX())
                {
                    tamanhox= 0.95;
                    tamanhoy= 1;
                }
                else
                {
                    tamanhox= 1.05;
                    tamanhoy= 1;
                }
            }
            else if(pontoCont==5)
            {
                
                    tamanhox= 1.05;
                    tamanhoy= 1.05;
                    
            }
            else if(pontoCont==6)
            {
                
                    tamanhox= 0.95;
                    tamanhoy= 0.95;

            }
            else if(pontoCont==7)
            {
                tamanhox= 0.95;
                tamanhoy= 0.95;
            }
            else if(pontoCont==8)
            {
                tamanhox= 1.05;
                tamanhoy= 1.05;
            }
            for(int i=0; i < figuras.get(indiceSel).faces.size();i++)
            {
                for(int j=0; j < figuras.get(indiceSel).faces.get(i).arestas.size();j++)
                {
                    for(int k = 0; k < 2; k++)
                    {

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() - figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() - figuras.get(indiceSel).getCentro().getZ());
                        
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() * tamanhox);
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() * tamanhoy);

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() + figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() + figuras.get(indiceSel).getCentro().getZ());

                    }
                }
            }
            cliques++;
            c=p;
        }
        tela();
        desenhaContorno2();
    }
    
    public void escala3(MouseEvent e)
    {
        detectaCanvas3();
        desenhaContorno3();
        if(cliques==0)
        {
            c=new Ponto();
            c.setZ(e.getX());
            c.setY(e.getY());
            
            cliques++;
        }
        else 
        {
            p=new Ponto();
            p.setZ(e.getX());
            p.setY(e.getY());
            double tamanhox=1;
            double tamanhoy=1;
            int pontoCont= pontoControle3(p);
            
            if(pontoCont==1)
            {
                if(p.getY()<c.getY())
                {
                    tamanhox= 1;
                    tamanhoy= 1.05;
                }
                else
                {
                    tamanhox= 1;
                    tamanhoy= 0.95;
                }
                
            }
            else if(pontoCont==2)
            {
                if(p.getZ()<c.getZ())
                {
                    tamanhox= 0.95;
                    tamanhoy= 1;
                }
                else
                {
                    tamanhox= 1.05;
                    tamanhoy= 1;
                }
               
            }
            else if(pontoCont==3)
            {
                if(p.getY()>c.getY())
                {
                    tamanhox= 1;
                    tamanhoy= 1.05;
                }
                else
                {
                    tamanhox= 1;
                    tamanhoy= 0.95;
                }
            }
            else if(pontoCont==4)
            {
                if(p.getZ()>c.getZ())
                {
                    tamanhox= 0.95;
                    tamanhoy= 1;
                }
                else
                {
                    tamanhox= 1.05;
                    tamanhoy= 1;
                }
            }
            else if(pontoCont==5)
            {
                
                    tamanhox= 1.05;
                    tamanhoy= 1.05;
                    
            }
            else if(pontoCont==6)
            {
                
                    tamanhox= 0.95;
                    tamanhoy= 0.95;

            }
            else if(pontoCont==7)
            {
                tamanhox= 0.95;
                tamanhoy= 0.95;
            }
            else if(pontoCont==8)
            {
                tamanhox= 1.05;
                tamanhoy= 1.05;
            }
            for(int i=0; i < figuras.get(indiceSel).faces.size();i++)
            {
                for(int j=0; j < figuras.get(indiceSel).faces.get(i).arestas.size();j++)
                {
                    for(int k = 0; k < 2; k++)
                    {

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() - figuras.get(indiceSel).getCentro().getZ());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() - figuras.get(indiceSel).getCentro().getY());
                        
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() * tamanhox);
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() * tamanhoy);

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() + figuras.get(indiceSel).getCentro().getZ());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() + figuras.get(indiceSel).getCentro().getY());

                    }
                }
            }
            cliques++;
            c=p;
        }
        tela();
        desenhaContorno3();
    }
    @FXML
    public void shear()
    {
        gc1 = canvas1.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc3 = canvas3.getGraphicsContext2D();
        gc4 = canvas4.getGraphicsContext2D();
        refresh();
        if (canvasAtivo == "Canvas3")
        {           
            desenhaContorno3();
            canvas3.setOnMouseDragged(this::cisalhar3);
        }
        else if (canvasAtivo == "Canvas2")
        {
            desenhaContorno2();
            canvas2.setOnMouseDragged(this::cisalhar2);
        }
        else if (canvasAtivo == "Canvas1")
        {
            desenhaContorno1();
            canvas1.setOnMouseDragged(this::cisalhar1);
        }
        refresh();
    }
  
    public void cisalhar1(MouseEvent e)
    {
        detectaCanvas1();
        desenhaContorno1();
        if(cliques==0)
        {
            c=new Ponto();
            c.setX(e.getX());
            c.setY(e.getY());
            
            cliques++;
        }
        else 
        {
            p=new Ponto();
            p.setX(e.getX());
            p.setY(e.getY());
            double tamanhox=0;
            double tamanhoy=0;
            int pontoCont= pontoControle1(p);
            
            if(pontoCont==1)
            {
                if(p.getY()<c.getY())
                {
                    tamanhox= 0;
                    tamanhoy= 0.05;
                }
                else
                {
                    tamanhox= 0;
                    tamanhoy= -0.05;
                }
                
            }
            else if(pontoCont==2)
            {
                if(p.getX()<c.getX())
                {
                    tamanhox= -0.05;
                    tamanhoy= 0;
                }
                else
                {
                    tamanhox= 0.05;
                    tamanhoy= 0;
                }
               
            }
            else if(pontoCont==3)
            {
                if(p.getY()<c.getY())
                {
                    tamanhox= 0;
                    tamanhoy= 0.05;
                }
                else
                {
                    tamanhox= 0;
                    tamanhoy= -0.05;
                }
            }
            else if(pontoCont==4)
            {
                if(p.getX()<c.getX())
                {
                    tamanhox= -0.05;
                    tamanhoy= 0;
                }
                else
                {
                    tamanhox= 0.05;
                    tamanhoy= 0;
                }
            }
            else if(pontoCont==5)
            {
                
                tamanhox= 0.05;
                tamanhoy= 0.05;
                    
            }
            else if(pontoCont==6)
            {
                
                tamanhox= -0.05;
                tamanhoy= -0.05;

            }
            else if(pontoCont==7)
            {
                tamanhox= -0.05;
                tamanhoy= -0.05;
            }
            else if(pontoCont==8)
            {
                tamanhox= 0.05;
                tamanhoy= 0.05;
            }
            for(int i=0; i < figuras.get(indiceSel).faces.size();i++)
            {
                for(int j=0; j < figuras.get(indiceSel).faces.get(i).arestas.size();j++)
                {
                    for(int k = 0; k < 2; k++)
                    {
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() - figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() - figuras.get(indiceSel).getCentro().getY());

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() + figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() * tamanhox);
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() + figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() * tamanhoy);

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() + figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() + figuras.get(indiceSel).getCentro().getY());     
                    }
                }
            }
            c=p;
            cliques++;
        }
       
        tela();
        desenhaContorno1();
        
    }
    
     public void cisalhar2(MouseEvent e)
    {
        detectaCanvas2();
        desenhaContorno2();
        if(cliques==0)
        {
            c=new Ponto();
            c.setX(e.getX());
            c.setZ(e.getY());
            
            cliques++;
        }
        else 
        {
            p=new Ponto();
            p.setX(e.getX());
            p.setZ(e.getY());
            double tamanhox=0;
            double tamanhoy=0;
            int pontoCont= pontoControle2(p);
            
            if(pontoCont==1)
            {
                if(p.getZ()<c.getZ())
                {
                    tamanhox= 0;
                    tamanhoy= 0.05;
                }
                else
                {
                    tamanhox= 0;
                    tamanhoy= -0.05;
                }
                
            }
            else if(pontoCont==2)
            {
                if(p.getX()<c.getX())
                {
                    tamanhox= -0.05;
                    tamanhoy= 0;
                }
                else
                {
                    tamanhox= 0.05;
                    tamanhoy= 0;
                }
               
            }
            else if(pontoCont==3)
            {
                if(p.getZ()<c.getZ())
                {
                    tamanhox= 0;
                    tamanhoy= 0.05;
                }
                else
                {
                    tamanhox= 0;
                    tamanhoy= -0.05;
                }
            }
            else if(pontoCont==4)
            {
                if(p.getX()<c.getX())
                {
                    tamanhox= -0.05;
                    tamanhoy= 0;
                }
                else
                {
                    tamanhox= 0.05;
                    tamanhoy= 0;
                }
            }
            else if(pontoCont==5)
            {
                
                tamanhox= 0.05;
                tamanhoy= 0.05;
                    
            }
            else if(pontoCont==6)
            {
                
                tamanhox= -0.05;
                tamanhoy= -0.05;

            }
            else if(pontoCont==7)
            {
                tamanhox= -0.05;
                tamanhoy= -0.05;
            }
            else if(pontoCont==8)
            {
                tamanhox= 0.05;
                tamanhoy= 0.05;
            }
            for(int i=0; i < figuras.get(indiceSel).faces.size();i++)
            {
                for(int j=0; j < figuras.get(indiceSel).faces.get(i).arestas.size();j++)
                {
                    for(int k = 0; k < 2; k++)
                    {
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() - figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() - figuras.get(indiceSel).getCentro().getZ());

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() + figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() * tamanhox);
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() + figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() * tamanhoy);

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setX(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() + figuras.get(indiceSel).getCentro().getX());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() + figuras.get(indiceSel).getCentro().getZ());     
                    }
                }
            }
            c=p;
            cliques++;
        }
       
        tela();
        desenhaContorno2();
        
    }
     
    public void cisalhar3(MouseEvent e)
    {
        detectaCanvas3();
        desenhaContorno3();
        if(cliques==0)
        {
            c=new Ponto();
            c.setZ(e.getX());
            c.setY(e.getY());
            
            cliques++;
        }
        else 
        {
            p=new Ponto();
            p.setZ(e.getX());
            p.setY(e.getY());
            double tamanhox=0;
            double tamanhoy=0;
            int pontoCont= pontoControle3(p);
            
            if(pontoCont==1)
            {
                if(p.getY()<c.getY())
                {
                    tamanhox= 0;
                    tamanhoy= 0.05;
                }
                else
                {
                    tamanhox= 0;
                    tamanhoy= -0.05;
                }
                
            }
            else if(pontoCont==2)
            {
                if(p.getZ()<c.getZ())
                {
                    tamanhox= -0.05;
                    tamanhoy= 0;
                }
                else
                {
                    tamanhox= 0.05;
                    tamanhoy= 0;
                }
               
            }
            else if(pontoCont==3)
            {
                if(p.getY()<c.getY())
                {
                    tamanhox= 0;
                    tamanhoy= 0.05;
                }
                else
                {
                    tamanhox= 0;
                    tamanhoy= -0.05;
                }
            }
            else if(pontoCont==4)
            {
                if(p.getZ()<c.getZ())
                {
                    tamanhox= -0.05;
                    tamanhoy= 0;
                }
                else
                {
                    tamanhox= 0.05;
                    tamanhoy= 0;
                }
            }
            else if(pontoCont==5)
            {
                
                tamanhox= 0.05;
                tamanhoy= 0.05;
                    
            }
            else if(pontoCont==6)
            {
                
                tamanhox= -0.05;
                tamanhoy= -0.05;

            }
            else if(pontoCont==7)
            {
                tamanhox= -0.05;
                tamanhoy= -0.05;
            }
            else if(pontoCont==8)
            {
                tamanhox= 0.05;
                tamanhoy= 0.05;
            }
            for(int i=0; i < figuras.get(indiceSel).faces.size();i++)
            {
                for(int j=0; j < figuras.get(indiceSel).faces.get(i).arestas.size();j++)
                {
                    for(int k = 0; k < 2; k++)
                    {
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() - figuras.get(indiceSel).getCentro().getZ());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() - figuras.get(indiceSel).getCentro().getY());

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() + figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() * tamanhox);
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() + figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() * tamanhoy);

                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setZ(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() + figuras.get(indiceSel).getCentro().getZ());
                        figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).setY(figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() + figuras.get(indiceSel).getCentro().getY());     
                    }
                }
            }
            c=p;
            cliques++;
        }
       
        tela();
        desenhaContorno1();
        
    }
            
    public void borda()
    {
        
        figuras.get(indiceSel).setCorBorda(corb.getValue().toString());
        tela();
    }
    public void fundo()
    {
        figuras.get(indiceSel).preenchido=true;
        figuras.get(indiceSel).setCorFundo(corf.getValue().toString());
        tela();
    }
    public void freeFundo()
    {
        figuras.get(indiceSel).preenchido=false;
        tela();
    }

    public void fullScrean()
    {
        
        if(fullscrean.getValue().equals("Frontal"))
        {
            if(flagDime)
            {
                for(int i = 0; i < figuras.size(); i++)
                {
                    for(int j = 0; j < figuras.get(i).faces.size(); j++)
                    {
                        for(int k = 0; k < figuras.get(i).faces.get(j).arestas.size(); k++)
                        {
                            for(int l = 0; l < 2; l++)
                            {
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setX(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getX()*2);
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setY(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getY()*2);
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setZ(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getZ()*2);
                            }
                        }
                    }
                    figuras.get(i).getCentro().setX(figuras.get(i).getCentro().getX()*2);
                    figuras.get(i).getCentro().setY(figuras.get(i).getCentro().getY()*2);
                    figuras.get(i).getCentro().setZ(figuras.get(i).getCentro().getZ()*2);
                }
                flagDime = false;
            }
            canvas1.setWidth(1260);
            canvas1.setHeight(560);
            canvas1.setVisible(true);
            label1.setVisible(true);
            canvas1V = true;
            canvasHeight1.fillHeightProperty();
            canvasWidth2.fillWidthProperty();
            canvas2.setVisible(false);
            label2.setVisible(false);
            canvas2V = false;
            canvas3.setVisible(false);
            label3.setVisible(false);
            canvas3V = false;
            canvas4.setVisible(false);
            label4.setVisible(false);
            canvas4V = false;
            gridCanvas.setGridLinesVisible(false);
            tela();
        }
        else if(fullscrean.getValue().equals("Lateral"))
        {
            if(flagDime)
            {
                for(int i = 0; i < figuras.size(); i++)
                {
                    for(int j = 0; j < figuras.get(i).faces.size(); j++)
                    {
                        for(int k = 0; k < figuras.get(i).faces.get(j).arestas.size(); k++)
                        {
                            for(int l = 0; l < 2; l++)
                            {
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setX(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getX()*2);
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setY(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getY()*2);
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setZ(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getZ()*2);
                            }
                        }
                    }
                    figuras.get(i).getCentro().setX(figuras.get(i).getCentro().getX()*2);
                    figuras.get(i).getCentro().setY(figuras.get(i).getCentro().getY()*2);
                    figuras.get(i).getCentro().setZ(figuras.get(i).getCentro().getZ()*2);
                }
                flagDime = false;
            }
            canvas3.setWidth(1260);
            canvas3.setHeight(560);
            canvas3.setVisible(true);
            label3.setVisible(true);
            canvas3V = true;
            canvasHeight2.fillHeightProperty();
            canvasWidth1.fillWidthProperty();
            canvas2.setVisible(false);
            label2.setVisible(false);
            canvas2V = false;
            canvas1.setVisible(false);
            label1.setVisible(false);
            canvas1V = false;
            canvas4.setVisible(false);
            label4.setVisible(false);
            canvas4V = false;
            gridCanvas.setGridLinesVisible(false);
            tela();
        }
        else if(fullscrean.getValue().equals("Aéreo"))
        {
            if(flagDime)
            {
                for(int i = 0; i < figuras.size(); i++)
                {
                    for(int j = 0; j < figuras.get(i).faces.size(); j++)
                    {
                        for(int k = 0; k < figuras.get(i).faces.get(j).arestas.size(); k++)
                        {
                            for(int l = 0; l < 2; l++)
                            {
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setX(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getX()*2);
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setY(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getY()*2);
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setZ(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getZ()*2);
                            }
                        }
                    }
                    figuras.get(i).getCentro().setX(figuras.get(i).getCentro().getX()*2);
                    figuras.get(i).getCentro().setY(figuras.get(i).getCentro().getY()*2);
                    figuras.get(i).getCentro().setZ(figuras.get(i).getCentro().getZ()*2);
                }
                flagDime = false;
            }
            canvas2.setWidth(1260);
            canvas2.setHeight(560);
            canvas2.setVisible(true);
            label2.setVisible(true);
            canvas2V = true;
            canvasHeight1.fillHeightProperty();
            canvasWidth2.fillWidthProperty();
            canvas1.setVisible(false);
            label1.setVisible(false);
            canvas1V = false;
            canvas3.setVisible(false);
            label3.setVisible(false);
            canvas3V = false;
            canvas4.setVisible(false);
            label4.setVisible(false);
            canvas4V = false;
            gridCanvas.setGridLinesVisible(false);
            tela();
        }
        else if(fullscrean.getValue().equals("Perspectiva"))
        {
            if(flagDime)
            {
                for(int i = 0; i < figuras.size(); i++)
                {
                    for(int j = 0; j < figuras.get(i).faces.size(); j++)
                    {
                        for(int k = 0; k < figuras.get(i).faces.get(j).arestas.size(); k++)
                        {
                            for(int l = 0; l < 2; l++)
                            {
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setX(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getX()*2);
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setY(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getY()*2);
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setZ(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getZ()*2);
                            }
                        }
                    }
                    figuras.get(i).getCentro().setX(figuras.get(i).getCentro().getX()*2);
                    figuras.get(i).getCentro().setY(figuras.get(i).getCentro().getY()*2);
                    figuras.get(i).getCentro().setZ(figuras.get(i).getCentro().getZ()*2);
                }
                flagDime = false;
            }
            
            canvas4.setWidth(1260);
            canvas4.setHeight(560);
            canvas4.setVisible(true);
            label4.setVisible(true);
            canvas4V = true;
            canvasHeight2.fillHeightProperty();
            canvasWidth2.fillWidthProperty();
            canvas2.setVisible(false);
            label2.setVisible(false);
            canvas2V = false;
            canvas3.setVisible(false);
            label3.setVisible(false);
            canvas3V = false;
            canvas1.setVisible(false);
            label1.setVisible(false);
            canvas1V = false;
            gridCanvas.setGridLinesVisible(false);
            tela();
        }
        else
        {
            if(!flagDime)
            {
                for(int i = 0; i < figuras.size(); i++)
                {
                    for(int j = 0; j < figuras.get(i).faces.size(); j++)
                    {
                        for(int k = 0; k < figuras.get(i).faces.get(j).arestas.size(); k++)
                        {
                            for(int l = 0; l < 2; l++)
                            {
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setX(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getX()/2);
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setY(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getY()/2);
                                figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).setZ(figuras.get(i).faces.get(j).arestas.get(k).pontos.get(l).getZ()/2);
                            }
                        }
                    }
                    figuras.get(i).getCentro().setX(figuras.get(i).getCentro().getX()/2);
                    figuras.get(i).getCentro().setY(figuras.get(i).getCentro().getY()/2);
                    figuras.get(i).getCentro().setZ(figuras.get(i).getCentro().getZ()/2);
                }
                flagDime = true;
            }
            canvas1.setVisible(true);
            label1.setVisible(true);
            canvas1V = true;
            canvas1.setWidth(630);
            canvas1.setHeight(280);
            canvas2.setVisible(true);
            label2.setVisible(true);
            canvas2V = true;
            canvas2.setWidth(630);
            canvas2.setHeight(280);
            canvas3.setVisible(true);
            label3.setVisible(true);
            canvas3V = true;
            canvas3.setWidth(630);
            canvas3.setHeight(280);
            canvas4.setVisible(true);
            label4.setVisible(true);
            canvas4V = false;
            canvas4.setWidth(630);
            canvas4.setHeight(280);
            gridCanvas.setGridLinesVisible(true);
            canvasHeight1.fillHeightProperty();
            canvasWidth1.fillWidthProperty();
            tela();
        }
        
    }
    
    @FXML
    private void vivaLaRevolution() throws IOException
    {
        if (indiceSel == -1) 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("NENHUM OBJETO SELECIONADO");
            alert.showAndWait();

        } 
        else 
        {
            
            Dialog<Results> dialog = new Dialog<>();

                dialog.setTitle("Revolução");
                dialog.setHeaderText("Digite os dados para a revolução");
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField textField = new TextField();
                textField.setPromptText("Graus");
                TextField textField2 = new TextField();
                textField2.setPromptText("Numero de Segmentos");

                grid.add(new Label("Graus:"), 0, 0);
                grid.add(textField, 1, 0);
                grid.add(new Label("Numero de Segmentos:"), 0, 1);
                grid.add(textField2, 1, 1);

                dialog.getDialogPane().setContent(grid);

                Platform.runLater(textField::requestFocus);

                dialog.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.OK) {
                        return new Results(textField.getText(), textField2.getText());
                    }
                    return null;
                });

                Optional<Results> optionalResult = dialog.showAndWait();
                graus = Double.parseDouble(optionalResult.get().graus);
                segmentos = Integer.parseInt(optionalResult.get().QuantidadeDePonto);
                
            if (canvasAtivo == "Canvas3")
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AVISO");
                alert.setHeaderText("Clique no canvas '(Z,Y)' para selecionar o centroi da revoluçao ");
                alert.showAndWait();
                canvas3.setOnMouseClicked(this::poliRevolucao3);
            }
            if (canvasAtivo == "Canvas2")
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AVISO");
                alert.setHeaderText("Clique no canvas '(X,Z)' para selecionar o centroi da revoluçao ");
                alert.showAndWait();
                canvas2.setOnMouseClicked(this::poliRevolucao2);
            }
            if (canvasAtivo == "Canvas1")
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AVISO");
                alert.setHeaderText("Clique no canvas '(X,Y)' para selecionar o centroi da revoluçao ");
                alert.showAndWait();
                canvas1.setOnMouseClicked(this::poliRevolucao1);
            }
        }
    }
    
    private void poliRevolucao1(MouseEvent e)
    {
        detectaCanvas1();
        c= new Ponto();
        c.setX(e.getX());
        c.setY(e.getY());
        
        figuras.get(indiceSel).revolucionario1(c, segmentos, graus);
        figuras.get(indiceSel).sel=false;
        indiceSel=-1;
        tela();
    }
    
    private void poliRevolucao2(MouseEvent e)
    {
        detectaCanvas2();
        c= new Ponto();
        c.setX(e.getX());
        c.setZ(e.getY());
        
        figuras.get(indiceSel).revolucionario2(c, segmentos, graus);
        figuras.get(indiceSel).sel=false;
        indiceSel=-1;
        tela();
    }
    private void poliRevolucao3(MouseEvent e)
    {
        detectaCanvas3();
        c= new Ponto();
        c.setZ(e.getX());
        c.setY(e.getY());
        
        figuras.get(indiceSel).revolucionario3(c, segmentos, graus);
        figuras.get(indiceSel).sel=false;
        indiceSel=-1;
        tela();
    }
    private void refresh() 
    {
        selectB.setOnMouseClicked(null);
        delB.setOnMouseClicked(null);
        transladeB.setOnMouseClicked(null);
        shearB.setOnMouseClicked(null);
        fundoB.setOnMouseClicked(null);
        rotationB.setOnMouseClicked(null);
        scaleB.setOnMouseClicked(null);
        vivaLaRevolutionB.setOnMouseClicked(null);
        bordaB.setOnMouseClicked(null);
        freeB.setOnMouseClicked(null);
        circleB.setOnMouseClicked(null);
        polygonB.setOnMouseClicked(null);
        freeFundoB.setOnMouseClicked(null);
    }
    
    @FXML
    public void detectaCanvas1()
    {
        canvasAtivo = "Canvas1";
        label1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        label2.setFont(Font.font("Verdana", 20));
        label3.setFont(Font.font("Verdana", 20));
        label4.setFont(Font.font("Verdana", 20));
        label1.setUnderline(true);
        label2.setUnderline(false);
        label3.setUnderline(false);
        label4.setUnderline(false);
        refresh();
    }
    @FXML
    public void detectaCanvas2()
    {
        canvasAtivo = "Canvas2";
        label1.setFont(Font.font("Verdana", 20));
        label2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        label3.setFont(Font.font("Verdana", 20));
        label4.setFont(Font.font("Verdana", 20));
        label1.setUnderline(false);
        label2.setUnderline(true);
        label3.setUnderline(false);
        label4.setUnderline(false);
        refresh();
    }
    @FXML
    public void detectaCanvas3()
    {
        canvasAtivo = "Canvas3";
        label1.setFont(Font.font("Verdana", 20));
        label2.setFont(Font.font("Verdana", 20));
        label3.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        label4.setFont(Font.font("Verdana", 20));
        label1.setUnderline(false);
        label2.setUnderline(false);
        label3.setUnderline(true);
        label4.setUnderline(false);
        refresh();
    }
    //@Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        inicializaVRP();
        corb.setValue(Color.BLACK);
        canvasAtivo = "Canvas1";
        label1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        label2.setFont(Font.font("Verdana", 20));
        label3.setFont(Font.font("Verdana", 20));
        label4.setFont(Font.font("Verdana", 20));
        label1.setUnderline(true);
        label2.setUnderline(false);
        label3.setUnderline(false);
        label4.setUnderline(false);
        visaoPerspectiva();

    }
    
    void desenhaContorno1() 
    {
        double maiorX = -999, menorX = 999, maiorY = -999, menorY = 999;
        for (int i = 0; i < figuras.get(indiceSel).faces.size(); i++) 
        {
            for (int j = 0; j < figuras.get(indiceSel).faces.get(i).arestas.size(); j++) 
            {
                for (int k = 0; k<2;k++)
                {
                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() > maiorX) 
                    {
                        maiorX = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() < menorX) 
                    {
                        menorX = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() > maiorY) 
                    {
                        maiorY = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() < menorY) 
                    {
                        menorY = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY();
                    }
                }
            }
        }
        gc1.setStroke(Color.BLACK);
        gc1.setLineWidth(2);        //Desenha a linha em volta do objeto
        gc1.strokeRect(menorX - 5, menorY - 5, maiorX - menorX + 10, maiorY - menorY + 10);
        gc1.setStroke(Color.BLUE);   //Desenha pontos em volta do objeto
        gc1.setLineWidth(4);
        gc1.strokeRect(menorX - 5, menorY - 5, 5, 5);
        gc1.strokeRect(maiorX + 5, menorY - 5, 5, 5);
        gc1.strokeRect(menorX - 5, maiorY + 5, 5, 5);
        gc1.strokeRect(maiorX + 5, maiorY + 5, 5, 5);

        gc1.strokeRect(((menorX + maiorX) / 2), menorY - 5, 5, 5);
        gc1.strokeRect(((menorX + maiorX) / 2), maiorY + 5, 5, 5);
        gc1.strokeRect(menorX - 5, ((menorY + maiorY) / 2), 5, 5);
        gc1.strokeRect(maiorX + 5, ((menorY + maiorY) / 2), 5, 5);
        

    }
    int pontoControle1(Ponto p1) 
    {
        double maiorX = -999, menorX = 999, maiorY = -999, menorY = 999;

        for (int i = 0; i < figuras.get(indiceSel).faces.size(); i++) 
        {
            for (int j = 0; j < figuras.get(indiceSel).faces.get(i).arestas.size(); j++) 
            {
                for (int k = 0; k<2;k++)
                {
                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() > maiorX) 
                    {
                        maiorX = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() < menorX) 
                    {
                        menorX = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() > maiorY) 
                    {
                        maiorY = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() < menorY) 
                    {
                        menorY = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY();
                    }
                }
            }
        }

        if (p1.getX() >= ((menorX + maiorX) / 2) - 5 && p1.getY() >= menorY - 10 && p1.getX() <= ((menorX + maiorX) / 2) + 8 && p1.getY() <= menorY + 3) 
        {
            return 1;
        }

        if (p1.getX() >= maiorX && p1.getY() >= ((menorY + maiorY) / 2) - 5 && p1.getX() <= maiorX + 13 && p1.getY() <= ((menorY + maiorY) / 2) + 8) 
        {
            return 2;
        }

        if (p1.getX() >= ((menorX + maiorX) / 2) - 5 && p1.getY() >= maiorY && p1.getX() <= ((menorX + maiorX) / 2) + 8 && p1.getY() <= maiorY + 13) 
        {
            return 3;
        }

        if (p1.getX() >= menorX - 10 && p1.getY() >= ((menorY + maiorY) / 2) - 5 && p1.getX() <= menorX + 3 && p1.getY() <= ((menorY + maiorY) / 2) + 8) 
        {
            return 4;
        }

        if (p1.getX() >= menorX - 10 && p1.getY() >= menorY - 10 && p1.getX() <= menorX + 3 && p1.getY() <= menorY + 3) 
        {
            return 5;
        }

        if (p1.getX() >= maiorX && p1.getY() >= menorY - 10 && p1.getX() <= maiorX + 13 && p1.getY() <= menorY + 3) 
        {
            return 6;
        }

        if (p1.getX() >= menorX - 10 && p1.getY() >= maiorY && p1.getX() <= menorX + 3 && p1.getY() <= maiorY + 13) 
        {
            return 7;
        }

        if (p1.getX() >= maiorX && p1.getY() >= maiorY && p1.getX() <= maiorX + 13 && p1.getY() <= maiorY + 13) 
        {
            return 8;
        }

        return 0;
    }
    
    void desenhaContorno2() 
    {
        double maiorX = -999, menorX = 999, maiorZ = -999, menorZ = 999;
        for (int i = 0; i < figuras.get(indiceSel).faces.size(); i++) 
        {
            for (int j = 0; j < figuras.get(indiceSel).faces.get(i).arestas.size(); j++) 
            {
                for (int k = 0; k<2;k++)
                {
                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() > maiorX) 
                    {
                        maiorX = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() < menorX) 
                    {
                        menorX = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() > maiorZ) 
                    {
                        maiorZ = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() < menorZ) 
                    {
                        menorZ = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ();
                    }
                }
            }
        }
        gc2.setStroke(Color.BLACK);
        gc2.setLineWidth(2);        //Desenha a linha em volta do objeto
        gc2.strokeRect(menorX - 5, menorZ - 5, maiorX - menorX + 10, maiorZ - menorZ + 10);
        gc2.setStroke(Color.BLUE);   //Desenha pontos em volta do objeto
        gc2.setLineWidth(4);
        gc2.strokeRect(menorX - 5, menorZ - 5, 5, 5);
        gc2.strokeRect(maiorX + 5, menorZ - 5, 5, 5);
        gc2.strokeRect(menorX - 5, maiorZ + 5, 5, 5);
        gc2.strokeRect(maiorX + 5, maiorZ + 5, 5, 5);

        gc2.strokeRect(((menorX + maiorX) / 2), menorZ - 5, 5, 5);
        gc2.strokeRect(((menorX + maiorX) / 2), maiorZ + 5, 5, 5);
        gc2.strokeRect(menorX - 5, ((menorZ + maiorZ) / 2), 5, 5);
        gc2.strokeRect(maiorX + 5, ((menorZ + maiorZ) / 2), 5, 5);
    }
    int pontoControle2(Ponto p1) 
    {
        double maiorX = -999, menorX = 999, maiorZ = -999, menorZ = 999;

        for (int i = 0; i < figuras.get(indiceSel).faces.size(); i++) 
        {
            for (int j = 0; j < figuras.get(indiceSel).faces.get(i).arestas.size(); j++) 
            {
                for (int k = 0; k<2;k++)
                {
                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() > maiorX) 
                    {
                        maiorX = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX() < menorX) 
                    {
                        menorX = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getX();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() > maiorZ) 
                    {
                        maiorZ = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() < menorZ) 
                    {
                        menorZ = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ();
                    }
                }
            }
        }

        if (p1.getX() >= ((menorX + maiorX) / 2) - 5 && p1.getZ() >= menorZ - 10 && p1.getX() <= ((menorX + maiorX) / 2) + 8 && p1.getZ() <= menorZ + 3) 
        {
            return 1;
        }

        if (p1.getX() >= maiorX && p1.getZ() >= ((menorZ + maiorZ) / 2) - 5 && p1.getX() <= maiorX + 13 && p1.getZ() <= ((menorZ + maiorZ) / 2) + 8) 
        {
            return 2;
        }

        if (p1.getX() >= ((menorX + maiorX) / 2) - 5 && p1.getZ() >= maiorZ && p1.getX() <= ((menorX + maiorX) / 2) + 8 && p1.getZ() <= maiorZ + 13)
        {
            return 3;
        }

        if (p1.getX() >= menorX - 10 && p1.getZ() >= ((menorZ + maiorZ) / 2) - 5 && p1.getX() <= menorX + 3 && p1.getZ() <= ((menorZ + maiorZ) / 2) + 8) 
        {
            return 4;
        }

        if (p1.getX() >= menorX - 10 && p1.getZ() >= menorZ - 10 && p1.getX() <= menorX + 3 && p1.getZ() <= menorZ + 3) 
        {
            return 5;
        }

        if (p1.getX() >= maiorX && p1.getZ() >= menorZ - 10 && p1.getX() <= maiorX + 13 && p1.getZ() <= menorZ + 3) 
        {
            return 6;
        }

        if (p1.getX() >= menorX - 10 && p1.getZ() >= maiorZ && p1.getX() <= menorX + 3 && p1.getZ() <= maiorZ + 13) {
            return 7;
        }

        if (p1.getX() >= maiorX && p1.getZ() >= maiorZ && p1.getX() <= maiorX + 13 && p1.getZ() <= maiorZ + 13) {
            return 8;
        }

        return 0;
    }
    
    void desenhaContorno3() 
    {
        double maiorZ = -999, menorZ = 999, maiorY = -999, menorY = 999;
        for (int i = 0; i < figuras.get(indiceSel).faces.size(); i++) 
        {
            for (int j = 0; j < figuras.get(indiceSel).faces.get(i).arestas.size(); j++) 
            {
                for (int k = 0; k<2;k++)
                {
                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() > maiorZ) {
                        maiorZ = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() < menorZ) {
                        menorZ = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() > maiorY) {
                        maiorY = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() < menorY) {
                        menorY = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY();
                    }
                }
            }
        }
        gc3.setStroke(Color.BLACK);
        gc3.setLineWidth(2);        //Desenha a linha em volta do objeto
        gc3.strokeRect(menorZ - 5, menorY - 5, maiorZ - menorZ + 10, maiorY - menorY + 10);
        gc3.setStroke(Color.BLUE);   //Desenha pontos em volta do objeto
        gc3.setLineWidth(4);
        gc3.strokeRect(menorZ - 5, menorY - 5, 5, 5);
        gc3.strokeRect(maiorZ + 5, menorY - 5, 5, 5);
        gc3.strokeRect(menorZ - 5, maiorY + 5, 5, 5);
        gc3.strokeRect(maiorZ + 5, maiorY + 5, 5, 5);

        gc3.strokeRect(((menorZ + maiorZ) / 2), menorY - 5, 5, 5);
        gc3.strokeRect(((menorZ + maiorZ) / 2), maiorY + 5, 5, 5);
        gc3.strokeRect(menorZ - 5, ((menorY + maiorY) / 2), 5, 5);
        gc3.strokeRect(maiorZ + 5, ((menorY + maiorY) / 2), 5, 5);
       

    }
    int pontoControle3(Ponto p1) 
    {
        double maiorZ = -999, menorZ = 999, maiorY = -999, menorY = 999;

        for (int i = 0; i < figuras.get(indiceSel).faces.size(); i++) 
        {
            for (int j = 0; j < figuras.get(indiceSel).faces.get(i).arestas.size(); j++) 
            {
                for (int k = 0; k<2;k++)
                {
                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() > maiorZ) {
                        maiorZ = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ() < menorZ) {
                        menorZ = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getZ();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() > maiorY) {
                        maiorY = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY();
                    }

                    if (figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY() < menorY) {
                        menorY = figuras.get(indiceSel).faces.get(i).arestas.get(j).pontos.get(k).getY();
                    }
                }
            }
        }

        if (p1.getZ() >= ((menorZ + maiorZ) / 2) - 5 && p1.getY() >= menorY - 10 && p1.getZ() <= ((menorZ + maiorZ) / 2) + 8 && p1.getY() <= menorY + 3) {
            return 1;
        }

        if (p1.getZ() >= maiorZ && p1.getY() >= ((menorY + maiorY) / 2) - 5 && p1.getZ() <= maiorZ + 13 && p1.getY() <= ((menorY + maiorY) / 2) + 8) {
            return 2;
        }

        if (p1.getZ() >= ((menorZ + maiorZ) / 2) - 5 && p1.getY() >= maiorY && p1.getZ() <= ((menorZ + maiorZ) / 2) + 8 && p1.getY() <= maiorY + 13) {
            return 3;
        }

        if (p1.getZ() >= menorZ - 10 && p1.getY() >= ((menorY + maiorY) / 2) - 5 && p1.getZ() <= menorZ + 3 && p1.getY() <= ((menorY + maiorY) / 2) + 8) {
            return 4;
        }

        if (p1.getZ() >= menorZ - 10 && p1.getY() >= menorY - 10 && p1.getZ() <= menorZ + 3 && p1.getY() <= menorY + 3) {
            return 5;
        }

        if (p1.getZ() >= maiorZ && p1.getY() >= menorY - 10 && p1.getZ() <= maiorZ + 13 && p1.getY() <= menorY + 3) {
            return 6;
        }

        if (p1.getZ() >= menorZ - 10 && p1.getY() >= maiorY && p1.getZ() <= menorZ + 3 && p1.getY() <= maiorY + 13) {
            return 7;
        }

        if (p1.getZ() >= maiorZ && p1.getY() >= maiorY && p1.getZ() <= maiorZ + 13 && p1.getY() <= maiorY + 13) {
            return 8;
        }

        return 0;
    }
    
    void visaoPerspectiva() {

        canvas4.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                System.out.println("entrou tela 4 perppectiva");
                XO = e.getX();
                YO = e.getY();
            }
        });

        canvas4.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("entrou aqui");
                X = event.getX();
                Y = event.getY();

                Ponto novo = new Ponto();

                novo = atualizaVRP(XO, YO, X, Y, VRP);
                
                VRP=novo;

                tela();

                XO = X;
                YO = Y;
            }
        });

        canvas4.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                X = e.getX();
                Y = e.getY();

               
                Ponto novo = new Ponto();

                novo = atualizaVRP(XO, YO, X, Y, VRP);
                

                VRP=novo;

                tela();
                XO = X;
                YO = Y;
            }
        });
    }
    public Ponto atualizaVRP(double XO, double YO, double X, double Y, Ponto VRP)
    {
        Ponto Novo = new Ponto();
        double[][] respostaF;
        double angulo = (X - XO);
        double cos, sin;
        angulo /= 80.0;
        double[][] matriz = new double[4][4];
        double[][] matrizM = new double[4][1];
        cos = Math.cos(angulo);
        sin = Math.sin(angulo);
        double[][] resposta;
        
        
        matriz[0][0] = cos;
        matriz[0][1] = 0;
        matriz[0][2] = -sin;
        matriz[0][3] = 0;
        
        matriz[1][0] = 0;
        matriz[1][1] = 1;
        matriz[1][2] = 0;
        matriz[1][3] = 0;
        
        matriz[2][0] = sin;
        matriz[2][1] = 0;
        matriz[2][2] = cos;
        matriz[2][3] = 0;
        
        matriz[3][0] = 0;
        matriz[3][1] = 0;
        matriz[3][2] = 0;
        matriz[3][3] = 1;
        
        
        
        matrizM[0][0] = VRP.getX();
        matrizM[1][0] = VRP.getY();
        matrizM[2][0] = VRP.getZ();
        matrizM[3][0] = 1;
        
        
        
        resposta = MultMatriz(matriz, matrizM, 4, 1);
        
        angulo = (Y - YO);
        
        //angulo = descobreAngulo(YO, Y, XO, X);
        angulo /= 80.0;
        
        cos = Math.cos(angulo);
        sin = Math.sin(angulo);
        
        matriz[0][0] = 1;
        matriz[0][1] = 0;
        matriz[0][2] = 0;
        matriz[0][3] = 0;
        
        matriz[1][0] = 0;
        matriz[1][1] = cos;
        matriz[1][2] = -sin;
        matriz[1][3] = 0;
        
        matriz[2][0] = 0;
        matriz[2][1] = sin;
        matriz[2][2] = cos;
        matriz[2][3] = 0;
        
        matriz[3][0] = 0;
        matriz[3][1] = 0;
        matriz[3][2] = 0;
        matriz[3][3] = 1;
        
        
        
        respostaF = MultMatriz(matriz, resposta, 4, 1);
        
        Novo.setX(respostaF[0][0]);
        Novo.setY(respostaF[1][0]);
        Novo.setZ(respostaF[2][0]);
        
        return Novo;
    }
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
    public void inicializaVRP() {
        this.VRP.setX(1000);
        this.VRP.setY(1000);
        this.VRP.setZ(1000);
    }
}


class Results {

    String graus;
    String QuantidadeDePonto;

    public Results(String name, String QuantidadeDePonto) 
    {
        this.graus = name;
        this.QuantidadeDePonto = QuantidadeDePonto;
    }
}