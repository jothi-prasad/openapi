package org.openapitools.api.impl;

import javax.servlet.ServletRequest;

public interface BaseAuthorize {
    public boolean authorize(ServletRequest request);

    public boolean evaluateRequest(String headerParam);
}
