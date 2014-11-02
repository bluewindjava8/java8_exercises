package test.property;

import java.util.LinkedList;
import java.util.List;

public class Circle {
    private double radius;
    private final Property<Double> radiusProperty;
    
    private double x, y;
    
    class   RadiusProperty implements Property<Double>{
        //List<Property<Double>> bindList = new LinkedList<>();
        Property<Double> bindSource;
        
        @Override
        public Double get() {
            return Circle.this.radius;
        }

        @Override
        public void set(Double t) {
            Circle.this.radius = t;
            bindList.stream().forEach(property -> property.set(t));
        }

        @Override
        public void bind(Property<Double> property) {
            bindList.add(property);
            property.set(this.get());
        }

        @Override
        public void biBind(Property<Double> property) {
            bindList.add(property);
            property.bind(this);
        }
            
            
    }
    
    public Circle(double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
        
        radiusProperty = new RadiusProperty();
    }
    
    public Property<Double> getRadiusProperty(){
        return radiusProperty;
    }
    
    public static void main(String[] args){
        Circle c1 = new Circle(0, 0, 100);
        Property<Double> radiusProperty1 = c1.getRadiusProperty();
        System.out.println(c1.radius);
        radiusProperty1.set(200.0);
        System.out.println(c1.radius);
        
        System.out.println();
        Circle c2 = new Circle(0, 0, 500);
        Property<Double> radiusProperty2 = c2.getRadiusProperty();
        System.out.println(c2.radius);
        radiusProperty1.bind(radiusProperty2);
        System.out.println(c2.radius);
        radiusProperty1.set(1000.0);
        System.out.println(c2.radius);
        
        System.out.println();
        radiusProperty2.set(3000.0);
        System.out.println(c2.radius);
        System.out.println(c1.radius);
        
        
        System.out.println();
        radiusProperty1.set(4000.0);
        System.out.println(c2.radius);
        System.out.println(c1.radius);       
        
        
                System.out.println();
        Circle c3 = new Circle(0, 0, 600);
        Property<Double> radiusProperty3 = c3.getRadiusProperty();
        
        
        
    }
    
}

interface Property<T>{
    T get();
    void set(T t);
    void bind(Property<T> property);
    void biBind(Property<T> property);
}
