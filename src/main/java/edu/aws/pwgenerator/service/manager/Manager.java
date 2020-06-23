package edu.aws.pwgenerator.service.manager;

import edu.aws.pwgenerator.service.Status;

public interface Manager {

    public Status init();
    public void update(Status st);
}
