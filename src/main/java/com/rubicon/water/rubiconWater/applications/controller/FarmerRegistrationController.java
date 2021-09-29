package com.rubicon.water.rubiconWater.applications.controller;


import com.rubicon.water.rubiconWater.applications.exceptions.BadRequestException;
import com.rubicon.water.rubiconWater.applications.models.farmer.FarmerDTO;
import com.rubicon.water.rubiconWater.commons.Helper;
import com.rubicon.water.rubiconWater.domains.entities.Farmer;
import com.rubicon.water.rubiconWater.infrastructre.farmerRepository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.rubicon.water.rubiconWater.commons.Constants.validEmailRegex;
import static com.rubicon.water.rubiconWater.commons.Constants.validPhoneRegex;


/**
 * This is the FarmerRegistrationController class which provides the set of API to farmer for registration
 */

@RestController
@RequiredArgsConstructor
@Slf4j
public class FarmerRegistrationController {

    private final FarmerRepository farmerRepository;


    /**
     * This function provides the endPoint to register the farmer
     *
     * @param farmerContext the requestBody context to register farmer
     * @return it returns ok if farmer registers successfully otherwise returns required status code with error MSG.
     */
    @PostMapping("/farmer")
    public ResponseEntity<?> registerFarmer(@RequestBody FarmerDTO farmerContext) {

        try {

            if (farmerContext == null)
                throw new BadRequestException("the request body can not be null or empty.");
            //validating the request body context
            if (!farmerContext.isValid())
                throw new BadRequestException("The request body doesn't seems to be valid");

            if (!farmerContext.getEmailId().matches(validEmailRegex))
                throw new IllegalArgumentException("The provided email does not seems to be valid");

            if (!farmerContext.getMobile().matches(validPhoneRegex))
                throw new IllegalArgumentException("The provided mobile does not seems to be valid");

            //generates farmer id
            String farmerId = Helper.generateFarmerId(farmerContext.getFirstName() +
                    "" + farmerContext.getLastName());

            //generates farmId
            String farmId = Helper.generateFarmId(farmerId);

            Farmer farmer = Farmer.Factory.createFarmer(farmerId, farmId, farmerContext.getFirstName(), farmerContext.getMiddleName(),
                    farmerContext.getLastName(), farmerContext.getMobile(), farmerContext.getEmailId(), null);

            //it saves the farmer in db
            farmerRepository.save(farmer);

            return ResponseEntity.created(URI.create("farmerId:" + farmer.getFarmerId().concat("farmId:" + farmer.getFarmId()))).build();

        } catch (BadRequestException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage())
                    .contentType(MediaType.APPLICATION_JSON).build();
        } catch (DuplicateKeyException keyException) {
            return ResponseEntity.badRequest().header("message", "There seems to be farmer config" + keyException.getCause())
                    .contentType(MediaType.APPLICATION_JSON).build();
        }
    }


}
