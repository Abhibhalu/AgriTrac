package AgriTrac;

public class Tractor {
	
	    protected int id;
	    protected String name;
	    protected double price;
	    protected double km;

	     Tractor(int id, String name,double  price,double km) {
	        this.id = id;
	        this.name = name;
	        this.price = price;
	        this.km = km;
	    }

	    @Override
	    public String toString() {
	        return "ID: " + id + ", Name: " + name+" , Price: ₹" + price+ " , Km: " +km;
	    }
	}



