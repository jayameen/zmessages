package com.jayameen.zmessages.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Madan KN
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    @JsonProperty("from_address")
    private String fromAddress;

    @JsonProperty("to_address_list")
    private List<String> toAddress;

    @JsonProperty("cc_address_list")
    private List<String> ccAddress;

    @JsonProperty("bcc_address_list")
    private List<String> bccAddress;

    @JsonProperty("msg_body")
    private String msgBody;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("attachment_name")
    private String attachmentName;

    @JsonProperty("attachment_base64")
    private String attachmentBase64;

}