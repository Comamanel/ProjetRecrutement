package be.ucm.projetrecrutementapi.cucumber;

import io.cucumber.java.ParameterType;

public class CustomParameters {
    @ParameterType("true|false")
    public boolean bool(String boo){
        return boo.equals("true");
    }

}
