package github.woz07.cactus.vm;

import github.woz07.cactus.exceptions.MemoryFullException;

import java.util.HashMap;

public class Stack {
    private final HashMap<String, String> stack; // k = var name & func name, v = address in heap
    private final int maxsize;
    public Stack(int size) {
        maxsize = size;
        stack = new HashMap<>(maxsize);
        _Initialize();
    }

    // Gets address
    public String Get(String name) {
        return stack.get(name);
    }

    // Create
    public void Create(String name, String address) throws MemoryFullException {
        if (stack.size() > maxsize) {
            throw new MemoryFullException();
        }
        stack.put(name, address);
    }

    // Delete
    public void Delete(String name) {
        stack.remove(name);
    }

    // Sets all values to null
    private void _Initialize() {
        for (int i = 0; i < maxsize; i++) {
            stack.put(null, null);
        }
    }

    //#TESTING
    public HashMap<String, String> _STACK_() {
        return stack;
    }
}
