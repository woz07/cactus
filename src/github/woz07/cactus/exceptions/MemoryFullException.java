package github.woz07.cactus.exceptions;

public class MemoryFullException extends Exception {
    public MemoryFullException() {
        super("[VM]: No more space in the memory. Try allocating more memory and then running again.");
    }
}
