import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Engine implements Runnable {
    private Connection connection;
    private Scanner scanner;

    Engine(Connection connection) {
        this.connection = connection;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        try {
            //this.getVillainsNames();
            //this.getMinionNames();
            //this.addMinion();
            //this.changeTownNamesCasing();
            //this.removeVillain();
            //this.increaseMinionsAge();
            //this.increaseAgeStoredProcedure();
            this.printAllMinionNames();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getVillainsNames() throws SQLException {
        PreparedStatement preparedStatement = this.connection.
                prepareStatement("SELECT v.name, COUNT(mv.minion_id) as minion_count\n" +
                        "FROM villains v\n" +
                        "JOIN minions_villains mv\n" +
                        "ON v.id = mv.villain_id\n" +
                        "GROUP BY v.name\n" +
                        "HAVING minion_count > ?\n" +
                        "ORDER BY minion_count DESC\n");

        preparedStatement.setInt(1, Integer.parseInt(scanner.nextLine()));

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(String.format("%s %d", resultSet.getString(1), resultSet.getInt(2)));
        }
    }

    private void getMinionNames() throws SQLException {
        int villainId = Integer.parseInt(scanner.nextLine());

        if (this.checkIfEntityExistsById(villainId, "villains")) {
            System.out.println(String.format("No villain with ID %d exists in the database.", villainId));
            return;
        }

        PreparedStatement preparedStatement = this.connection
                .prepareStatement("SELECT v.name, m.name, m.age\n" +
                        "FROM minions m\n" +
                        "JOIN minions_villains mv on m.id = mv.minion_id\n" +
                        "JOIN villains v on mv.villain_id = v.id\n" +
                        "WHERE mv.villain_id = ?");

        preparedStatement.setInt(1, villainId);

        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println(String.format("Villain: %s", resultSet.getString(1)));

        int ssn = 1;
        while (resultSet.next()) {
            System.out.println(String.format("%d. %s %d", ssn++, resultSet.getString(2), resultSet.getInt(3)));
        }

        if (ssn == 1) {
            System.out.println("<no minions>");
        }
    }

    private boolean checkIfEntityExistsById(int villainId, String tableName) throws SQLException {
        PreparedStatement preparedStatement = this.connection
                .prepareStatement("SELECT id FROM " + tableName + " WHERE id = ?");

        preparedStatement.setInt(1, villainId);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    private void addMinion() throws SQLException {
        String[] minionParams = scanner.nextLine().split("\\s+");
        String[] villainParams = scanner.nextLine().split("\\s+");

        String minionName = minionParams[1];
        int minionAge = Integer.parseInt(minionParams[2]);
        String townName = minionParams[3];
        String villainName = villainParams[1];

        if (this.checkIfEntityExistsByName(townName, "towns")) {
            this.insertTown(townName);
        }

        int townId = this.getEntityId(townName, "towns");

        this.insertMinion(minionName, minionAge, townId);

        int minionId = this.getEntityId(minionName, "minions");

        if (this.checkIfEntityExistsByName(villainName, "villains")) {
            this.insertVillain(villainName);
        }

        int villainId = this.getEntityId(villainName, "villains");

        this.hireMinion(minionId, villainId);

        System.out.println(String.format("Successfully added %s to be minion of %s.", minionName, villainName));
    }

    private void hireMinion(int minionId, int villainId) throws SQLException {
        PreparedStatement preparedStatement = this.connection
                .prepareStatement("INSERT INTO minions_villains (minion_id, villain_id) VALUE (?, ?)");

        preparedStatement.setInt(1, minionId);
        preparedStatement.setInt(2, villainId);

        preparedStatement.executeUpdate();
    }

    private void insertVillain(String villainName) throws SQLException {
        PreparedStatement preparedStatement = this.connection
                .prepareStatement("INSERT INTO villains (name, evilness_factor) VALUES (?, ?)");

        preparedStatement.setString(1, villainName);
        preparedStatement.setString(1, "evil");

        preparedStatement.executeUpdate();

        System.out.println(String.format("Villain %s was added to the database.", villainName));
    }

    private void insertMinion(String minionName, int minionAge, int townId) throws SQLException {
        PreparedStatement newMinion = this.connection
                .prepareStatement("INSERT INTO minions (name, age, town_id) VALUES (?, ?, ?)");

        newMinion.setString(1, minionName);
        newMinion.setInt(2, minionAge);
        newMinion.setInt(3, townId);

        newMinion.executeUpdate();
    }

    private int getEntityId(String name, String tableName) throws SQLException {
        PreparedStatement preparedStatement = this.connection
                .prepareStatement("SELECT id FROM " + tableName + " WHERE name = ?");

        preparedStatement.setString(1, name);

        final ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt(1);
    }

    private boolean checkIfEntityExistsByName(String entityName, String tableName) throws SQLException {
        PreparedStatement preparedStatement = this.connection
                .prepareStatement("SELECT id FROM " + tableName + " WHERE name = ?");

        preparedStatement.setString(1, entityName);

        ResultSet resultSet = preparedStatement.executeQuery();

        return !resultSet.next();
    }

    private void insertTown(String townName) throws SQLException {
        PreparedStatement newTown = this.connection
                .prepareStatement("INSERT INTO towns (name, country) VALUES (?, ?)");

        newTown.setString(1, townName);
        newTown.setString(2, null);

        newTown.executeUpdate();

        System.out.println(String.format("Town %s was added to the database.", townName));
    }

    private void changeTownNamesCasing() throws SQLException {
        final String countryName = scanner.nextLine();

        final PreparedStatement preparedStatement = this.connection
                .prepareStatement("SELECT name FROM towns WHERE country = ?");

        preparedStatement.setString(1, countryName);

        final ResultSet resultSet = preparedStatement.executeQuery();

        int counter = 0;
        List<String> towns = new ArrayList<>();

        while (resultSet.next()) {
            final String toUpperCaseTown = resultSet.getString(1).toUpperCase();

            towns.add(toUpperCaseTown);

            this.updateTownsTable(toUpperCaseTown, countryName);

            counter++;
        }

        if (counter == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        System.out.println(counter + " town names were affected.");
        System.out.println(towns);
    }

    private void updateTownsTable(String town, String country) throws SQLException {
        PreparedStatement preparedStatement = this.connection
                .prepareStatement("UPDATE towns SET name = ? WHERE country = ?");

        preparedStatement.setString(1, town);
        preparedStatement.setString(2, country);

        preparedStatement.executeUpdate();
    }

    private void removeVillain() throws SQLException {
        int villainId = Integer.parseInt(scanner.nextLine());

        if (!this.checkIfEntityExistsById(villainId, "villains")) {
            System.out.println("No such villain was found");
            return;
        }

        final int releasedMinions = this.releaseMinionsFromVillain(villainId);

        this.deleteVillain(villainId);

        System.out.println(releasedMinions + " minions released");
    }

    private int releaseMinionsFromVillain(int villainId) throws SQLException {
        final PreparedStatement preparedStatement = this.connection
                .prepareStatement("DELETE FROM minions_villains WHERE villain_id = ?");

        preparedStatement.setInt(1, villainId);

        return preparedStatement.executeUpdate();
    }

    private void deleteVillain(int villainId) throws SQLException {
        final String villainName = this.villainName(villainId);
        final PreparedStatement preparedStatement = this.connection
                .prepareStatement("DELETE FROM villains WHERE id = ?");

        preparedStatement.setInt(1, villainId);

        preparedStatement.executeUpdate();

        System.out.println(villainName + " was deleted");
    }

    private String villainName(int villainId) throws SQLException {
        final PreparedStatement preparedStatement = this.connection
                .prepareStatement("SELECT name FROM villains WHERE id = ?");

        preparedStatement.setInt(1, villainId);

        final ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getString(1);
    }

    private void printAllMinionNames() throws SQLException {
        final PreparedStatement preparedStatement = this.connection
                .prepareStatement("SELECT name FROM minions");

        final ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.last();
        final int size = resultSet.getRow();

        for (int i = 0; i < size; i++) {
            resultSet.absolute(i + 1);
            System.out.println(resultSet.getString(1));

            resultSet.absolute(size - i);
            System.out.println(resultSet.getString(1));
        }
    }

    private void increaseMinionsAge() throws SQLException {
        int[] minionsIds = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int minionId : minionsIds) {
            this.updateMinionAge(minionId);
            this.makeNameTitleCase(minionId);
            this.displayMinionInfo(minionId);
        }
    }

    private void displayMinionInfo(int minionId) throws SQLException {
        PreparedStatement preparedStatement = this.connection
                .prepareStatement("SELECT name, age FROM minions WHERE id = ?");

        preparedStatement.setInt(1, minionId);

        final ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2));
    }

    private void makeNameTitleCase(int minionId) throws SQLException {
        PreparedStatement preparedStatement = this.connection
                .prepareStatement("UPDATE minions SET name = concat(upper(left(name, 1)), substring(name, 2)) WHERE id = ?");

        preparedStatement.setInt(1, minionId);

        preparedStatement.executeUpdate();
    }

    private void updateMinionAge(int minionId) throws SQLException {
        PreparedStatement preparedStatement = this.connection
                .prepareStatement("UPDATE minions SET age = age + 1 WHERE id = ?");

        preparedStatement.setInt(1, minionId);

        preparedStatement.executeUpdate();
    }

    private void increaseAgeStoredProcedure() throws SQLException {
        int minionId = Integer.parseInt(scanner.nextLine());

        CallableStatement callableStatement = this.connection
                .prepareCall("CALL usp_get_older(?)");

        callableStatement.setInt(1, minionId);

        callableStatement.execute();
    }
}
