/*
 * Date.java
 * This class represents a Date object
*/
public class Date
{
    private int _day; // the day in the month (1-31)
    private int _month; // the month in the year (1-12)
    private int _year; // the year (4 digits)
    
    //Constructors
    public Date(){ // Default constructor, sets the date to 1/1/2024
        _day = 1;
        _month = 1;
        _year = 2024;
    }
    
    public Date(int day, int month, int year){ // Date constructor - If the given date is valid - creates a new Date object, otherwise creates the date 01/01/2024.
        this();
            
        if (checkDate(day, month, year)){
        _day = day;
        _month = month;
        _year = year;
        }
    }
    
    public Date(Date other){ // Copy constructor
        this._day = other._day;
        this._month = other._month;
        this._year = other._year;
    }
    
    // Get
    public int GetDay(){ // Gets the day
        return _day;
    }
    
    public int GetMonth(){ // Gets the month
        return _month;
    }
    
    public int GetYear(){ // Gets the year
        return _year;
    }
    
    //Set
    public void setDay(int dayToSet){ // Sets the day if valid
        if (checkDate(dayToSet, _month, _year)) _day = dayToSet;
    }
    
    public void setMonth(int monthToSet){ // Sets the month if valid
        if (checkDate(_day, monthToSet, _year)) _month = monthToSet;
    }
    
    public void setYear(int yearToSet){ // Sets the year if valid
        if (checkDate(_day, _month, yearToSet)) _year = yearToSet;
    }
    
    //Public
    public boolean equals(Date other){ // Checks if two dates are the same
        return  this._day == other._day && 
                this._month == other._month &&
                this._year == other._year;
    }

    public boolean before(Date other){ // Checks if this date comes before another date
        return  this._year <= other._year &&
                this._month <= other._month &&
                this._day < other._day;
    }
    
    public boolean after(Date other){ // Checks if this date comes before another date
        return !before(other);
    }
    
    public int difference(Date other){ // Calculates the difference in days between two dates
        return Math.abs(calculateDate(this._day, this._month, this._year) - calculateDate(other._day, other._month, other._year));
    }
    
    public String toString(){ // Returns a String that represents this date
        return (_day < 10 ? "0" + _day : _day) +
        "/" + (_month < 10 ? "0" + _month : _month) +
        "/" + _year;
    }
    
    public Date tomorrow(){
        int daysInMonth = daysInMonth(_month, _year);
        int day = _day+1;
        int month = _month;
        int year = _year;
        
        if (day > daysInMonth){
            day = 1;
            month++;
        }
        
        if (month > 12){
            month = 1;
            year++;
        }
        
        return new Date(day, month, year);
    }
    
    // Private
    private int daysInMonth(int month, int year){ // returns how many days are there in this month
        switch (month){ // Check day in month             
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
                
            case 2:
                return isLeapYear(year) ? 29 : 28;
                
            default:
                return 31;
        }
    }
    
    // checks if the year is a leap year
    private boolean isLeapYear (int y){
        return (y%4==0 && y%100!=0) || (y%400==0) ? true : false;
    }
    
    // computes the day number since the beginning of the Christian counting of years
    private int calculateDate ( int day, int month, int year)
    {
         if (month < 3) {
             year--;
             month = month + 12;
         }
         return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    }
    
    private boolean checkDate(int day, int month, int year){ // Checks if the date is valid
        return month >=1 && month <= 12 && // Check month
                year >= 1000 && year <= 9999 && // Check Year
                day >= 1 && day <= daysInMonth(month, year); // Check Day
    }
}