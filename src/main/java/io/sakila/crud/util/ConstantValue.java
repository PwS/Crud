package io.sakila.crud.util;

import java.time.Instant;
import java.util.Date;

public class ConstantValue {
    private ConstantValue() {

    }

    public static final String DEFAULT_CREATED_BY = "System";
    public static final Date DEFAULT_DATE_NOW = Date.from(Instant.now());
    public static final String RESPONSE_SUCCESS = "Success";
    public static final String RESPONSE_FAILED = "Failed";

    public static final String GLB_MESSAGE_FOUND = "Data Found !";
    public static final String GLB_MESSAGE_NOT_FOUND = "Data Not Found !";


    public static final String UNRECOGNIZED = "Unrecognized";

    public static final String DELETED = "Deleted";
    public static final String UPDATED = "Updated";


    /**
     * SUCCESS
     */
    public static final String SUCCESS_CREATE_DATA = "Berhasil Create Data";
    public static final String SUCCESS_UPDATE_DATA = "Berhasil Update Data";
    public static final String SUCCESS_DELETE_DATA = "Berhasil Delete Data";

    /**
     * FAILED
     */
    public static final String FAILED_CREATE_DATA = "Gagal Create Data";
    public static final String FAILED_UPDATE_DATA = "Gagal Update Data";
    public static final String FAILED_DELETE_DATA = "Gagal Delete Data";


}
