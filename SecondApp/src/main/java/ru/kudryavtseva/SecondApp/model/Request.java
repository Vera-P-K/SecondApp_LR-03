package ru.kudryavtseva.SecondApp.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Request {

    @NotBlank
    private @Pattern(regexp = "^(?!.*123).*$") String uid;

    @NotBlank
    private String operationUid;

    private String systemName;


    private String systemTime;
    private String source;



    private @Min(1) @Max(1000000) Integer communicationId;
    private Integer  templateId;
    private Integer productCode;
    private Integer smsCode;

   // @Override
  //  public String toString() {
    //    return "{"+
    //            "uid='"+ uid+'\''+
    //            ", operationUid='"+ operationUid+'\''+
    //            ", systemName='"+ systemName+'\''+
    //           ", systemTime='"+ systemTime+ '\''+
    //            ", source='"+ source+'\''+
    //            ", communicationId="+ communicationId+
    //            ", templateId="+ templateId+
    //            ", productCode="+ productCode+
    //            ", smsCode="+ smsCode+
    //            "}";
    //}
}
