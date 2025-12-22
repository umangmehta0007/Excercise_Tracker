package comp2450.Model;

import ca.umanitoba.cs.comp2450.stack.Stack;
import com.github.lalyos.jfiglet.FigletFont;
import comp2450.tests.TestResults;

import java.io.IOException;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class StackTestHarness {

    private static int successes = 0;
    private static int failures = 0;


    public TestResults runTests() {


        // TODO: The test for each BadStack will be done here now:
        // TODO: And the result for each will be done all together


        runtest();

        return new TestResults(successes, failures);
    }

    private static Stack<String> instanceOfTest() {


        return new LinkedListStack<>();

        //return new BadStack1<>();
        //return new BadStack2<>();
        //return new BadStack3<>();
        //return new BadStack4<>();
        //return new BadStack5<>();
    }

    private static void runtest() {

        System.out.println("Testing Push");
        testPush();

        System.out.println();
        System.out.println("Testing Pop");
        testPop();

        System.out.println();
        System.out.println("Testing size");
        testSize();

        System.out.println();
        System.out.println("Testing empty");
        testEmpty();

        System.out.println();
        System.out.println("Testing peek");
        testPeek();
    }

    private static void testPush() {

        System.out.println("Testing Push with Empty Stack");
        emptyStackPush();

        System.out.println("Testing Push with One element in Stack");
        oneElementPush();

        System.out.println("Testing Push with TWO element in Stack");
        twoElementPush();
    }

    private static void emptyStackPush() {

        Stack<String> stack = instanceOfTest();

        String top = "Hello";
        stack.push(top);

        System.out.println();

        if (stack.size() == 1) {
            pass("Success the size in EmptyStack push");
        } else {
            fail("Failure in size in EmptyStack push");
        }


        if (stack.isEmpty()) {
            fail("Failure in empty in Empty stack push");
        } else {
            pass("Success the empty in EmptyStack push");

        }

        try {
            String result = stack.peek();

            if (top.equals(result)) {
                pass("Success the peek in EmptyStack push");
            } else {
                fail("Failure in peek in EmptyStack push");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException in peek EmptyStack unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        try {
            String result = stack.pop();
            if (top.equals(result)) {
                pass("Success the pop in EmptyStack push");
            } else {
                fail("Failure in pop in EmptyStack push");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException in Pop empty Stack unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void oneElementPush() {

        System.out.println();
        Stack<String> stack = instanceOfTest();

        String push1 = "Hello";
        String top = "COMP 2450";

        stack.push(push1);
        stack.push(top);

        if (stack.size() == 2) {
            pass("Success the size in one Element Existing push");
        } else {
            fail("Failure in size in one Element Existing push");
        }

        if (!stack.isEmpty()) {
            pass("Success the empty in one Element Existing push");
        } else {
            fail("Failure in empty in one Element Existing push");
        }

        try {
            String result = stack.peek();
            if (top.equals(result)) {
                pass("Success the peek in one Element Existing push");
            } else {
                fail("Failure in peek in one Element Existing push");
            }

        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException in PEEK empty Stack unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        try {
            String result = stack.pop();
            if (top.equals(result)) {
                pass("Success the pop in one Element Existing push");
            } else {
                fail("Failure in pop in one Element Existing push");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException in Pop empty Stack unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }


    private static void twoElementPush() {

        System.out.println();
        Stack<String> stack = instanceOfTest();

        String push1 = "Hello";
        String push2 = "COMP 2450";
        String push3 = "UMANG";

        stack.push(push1);
        stack.push(push2);
        stack.push(push3);

        if (stack.size() == 3) {
            pass("Success the size in two Element Existing push");
        } else {
            fail("Failure in size in two Element Existing push");
        }

        if (!stack.isEmpty()) {
            pass("Success the empty in two Element Existing push");
        } else {
            fail("Failure in empty in two Element Existing push");
        }

        try {
            String result = stack.peek();
            if (push3.equals(result)) {
                pass("Success the peek in two Element Existing push");
            } else {
                fail("Failure in peek in two Element Existing push");
            }

        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException in PEEK empty Stack unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        try {
            String result = stack.pop();
            if (push3.equals(result)) {
                pass("Success the pop in two Element Existing push");
            } else {
                fail("Failure in pop in two Element Existing push");
            }

        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException in Pop empty Stack unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void testPop() {


        System.out.println("Testing POP with Empty Stack");
        emptyStackPop();

        System.out.println("Testing POP with One element Stack");
        oneElementPop();

        System.out.println("Testing POP with two element Stack");
        twoElementPop();
    }

    private static void emptyStackPop() {

        Stack<String> stack = instanceOfTest();

        try {
            String result = stack.pop();
            fail("Failure in pop in EmptyStack pop");
        } catch (Stack.EmptyStackException e) {
            pass("Success: Caught EmptyStackException POP as expected");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        if (stack.size() == 0) {
            pass("Success the size in EmptyStack pop");
        } else {
            fail("Failure in size in EmptyStack pop");
        }

        if (stack.isEmpty()) {
            pass("Success the empty in EmptyStack pop");
        } else {
            fail("Failure in empty in EmptyStack pop");
        }

        try {
            String result = stack.peek();
            fail("Failure in peek in EmptyStack pop");
        } catch (Stack.EmptyStackException e) {
            pass("Success: Caught EmptyStackException PEEK as expected");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void oneElementPop() {

        Stack<String> stack = instanceOfTest();
        stack.push("Hello");

        try {
            String result = stack.pop();
            if ("Hello".equals(result)) {
                pass("Success the pop in one Element pop");
            } else {
                fail("Failure in pop in one Element pop");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException POP unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        if (stack.size() == 0) {
            pass("Success the size in one Element pop");
        } else {
            fail("Failure in size in one Element pop");
        }

        if (stack.isEmpty()) {
            pass("Success the empty in one Element pop");
        } else {
            fail("Failure in empty in one Element pop");
        }

        try {
            String result = stack.peek();
            fail("Failure in peek in one Element pop");
        } catch (Stack.EmptyStackException e) {
            pass("Success: Caught EmptyStackException PEEK as expected");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void twoElementPop() {

        Stack<String> stack = instanceOfTest();
        stack.push("Hello");
        stack.push("COMP 2450");

        try {
            String result = stack.pop();
            if ("COMP 2450".equals(result)) {
                pass("Success the pop in two Element pop");
            } else {
                fail("Failure in pop in two Element pop");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException POP unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        if (stack.size() == 1) {
            pass("Success the size in two Element pop");
        } else {
            fail("Failure in size in two Element pop");
        }

        if (stack.isEmpty()) {
            fail("Failure in empty in two Element pop");
        } else {
            pass("Success the empty in two Element pop");
        }

        try {
            String result = stack.peek();
            if ("Hello".equals(result)) {
                pass("Success the peek in two Element pop");
            } else {
                fail("Failure in peek in two Element pop");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException PEEK unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void testSize() {

        System.out.println("Testing SIZE with NO element Stack");
        emptyStackSize();

        System.out.println("Testing SIZE with One element Stack");
        oneElementSize();

        System.out.println("Testing SIZE with TWO element Stack");
        twoElementSize();
    }

    private static void emptyStackSize() {

        Stack<String> stack = instanceOfTest();

        if (stack.size() == 0) {
            pass("Success the size in EmptyStack size");
        } else {
            fail("Failure in size in EmptyStack size");
        }

        if (stack.isEmpty()) {
            pass("Success the empty in EmptyStack size");
        } else {
            fail("Failure in empty in EmptyStack size");
        }

        try {
            String result = stack.peek();
            fail("Failure in peek in EmptyStack size");
        } catch (Stack.EmptyStackException e) {
            pass("Success: Caught EmptyStackException PEEK as expected");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        try {
            String result = stack.pop();
            fail("Failure in pop in EmptyStack size");
        } catch (Stack.EmptyStackException e) {
            pass("Success: Caught EmptyStackException POP as expected");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void oneElementSize() {

        Stack<String> stack = instanceOfTest();
        stack.push("Hello");

        if (stack.size() == 1) {
            pass("Success the size in one Element size");
        } else {
            fail("Failure in size in one Element size");
        }

        if (!stack.isEmpty()) {
            pass("Success the empty in one Element size");
        } else {
            fail("Failure in empty in one Element size");
        }

        try {
            String result = stack.peek();
            if ("Hello".equals(result)) {
                pass("Success the peek in one Element size");
            } else {
                fail("Failure in peek in one Element size");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException PEEK unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        try {
            String result = stack.pop();
            if ("Hello".equals(result)) {
                pass("Success the pop in one Element size");
            } else {
                fail("Failure in pop in one Element size");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException POP unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void twoElementSize() {

        Stack<String> stack = instanceOfTest();
        stack.push("Hello");
        stack.push("COMP 2450");

        if (stack.size() == 2) {
            pass("Success the size in two Element size");
        } else {
            fail("Failure in size in two Element size");
        }

        if (!stack.isEmpty()) {
            pass("Success the empty in two Element size");
        } else {
            fail("Failure in empty in two Element size");
        }

        try {
            String result = stack.peek();
            if ("COMP 2450".equals(result)) {
                pass("Success the peek in two Element size");
            } else {
                fail("Failure in peek in two Element size");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException PEEK unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        try {
            String result = stack.pop();
            if ("COMP 2450".equals(result)) {
                pass("Success the pop in two Element size");
            } else {
                fail("Failure in pop in two Element size");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException POP unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void testEmpty() {

        System.out.println("Testing EMPTY with NO element Stack");
        emptyStackEmpty();

        System.out.println("Testing EMPTY with One element Stack");
        oneElementEmpty();

        System.out.println("Testing EMPTY with TWO element Stack");
        twoElementEmpty();
    }

    private static void emptyStackEmpty() {

        Stack<String> stack = instanceOfTest();

        if (stack.isEmpty()) {
            pass("Success the empty in EmptyStack empty");
        } else {
            fail("Failure in empty in EmptyStack empty");
        }

        if (stack.size() == 0) {
            pass("Success the size in EmptyStack empty");
        } else {
            fail("Failure in size in EmptyStack empty");
        }

        try {
            String result = stack.peek();
            fail("Failure in peek in EmptyStack empty");
        } catch (Stack.EmptyStackException e) {
            pass("Success: Caught EmptyStackException PEEK as expected");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        try {
            String result = stack.pop();
            fail("Failure in pop in EmptyStack empty");
        } catch (Stack.EmptyStackException e) {
            pass("Success: Caught EmptyStackException POP as expected");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void oneElementEmpty() {

        Stack<String> stack = instanceOfTest();
        stack.push("Hello");

        if (!stack.isEmpty()) {
            pass("Success the empty in one Element empty");
        } else {
            fail("Failure in empty in one Element empty");
        }

        if (stack.size() == 1) {
            pass("Success the size in one Element empty");
        } else {
            fail("Failure in size in one Element empty");
        }

        try {
            String result = stack.peek();
            if ("Hello".equals(result)) {
                pass("Success the peek in one Element empty");
            } else {
                fail("Failure in peek in one Element empty");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException PEEK unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        try {
            String result = stack.pop();
            if ("Hello".equals(result)) {
                pass("Success the pop in one Element empty");
            } else {
                fail("Failure in pop in one Element empty");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException POP unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void twoElementEmpty() {

        Stack<String> stack = instanceOfTest();
        stack.push("Hello");
        stack.push("COMP 2450");

        if (!stack.isEmpty()) {
            pass("Success the empty in two Element empty");
        } else {
            fail("Failure in empty in two Element empty");
        }

        if (stack.size() == 2) {
            pass("Success the size in two Element empty");
        } else {
            fail("Failure in size in two Element empty");
        }

        try {
            String result = stack.peek();
            if ("COMP 2450".equals(result)) {
                pass("Success the peek in two Element empty");
            } else {
                fail("Failure in peek in two Element empty");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException PEEK unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        try {
            String result = stack.pop();
            if ("COMP 2450".equals(result)) {
                pass("Success the pop in two Element empty");
            } else {
                fail("Failure in pop in two Element empty");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException POP unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void testPeek() {

        System.out.println("Testing PEEK with NO element Stack");
        emptyStackPeek();

        System.out.println("Testing PEEK with ONe element Stack");
        oneElementPeek();

        System.out.println("Testing PEEK with TWO element Stack");
        twoElementPeek();
    }

    private static void emptyStackPeek() {

        Stack<String> stack = instanceOfTest();

        try {
            String result = stack.peek();
            fail("Failure in peek in EmptyStack peek");
        } catch (Stack.EmptyStackException e) {
            pass("Success: Caught EmptyStackException PEEK as expected");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        if (stack.size() == 0) {
            pass("Success the size in EmptyStack peek");
        } else {
            fail("Failure in size in EmptyStack peek");
        }

        if (stack.isEmpty()) {
            pass("Success the empty in EmptyStack peek");
        } else {
            fail("Failure in empty in EmptyStack peek");
        }

        try {
            String result = stack.pop();
            fail("Failure in pop in EmptyStack peek");
        } catch (Stack.EmptyStackException e) {
            pass("Success: Caught EmptyStackException POP as expected");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void oneElementPeek() {

        Stack<String> stack = instanceOfTest();
        stack.push("Hello");

        try {
            String result = stack.peek();
            if ("Hello".equals(result)) {
                pass("Success the peek in one Element peek");
            } else {
                fail("Failure in peek in one Element peek");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException PEEK unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        if (stack.size() == 1) {
            pass("Success the size in one Element peek");
        } else {
            fail("Failure in size in one Element peek");
        }

        if (!stack.isEmpty()) {
            pass("Success the empty in one Element peek");
        } else {
            fail("Failure in empty in one Element peek");
        }

        try {
            String result = stack.pop();
            if ("Hello".equals(result)) {
                pass("Success the pop in one Element peek");
            } else {
                fail("Failure in pop in one Element peek");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException POP unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void twoElementPeek() {

        Stack<String> stack = instanceOfTest();
        stack.push("Hello");
        stack.push("COMP 2450");

        try {
            String result = stack.peek();
            if ("COMP 2450".equals(result)) {
                pass("Success the peek in two Element peek");
            } else {
                fail("Failure in peek in two Element peek");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException PEEK unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }

        if (stack.size() == 2) {
            pass("Success the size in two Element peek");
        } else {
            fail("Failure in size in two Element peek");
        }

        if (!stack.isEmpty()) {
            pass("Success the empty in two Element peek");
        } else {
            fail("Failure in empty in two Element peek");
        }

        try {
            String result = stack.pop();
            if ("COMP 2450".equals(result)) {
                pass("Success the pop in two Element peek");
            } else {
                fail("Failure in pop in two Element peek");
            }
        } catch (Stack.EmptyStackException e) {
            fail("Failure: Caught EmptyStackException POP unexpectedly");
        } catch (Exception e) {
            fail("Some other exception was thrown.");
            e.printStackTrace();
        }
    }

    private static void pass(String message) {
        successes++;
        green("PASS: " + message);
    }

    private static void fail(String message) {
        failures++;
        red("FAIL: " + message);
    }

    private static void bubblePrint(String message) {
        try {
            System.out.println(FigletFont.convertOneLine(message));
        } catch (IOException ignored) {
        }
    }


}
