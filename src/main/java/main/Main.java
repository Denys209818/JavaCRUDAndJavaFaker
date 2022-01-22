package main;

import database.DbQuery;
import models.Product;
import services.DbServices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;


public class Main  {


    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String connString = "jdbc:mariadb://localhost:3306/vpu911db";
        DbQuery db = new DbQuery();

        db.InsertIntoDb(connString);
        ArrayList<Product> products = db.SelectProductsFromDb(connString);
        DbServices.ShowAllItems(products);

        db.UpdateDb(connString);
        products = db.SelectProductsFromDb(connString);
        DbServices.ShowAllItems(products);

        db.DeleteDb(connString);
         products = db.SelectProductsFromDb(connString);
        DbServices.ShowAllItems(products);

        db.SeedData(connString);

    }



}
