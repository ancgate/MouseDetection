/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousedetection;

/**
 *
 * @author jeffersonbienaime
 */
public class Coordinate {
    
    private Double xCor;
    private Double yCor;

    public Coordinate(Double xCor, Double yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
    }

    public Double getxCor() {
        return xCor;
    }

    public void setxCor(Double xCor) {
        this.xCor = xCor;
    }

    public Double getyCor() {
        return yCor;
    }

    public void setyCor(Double yCor) {
        this.yCor = yCor;
    }

    @Override
    public String toString() {
        return "Coordinate{" + "xCor=" + xCor + ", yCor=" + yCor + '}';
    }
   
    
    
}
