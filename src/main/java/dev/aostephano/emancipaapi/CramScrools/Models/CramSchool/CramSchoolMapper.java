package dev.aostephano.emancipaapi.CramScrools.Models.CramSchool;

import dev.aostephano.emancipaapi.CramScrools.Models.Address.AddressMapper;

public class CramSchoolMapper {

  public static CramSchoolResponse fromCramSchoolToResponse(CramSchool cramSchool) {

    var addressResponse = AddressMapper.fromAddressToResponse(cramSchool.getAddress());

    return new CramSchoolResponse(cramSchool.getUuid(), cramSchool.getName(),
        cramSchool.getCramSchoolSuffix(), cramSchool.getSchool(), addressResponse,
        cramSchool.getBusinessHour(), cramSchool.getDescription(), cramSchool.isActive());
  }

  public static CramSchool fromRequestToCramSchool(CramSchoolRequest cramSchoolRequest) {

    return new CramSchool(cramSchoolRequest);
  }
}
