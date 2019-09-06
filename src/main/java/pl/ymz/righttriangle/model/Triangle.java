package pl.ymz.righttriangle.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Triangle {

    private Point firsrVertex;
    private Point secondVertex;
    private Point thirdVertex;

}
