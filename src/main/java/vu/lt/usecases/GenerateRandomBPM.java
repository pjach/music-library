package vu.lt.usecases;

import vu.lt.services.GenerateBPM;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@SessionScoped
@Named
public class GenerateRandomBPM implements Serializable {

    @Inject
    GenerateBPM bpmGenerator;

    private Future<Integer> bpmGenerationTask = null;

    public String generateNewBPM() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        bpmGenerationTask = bpmGenerator.generateBPM();
        return  "/songDetails.xhtml?faces-redirect=true&songId=" + requestParameters.get("songId");
    }

    public boolean isBPMGenerationRunning() {
        return bpmGenerationTask != null && !bpmGenerationTask.isDone();
    }

    public String getBPMGenerationStatus() throws ExecutionException, InterruptedException {
        if (bpmGenerationTask == null) {
            return null;
        } else if (isBPMGenerationRunning()) {
            return "BPM generation in progress";
        }
        return "Suggested BPM: " + bpmGenerationTask.get();
    }
}
