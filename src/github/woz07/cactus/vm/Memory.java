package github.woz07.cactus.vm;

import github.woz07.cactus.exceptions.MemoryFullException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

public class Memory {
    private final Heap heap;
    private final Stack stack;
    private final FunctionTable functions;
    private final int maxsize;
    public Memory(int size) {
        maxsize   = size;
        heap      = new Heap(maxsize);
        stack     = new Stack(maxsize);
        functions = new FunctionTable(maxsize);
    }

    // Create
    public void create(String name, Object value, boolean pointer, boolean function, Function<Objects, Object> func) {
        String address;

        // Add to heap
        try {
            address = heap.ALLOC(value, pointer, function);
        } catch (MemoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Add to stack
        try {
            stack.Create(name, address);
        } catch (MemoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }

        // If function is true, then add to functions
        if (function) {
            try {
                functions.Store(address, func);
            } catch (MemoryFullException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Update
    // Can only update variables, can assign them to another variables address or a new value
    public void update(String name, Object value, boolean pointer) {
        String address = stack.Get(name);
        heap.MALLOC(address, value, pointer, false);
    }

    // Delete
    public void delete(String name, boolean function) {
        // Get address and remove from heap and stack
        String address = stack.Get(name);
        stack.Delete(name);
        heap.DEALLOC(address);

        // If was a function then remove from functions too
        if (function) {
            functions.Remove(address);
        }
    }

    //#TESTING
    public void _DUMP_() {
        Cell[] mem = heap._HEAP_();
        ArrayList<String> stk = new ArrayList<>(stack._STACK_().values());
        for (int i = 0; i < maxsize; i++) {
            System.out.println("LOC: " + mem[i].address + "\t\t\t| VAL: " + mem[i].value + "\t\t\t| STK: " + stk.get(0) + "\t\t\t| FUN: " + functions.Get(mem[i].address));
        }
    }
}
