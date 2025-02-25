/**
 * Baby.java
 * This class represents a Weight object
 */
public class Baby
{
    private String _firstName; // Represents the first name of the baby
    private String _lastName; // Represents the last name of the baby
    private String _id; // Represents the id of the baby
    private Date _dateOfBirth; // Represents the date of the birth of the baby
    private Weight _birthWeight; // Represents the birth weight of the baby
    private Weight _currentWeight; // Represents the current weight of the baby
    
    private final int ID_LIMIT = 9;
    private final int LIMIT_GRAMS = 1000;
    private final Weight MIN_WEIGHT = new Weight(1, 0);
    
    //Constructor
    public Baby(String fName, String lName, String id, int day, int month, int year, int birthWeightInGrams){ // Baby constructor - If the given id and birthWeightInGrams are valid - creates a new Baby object with the parameters, otherwise, if the id is not calid creates the Baby with id = "000000000" and all other parameters. if the weight of the baby at birth is not 1KG and above it willset it to 1KG
        _firstName = fName;
        _lastName = lName;
        _id = id.length() == ID_LIMIT ? id : "000000000";
        _dateOfBirth = new Date(day, month, year);
        _birthWeight = new Weight(birthWeightInGrams);
        _currentWeight = new Weight(birthWeightInGrams);
    }
    
    public Baby(Baby other){ // Copy constructor
        _firstName = other._firstName;
        _lastName = other._lastName;
        _id = other._id;
        _dateOfBirth = other._dateOfBirth;
        _birthWeight = other._birthWeight;
        _currentWeight = other._currentWeight;
    }
    
    // Get
    public String getFirstName(){ // Gets the first name
        return _firstName;
    }
    public String getLastName(){ // Gets the last name
        return _lastName;
    }
    public String getId(){ // Gets the id
        return _id;
    }
    public Date getDateOfBirth(){ // Gets the first name
        return _dateOfBirth;
    }
    public Weight getBirthWeight(){ // Gets the first name
        return _birthWeight;
    }
    public Weight getCurrentWeight(){ // Gets the first name
        return _currentWeight;
    }
    
    public String toString(){ // Returns a String that represents this baby.
        return "Name: " + _firstName + " " + _lastName + "\r\n" +
               "Id: " + _id + "\r\n" +
               "Date of Birth: " + _dateOfBirth + "\r\n" +
               "Birth Weight: " + _birthWeight + "\r\n" +
               "Current Weight: " + _currentWeight;
    }
    
    //Set (only one)
    public void setCurrentWeight(Weight weightToSet){ // Sets the current weight if the given parameter is valid.
        if (weightToSet.heavier(MIN_WEIGHT))
            _currentWeight = new Weight(weightToSet);
    }
    
    //Public
    public boolean equals (Baby other){ // Checks if two babies are the same. Two babies are consider the same if they have the same first and last name, same ID and similar date of birth.
        return _firstName.equals(other._firstName) &&
               _lastName.equals(other._lastName) &&
               _id.equals(other._id) &&
               _dateOfBirth.equals(other._dateOfBirth);
    }
    
    public boolean areTwins (Baby other){ // Checks if two babies are twins. Twins should have similar last name, different first name, different ID and similar date of birth or difference of one day between the date of birth of the current baby and the other.
        int birthDateDifference = _dateOfBirth.difference(other._dateOfBirth);
        return !_firstName.equals(other._firstName) &&
               _lastName.equals(other._lastName) &&
               !_id.equals(other._id) &&
               (birthDateDifference == 0 || birthDateDifference == 1);
    }
    
    public boolean heavier (Baby other){ // Checks if the weight of this baby is heavier than the weight of another baby.
        return _currentWeight.heavier(other._currentWeight);
    }
    
    public void updateCurrentWeight (int grams){ // Updates the baby's current weight by adding the additional grams. If the sum of the current weight and the additional grams is negative, the baby's current weight will remain unchanged.
        Weight newWeight = _currentWeight.add(grams);
        if (newWeight.heavier(MIN_WEIGHT)) {
            _currentWeight = newWeight;
        }
    }
    
    public boolean older (Baby other){ // Checks if the date of birth of this baby is before than the date of birth of another baby.
        return _dateOfBirth.before(other._dateOfBirth);
    }
    
    public int isWeightInValidRange(int numOfDays){ // Checks if the current weight of this baby is within the valid range.
        if (numOfDays < 1 || numOfDays > 365) return 1;
        
        int birthWeightInGrams = this._birthWeight.returnGrams();
        int currentWeightInGrams = this._currentWeight.returnGrams();
        
        double expectedWeightInGrams = birthWeightInGrams;
        
        if (numOfDays <= 7) {
            double weightLoss = birthWeightInGrams * 0.10 * (numOfDays / 7.0);
            expectedWeightInGrams -= weightLoss; }

        else if (numOfDays <= 60) {
            expectedWeightInGrams -= birthWeightInGrams * 0.10;
            expectedWeightInGrams += 30 * (numOfDays - 7); }

        else if (numOfDays <= 120) {
            expectedWeightInGrams -= birthWeightInGrams * 0.10;
            expectedWeightInGrams += 30 * 53;
            expectedWeightInGrams += 25 * (numOfDays - 60); }

        else if (numOfDays <= 240) {
            expectedWeightInGrams -= birthWeightInGrams * 0.10;
            expectedWeightInGrams += 30 * 53;
            expectedWeightInGrams += 25 * 60;
            expectedWeightInGrams += 16 * (numOfDays - 120); }

        else {
            expectedWeightInGrams -= birthWeightInGrams * 0.10;
            expectedWeightInGrams += 30 * 53;
            expectedWeightInGrams += 25 * 60;
            expectedWeightInGrams += 16 * 120;
            expectedWeightInGrams += 8 * (numOfDays - 240);
        }
        
        if (currentWeightInGrams < expectedWeightInGrams)
            return 2;
        
        return 3;
    }
}