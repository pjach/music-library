package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import java.util.concurrent.Future;

public interface GenerateBPM {

    @Futureable
    public Future<Integer> generateBPM();
}
