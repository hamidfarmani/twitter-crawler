/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.enums;

/**
 *
 * @author Hamid
 */
public enum Status {

    OK(1, "OK"),
    CONFLICT(-1, "CONFLICT"),
    NOT_FOUND(-2, "NOT FOUND"),
    BAD_JSON(-3,"BAD JSON");
    

    private int statusCode;
    private String statusDesc;

    Status(int statusCode, String status) {
        this.statusCode = statusCode;
        this.statusDesc = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

}
