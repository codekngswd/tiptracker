package com.lequanly.tiptracker;

import java.io.Serializable;

/**
 * Created by LeQuan on 21/06/2016.
 */
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    private double subtotal;
    private double tax;
    private double tip;
    private int people;
    private double total;

    public Bill () {
    }

    public Bill (double subtotal, double tax, double tip, int people, double total) {
        super();
        this.subtotal = subtotal;
        this.tax = tax;
        this.tip = tip;
        this.people = people;
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) { this.tip = tip; }

    public int getPeople() { return people; }

    public void setPeople(int people) { this.people = people; }

    public double getTotal() { return total; }

    public void setTotal(double total) {
        this.total = total;
    }
}