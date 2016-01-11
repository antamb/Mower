package service;

import bean.Mower;
import bean.Plot;
import exception.IllegalMoveException;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class MowerService {

    Plot plot;
    List<Mower> mowers;

    public MowerService(){
        mowers = new LinkedList<>();
    }

    /**
     *
     * Initialize the information of the plot and mowers for the service
     *
     * @param lines
     */
    public void init(Stream<String> lines) {
        lines.forEach(line -> {

            if (Pattern.matches("[0-9]+\\s[0-9]+", line)){
                String[] array = line.split(" ");
                plot = new Plot(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            } else if (Pattern.matches("[0-9]+\\s[0-9]+\\s[NWES]{1}", line)) {
                String[] array = line.split(" ");
                mowers.add(new Mower(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Mower.DIRECTION.valueOf(String.valueOf(array[2]))));
            } else if (Pattern.matches("[GDA]*", line)) {
                String[] array = line.split("");
                List<Mower.MOVE> instructions = new ArrayList<>();
                for (String instruction : array) {
                    instructions.add(Mower.MOVE.valueOf(instruction));
                }

                mowers.get(mowers.size() - 1).setInstructions(instructions);
            }
        });
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(final Plot plot) {
        this.plot = plot;
    }

    public void setMowers(final List<Mower> mowers) {
        this.mowers = mowers;
    }

    public List<Mower> getMowers() {
        return mowers;
    }


    /**
     * Execute the list of instructions for each mower
     */
    public void start(){
        if (mowers != null || plot != null) {
            mowers.stream().forEach(mower -> {
                try {
                    mower.browse(plot);
                } catch (IllegalMoveException e) {
                    e.printStackTrace();
                }
                System.out.println(mower.toString());
            });
        } else {
            throw new IllegalStateException("The service has not been initialized");
        }
    }
}
