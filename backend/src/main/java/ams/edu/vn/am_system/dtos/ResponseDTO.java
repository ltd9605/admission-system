package ams.edu.vn.am_system.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO {
    private int status;
    private String message;
    private Object body;
}
