package com.rubicon.water.rubiconWater.applications.models.farmer;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FarmerDTO class is for validating the RequestBody .
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmerDTO {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("middleName")
    private String middleName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("emailId")
    private String emailId;

    /**
     * This function is for validating FarmerDTO model .
     * This function return 'true' if and only if , all the conditions are false
     *
     * @return boolean value which is "true".
     */
    public boolean isValid(){

        if (firstName == null || firstName.trim().isEmpty())
            throw new IllegalArgumentException("The firstName can not be null or empty");

        if (middleName == null || middleName.trim().isEmpty())
            throw new IllegalArgumentException("The middleName can not be null or empty");

        if (lastName == null || lastName.trim().isEmpty())
            throw new IllegalArgumentException("The lastName can not be null or empty");

        if (mobile == null || mobile.trim().isEmpty())
            throw new IllegalArgumentException("The mobile can no tbe null or empty");

        if (emailId == null || emailId.trim().isEmpty())
            throw new IllegalArgumentException("The email id can not be null or empty");

        return true;
    }

}
