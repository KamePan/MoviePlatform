package cn.edu.ecnu.util;

import cn.edu.ecnu.model.dataobject.RateDO;
import org.neo4j.driver.*;
import org.neo4j.driver.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Neo4jWrapper {

    private final Driver driver;

    private Neo4jWrapper(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public static Neo4jWrapper newInstance() {
        return new Neo4jWrapper("bolt://localhost:7687", "neo4j", "pan151312");
    }

    public void queryMovieByUserId(Integer id) {
        try (Session session = driver.session()){
            String status = session.writeTransaction(tx -> {
                Result result = tx.run(
                        "MATCH (u:User{id:\'" + id.toString() + "\'})-[r:RATED]-(m:Movie) " +
                                "WITH id(r) AS id, m.title AS title, r.grading AS grading " +
                                "RETURN id, title, grading "
                );
                List<Record> records = result.list();
                for (Record record: records) {
                    System.out.println(record.get(0));
                }
                return "success";
            });
        }
    }

    public List<RateDO> queryRatingMovieByUserId(Integer id) {
        try (Session session = driver.session()){
            List<RateDO> rateDOS = session.writeTransaction(tx -> {
                Result result = tx.run(
                        "MATCH (u:User{id:\'" + id.toString() + "\'})-[r:RATED]-(m:Movie) " +
                                "WITH id(r) AS id, m.title AS title, r.grading AS rate " +
                                "RETURN id, title, rate "
                );
                List<RateDO> rates = result.stream().map(record -> {
                    RateDO rateDO = new RateDO();
                    rateDO.setId(record.get("id").asInt());
                    rateDO.setTitle(String.valueOf(record.get("title")));
                    rateDO.setRate(record.get("rate").asDouble());
                    return rateDO;
                }).collect(Collectors.toList());
                return rates;
            });
            return rateDOS;
        }
    }
}
