package com.jayameen.zmessages.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayameen.zmessages.utils.ZUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Madan KN
 */

@RequiredArgsConstructor
public @Data class MessageRequest implements Serializable {

    @JsonProperty("file_name")
    @Setter(AccessLevel.NONE)
    private String fileName;

    @JsonProperty("file_path")
    @Setter(AccessLevel.NONE)
    private String filePath;

    @JsonProperty("file_url")
    private String fileUrl;

    @JsonProperty("is_private")
    private Boolean isPrivate = true;

    @JsonProperty("base64_data")
    private String base64Data;

    @JsonProperty("byte_array")
    private byte[] byteArray;

    public void setFileName(String value){
        this.fileName = ZUtils.cleanFileName(value);
    }

    public void setFilePath(String value){
        this.filePath = ZUtils.cleanFilePath(value);
    }

}
