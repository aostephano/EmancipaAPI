package dev.aostephano.emancipaapi.CramScrools.Models.CramSchool;

import dev.aostephano.emancipaapi.CramScrools.Models.Address.AddressMapper;
import dev.aostephano.emancipaapi.CramScrools.Models.Address.AddressResponse;

public class CramSchoolMapper {

    public static CramSchoolResponse fromCramSchoolToResponse(CramSchool cramSchool){
        AddressResponse addressResponse = AddressMapper.fromAddressToResponse(cramSchool.getAddress());

        return new  CramSchoolResponse(
                cramSchool.getUuid(),
                cramSchool.getName(),
                addressResponse,
                cramSchool.getBusinessHour(),
                cramSchool.getDescription()
        );
    }

    public static CramSchool fromRequestToCramSchool(CramSchoolRequest cramSchoolRequest){

        return new CramSchool(
                cramSchoolRequest
        );
    }


}
