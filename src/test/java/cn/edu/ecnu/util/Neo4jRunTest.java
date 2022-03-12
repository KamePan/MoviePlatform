package cn.edu.ecnu.util;

import cn.edu.ecnu.model.dataobject.RateDO;
import cn.edu.ecnu.util.Neo4jWrapper;
import org.neo4j.driver.*;
import org.neo4j.driver.Result;

import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class Neo4jRunTest implements AutoCloseable  {

    private final Driver driver;

    public Neo4jRunTest(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void printGreeting( final String message ) {
        try (Session session = driver.session()) {
            String greeting = session.writeTransaction(tx -> {
                org.neo4j.driver.Result result = tx.run(
                        "CREATE (a:Greeting) " + "SET a.message = $message " +
                                "RETURN a.message + ', from node ' + id(a)",
                        parameters("message", message ));
                return result.single().get( 0 ).asString();
            });
            System.out.println(greeting);
        }
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

    public static void main( String... args ) throws Exception {
        /*Neo4jWrapper neo4jWrapper = Neo4jWrapper.newInstance();
        List<RateDO> rateDOS = neo4jWrapper.queryRatingMovieByUserId(440);
        System.out.println(rateDOS);*/

    }
}