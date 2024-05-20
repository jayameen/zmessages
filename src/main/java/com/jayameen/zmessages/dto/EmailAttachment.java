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
public class EmailAttachment {

    @JsonProperty("attachment_name")
    private String attachmentName;

    @JsonProperty("attachment_base64")
    private String attachmentBase64;

}