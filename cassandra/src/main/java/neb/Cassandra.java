package neb;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Cassandra {
    private static final String replString = "WITH REPLICATION = { \'class\' : \'SimpleStrategy\', \'replication_factor\' : 3 }";
    private Session session;
    private Cluster cluster;

    public void connect() {
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect();
    }

    public void createSchema() {
        session.execute("CREATE KEYSPACE IF NOT EXISTS nebula " +
                replString + ";");
        session.execute("CREATE TABLE IF NOT EXISTS nebula.products ("
                + "id uuid PRIMARY KEY,"
                + "name text);");
        session.execute("CREATE TABLE IF NOT EXISTS nebula.productreviews ("
                + "id uuid PRIMARY KEY,"
                + "productId uuid,"
                + "reviewerName text,"
                + "email text,"
                + "rating int,"
                + "comments text);");
        session.execute("CREATE TABLE IF NOT EXISTS nebula.productreviewstates ("
                + "id uuid PRIMARY KEY,"
                + "productReviewId uuid,"
                + "state text);");
    }

    public void insert(String uuid, String name) {
        session.execute("INSERT INTO nebula.products (id, name) "
                + "VALUES (" + uuid + ", \"" + name.replace('\'', '.') + "\");");
    }

    public void close() {
        cluster.close();
    }

    public static void main(String[] args) {
        Cassandra client = new Cassandra();
        client.connect();

        client.createSchema();
        try (Stream<String> lines = Files.lines(Paths.get(args[0]))) {
            lines.map(line -> line.split("\\t")).forEach(
                    x -> client.insert(UUIDs.timeBased().toString(), x[1])
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
    }

}