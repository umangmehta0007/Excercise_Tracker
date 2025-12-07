package comp2450.Model;

import comp2450.Exceptions.InvalidNameException;
import comp2450.Exceptions.InvalidUsageException;
import comp2450.Model.Person.Gears;
import comp2450.tests.TestResults;

import static comp2450.UI.OutputTools.green;
import static comp2450.UI.OutputTools.red;

public class TestGear {

    private int successes;
    private int failures;

    public TestGear(){}


    public TestResults runTests(){

        testInvalidName();
        testInvalidUses();
        testCreateGear();

        return new TestResults(successes, failures);
    }

    private void testInvalidName(){

        /*
        This is a class that is static, and I've call on this class to create my gears.
        Builder have method inside them that throws exception and check for validity, which are caught by UI.
        And UI takes it exception catches it and then runs it again to ask for input.

        If Name.isBlank() throwsInvalidNameException, so this is good.
        My builder is responsible to check if the number is greater than or less than something too.
         */
        Gears.GearsBuilder builder = new Gears.GearsBuilder();

        try {
            builder.nameBuilder("");
            fail("Should not have succeeded in creating empty name");
        } catch (InvalidNameException e) {
            pass("Successfully Rejected Empty String for Name");
        }catch(Exception e){
            fail("Some other exception throws");
            e.printStackTrace();
        }

    }

    // THis is my happy path
    private void testCreateGear(){

        Gears.GearsBuilder builder = new Gears.GearsBuilder();


        try{

            Gears gear = builder.nameBuilder("Shoes").usageBuilder("To run").build();

            if(!gear.getName().equals("Shoes")){

                fail("gear name was not set correctly got: "+gear.getName()+ ".Excepected Shoes");
            }else if(!gear.getUses().equals("To run")){
                fail("Gear usage was not set correctly got: "+ gear.getUses()+". Expected To run");
            }

            pass("All properties in Gears were set as expected");

        }catch(Exception e){
            fail("Exception thrown for happy path");
        }

    }

    private void testInvalidUses(){

        Gears.GearsBuilder builder = new Gears.GearsBuilder();

        try {
            builder.usageBuilder("");

            fail("Should not have succeeded in creating empty name");

        } catch (InvalidUsageException e) {
            pass("Successfully Rejected Empty String for Usage");
        }catch(Exception e){
            fail("Some other exception throws");
            e.printStackTrace();
        }
    }


    private void pass(String message) {
        successes++;
        green("PASS: " + message);
    }

    private void fail(String message) {
        failures++;
        red("FAIL: " + message);
    }
}
