package cn.edu.ecnu.model.dataobject;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.io.Serializable;

@Data
@Node
public class RateDO implements Serializable {

    @Id
    Integer id;

    @Property("rate")
    Double rate;

    @Property("title")
    String title;
}
