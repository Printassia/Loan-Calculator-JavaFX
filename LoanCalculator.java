package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Date;

public class LoanCalculator extends Application {
    private TextField annualInterestRate = new TextField();
    private TextField numberOfYears = new TextField();
    private TextField loanAmount = new TextField();
    private TextField monthlyPayment = new TextField();
    private TextField totalPayment = new TextField();
    private Button calculateBtn = new Button("Calculate");

    @Override
    public void start(Stage primaryStage) {
        //GridPane properties
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        //Annual Interest rate
        gridPane.add(new Label("Annual Interest Rate: "),0,0);
        gridPane.add(annualInterestRate, 1,0);
        annualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
        //Number of years
        gridPane.add(new Label("Number of Years: "),0,1);
        gridPane.add(numberOfYears, 1,1);
        numberOfYears.setAlignment(Pos.BOTTOM_RIGHT);
        //Loan Amount
        gridPane.add(new Label("Loan Amount: "),0,2);
        gridPane.add(loanAmount, 1,2);
        loanAmount.setAlignment(Pos.BOTTOM_RIGHT);
        //Monthly Payment
        gridPane.add(new Label("Monthly Payment: "),0,3);
        gridPane.add(monthlyPayment, 1,3);
        monthlyPayment.setAlignment(Pos.BOTTOM_RIGHT);
        monthlyPayment.setEditable(false);
        //Total Payment
        gridPane.add(new Label("Total Payment: "),0,4);
        gridPane.add(totalPayment, 1,4);
        totalPayment.setAlignment(Pos.BOTTOM_RIGHT);
        totalPayment.setEditable(false);
        //Calculate button
        gridPane.add(calculateBtn,1,5);
        GridPane.setHalignment(calculateBtn, HPos.RIGHT);

        //Lambada Expression
        calculateBtn.setOnAction(actionEvent -> calculateLoanPayment());

        //Scene properties
        primaryStage.setScene(new Scene(gridPane, 400,250));
        primaryStage.setTitle("Loan Calculator");
        primaryStage.show();
    }

    //calculate payment
    private void calculateLoanPayment(){
        //Get the values
        double interest = Double.parseDouble(annualInterestRate.getText());
        int year = Integer.parseInt(numberOfYears.getText());
        double loan = Double.parseDouble(loanAmount.getText());

        Loan loanClass = new Loan(interest, year, loan);

        //Display monthly payment and total
        monthlyPayment.setText(String.format("$%.2f", loanClass.getMonthlyPayment()));
        totalPayment.setText(String.format("$%.2f", loanClass.getTotalPayment()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Loan {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private Date loanDate;

    /** Default constructor */
    public Loan(){
        this(2.5,1,1000);
    }

    /** Construct a loan with a specified annual interest rate, number of years
     * and loan amount */
    public Loan(double annualInterestRate, int numberOfYears, double loanAmount){
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        loanDate = new Date();
    }

    /** Getter(accessor) method that return the annual interest rate */
    public double getAnnualInterestRate(){
        return annualInterestRate;
    }

    /** Setter(mutator) method for the annual interest rate */
    public void setAnnualInterestRate(double annualInterestRate){
        this.annualInterestRate = annualInterestRate;
    }

    /** Getter(accessor) method that returns the number of years */
    public int getNumberOfYears(){
        return numberOfYears;
    }

    /** Setter(mutator) method for the annual interest rate */
    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /** Getter(accessor) method that returns the loan amount */
    public double getLoanAmount(){
        return loanAmount;
    }

    /** Setter(mutator) method for the loan amount */
    public void setLoanAmount(double loanAmount){
        this.loanAmount = loanAmount;
    }

    /** A getter method that returns the loan date */
    public Date getLoanDate(){
        return loanDate;
    }

    /** Find the monthly payment */
    public double getMonthlyPayment(){
        double monthlyInterestRate = annualInterestRate / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
                (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
        return monthlyPayment;
    }

    /** Find the total payment */
    public double getTotalPayment(){
        double totalPayment = getMonthlyPayment() * numberOfYears * 12;
        return totalPayment;
    }
}

