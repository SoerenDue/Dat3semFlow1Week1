/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author zarpy
 */
@Entity
public class Point implements Serializable {
    
    
    public Point (){
    }
    
    public Point(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer x;
    private Integer y;

    
    

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Point{" + "id=" + id + ", x=" + x + ", y=" + y + '}';
    }
    


    
    
}
