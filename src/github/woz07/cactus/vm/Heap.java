package github.woz07.cactus.vm;

import github.woz07.cactus.exceptions.MemoryFullException;

public class Heap {
    private final Cell[] heap;
    public Heap(int size) {
        heap = new Cell[size];
        _InitializeAddresses();
    }

    // Get value
    public Cell GET(String address) {
        for (Cell cell : heap) {
            if (cell.address.equals(address)) {
                return cell;
            }
        }
        return null;
    }

    // Add
    public String ALLOC(Object value, boolean pointer, boolean function) throws MemoryFullException {
        for (Cell cell : heap) {
            if (cell.value == null) {
                cell.value    = value;
                cell.pointer  = pointer;
                cell.function = function;
                return cell.address;
            }
        }
        throw new MemoryFullException();
    }

    // Update/Change
    public void MALLOC(String address, Object value, boolean pointer, boolean function) {
        for (Cell cell : heap) {
            if (cell.address.equals(address)) {
                cell.value    = value;
                cell.pointer  = pointer;
                cell.function = function;
                return;
            }
        }
    }

    // Delete
    public void DEALLOC(String address) {
        for (Cell cell : heap) {
            if (cell.address.equals(address)) {
                cell.value    = null;
                cell.pointer  = false;
                cell.function = false;
                return;
            }
        }
    }

    // Initializes the heaps addresses
    private void _InitializeAddresses() {
        String address = "0x";
        for (int i = 0; i < heap.length; i++) {
            if (i <= 9) {
                address = address.concat("00");
            }
            if (i >= 10 && i <= 99) {
                address = address.concat("0");
            }
            address = address.concat(String.valueOf(i));

            heap[i] = new Cell();
            heap[i].address = address;
            heap[i].value = null;
            heap[i].pointer = false;
            heap[i].function = false;

            address = "0x";
        }
    }

    //#TESTING
    public Cell[] _HEAP_() {
        return heap;
    }
}
