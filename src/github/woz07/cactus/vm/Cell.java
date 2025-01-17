package github.woz07.cactus.vm;

public class Cell {
    String  address;    // The address of the cell
    Object  value;      // The value of the cell
    boolean pointer;    // Is the value an address?
    boolean function;   // Is the value a function?
}
