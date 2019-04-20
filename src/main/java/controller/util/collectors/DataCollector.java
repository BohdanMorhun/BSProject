package controller.util.collectors;

import controller.exception.WrongInputDataException;

import javax.servlet.http.HttpServletRequest;

/**
 * Abstract class for retrieving and validation data from request
 * See package impl for concrete entity
 * */
public abstract class DataCollector<E> {

    /**
     * @return some entity see package domain
     * @throws WrongInputDataException
     * */
    public abstract E retrieveData(HttpServletRequest request) throws WrongInputDataException;
}
