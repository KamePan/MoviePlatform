package cn.edu.ecnu.model.dto;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("movie")
@Data
public class MovieDTO {

    @Id
    Integer id;

    @Property
    String title;

}
