package com.jayameen.zmessages.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Madan KN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMSDetails {

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("phone_prefix")
    private String phonePrefix;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("content")
    private String content;

}
