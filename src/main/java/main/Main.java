package main;

import view.View;
import util.DatabaseSeeder;


public class Main {
    public static void main(String[] args) {
//        DatabaseSeeder databaseSeeder = new DatabaseSeeder();
//
//        databaseSeeder.run();

        View view = new View();

        view.run();
    }
}