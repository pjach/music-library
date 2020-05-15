package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Future;

@ApplicationScoped
@Specializes
public class LowerTempoTrapBPMGenerator
    extends TrapBPMGenerator implements Serializable {

    @Override
    @Futureable
    public Future<Integer> generateBPM(){
        try {
            Thread.sleep(4000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        final Integer generatedBPM = new Random().nextInt((88 - 50) + 1) + 50;
        return new AsyncResult<>(generatedBPM);
    }
}
