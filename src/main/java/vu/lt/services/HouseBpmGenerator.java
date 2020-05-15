package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Future;


@ApplicationScoped
@Alternative
public class HouseBpmGenerator implements Serializable, GenerateBPM {

    @Override
    @Futureable
    public Future<Integer> generateBPM(){
        try {
            Thread.sleep(4000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        final Integer generatedBPM = new Random().nextInt((130 - 120) + 1) +120;
        return new AsyncResult<>(generatedBPM);
    }
}
