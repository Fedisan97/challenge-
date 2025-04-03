 
package com.company;

import java.util.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static ResultSet resultSet;
    private static String randomNumber;

    public Main() {
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = null;

        conn = DriverManager.getConnection("jdbc:postgresql://45.67.56.214:5424/" + "user4", "user4", "jG9d7YhI");

        Statement statement = conn.createStatement();
        Scanner gg = new Scanner(System.in);
        System.out.println("привет,это лут-бокс, где ты проиграешь квартиру:)");
        System.out.println("Выберите лут-бокс\nРыцарский\nГеройский\nБожественный");

        Probability probability = new Probability();

        String loot = gg.nextLine();
        switch (loot) {
            case "Рыцарский": {
                ResultSet St = statement.executeQuery(
                        "SELECT item.name, category.name as category_name, lootboxitems.lootbox_id as loot_id, loot.name as lname, loot.description as desc, loot.rollnumber as roll, rarity.numbers as probability, category.id as id  " +
                                " FROM category" +
                                " INNER JOIN item " +
                                " ON item.category = category.id" +
                                " INNER JOIN lootboxitems " +
                                " ON category.id = lootboxitems.item_id" +
                                " INNER JOIN loot" +
                                " ON lootboxitems.lootbox_id = loot.id"+
                                " INNER JOIN rarity" +
                                " ON rarity.id = category.id\n" +
                                "WHERE loot.name = 'Рыцарский' "
                );

                while (St.next()) {
                    String vue1 = St.getString("name");
                    String vue2 = St.getString("category_name");
                    String vue3 = St.getString("loot_id");
                    String vue4 = St.getString("lname");
                    String vue5 = St.getString("desc");
                    String vue6 = St.getString("roll");
                    String vue7 = St.getString("probability");
                    String vue8 = St.getString("id");

                    String textSQL = vue1 + " | " + vue2 + " | " + vue3 + " | " + vue4 + " | " + vue5 + " | " + vue6 + " | " + vue7 + " | " + vue8;

                    probability.recordAndPrintSQL(textSQL, vue8, vue7);
                }
                probability.probabilityDistribution();
                break;
            }
            case "Геройский": {
                ResultSet St = statement.executeQuery(

                        "SELECT item.name, category.name as category_name, lootboxitems.lootbox_id as loot_id, loot.name as lname, loot.description as desc, loot.rollnumber as roll, rarity.numbers as probability, category.id as id  " +
                                " FROM category" +
                                " INNER JOIN item " +
                                " ON item.category = category.id" +
                                "  INNER JOIN lootboxitems " +
                                " ON category.id = lootboxitems.item_id" +
                                " INNER JOIN loot" +
                                " ON lootboxitems.lootbox_id = loot.id"+
                                " INNER JOIN rarity" +
                                " ON rarity.id = category.id\n" +
                                "WHERE loot.name = 'Геройский' "
                );
                while (St.next()) {
                    String vue1 = St.getString("name");
                    String vue2 = St.getString("category_name");
                    String vue3 = St.getString("loot_id");
                    String vue4 = St.getString("lname");
                    String vue5 = St.getString("desc");
                    String vue6 = St.getString("roll");
                    String vue7 = St.getString("probability");
                    String vue8 = St.getString("id");

                    String textSQL = vue1 + " | " + vue2 + " | " + vue3 + " | " + vue4 + " | " + vue5 + " | " + vue6 + " | " + vue7 + " | " + vue8;

                    probability.recordAndPrintSQL(textSQL, vue8, vue7);
                }
                probability.probabilityDistribution();
                break;
            }
            case "Божественный":
                System.out.println("Вам могут выпасть следующие предметы в этом лутбоксе: \n");
                ResultSet St = statement.executeQuery(

                        "SELECT item.name, category.name as category_name, lootboxitems.lootbox_id as loot_id, loot.name as lname, loot.description as desc, loot.rollnumber as roll, rarity.numbers as probability, category.id as id " +
                                " FROM category" +
                                " INNER JOIN item " +
                                " ON item.category = category.id" +
                                "  INNER JOIN lootboxitems " +
                                " ON category.id = lootboxitems.item_id" +
                                " INNER JOIN loot" +
                                " ON lootboxitems.lootbox_id = loot.id" +
                                " INNER JOIN rarity" +
                                " ON rarity.id = category.id\n" +
                                "WHERE loot.name = 'Божественный' "
                );
                while (St.next()) {
                    String vue1 = St.getString("name");
                    String vue2 = St.getString("category_name");
                    String vue3 = St.getString("loot_id");
                    String vue4 = St.getString("lname");
                    String vue5 = St.getString("desc");
                    String vue6 = St.getString("roll");
                    String vue7 = St.getString("probability");
                    String vue8 = St.getString("id");

                    String textSQL = vue1 + " | " + vue2 + " | " + vue3 + " | " + vue4 + " | " + vue5 + " | " + vue6 + " | " + vue7 + " | " + vue8;

                    probability.recordAndPrintSQL(textSQL, vue8, vue7);
                }
                probability.probabilityDistribution();
                break;
        }
    }
}

class Probability{

    //Содержит id предметов и вероятность их выпадения
    Map<Integer, Double> probabilitiesItems = new HashMap<>();
    //Содержит id предметов, и весь вывод SQL по этим предметам
    Map<Integer, String> allSQL = new HashMap<>();

    Integer idPrize = null;

    /***
     * для записи в map probabilitiesItems и allSQL
     */
    protected void recordAndPrintSQL(String textSQL, String idItemStr, String probabilityStr){

        Integer idItem = conversionInInt(idItemStr);
        Double probability = conversionInDouble(probabilityStr);


        probabilitiesItems.put(idItem, probability);


        allSQL.put(idItem, textSQL);
        System.out.println(textSQL);
    }



    protected void probabilityDistribution(){

        Integer[] idItemWithDublicate = duplicationItem(minProbability());
        idPrize = randomItemId(idItemWithDublicate);
        droppedPrize(idPrize);
    }


    private Integer[] duplicationItem(Double minProbability) {

        int sizeMas = countDublicate(minProbability);
        int i = 0;

        Integer[] idDublicate = new Integer[sizeMas];

        for (Map.Entry<Integer, Double> entry : probabilitiesItems.entrySet()) {
            Double value = entry.getValue();

            while (value > 0){
                value = value - minProbability;
                idDublicate[i] = entry.getKey();
                i++;
            }
        }
        return idDublicate;
    }


    private int countDublicate(Double minProbability) {
        int i = 0;
        for (Map.Entry<Integer, Double> entry : probabilitiesItems.entrySet()) {
            Double value = entry.getValue();
            while (value > 0){
                i++;
                value = value - minProbability;
            }
        }

        return i;
    }


    private Double minProbability() {
        double min = 1;
        for (Map.Entry<Integer, Double> entry : probabilitiesItems.entrySet()) {
            Double value = entry.getValue();
            if (value < min) {
                min = value;
            }
        }
        return min;
    }


    private Integer randomItemId(Integer[] idItemWithDublicate) {
        int size = idItemWithDublicate.length;

        Random rn = new Random();
        int randomNum = rn.nextInt(size);


        return idItemWithDublicate[randomNum];
    }


    private void droppedPrize(Integer idPrize){
        System.out.println("\nВАМ ВЫПАЛ:");
        System.out.println(allSQL.get(idPrize));
    }


    private Integer conversionInInt(String idItem) {
        return Integer.valueOf(idItem);
    }


    private Double conversionInDouble(String probability) {
        return Double.valueOf(probability);
    }

    /***
     нужен если мы за один раз будем прокручивать несколько лутбоксов)
     */
    private void clear(){
        probabilitiesItems.clear();
        allSQL.clear();
    }


}



 














