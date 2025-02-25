
/**
 * Weight.java
 * This class represents a Weight object
 */
public class Weight
{
    private int _kilos; // represnting the number of kilos (>1)
    private int _grams; // represnting the number of grams (0-MIN_GRAMS)
    private final int MIN_GRAMS = 1000;
    
    //Constructors
    public Weight(int kilos, int grams){ // Weight constructor - If the given weight is valid - creates a new Weight object, otherwise if one of the parameters in not valid initialize it to 1.        
        _kilos = kilos >= 1 ? kilos : 1;    
        _grams = (grams >= 0 && grams <= MIN_GRAMS) ? grams : 0;
    }
    
    public Weight (Weight other){ // Copy constructor
        _kilos = other._kilos;
        _grams = other._grams;
    }
    
    public Weight(int totalGrams){ // Constructor with only one parameter
        if (totalGrams < MIN_GRAMS){
            _kilos = 1;
            _grams = 0;
        }
        
        else{
            _kilos = totalGrams / MIN_GRAMS;
            _grams = totalGrams % MIN_GRAMS;   
        }
    }
    
    //Get
    public int getKilos(){ // Gets the kilos
        return _kilos;
    }
    
    public int getGrams(){ // Gets the grams
        return _grams;
    }
    
    //Public
    public int returnGrams(){ // Returns the weight in Grams
        return (_kilos * 1000) + _grams;
    }
    
    public boolean equals (Weight other){ // Checks if two weights are the same
        return _grams == other._grams &&
               _kilos == other._kilos;
    }
    
    public boolean lighter (Weight other){ // Checks if this weight is lighter than another weight
        return _grams < other._grams &&
               _kilos <= other._kilos;
    }
    
    public boolean heavier(Weight other){ // Checks if this weight is heavier than another weight
        return !lighter(other);
    }
    
    public String toString(){ // Returns a String that represents this date
        return _kilos + "." +
                ((_grams < 100) ? "0" : 
                (_grams < 10) ? "0" : _grams);
    }
    
    public Weight add(int grams){ // Return a new weight with the additional grams given as parameter
        int new_grams = grams + _grams;
        int kilos = _kilos;
        if (new_grams > MIN_GRAMS){
            new_grams -= MIN_GRAMS;
            kilos++;
        }
        
        if (new_grams < 0){
            new_grams += MIN_GRAMS;
            kilos--;
        }
        
        if (kilos < 1)
            return new Weight(_kilos, _grams);
        
        return new Weight(kilos, new_grams);
    }
}
