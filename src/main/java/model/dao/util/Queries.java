package model.dao.util;

public interface Queries {

    default String getUpdateElementQuery(){return null;}

    default String getDeleteElementQuery(){return null;}

    default String getElementByIdQuery(){return null;}

    default String getInsertElementQuery(){return null;}

    default String getElementsAmountQuery(){return null;}

    default String getPaginatedListQuery(){return null;}

}
