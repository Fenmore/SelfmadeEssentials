package de.wonderworld.plugins.selfmadeEssentials.exceptions;

public class ArgumentNumberExpectedException extends Throwable {

    private String argument;

    public ArgumentNumberExpectedException(String arg) {
        this.argument = arg;
    }

    public String getArgument() {
        return argument;
    }
}
