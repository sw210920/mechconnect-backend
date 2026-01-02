package com.mechconnect.backend.dto;

/**
 * PasswordResetRequest
 *
 * Part of the MechConnect backend application.
 * Responsible for handling dto related logic.
 */


public class PasswordResetRequestDto {

    private Long customerId;
    private String oldPassword;
    private String newPassword;
    private Long mechanicId;

    
	//no-args constructor
    public PasswordResetRequestDto() {}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public Long getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(Long mechanicId) {
		this.mechanicId = mechanicId;
	}

}
