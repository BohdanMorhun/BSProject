package controller.constants;

/**Some messages to log which duplicated with error messages
 * */
public interface LogMessages {

    /*AbstractGenericService methods*/
    String INCORRECT_INPUT_DATA = "Incorrect input data";

    String GETTING_ELEMENT_BY_ID = "Try to get element by id";
    String UPDATING_ELEMENT = "Updating element";
    String INSERTING_ELEMENT = "Inserting element";
    String DELETING_ELEMENT = "Deleting element";
    String GETTING_ELEMENTS_AMOUNT = "Getting elements amount";
    String GETTING_PAGINATED_LIST = "Getting paginated list";

    String CAN_NOT_GET_ELEMENT_BY_ID = "Couldn't get element by id";
    String CAN_NOT_UPDATE_ELEMENT = "Couldn't update element";
    String CAN_NOT_INSERT_ELEMENT = "Couldn't insert element";
    String CAN_NOT_DELETE_ELEMENT = "Couldn't delete element";
    String CAN_NOT_GET_ELEMENTS_AMOUNT = "Couldn't get elements amount.";
    String CAN_NOT_GET_PAGINATED_LIST = "Couldn't get paginated list.";

    /*AdminServiceImpl methods*/
    String GETTING_ADMIN_BY_USER_ID = "Try to get admin info by user id";
    String CAN_NOT_GET_ADMIN_BY_USER_ID = "Couldn't get admin info";

    /*BusServiceImpl methods*/
    String GET_FREE_BUSES = "Getting fre buses";
    String CAN_NOT_GET_FREE_BUSES = "Couldn't get free buses";
}
