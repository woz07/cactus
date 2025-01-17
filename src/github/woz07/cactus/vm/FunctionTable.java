package github.woz07.cactus.vm;

import github.woz07.cactus.exceptions.MemoryFullException;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

public class FunctionTable {
    private final HashMap<String, Function<Objects, Object>> functions; // k = address, v = function
    private final int maxsize;
    public FunctionTable(int size) {
        maxsize = size;
        functions = new HashMap<>(maxsize);
    }

    // Get
    public Function<Objects, Object> Get(String address) {
        return functions.get(address);
    }

    // Add
    public void Store(String address, Function<Objects, Object> function) throws MemoryFullException {
        if (functions.size() > maxsize) {
            throw new MemoryFullException();
        }
        functions.put(address, function);
    }

    // Remove
    public void Remove(String address) {
        functions.remove(address);
    }

    // Sets all values to null
    private void _Initialize() {
        for (int i = 0; i < maxsize; i++) {
            functions.put(null, null);
        }
    }

}
