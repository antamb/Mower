import bean.Mower;
import bean.Plot;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javafx.geometry.Point3D;
import service.MowerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Steps {
    MowerService service = new MowerService();


    @Given("^A plot with a dimension of (\\d+) (\\d+)$")
    public void A_plot_with_a_dimension_of(int x, int y) throws Throwable {
        Plot plot = new Plot(x,y);
        service.setPlot(plot);
    }

    @Given("^An initial position to (\\d+) (\\d+) \"([^\"]*)\"$")
    public void A_initial_position_to(int x, int y, String direction) throws Throwable {
        List<Mower> mowers = new ArrayList<>();
        mowers.add(new Mower(x,y, Mower.DIRECTION.valueOf(direction)));
        service.setMowers(mowers);
    }

    @Given("^I get the following instructions:$")
    public void I_get_the_following_instructions(List<Mower.MOVE> instructions) throws Throwable {
        List<Mower> mowers = service.getMowers();
        service.getMowers().get(0).setInstructions(instructions);
    }

    @When("^I browse the entire surface of the plot$")
    public void I_browse_the_entire_surface_of_the_plot() throws Throwable {
        service.getMowers().get(0).browse(service.getPlot());
    }

    @Then("^The final position should be (\\d+) (\\d+) \"([^\"]*)\"$")
    public void The_final_position_should_be(int x, int y, String direction) throws Throwable {
        Point3D position  = service.getMowers().get(0).getPosition();
        assertTrue(position.getX() == x && position.getY() == y && Mower.DIRECTION.valueOf(direction) == Mower.getDirection((int)position.getZ()));
    }
}
