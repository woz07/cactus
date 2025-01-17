package github.woz07;

import github.woz07.cactus.vm.Memory;

import java.io.Console;
import java.util.Objects;
import java.util.function.Function;

public class Cactus {
    public static void main(String[] args) {

        //#TESTING
        Memory memory = new Memory(20 + 1); // maxsize=20
        memory.create("x", 10, false, false, null);
        memory.create("y", 20, false, false, null);
        memory.create("px", "0x000", true, false, null);
        Function<Objects, Object> square = (Objects a) -> {
            return Integer.parseInt(String.valueOf(a)) + Integer.parseInt(String.valueOf(a));
        };
        memory.create("square", null, false, true, square);
        memory._DUMP_();

        //TODO
        //  Now I need to work on the language it self as I have completed everything related to the memory.
        //  Also need to figure out how to run these functions that are stored in function table
        //  Get started with tokenizing and compiling
    }
}
