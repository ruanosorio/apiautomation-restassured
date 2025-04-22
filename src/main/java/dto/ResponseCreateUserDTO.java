package dto;

import lombok.Data;

@Data
public class ResponseCreateUserDTO {

    private String name;
    private String job;
    private String id;
    private String createdAt;

}
