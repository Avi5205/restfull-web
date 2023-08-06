package rest.webservices.restfulwebservices.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {

    private Integer id;
    private String name;
    private LocalDate birthDate;
}
