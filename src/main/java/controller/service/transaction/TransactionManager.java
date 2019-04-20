package controller.service.transaction;

import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;

public interface TransactionManager<E> {

    E executeTransaction(TransactionUnit<E> unit) throws ServiceLayerException;

    void processVoidTransaction(VoidTransactionUnit unit) throws ServiceLayerException;
}
